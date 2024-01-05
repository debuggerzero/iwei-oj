package com.zero.iweiojcodesandbox.codesandbox;


import com.zero.iweiojcodesandbox.model.ExecuteCodeRequest;
import com.zero.iweiojcodesandbox.model.ExecuteCodeResponse;

/**
 * CodeSendBox
 *
 * @author ZERO
 * @date 2023/12/30
 */
public interface CodeSendBox {

    /**
     * 执行代码
     * @param executeCodeRequest 请求参数
     * @return 响应参数
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);

}
