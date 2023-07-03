package com.zero.acskybackend.model.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 历史记录
 *
 * @author ZERO
 * @date 2023/7/3
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class History {

    /**
     * 历史编号
     */
    private Integer id;

    /**
     * 题目 ID
     */
    private Integer pid;

    /**
     * 用户 ID
     */
    private Integer uid;

    /**
     * 状态
     */
    private String status;

    /**
     * 代码
     */
    private String code;

    /**
     * 语言种类
     */
    private String type;

    /**
     * 做题时间
     */
    private Date proTime;

}
