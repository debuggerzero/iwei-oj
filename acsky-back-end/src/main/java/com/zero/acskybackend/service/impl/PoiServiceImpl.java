package com.zero.acskybackend.service.impl;

import com.zero.acskybackend.model.po.UserInfo;
import com.zero.acskybackend.service.PoiService;
import com.zero.acskybackend.utils.PoiUtil;
import com.zero.acskybackend.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * PoiServiceImpl
 *
 * @author ZERO
 * @date 2023/7/3
 */
@Service("poiServiceImpl")
@RequiredArgsConstructor
public class PoiServiceImpl implements PoiService {

    public List<UserInfo> getUserInfoList(InputStream file) throws IOException {
        List<UserInfo> list = new ArrayList<>();
        XSSFWorkbook sheets = new XSSFWorkbook(file);
        XSSFSheet sheet = sheets.getSheetAt(0);
        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue;
            }
            UserInfo userInfo = new UserInfo();
            userInfo.setAccount((String) PoiUtil.getCellValue(row.getCell(0)));
            userInfo.setName((String) PoiUtil.getCellValue(row.getCell(1)));
            userInfo.setPassword(StringUtil.md5(StringUtil.md5("123456")));
            list.add(userInfo);
        }
        sheets.close();

        return list;
    }

}
