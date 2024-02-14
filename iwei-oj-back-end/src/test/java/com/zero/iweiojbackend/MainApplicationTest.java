package com.zero.iweiojbackend;

import cn.hutool.http.HttpUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test
 *
 * @author ZERO
 * @date 2024/2/13
 */
@SpringBootTest
public class MainApplicationTest {

    @Test
    void contextLoads() {
        String url = "http://localhost:8082/sandbox/languages";
        String responseStr = HttpUtil.get(url);
        System.out.println(responseStr);
    }

    public static void main(String[] args) {
        String url = "http://172.24.250.66:8082/sandbox/languages";
        String responseStr = HttpUtil.get(url);
        System.out.println(responseStr);
    }

}
