package com.zero.iweiojbackend;

import com.zero.iweiojbackend.model.common.Page;
import com.zero.iweiojbackend.model.dto.question.ProblemRequest;
import com.zero.iweiojbackend.model.po.*;
import com.zero.iweiojbackend.model.query.BaseQuery;
import com.zero.iweiojbackend.model.query.UserInfoQuery;
import com.zero.iweiojbackend.repo.SampleRepo;
import com.zero.iweiojbackend.repo.mapper.*;
import com.zero.iweiojbackend.service.ProbInfoService;
import com.zero.iweiojbackend.service.UserInfoService;
import com.zero.iweiojbackend.utils.StringUtil;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@SpringBootTest
class MainApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    UserInfoMapper userInfoMapper;

    @Resource
    ProbInfoMapper probInfoMapper;

    @Resource(name = "probInfoServiceImpl")
    ProbInfoService probInfoService;

    @Test
    public void testUserInfoMapper() {
        // System.out.println(userInfoMapper.getOne(UserInfoQuery.builder().id(1001).build()));
        // System.out.println(userInfoMapper.getOne(UserInfoQuery.builder().id(1002).build()));
        // System.out.println(userInfoMapper.getOne(UserInfoQuery.builder().account("admin").build()));
        // System.out.println(userInfoMapper.getOne(UserInfoQuery.builder().account("123456").build()));
        // System.out.println(userInfoMapper.getOne(UserInfoQuery.builder().account("1234561").build()));

        // System.out.println(userInfoMapper.getAll(new BaseQuery("", null, null)));
        // System.out.println(userInfoMapper.getAll(new BaseQuery("debuggerzero", null, null)));
        // System.out.println(userInfoMapper.getAll(new BaseQuery("debuggerzero", null, 0)));
        // System.out.println(userInfoMapper.getAll(new BaseQuery("", null, 1)));

        // UserInfo userInfo = new UserInfo();
        // userInfo.setName("user2");
        // userInfo.setAccount("1234562");
        // userInfo.setPassword(StringUtil.md5(StringUtil.md5("123456")));
        // userInfo.setCreatePerson("1001");
        // userInfo.setUpdatePerson("1001");
        // userInfo.setRole(SystemRole.builder().id(1002).build());
        // userInfoMapper.save(userInfo);

        // UserInfo userInfo1 = new UserInfo();
        // userInfo1.setName("user3");
        // userInfo1.setAccount("1234563");
        // userInfo1.setPassword(StringUtil.md5(StringUtil.md5("123456")));
        // userInfo1.setCreatePerson("1001");
        // userInfo1.setUpdatePerson("1001");
        // userInfo1.setRole(SystemRole.builder().id(1002).build());
        //
        // UserInfo userInfo2 = new UserInfo();
        // userInfo2.setName("user4");
        // userInfo2.setAccount("1234564");
        // userInfo2.setPassword(StringUtil.md5(StringUtil.md5("123456")));
        // userInfo2.setCreatePerson("1001");
        // userInfo2.setUpdatePerson("1001");
        // userInfo2.setRole(SystemRole.builder().id(1002).build());
        //
        // userInfoMapper.saveAll(Arrays.asList(userInfo1, userInfo2));

        // UserInfo build = UserInfo.builder().id(1003).status(1).build();
        // userInfoMapper.updateById(build);

    }

    @Test
    public void testProbInfoService() {
        // probInfoService.deleteById(1);
        // ProbInfo probInfo = ProbInfo.builder()
        //         .id(6)
        //         .title("两数之差")
        //         .difficulty(1)
        //         .timeLimit(2)
        //         .spaceLimit(64)
        //         .description("给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出差为目标值的那两个整数，并返回它们的数组下标。")
        //         .tagInfos(Arrays.asList(new TagInfo(4, null)))
        //         .build();
        // List<Sample> samples = Arrays.asList(
        //         new Sample(6, "4 1", "3", null),
        //         new Sample(9, "4 3", "1", null),
        //         new Sample(null, "4 5", "-1", null)
        // );
        // probInfoService.updateById(ProblemRequest.builder().uid(1002).probInfo(probInfo).samples(samples).build());
    }

    @Test
    public void testProbInfoMapper() {
        // ProbInfo probInfo = ProbInfo.builder()
        //         .id(6)
        //         .title("两数之和")
        //         .difficulty(1)
        //         .timeLimit(2)
        //         .spaceLimit(64)
        //         .description("给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回它们的数组下标。")
        //         .status(1)
        //         .tagInfos(Arrays.asList(new TagInfo(1, null), new TagInfo(2, null), new TagInfo(3, null)))
        //         .build();
        // probInfoService.updateById(ProblemRequest.builder().uid(1001).probInfo(probInfo).build());
    }

    @Resource
    private TagInfoMapper tagInfoMapper;

    @Test
    public void testTagInfoMapper() {
        tagInfoMapper.getAllByQuery(new BaseQuery("动", new Page(0L, 10L), null)).forEach(System.out::println);
        tagInfoMapper.getAll().forEach(System.out::println);
    }

    @Resource
    private SampleMapper sampleMapper;

    @Resource(name = "sampleRepoImpl")
    private SampleRepo sampleRepo;

    @Test
    public void testSampleMapper() {
        // System.out.println(sampleRepo.updateByIds(Arrays.asList(new Sample(6, "5 1", "3", 6))));
        // sampleMapper.getAllByProId(1).forEach(System.out::println);
    }

    @Resource(name = "userInfoServiceImpl")
    UserInfoService userInfoService;

    @Test
    public void testUserInfoService() {
        // System.out.println(userInfoService.queryUserInfoById(1001));
        // System.out.println(userInfoService.queryUserInfoVOList(new BaseQuery(null, null, null)));
        // System.out.println(userInfoService.queryUserRoleList());
    }

}