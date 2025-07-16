package configuration;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;

public class Utilities {
    static XSSFWorkbook workbook;
    static XSSFSheet sheet;
    static Object testData[][];

    public static Object[][] getTestData(File excelFile, String sheetname) {
        try {
            workbook = new XSSFWorkbook(excelFile);
            sheet = workbook.getSheet(sheetname);
            int rows = sheet.getLastRowNum();
            int cols = sheet.getRow(rows).getLastCellNum();
            testData = new Object[rows][cols];
            for (int i = 0; i < rows; i++) {
                XSSFRow row = sheet.getRow(i + 1);
                for (int j = 0; j < cols; j++) {
                    XSSFCell cell = row.getCell(j);
                    CellType cellType = cell.getCellType();
                    switch (cellType) {
                        case STRING:
                            testData[i][j] = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                testData[i][j] = cell.getDateCellValue();
                                break;
                            } else {
                                double numericValue = cell.getNumericCellValue();
                                if (numericValue >= 1000000000 && numericValue <= 9999999999L) {
                                    testData[i][j] = (long) numericValue;
                                } else {
                                    testData[i][j] = (int)(numericValue);
                                }
                                break;
                            }

                        case BOOLEAN:
                            testData[i][j] = cell.getBooleanCellValue();
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return testData;
    }
}
