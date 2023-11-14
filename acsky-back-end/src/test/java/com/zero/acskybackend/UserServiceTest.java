package com.zero.acskybackend;

import com.zero.acskybackend.exception.AssertionException;
import com.zero.acskybackend.model.request.InsertUserRequest;
import com.zero.acskybackend.service.UserInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Resource
    private UserInfoService userService;

    @Test
    void testInsertOneUserInfo_normal() {
        // 正向用例：单参数正常
        InsertUserRequest insertUserRequest = new InsertUserRequest();
        insertUserRequest.setAccount("testAccount");
        insertUserRequest.setName("testName");
        Integer result = userService.insertOneUserInfo(insertUserRequest);
        assertEquals(1, result);
    }

    @Test
    void testInsertOneUserInfo_combination() {
        // 组合参数组正常
        InsertUserRequest insertUserRequest1 = new InsertUserRequest();
        insertUserRequest1.setAccount("testAccount1");
        insertUserRequest1.setName("testName1");
        InsertUserRequest insertUserRequest2 = new InsertUserRequest();
        insertUserRequest2.setAccount("testAccount2");
        insertUserRequest2.setName("testName2");
        Integer result1 = userService.insertOneUserInfo(insertUserRequest1);
        Integer result2 = userService.insertOneUserInfo(insertUserRequest2);
        assertEquals(1, result1);
        assertEquals(1, result2);
    }

    @Test
    void testInsertOneUserInfo_exception() {
        // 反向用例：异常的请求
        InsertUserRequest insertUserRequest = new InsertUserRequest();
        insertUserRequest.setAccount(null);
        assertThrows(AssertionException.class, () -> userService.insertOneUserInfo(insertUserRequest));

        insertUserRequest.setName(null);
        assertThrows(AssertionException.class, () -> userService.insertOneUserInfo(insertUserRequest));

        insertUserRequest.setAccount("");
        assertThrows(AssertionException.class, () -> userService.insertOneUserInfo(insertUserRequest));

        insertUserRequest.setName("");
        assertThrows(AssertionException.class, () -> userService.insertOneUserInfo(insertUserRequest));

        insertUserRequest.setAccount("testAccount");
        insertUserRequest.setName(null);
        assertThrows(AssertionException.class, () -> userService.insertOneUserInfo(insertUserRequest));

        insertUserRequest.setAccount("testAccount");
        insertUserRequest.setName("");
        assertThrows(AssertionException.class, () -> userService.insertOneUserInfo(insertUserRequest));
    }
}
