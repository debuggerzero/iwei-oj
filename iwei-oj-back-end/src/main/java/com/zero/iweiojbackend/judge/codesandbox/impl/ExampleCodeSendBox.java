package com.zero.iweiojbackend.judge.codesandbox.impl;

import com.zero.iweiojbackend.judge.codesandbox.CodeSendBox;
import com.zero.iweiojbackend.judge.codesandbox.model.ExecuteCodeRequest;
import com.zero.iweiojbackend.judge.codesandbox.model.ExecuteCodeResponse;
import org.springframework.stereotype.Component;

/**
 * 示例沙箱，用于测试
 *
 * @author ZERO
 * @date 2023/12/30
 */
@Component
public class ExampleCodeSendBox implements CodeSendBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        return null;
    }
}
