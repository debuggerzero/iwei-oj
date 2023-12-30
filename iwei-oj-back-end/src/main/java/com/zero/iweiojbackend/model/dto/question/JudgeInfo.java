package com.zero.iweiojbackend.model.dto.question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 判题信息
 *
 * @author ZERO
 * @date 2023/12/30
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JudgeInfo {

    /**
     * 判题信息
     */
    private String message;

    /**
     * 消耗内存
     */
    private Long memory;

    /**
     * 消耗时间
     */
    private Long time;

}
