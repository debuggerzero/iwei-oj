package com.zero.iweiojcodesandbox.codesandbox.impl;

import com.zero.iweiojcodesandbox.codesandbox.JavaCodeSandboxTemplate;
import com.zero.iweiojcodesandbox.model.ExecuteCodeRequest;
import com.zero.iweiojcodesandbox.model.ExecuteCodeResponse;
import org.springframework.stereotype.Component;

/**
 * Java 原生代码沙箱
 *
 * @author ZERO
 * @date 2023/12/31
 */
@Component
public class JavaNativeCodeSandBox extends JavaCodeSandboxTemplate {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        return super.executeCode(executeCodeRequest);
    }
}
