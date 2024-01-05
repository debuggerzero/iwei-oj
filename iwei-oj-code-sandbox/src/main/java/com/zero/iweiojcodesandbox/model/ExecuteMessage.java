package com.zero.iweiojcodesandbox.model;

import lombok.Data;

/**
 * 进程执行信息
 *
 * @author ZERO
 * @date 2024/1/4
 */
@Data
public class ExecuteMessage {
    
    private Integer exitValue;

    private String message;

    private String errorMessage;

    private Long time;

    private Long memory;

}
