package com.zero.acskybackend;

import com.zero.acskybackend.config.CosConfig;
import com.zero.acskybackend.model.command.ProblemCommand;
import com.zero.acskybackend.model.common.Page;
import com.zero.acskybackend.repo.SystemRoleRepo;
import com.zero.acskybackend.repo.UserInfoRepo;
import com.zero.acskybackend.repo.mapper.*;
import com.zero.acskybackend.service.ProbInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@SpringBootTest
class AcskyBackEndApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    public UserInfoMapper userInfoMapper;

    // @Resource
    // public UserInfoService userInfoService;

    @Test
    void testQueryUserInfo() {
        // System.out.println(userInfoMapper.queryUserInfo("123456"));
        userInfoMapper.queryUserInfoList(new Page(0L, 6L)).forEach(System.out::println);
    }

    @Test
    void testUpdateUserInfo() {
        // UserInfo userInfo = userInfoMapper.queryUserInfo("123456");
        // userInfo.setName("zero");
        // System.out.println(userInfoMapper.updateUserInfo(userInfo));
    }

    @Test
    void testUpdateUserPassword() {
        // System.out.println(userInfoMapper.updateUserPassword("123456", StringUtil.md5("hwc20021123L")));
    }

    @Resource
    public SystemResourceMapper systemResourceMapper;

    @Test
    void testQuerySystemResourceList() {
        // systemResourceMapper.querySystemResourceList().forEach(System.out::println);
    }

    @Resource
    public SystemRoleMapper systemRoleMapper;

    @Resource(name = "systemRoleRepoImpl")
    public SystemRoleRepo systemRoleRepo;

    @Test
    void testQuerySystemRole() {
        // systemRoleRepo.queryAllSystemRole().forEach(System.out::println);
    }

    @Resource
    public ImageInfoMapper imageInfoMapper;

    @Resource
    public CosConfig cosConfig;

    @Test
    void testInsertImage() {
        // UserInfo userInfo = new UserInfo();
        // userInfo.setId(1001);
        // ImageInfo imageInfo = new ImageInfo(null, "text", "text", "text", userInfo, null);
        // System.out.println(imageInfoMapper.insertImage(imageInfo));
        // System.out.println(imageInfoMapper.queryImage(1001, "text"));
    }

    @Test
    void testQueryImageInfo() {

    }

    @Resource(name = "userInfoRepoImpl")
    private UserInfoRepo userInfoRepo;

    @Test
    void testInsertUserInfo() {
        // UserInfo u1 = new UserInfo();
        // u1.setName("testUser1");
        // u1.setAccount("3212011351");
        // u1.setPassword(StringUtil.md5(StringUtil.md5("123456")));
        // UserInfo u2 = new UserInfo();
        // u2.setName("testUser2");
        // u2.setAccount("3212011352");
        // u2.setPassword(StringUtil.md5(StringUtil.md5("123456")));
        // UserInfo u3 = new UserInfo();
        // u3.setName("testUser3");
        // u3.setAccount("3212011353");
        // u3.setPassword(StringUtil.md5(StringUtil.md5("123456")));
        // UserInfo u4 = new UserInfo();
        // u4.setName("testUser4");
        // u4.setAccount("3212011354");
        // u4.setPassword(StringUtil.md5(StringUtil.md5("123456")));
        // List<UserInfo> list = new ArrayList<>();
        // list.add(u1);
        // list.add(u2);
        // list.add(u3);
        // list.add(u4);
        // System.out.println(userInfoRepo.insertUserInfoList(list));
    }

    @Test
    void testPoiExcel() throws IOException {
        // FileInputStream file = new FileInputStream("C:\\Users\\14132\\Desktop\\test.xlsx");
        // List<UserInfo> list = new ArrayList<>();
        // XSSFWorkbook sheets = new XSSFWorkbook(file);
        // XSSFSheet sheet = sheets.getSheetAt(0);
        // for (Row row : sheet) {
        //     if (row.getRowNum() == 0) {
        //         continue;
        //     }
        //     UserInfo userInfo = new UserInfo();
        //     userInfo.setAccount((String) PoiUtil.getCellValue(row.getCell(0)));
        //     userInfo.setName((String) PoiUtil.getCellValue(row.getCell(1)));
        //     userInfo.setPassword(StringUtil.md5(StringUtil.md5("123456")));
        //     list.add(userInfo);
        // }
        // sheets.close();
        // file.close();
        // list.forEach(System.out::println);
    }

    @Resource
    private UserRoleMapper userRoleMapper;

    @Test
    void testUserRoleMapper() {
        // UserInfo u3 = new UserInfo();
        // u3.setAccount("3212011352");
        // UserInfo u4 = new UserInfo();
        // u4.setAccount("3212011353");
        //
        // List<UserInfo> list = new ArrayList<>();
        // list.add(u3);
        // list.add(u4);
        //
        // System.out.println(userRoleMapper.insertUserRoleList(list));
    }

    @Resource
    private ProbInfoMapper probInfoMapper;

    @Test
    void testProbInfoMapper() {
        // System.out.println(probInfoMapper.queryTotal());
        // System.out.println(probInfoService.queryOneQuestionVO(1));
    }

    @Resource
    private ProbInfoService probInfoService;

    @Test
    void testProbInfoService() {
        // System.out.println(probInfoService.queryOneProbInfo(1));
        // ProblemCommand problemCommand = new ProblemCommand();
        // problemCommand.setPid(1);
        // problemCommand.setUid(1001);
        // problemCommand.setType("c++");
        // // problemCommand.setCode("");
        // problemCommand.setCode("#include <iostream>\n" +
        //         "using namespace std;\n" +
        //         "int main(){\n" +
        //         "int a, b;\n" +
        //         "cin >> a >> b;\n" +
        //         "cout << a + b << endl;\n" +
        //         "}");
        // problemCommand.setCode(new String(("import java.util.*;\n" +
        //         "public class Main{\n" +
        //         "public static void main(String[] args){\n" +
        //         "Scanner input = new Scanner(System.in);\n" +
        //         "int a = input.nextInt();\n" +
        //         "int b = input.nextInt();\n" +
        //         "System.out.println(a + b);" +
        //         "}}").getBytes(), StandardCharsets.UTF_8));
        // problemCommand.setTase("1 2");
        // problemCommand.setTimeLimit(1);
        // System.out.println(probInfoService.commitAndRun(problemCommand));
    }

    @Resource
    private SampleMapper sampleMapper;

    @Test
    void testSampleMapper() {
        // sampleMapper.querySampleByProbId(1).forEach(System.out::println);
    }

    @Resource
    private HistoryMapper historyMapper;

    @Test
    void testHistoryMapper() {
        // historyMapper.queryHistoryList(1001).forEach(System.out::println);
    }

}