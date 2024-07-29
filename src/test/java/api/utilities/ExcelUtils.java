package api.utilities;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {
    public static Object[][] getTableArray(String filePath, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);
        int totalRows = sheet.getPhysicalNumberOfRows();
        int totalCols = sheet.getRow(0).getPhysicalNumberOfCells();

        List<Object[]> validRows = new ArrayList<>();

        for (int i = 1; i < totalRows; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
//                System.out.println("Skipping null row at index: " + i);
                continue;  // Skip rows that are null
            }
            Object[] rowData = new Object[totalCols];
            boolean isValidRow = true;
            for (int j = 0; j < totalCols; j++) {
                if (row.getCell(j) == null) {
//                    System.out.println("Found null cell at row " + i + ", col " + j);
                    rowData[j] = "";  // Set empty string if cell is null
                } else {
                    rowData[j] = row.getCell(j).toString();
                }
                if (rowData[j].equals("")) {
                    isValidRow = false;
                }
            }
            if (isValidRow) {
                validRows.add(rowData);
            }
        }

        workbook.close();
        return validRows.toArray(new Object[0][0]);
    }
}
