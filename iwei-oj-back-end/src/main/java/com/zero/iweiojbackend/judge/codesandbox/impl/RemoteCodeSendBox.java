package com.zero.iweiojbackend.judge.codesandbox.impl;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.zero.iweiojbackend.exception.AssertionException;
import com.zero.iweiojbackend.judge.codesandbox.CodeSendBox;
import com.zero.iweiojbackend.judge.codesandbox.model.ExecuteCodeRequest;
import com.zero.iweiojbackend.judge.codesandbox.model.ExecuteCodeResponse;
import com.zero.iweiojbackend.model.common.ErrorCode;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 远程沙箱
 *
 * @author ZERO
 * @date 2023/12/30
 */
@Component
public class RemoteCodeSendBox implements CodeSendBox {

    @Value("${judge.url}")
    private String url;

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        String url = this.url;
        String json = JSONUtil.toJsonStr(executeCodeRequest);
        HttpResponse execute = HttpUtil.createPost(url)
                .body(json)
                .execute();
        boolean status = execute.getStatus() == 200;
        String responseStr = execute.body();
        execute.close();
        if (!status || ObjectUtils.isEmpty(responseStr)) {
            throw new AssertionException(ErrorCode.API_REQUEST_ERROR, "executeCode remoteSandbox error, message = " + responseStr);
        }
        return JSONUtil.toBean(responseStr, ExecuteCodeResponse.class);
    }
}
