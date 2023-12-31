package com.zero.iweiojbackend.judge.codesandbox;

import com.zero.iweiojbackend.judge.codesandbox.CodeSendBox;
import com.zero.iweiojbackend.judge.codesandbox.impl.ExampleCodeSendBox;
import com.zero.iweiojbackend.judge.codesandbox.impl.RemoteCodeSendBox;
import com.zero.iweiojbackend.judge.codesandbox.impl.ThirdPartyCodeSandBox;

/**
 * 代码沙箱工厂类
 *
 * @author ZERO
 * @date 2023/12/30
 */
public class CodeSandBoxFactory {

    public static CodeSendBox newInstance(String type) {
        switch (type) {
            case "remote":
                return new RemoteCodeSendBox();
            case "thirdParty":
                return new ThirdPartyCodeSandBox();
            default:
                return new ExampleCodeSendBox();
        }
    }

}
