package com.zero.iweiojbackend.service.impl;

import com.zero.iweiojbackend.exception.AssertionException;
import com.zero.iweiojbackend.model.po.Sample;
import com.zero.iweiojbackend.model.po.TagInfo;
import com.zero.iweiojbackend.model.query.BaseQuery;
import com.zero.iweiojbackend.model.converter.ToProbInfoVoConverter;
import com.zero.iweiojbackend.model.dto.question.ProblemRequest;
import com.zero.iweiojbackend.model.common.ErrorCode;
import com.zero.iweiojbackend.model.po.ProbInfo;
import com.zero.iweiojbackend.model.vo.GeneralCollectionResult;
import com.zero.iweiojbackend.model.vo.ProbInfoVO;
import com.zero.iweiojbackend.repo.ProbInfoRepo;
import com.zero.iweiojbackend.repo.SampleRepo;
import com.zero.iweiojbackend.service.ProbInfoService;
import com.zero.iweiojbackend.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    @Resource(name = "sampleRepoImpl")
    private SampleRepo sampleRepo;

    @Resource(name = "userInfoServiceImpl")
    private UserInfoService userInfoService;

    @Override
    public GeneralCollectionResult<ProbInfoVO> queryProbInfoVOList(BaseQuery query) {
        if (BaseQuery.isNull(query)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        query.setStatus(0);
        Collection<ProbInfoVO> probInfoVos = probInfoRepo.getAll(query)
                .stream()
                .map(ToProbInfoVoConverter.CONVERTER::toProbInfoVO)
                .collect(Collectors.toList());
        Long count = probInfoRepo.queryTotal(0);
        return new GeneralCollectionResult<>(probInfoVos, count);
    }

    @Override
    public GeneralCollectionResult<ProbInfo> queryProbInfoList(BaseQuery query) {
        if (BaseQuery.isNull(query)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        Collection<ProbInfo> probInfos = probInfoRepo.getAll(query);
        if (probInfos == null) {
            return new GeneralCollectionResult<>(Collections.emptyList(), 0L);
        }
        Long count = probInfoRepo.queryTotal(query.getStatus());
        return new GeneralCollectionResult<>(probInfos, count);
    }

    @Override
    public GeneralCollectionResult<Sample> querySampleList(Integer probId) {
        if (Objects.isNull(probId)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        Collection<Sample> list = sampleRepo.getAllByProId(probId);
        return new GeneralCollectionResult<>(list, (long) list.size());
    }

    @Override
    public ProbInfoVO queryOneProbInfo(Integer probId) {
        if (Objects.isNull(probId)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        return ToProbInfoVoConverter.CONVERTER.toProbInfoVO(probInfoRepo.getById(probId));
    }

    @Override
    public Integer save(ProblemRequest problemRequest, HttpServletRequest request) {
        String uid = userInfoService.getLoginUser(request).getId().toString();
        if (ProblemRequest.isNull(problemRequest)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        ProbInfo probInfo = problemRequest.getProbInfo();
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
            Integer i = probInfoRepo.insertTagInfoById(probInfo.getId(), tagIds);
            if (i != tagIds.size()) {
                throw new AssertionException(ErrorCode.OPERATION_ERROR);
            }
        }
        // 上传样例
        if (!problemRequest.getSamples().isEmpty()) {
            List<Sample> samples = problemRequest.getSamples()
                    .stream()
                    .peek(o -> o.setProId(probInfo.getId()))
                    .collect(Collectors.toList());
            Integer i = sampleRepo.saveAll(samples);
            if (i != samples.size()) {
                throw new AssertionException(ErrorCode.OPERATION_ERROR);
            }
        }
        return save;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateById(ProblemRequest problemRequest, HttpServletRequest request) {
        String uid = userInfoService.getLoginUser(request).getId().toString();
        // 验证参数是否正确
        if (ProblemRequest.isNull(problemRequest)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        ProbInfo probInfo = problemRequest.getProbInfo();
        if (Objects.isNull(probInfo.getId())) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        // 如果上传的参数为空默认给个空集合
        if (Objects.isNull(probInfo.getTagInfos())) {
            probInfo.setTagInfos(new ArrayList<>());
        }
        probInfo.setUpdatePerson(uid);
        // 修改 tagInfo 与 probInfo 关联
        ProbInfoServiceImpl probInfoService = (ProbInfoServiceImpl) AopContext.currentProxy();
        probInfoService.updateTagIds(probInfo);
        // 修改题目样例
        probInfoService.updateSamples(problemRequest.getSamples(), probInfo.getId());
        // 修改题目信息
        Integer i = probInfoRepo.updateById(probInfo);
        if (i == 0) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        }
        return i;
    }

    @Override
    public Integer updateSubmitCnt(Integer pid) {
        if (Objects.isNull(pid)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        Integer result = probInfoRepo.updateSubmitCnt(pid);
        if (result == 0) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        }
        return result;
    }

    @Override
    public Integer updateAcceptCnt(Integer pid) {
        if (Objects.isNull(pid)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        Integer result = probInfoRepo.updateAcceptCnt(pid);
        if (result == 0) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        }
        return result;
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
        if (!Objects.isNull(probInfo.getTagInfos()) && !probInfo.getTagInfos().isEmpty()) {
            Integer tagIdsSize = probInfoRepo.deleteTagInfoById(id, null);
            if (probInfo.getTagInfos().size() != tagIdsSize) {
                throw new AssertionException(ErrorCode.OPERATION_ERROR);
            }
        }
        // 删除用例
        Collection<Number> sampleIds = sampleRepo.getAllByProId(probInfo.getId())
                .stream()
                .map(Sample::getId)
                .collect(Collectors.toList());
        if (!sampleIds.isEmpty()) {
            Integer sampleIdsSuccessCount = sampleRepo.deleteByIds(sampleIds);
            if (sampleIdsSuccessCount != sampleIds.size()) {
                throw new AssertionException(ErrorCode.OPERATION_ERROR);
            }
        }
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

    @Transactional(rollbackFor = Exception.class)
    public void updateSamples(Collection<Sample> samples, Integer proId) {
        // 获取新样例的 id
        Collection<Integer> sampleIds = samples.stream().map(Sample::getId).collect(Collectors.toList());
        // 获取旧样例的列表
        Collection<Sample> oldSamples = sampleRepo.getAllByProId(proId);
        if (Objects.isNull(oldSamples)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        // 筛选出预处理的 旧 sample 对象 id
        Collection<Sample> primaryOldSample = oldSamples
                .stream()
                .peek(o -> o.setProId(proId))
                .filter(item -> !samples.contains(item))
                .collect(Collectors.toList());
        // 筛选出预更新与预删除的数据
        Collection<Sample> primaryUpdateSample = new ArrayList<>();
        Collection<Number> primaryDeleteSampleIds = new ArrayList<>();
        for (Sample item : primaryOldSample) {
            if (sampleIds.contains(item.getId())) {
                primaryUpdateSample.add(item);
            } else {
                primaryDeleteSampleIds.add(item.getId());
            }
        }
        // 获取旧样例的 id
        Collection<Integer> oldSampleIds = oldSamples.stream().map(Sample::getId).collect(Collectors.toList());
        // 筛选出预添加的数据
        Collection<Sample> primaryAddSample = new ArrayList<>();
        for (Sample item : samples) {
            if (!oldSampleIds.contains(item.getId())) {
                item.setProId(proId);
                primaryAddSample.add(item);
            }
        }
        if (!primaryDeleteSampleIds.isEmpty()) {
            // 删除数据
            Integer successDelCount = sampleRepo.deleteByIds(primaryDeleteSampleIds);
            if (successDelCount != primaryDeleteSampleIds.size()) {
                throw new AssertionException(ErrorCode.OPERATION_ERROR);
            }
        }
        if (!primaryUpdateSample.isEmpty()) {
            // 修改数据
            Integer successUpCount = sampleRepo.updateByIds(primaryUpdateSample);
            if (successUpCount != primaryUpdateSample.size()) {
                throw new AssertionException(ErrorCode.OPERATION_ERROR);
            }
        }
        if (!primaryAddSample.isEmpty()) {
            // 添加数据
            Integer successAddCount = sampleRepo.saveAll(primaryAddSample);
            if (successAddCount != primaryAddSample.size()) {
                throw new AssertionException(ErrorCode.OPERATION_ERROR);
            }
        }
    }
}
