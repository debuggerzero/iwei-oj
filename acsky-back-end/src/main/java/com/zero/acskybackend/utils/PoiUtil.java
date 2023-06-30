package com.zero.acskybackend.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

import java.text.DecimalFormat;

/**
 * Poi 工具类
 *
 * @author ZERO
 * @date 2023/6/28
 */
public class PoiUtil {

    public static Object getCellValue(Cell cell) {
        Object result = null;
        switch (cell.getCellType()) {
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    result = cell.getDateCellValue();
                } else {
                    DecimalFormat decimalFormat = new DecimalFormat("0");
                    double numericValue = cell.getNumericCellValue();
                    result = decimalFormat.format(numericValue);
                }
                break;
            case STRING:
                result = cell.getStringCellValue();
                break;
            case BOOLEAN:
                result = cell.getBooleanCellValue();
                break;
            default:
        }
        return result;
    }

}
