package com.zero.iweiojbackend.service.impl;

import com.zero.iweiojbackend.exception.AssertionException;
import com.zero.iweiojbackend.model.po.TagInfo;
import com.zero.iweiojbackend.model.query.BaseQuery;
import com.zero.iweiojbackend.model.converter.ToProbInfoVoConverter;
import com.zero.iweiojbackend.model.dto.question.ProblemRequest;
import com.zero.iweiojbackend.model.common.ErrorCode;
import com.zero.iweiojbackend.model.common.Page;
import com.zero.iweiojbackend.model.po.ProbInfo;
import com.zero.iweiojbackend.model.vo.ProbInfoVO;
import com.zero.iweiojbackend.repo.ProbInfoRepo;
import com.zero.iweiojbackend.service.ProbInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ProbInfoServiceImpl
 *
 * @author ZERO
 * @date 2023/7/3
 */
@Service("probInfoServiceImpl")
@RequiredArgsConstructor
public class ProbInfoServiceImpl implements ProbInfoService {

    @Resource(name = "probInfoRepoImpl")
    private ProbInfoRepo probInfoRepo;

    @Override
    public Long queryTotal() {
        return probInfoRepo.queryTotal();
    }

    @Override
    public List<ProbInfoVO> queryProbInfoVOList(BaseQuery query) {
        if (Objects.isNull(query)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        query.setIsDelete(0);
        List<ProbInfo> probInfos = queryProbInfoList(query);
        return probInfos.stream()
                .map(ToProbInfoVoConverter.CONVERTER::toProbInfoVO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProbInfo> queryProbInfoList(BaseQuery query) {
        Page page = query.getPage();
        page.setPageNumber((page.getPageNumber() - 1) * page.getPageSize());
        if (page.getPageNumber() < 0 || page.getPageSize() <= 0) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        Collection<ProbInfo> probInfos = probInfoRepo.getAll(query);
        if (probInfos == null) {
            return Collections.emptyList();
        }
        return (List<ProbInfo>) probInfos;
    }

    @Override
    public ProbInfoVO queryOneProbInfo(Integer probId) {
        return ToProbInfoVoConverter.CONVERTER.toProbInfoVO(probInfoRepo.getById(probId));
    }

    @Override
    public Integer save(ProblemRequest problemRequest) {
        if (Objects.isNull(problemRequest) || Objects.isNull(problemRequest.getUid())) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        ProbInfo probInfo = problemRequest.getProbInfo();
        if (Objects.isNull(probInfo)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        String uid = problemRequest.getUid().toString();
        // 设置创建用户与修改用户 id
        probInfo.setCreatePerson(uid);
        probInfo.setUpdatePerson(uid);
        // 上传题目
        Integer save = probInfoRepo.save(probInfo);
        if (save == 0) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        }
        // 上传标签
        if (Objects.nonNull(probInfo.getTagInfos()) && !probInfo.getTagInfos().isEmpty()) {
            List<Integer> tagIds = probInfo.getTagInfos().stream().map(TagInfo::getId).collect(Collectors.toList());
            probInfoRepo.insertTagInfoById(probInfo.getId(), tagIds);
        }
        // TODO 上传用例

        return save;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateById(ProblemRequest problemRequest) {
        // 验证参数是否正确
        if (Objects.isNull(problemRequest) || Objects.isNull(problemRequest.getUid())) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        ProbInfo probInfo = problemRequest.getProbInfo();
        if (Objects.isNull(probInfo) || Objects.isNull(probInfo.getId())) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        // 如果上传的参数为空默认给个空集合
        if (Objects.isNull(probInfo.getTagInfos())) {
            probInfo.setTagInfos(new ArrayList<>());
        }
        probInfo.setUpdatePerson(problemRequest.getUid().toString());
        // 修改 tagInfo 与 probInfo 关联
        ProbInfoServiceImpl probInfoService = (ProbInfoServiceImpl) AopContext.currentProxy();
        probInfoService.updateTagIds(probInfo);
        // TODO 修改用例
        // 修改题目信息
        Integer i = probInfoRepo.updateById(probInfo);
        if (i == 0) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        }
        return i;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteById(Integer id) {
        if (Objects.isNull(id)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        // 查询预删除的题目信息
        ProbInfo probInfo = probInfoRepo.getById(id);
        // 删除题目与标签的关联信息
        Integer tagIdsSize = probInfoRepo.deleteTagInfoById(id, null);
        if (probInfo.getTagInfos().size() != tagIdsSize) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        }
        // TODO 删除用例
        // 删除题目
        Integer i = probInfoRepo.deleteById(id);
        if (i == 0) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        }
        return i;
    }

    /**
     * 修改 pro_tag 关联信息
     *
     * @param probInfo 题目信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateTagIds(ProbInfo probInfo) {
        // 获取修改前的问题信息
        ProbInfo oldProblem = probInfoRepo.getById(probInfo.getId());
        if (Objects.isNull(oldProblem)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        List<Integer> oldTagIds = oldProblem.getTagInfos().stream().map(TagInfo::getId).collect(Collectors.toList());
        List<Integer> newTagIds = probInfo.getTagInfos().stream().map(TagInfo::getId).collect(Collectors.toList());
        // 删除旧的标签关系
        if (!oldTagIds.isEmpty()) {
            List<Integer> tagIds = oldTagIds
                    .stream()
                    .filter(id -> !newTagIds.contains(id))
                    .collect(Collectors.toList());
            if (!tagIds.isEmpty()) {
                Integer length = probInfoRepo.deleteTagInfoById(probInfo.getId(), tagIds);
                if (length != tagIds.size()) {
                    throw new AssertionException(ErrorCode.OPERATION_ERROR);
                }
            }
        }
        // 添加新的标签关系
        if (!newTagIds.isEmpty()) {
            List<Integer> tagIds = newTagIds
                    .stream()
                    .filter(id -> !oldTagIds.contains(id))
                    .collect(Collectors.toList());
            if (!tagIds.isEmpty()) {
                Integer length = probInfoRepo.insertTagInfoById(probInfo.getId(), tagIds);
                if (length != tagIds.size()) {
                    throw new AssertionException(ErrorCode.OPERATION_ERROR);
                }
            }
        }
    }
}
