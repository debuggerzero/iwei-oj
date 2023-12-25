package com.zero.iweiojbackend.model.dto;

import com.zero.iweiojbackend.model.common.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RunResultDTO
 *
 * @author ZERO
 * @date 2023/7/3
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RunResultDTO {

    /**
     * 枚举状态
     */
    private StatusEnum statusEnum;

    /**
     * 返回结果
     */
    private String result;

}
