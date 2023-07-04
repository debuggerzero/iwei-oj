package com.zero.acskybackend.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 排行榜
 *
 * @author ZERO
 * @date 2023/7/4
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RankingVO {

    /**
     * 用户 ID
     */
    private Integer id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 提交数
     */
    private String submitCnt;

    /**
     * 通过数
     */
    private Long passCnt;

    /**
     * 个人简介
     */
    private String profile;

}
