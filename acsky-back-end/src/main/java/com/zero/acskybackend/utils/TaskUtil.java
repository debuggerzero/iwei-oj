package com.zero.acskybackend.utils;

import com.zero.acskybackend.model.command.ProblemCommand;
import com.zero.acskybackend.model.common.LanguageEnum;
import com.zero.acskybackend.model.common.StatusEnum;
import com.zero.acskybackend.model.dto.RunResultDTO;
import com.zero.acskybackend.model.vo.Answer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @author wjh
 * @date 2023/6/20 10:10
 */
public class TaskUtil {


    /**
     * 所有临时文件都放在 tmp 中
     */
    private static final String WORK_DIR = "./tmp/";

    /**
     * 要编译的代码对应的文件名   要和类名一致
     */
    private static final String CODE = WORK_DIR + "Main.java";

    private static final Runtime RUNTIME;

    static {
        RUNTIME = Runtime.getRuntime();
    }

    public static Answer compileAndRun(ProblemCommand question) throws IOException, InterruptedException {

        String type = question.getType() != null ? question.getType() : "c++";
        if (question.getTimeLimit() == null) {
            question.setTimeLimit(1);
        }
        if (question.getSpaceLimit() == null) {
            question.setSpaceLimit(64);
        }
        if (question.getTase() == null) {
            question.setTase("");
        }
        if (question.getCode() == null) {
            question.setCode("");
        }

        File wordDir = new File(WORK_DIR);
        if (!wordDir.exists()) {
            boolean success = wordDir.mkdirs();
            if (!success) {
                throw new RuntimeException("临时文件创建失败...");
            }
        }

        if (LanguageEnum.CPP.getName().equals(type)) {
            writeFile(WORK_DIR + "main.cpp", question.getCode());
        } else if (LanguageEnum.JAVA.getName().equals(type)) {
            writeFile(CODE, question.getCode());
        }

        // 默认执行Java
        String cmd = "";
        if (LanguageEnum.CPP.getName().equals(type)) {
            cmd = "g++ -std=c++11 ./tmp/main.cpp -o ./tmp/main.exe";
        } else if (LanguageEnum.JAVA.getName().equals(type)) {
            cmd = "javac .\\tmp\\Main.java";
        }

        StatusEnum compile = compile(cmd);

        if (compile == StatusEnum.COMPILE_ERROR) {
            return new Answer(compile.getCode(), compile.getMessage(), null);
        }

        if (LanguageEnum.CPP.getName().equals(type)) {
            cmd = "./tmp/main.exe";
        } else if (LanguageEnum.JAVA.getName().equals(type)) {
            question.setTimeLimit(question.getTimeLimit() << 1);
            cmd = "java -cp ./tmp Main";
        }

        RunResultDTO run = run(cmd, question.getTase(), question.getTimeLimit());

        return new Answer(run.getStatusEnum().getCode(), run.getStatusEnum().getMessage(), run.getResult());
    }

    private static StatusEnum compile(String cmd) throws IOException, InterruptedException {
        Process process = RUNTIME.exec(cmd);
        InputStream stderrFrom = process.getErrorStream();
        ByteArrayOutputStream stderrTo = new ByteArrayOutputStream();
        int ch;
        while ((ch = stderrFrom.read()) != -1) {
            stderrTo.write(ch);
        }
        byte[] byteArray = stderrTo.toByteArray();
        stderrFrom.close();
        stderrTo.close();
        process.waitFor();
        return byteArray.length == 0 ? StatusEnum.SUCCESS : StatusEnum.COMPILE_ERROR;
    }

    private static RunResultDTO run(String cmd, String stdIn, Integer timeLimit) throws IOException, InterruptedException {
        Process process = RUNTIME.exec(cmd);
        OutputStream out = process.getOutputStream();
        out.write(stdIn.getBytes(StandardCharsets.UTF_8));
        out.close();

        if (!process.waitFor(timeLimit, TimeUnit.SECONDS)) {
            // 进程运行超时
            process.destroy();
            return new RunResultDTO(StatusEnum.TIME_OUT, null);
        }

        InputStream stderrFrom = process.getErrorStream();
        ByteArrayOutputStream stderrTo = new ByteArrayOutputStream();
        int ch;
        while ((ch = stderrFrom.read()) != -1) {
            stderrTo.write(ch);
        }
        if (stderrTo.toByteArray().length != 0) {
            return new RunResultDTO(StatusEnum.EXCEPTION, null);
        }
        stderrTo.close();
        stderrFrom.close();

        InputStream stdoutFrom = process.getInputStream();
        ByteArrayOutputStream stdoutTo = new ByteArrayOutputStream();
        while ((ch = stdoutFrom.read()) != -1) {
            stdoutTo.write(ch);
        }
        String result = new String(stdoutTo.toByteArray(), StandardCharsets.UTF_8);
        stdoutTo.close();
        stdoutFrom.close();
        process.waitFor();

        return new RunResultDTO(StatusEnum.SUCCESS, result);
    }

    private static void writeFile(String filePath, String content) {
        File file = new File(filePath);
        try (FileOutputStream outputStream = new FileOutputStream(file);
             BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8))
        ) {
            bufferedWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}