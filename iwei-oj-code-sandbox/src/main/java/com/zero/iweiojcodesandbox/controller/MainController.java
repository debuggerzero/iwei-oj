package com.zero.iweiojcodesandbox.controller;

import com.zero.iweiojcodesandbox.codesandbox.impl.JavaNativeCodeSandBox;
import com.zero.iweiojcodesandbox.model.ExecuteCodeRequest;
import com.zero.iweiojcodesandbox.model.ExecuteCodeResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * MainController
 *
 * @author ZERO
 * @date 2024/1/4
 */
@RestController
public class MainController {

    @Resource
    private JavaNativeCodeSandBox javaNativeCodeSandBox;

    /**
     * 执行代码
     *
     * @param executeCodeRequest executeCodeRequest
     * @return ExecuteCodeResponse
     */
    @PostMapping("/executeCode")
    ExecuteCodeResponse executeCode(@RequestBody ExecuteCodeRequest executeCodeRequest) {
        if (executeCodeRequest == null) {
            throw new RuntimeException("请求参数为空");
        }
        return javaNativeCodeSandBox.executeCode(executeCodeRequest);
    }

}
