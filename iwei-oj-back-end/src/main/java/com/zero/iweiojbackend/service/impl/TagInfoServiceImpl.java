package com.zero.iweiojbackend.service.impl;

import com.zero.iweiojbackend.exception.AssertionException;
import com.zero.iweiojbackend.model.common.ErrorCode;
import com.zero.iweiojbackend.model.converter.ToTagInfoVoConverter;
import com.zero.iweiojbackend.model.dto.tag.TagInfoRequest;
import com.zero.iweiojbackend.model.po.TagInfo;
import com.zero.iweiojbackend.model.query.BaseQuery;
import com.zero.iweiojbackend.model.vo.GeneralCollectionResult;
import com.zero.iweiojbackend.model.vo.TagInfoVO;
import com.zero.iweiojbackend.repo.ProbInfoRepo;
import com.zero.iweiojbackend.repo.TagInfoRepo;
import com.zero.iweiojbackend.service.TagInfoService;
import com.zero.iweiojbackend.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * TagInfoServiceImpl
 *
 * @author ZERO
 * @date 2023/12/26
 */
@Service("tagInfoServiceImpl")
public class TagInfoServiceImpl implements TagInfoService {

    @Resource
    private TagInfoRepo tagInfoRepo;

    @Resource
    private ProbInfoRepo probInfoRepo;

    @Resource(name = "userInfoServiceImpl")
    private UserInfoService userInfoService;

    @Override
    public Integer insertTagInfo(TagInfoRequest tagInfoRequest, HttpServletRequest request) {
        if (TagInfoRequest.isNull(tagInfoRequest)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        String uid = userInfoService.getLoginUser(request).getId().toString();
        TagInfoVO tagInfoVO = tagInfoRequest.getTagInfoVO();
        TagInfo tagInfo = TagInfo.builder().name(tagInfoVO.getName()).createPerson(uid).updatePerson(uid).build();
        Integer i = tagInfoRepo.save(tagInfo);
        if (i == 0) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        }
        return i;
    }

    @Override
    public Integer deleteTagInfoById(Integer id) {
        if (Objects.isNull(id)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        Integer success = probInfoRepo.deleteTagInfoById(null, Collections.singletonList(id));
        if (!success.equals(Math.toIntExact(tagInfoRepo.tagRelevancyCount(id)))) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        }
        Integer i = tagInfoRepo.deleteById(id);
        if (i == 0) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        }
        return i;
    }

    @Override
    public Integer updateTagInfoById(TagInfoRequest tagInfoRequest, HttpServletRequest request) {
        if (TagInfoRequest.isNull(tagInfoRequest)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        String uid = userInfoService.getLoginUser(request).getId().toString();
        TagInfoVO tagInfoVO = tagInfoRequest.getTagInfoVO();
        TagInfo tagInfo = TagInfo
                .builder()
                .id(tagInfoRequest.getTagInfoVO().getId())
                .name(tagInfoVO.getName())
                .updatePerson(uid)
                .build();
        Integer i = tagInfoRepo.updateById(tagInfo);
        if (i == 0) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        }
        return i;
    }

    @Override
    public TagInfo getTagInfoById(Integer id) {
        if (Objects.isNull(id)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        return tagInfoRepo.getById(id);
    }

    @Override
    public GeneralCollectionResult<TagInfo> getTagInfoList(BaseQuery query) {
        if (BaseQuery.isNull(query)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        Collection<TagInfo> tagInfos = tagInfoRepo.getAll(query);
        if (Objects.isNull(tagInfos)) {
            return new GeneralCollectionResult<>(Collections.emptyList(), 0L);
        }
        Long count = tagInfoRepo.tagCount();
        return new GeneralCollectionResult<>(tagInfos, count);
    }

    @Override
    public GeneralCollectionResult<TagInfoVO> getTagInfoVOList() {
        Collection<TagInfoVO> tagInfoVos =
                tagInfoRepo.getAll()
                        .stream()
                        .map(ToTagInfoVoConverter.CONVERTER::toTagInfoVO)
                        .collect(Collectors.toList());
        Long count = tagInfoRepo.tagCount();
        return new GeneralCollectionResult<>(tagInfoVos, count);
    }
}
