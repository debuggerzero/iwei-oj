package com.zero.iweiojbackend.judge.codesandbox;

import com.zero.iweiojbackend.judge.codesandbox.model.ExecuteCodeRequest;
import com.zero.iweiojbackend.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * 代码沙箱代理类
 *
 * @author ZERO
 * @date 2023/12/30
 */
@Slf4j
public class CodeSendBoxProxy implements CodeSendBox {

    private final CodeSendBox codeSendBox;

    public CodeSendBoxProxy(CodeSendBox codeSendBox) {
        this.codeSendBox = codeSendBox;
    }

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        log.info("代码沙箱请求信息：" + executeCodeRequest.toString());
        ExecuteCodeResponse executeCodeResponse = codeSendBox.executeCode(executeCodeRequest);
        if (Objects.nonNull(executeCodeResponse)) {
            log.info("代码沙箱响应信息：" + executeCodeResponse);
        }
        return executeCodeResponse;
    }
}
