package com.zero.iweiojbackend.judge.codesandbox.impl;

import com.zero.iweiojbackend.judge.codesandbox.CodeSendBox;
import com.zero.iweiojbackend.judge.codesandbox.model.ExecuteCodeRequest;
import com.zero.iweiojbackend.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * 第三方沙箱
 *
 * @author ZERO
 * @date 2023/12/30
 */
public class ThirdPartyCodeSandBox implements CodeSendBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("第三方代码沙箱");
        return null;
    }
}
