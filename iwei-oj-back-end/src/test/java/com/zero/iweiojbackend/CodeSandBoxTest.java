package com.zero.iweiojbackend;

import com.zero.iweiojbackend.judge.codesandbox.CodeSendBox;
import com.zero.iweiojbackend.judge.codesandbox.impl.ExampleCodeSendBox;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CodeSandBoxTest {

    @Test
    void executeCode() {
        CodeSendBox codeSendBox = new ExampleCodeSendBox();
        codeSendBox.executeCode(null);
    }

}
