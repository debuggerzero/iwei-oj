package com.zero.acskybackend;

import com.zero.acskybackend.model.po.UserInfo;
import com.zero.acskybackend.config.CosConfig;
import com.zero.acskybackend.repo.SystemRoleRepo;
import com.zero.acskybackend.repo.mapper.ImageInfoMapper;
import com.zero.acskybackend.repo.mapper.SystemResourceMapper;
import com.zero.acskybackend.repo.mapper.SystemRoleMapper;
import com.zero.acskybackend.repo.mapper.UserInfoMapper;
import com.zero.acskybackend.utils.StringUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class AcskyBackEndApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    public UserInfoMapper userInfoMapper;

    @Test
    void textQueryUserInfo() {
        System.out.println(userInfoMapper.queryUserInfo("123456"));
    }

    @Test
    void testUpdateUserInfo() {
        UserInfo userInfo = userInfoMapper.queryUserInfo("123456");
        userInfo.setName("zero");
        System.out.println(userInfoMapper.updateUserInfo(userInfo));
    }

    @Test
    void testUpdateUserPassword() {
        System.out.println(userInfoMapper.updateUserPassword("123456", StringUtil.md5("hwc20021123L")));
    }

    @Resource
    public SystemResourceMapper systemResourceMapper;

    @Test
    void textQuerySystemResourceList() {
        systemResourceMapper.querySystemResourceList().forEach(System.out::println);
    }

    @Resource
    public SystemRoleMapper systemRoleMapper;

    @Resource(name = "systemRoleRepoImpl")
    public SystemRoleRepo systemRoleRepo;

    @Test
    void textQuerySystemRole() {
        System.out.println(systemRoleRepo.querySystemRole("123456"));
    }

    @Resource
    public ImageInfoMapper imageInfoMapper;

    @Resource
    public CosConfig cosConfig;

    @Test
    void textInsertImage() {
        // UserInfo userInfo = new UserInfo();
        // userInfo.setId(1001);
        // ImageInfo imageInfo = new ImageInfo(null, "text", "text", "text", userInfo, null);
        // System.out.println(imageInfoMapper.insertImage(imageInfo));
        System.out.println(imageInfoMapper.queryImage(1001, "text"));
    }

    @Test
    void textQueryImageInfo() {

    }

}