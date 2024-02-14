package com.zero.iweiojbackend.judge.codesandbox;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 代码沙箱工厂类
 *
 * @author ZERO
 * @date 2023/12/30
 */
@Component
public class CodeSandBoxFactory {

    @Resource
    private CodeSendBox remoteCodeSendBox;

    @Resource
    private CodeSendBox thirdPartyCodeSandBox;

    @Resource
    private CodeSendBox exampleCodeSendBox;

    @Value("${codesandbox.type:example}")
    private String type;

    public CodeSendBox newInstance() {
        switch (type) {
            case "remote":
                return remoteCodeSendBox;
            case "thirdParty":
                return thirdPartyCodeSandBox;
            default:
                return exampleCodeSendBox;
        }
    }

}
