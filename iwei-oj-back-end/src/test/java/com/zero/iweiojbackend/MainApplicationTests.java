package com.zero.iweiojbackend;

import com.zero.iweiojbackend.model.common.Page;
import com.zero.iweiojbackend.model.dto.question.ProblemRequest;
import com.zero.iweiojbackend.model.po.ProbInfo;
import com.zero.iweiojbackend.model.po.Sample;
import com.zero.iweiojbackend.model.po.TagInfo;
import com.zero.iweiojbackend.model.query.BaseQuery;
import com.zero.iweiojbackend.repo.SampleRepo;
import com.zero.iweiojbackend.repo.mapper.*;
import com.zero.iweiojbackend.service.ProbInfoService;
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
        ProbInfo probInfo = ProbInfo.builder()
                .id(6)
                .title("两数之和")
                .difficulty(1)
                .timeLimit(2)
                .spaceLimit(64)
                .description("给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回它们的数组下标。")
                .status(1)
                .tagInfos(Arrays.asList(new TagInfo(1, null), new TagInfo(2, null), new TagInfo(3, null)))
                .build();
        probInfoService.updateById(ProblemRequest.builder().uid(1001).probInfo(probInfo).build());
    }

    @Test
    public void testUserInfoMapper() {
        probInfoService.queryProbInfoVOList(new BaseQuery("两", null, null)).getCollection().forEach(System.out::println);
        // System.out.println(probInfoService.queryOneProbInfo(1));
        // System.out.println(probInfoMapper.queryTotal(0));
    }

    @Resource
    private TagInfoMapper tagInfoMapper;

    @Test
    public void testTagInfoMapper() {
        // tagInfoMapper.getAll(new BaseQuery("动", new Page(0L, 10L), null)).forEach(System.out::println);
    }

    @Resource
    private SampleMapper sampleMapper;

    @Resource(name = "sampleRepoImpl")
    private SampleRepo sampleRepo;

    @Test
    public void testSampleMapper() {
        System.out.println(sampleRepo.updateByIds(Arrays.asList(new Sample(6, "5 1", "3", 6))));
        // sampleMapper.getAllByProId(1).forEach(System.out::println);
    }

}