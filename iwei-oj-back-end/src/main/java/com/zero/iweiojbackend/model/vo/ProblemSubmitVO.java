package com.zero.iweiojbackend.model.vo;

import cn.hutool.json.JSONUtil;
import com.zero.iweiojbackend.judge.codesandbox.model.JudgeResult;
import com.zero.iweiojbackend.model.common.ProblemSubmitStatusEnum;
import com.zero.iweiojbackend.model.po.ProblemSubmit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.Objects;

/**
 * ProblemSubmitVO
 *
 * @author ZERO
 * @date 2023/12/30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProblemSubmitVO {

    /**
     * 记录 id
     */
    private Long id;

    /**
     * 语言
     */
    private String language;

    /**
     * 用户代码
     */
    private String code;

    /**
     * 判题信息（json 对象）
     */
    private JudgeResult judgeResult;

    /**
     * 判题状态（0 - 待判题、1 - 判题中、2 - 成功、3 - 失败）
     */
    private String status;

    /**
     * 题目信息
     */
    private ProbInfoVO probInfo;

    /**
     * 创建人
     */
    private String createPerson;

    /**
     * 创建时间
     */
    private Date createDate;

    public static ProblemSubmitVO objToVo(ProblemSubmit problemSubmit) {
        if (Objects.isNull(problemSubmit)) {
            return null;
        }
        ProblemSubmitVO problemSubmitVO = new ProblemSubmitVO();
        BeanUtils.copyProperties(problemSubmit, problemSubmitVO);
        problemSubmitVO.setStatus(Objects.requireNonNull(ProblemSubmitStatusEnum.getEnumByValue(problemSubmit.getStatus())).getText());
        problemSubmitVO.setCreatePerson(problemSubmit.getCreatePerson().getName());
        String judgeInfoStr = problemSubmit.getJudgeInfo();
        problemSubmitVO.setJudgeResult(JSONUtil.toBean(judgeInfoStr, JudgeResult.class));
        return problemSubmitVO;
    }

}
