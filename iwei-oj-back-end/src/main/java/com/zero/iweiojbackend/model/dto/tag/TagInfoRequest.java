package com.zero.iweiojbackend.model.dto.tag;

import com.zero.iweiojbackend.exception.AssertionException;
import com.zero.iweiojbackend.model.common.ErrorCode;
import com.zero.iweiojbackend.model.po.TagInfo;
import com.zero.iweiojbackend.model.vo.TagInfoVO;
import com.zero.iweiojbackend.utils.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * TagInfoRequest
 *
 * @author ZERO
 * @date 2023/12/25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagInfoRequest {

    /**
     * 标签信息
     */
    private TagInfoVO tagInfoVO;

    public static boolean isNull(TagInfoRequest tagInfoRequest) {
        return Objects.isNull(tagInfoRequest) ||
                Objects.isNull(tagInfoRequest.getTagInfoVO()) ||
                StringUtil.isEmpty(tagInfoRequest.getTagInfoVO().getName());
    }

}
