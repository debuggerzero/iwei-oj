package com.zero.iweiojbackend.service.impl;

import com.zero.iweiojbackend.exception.AssertionException;
import com.zero.iweiojbackend.model.common.ErrorCode;
import com.zero.iweiojbackend.model.common.ProblemSubmitLanguageEnum;
import com.zero.iweiojbackend.model.common.ProblemSubmitStatusEnum;
import com.zero.iweiojbackend.model.converter.ToUserInfoConverter;
import com.zero.iweiojbackend.model.dto.questionsubmit.ProblemSubmitAddRequest;
import com.zero.iweiojbackend.model.dto.questionsubmit.ProblemSubmitQueryRequest;
import com.zero.iweiojbackend.model.po.ProbInfo;
import com.zero.iweiojbackend.model.po.ProblemSubmit;
import com.zero.iweiojbackend.model.po.UserInfo;
import com.zero.iweiojbackend.model.vo.GeneralCollectionResult;
import com.zero.iweiojbackend.model.vo.ProbInfoVO;
import com.zero.iweiojbackend.model.vo.ProblemSubmitVO;
import com.zero.iweiojbackend.model.vo.UserInfoVO;
import com.zero.iweiojbackend.repo.ProblemSubmitRepo;
import com.zero.iweiojbackend.service.ProbInfoService;
import com.zero.iweiojbackend.service.ProblemSubmitService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.zero.iweiojbackend.model.common.UserConstant.USER_ROLE_ADMIN;

/**
 * ProblemSubmitServiceImpl
 *
 * @author ZERO
 * @date 2023/12/30
 */
@Service("problemSubmitServiceImpl")
public class ProblemSubmitServiceImpl implements ProblemSubmitService {

    @Resource(name = "probInfoServiceImpl")
    private ProbInfoService probInfoService;

    @Resource(name = "problemSubmitRepoImpl")
    private ProblemSubmitRepo problemSubmitRepo;

    @Override
    public Long doQuestionSubmit(ProblemSubmitAddRequest questionSubmitAddRequest, UserInfoVO loginUser) {
        if (Objects.isNull(questionSubmitAddRequest)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        // 判断语言类型是否正确
        String language = questionSubmitAddRequest.getLanguage();
        ProblemSubmitLanguageEnum languageEnum = ProblemSubmitLanguageEnum.getEnumByValue(language);
        if (languageEnum == null) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR, "编程语言错误");
        }
        ProbInfoVO probInfoVO = probInfoService.queryOneProbInfo(questionSubmitAddRequest.getPid());
        if (Objects.isNull(probInfoVO)) {
            throw new AssertionException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 每个用户串行提交题目
        ProblemSubmit problemSubmit = getProblemSubmit(questionSubmitAddRequest, loginUser, language);
        Integer save = problemSubmitRepo.save(problemSubmit);
        if (save == 0){
            throw new AssertionException(ErrorCode.SYSTEM_ERROR);
        }
        // TODO 判题实现
        return problemSubmit.getId();
    }

    @Override
    public Integer updateById(ProblemSubmit problemSubmit) {
        if (Objects.isNull(problemSubmit) || Objects.isNull(problemSubmit.getId())) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        return problemSubmitRepo.updateById(problemSubmit);
    }

    @Override
    public ProblemSubmit getById(Integer id) {
        if (Objects.isNull(id)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        ProblemSubmit problemSubmit = problemSubmitRepo.getById(id);
        if (Objects.isNull(problemSubmit)) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        }
        return problemSubmit;
    }

    @Override
    public GeneralCollectionResult<ProblemSubmitVO> getProblemSubmitVO(ProblemSubmitQueryRequest problemSubmitQueryRequest, UserInfoVO loginUser) {
        if (Objects.isNull(problemSubmitQueryRequest)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        Long pageNumber = problemSubmitQueryRequest.getPageNumber();
        Long pageSize = problemSubmitQueryRequest.getPageSize();
        if (pageNumber <= 0 && pageSize < 0 && (pageNumber - 1) * pageSize < 0) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        problemSubmitQueryRequest.setPageNumber((pageNumber - 1) * pageSize);
        List<ProblemSubmit> submits = problemSubmitRepo.getAll(problemSubmitQueryRequest);
        Integer uid = loginUser.getId();
        Collection<ProblemSubmitVO> problemSubmitVos = submits
                .stream()
                .peek(problemSubmit -> {
                    UserInfo user = problemSubmit.getCreatePerson();
                    if (!uid.equals(user.getId()) && !USER_ROLE_ADMIN.equals(loginUser.getRole().getName())) {
                        problemSubmit.setCode(null);
                    }
                })
                .map(ProblemSubmitVO::objToVo)
                .collect(Collectors.toList());
        return new GeneralCollectionResult<>(problemSubmitVos, problemSubmitRepo.getTotal(problemSubmitQueryRequest));
    }


    @NotNull
    private static ProblemSubmit getProblemSubmit(ProblemSubmitAddRequest questionSubmitAddRequest, UserInfoVO loginUser, String language) {
        ProblemSubmit problemSubmit = new ProblemSubmit();
        problemSubmit.setCreatePerson(ToUserInfoConverter.CONVERTER.toUserInfo(loginUser));
        ProbInfo probInfo = new ProbInfo();
        probInfo.setId(questionSubmitAddRequest.getPid());
        problemSubmit.setProbInfo(probInfo);
        problemSubmit.setCode(questionSubmitAddRequest.getCode());
        problemSubmit.setLanguage(language);
        // 设置初始状态
        problemSubmit.setStatus(ProblemSubmitStatusEnum.WAITING.getValue());
        problemSubmit.setJudgeInfo("{}");
        return problemSubmit;
    }
}
