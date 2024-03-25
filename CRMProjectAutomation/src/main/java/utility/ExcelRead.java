package utility;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelRead {
	


public static Object[][] getDataFromExcel(String filePath, String sheetName) throws InvalidFormatException, IOException
			 {
		Object[][] data;
		FileInputStream inputStream = new FileInputStream(new File(filePath));
		Workbook wb = WorkbookFactory.create(inputStream);
		Sheet s = wb.getSheet(sheetName);

		// sheet range
		int rowCount = s.getLastRowNum(); //4
		int colCount = s.getRow(0).getLastCellNum();//2

		// set data

		data = new Object[rowCount][colCount];
		for (int i = 1; i <= rowCount; i++) {
			for (int j = 0; j <= colCount - 1; j++) {
				if (!getCellValue(s, 0, j).equals("")) {
					data[i - 1][j] = getCellValue(s, i, j);
				}
			}
		}
	
		return data;
	}

public static String getDataFromExcel(String filePath, String sheetName, int rowNo, int colNo) throws InvalidFormatException, IOException
{
String data="";
FileInputStream inputStream = new FileInputStream(new File(filePath));
Workbook wb = WorkbookFactory.create(inputStream);
Sheet s = wb.getSheet(sheetName);

	if (!getCellValue(s, 0, colNo).equals("")) {
		data=getCellValue(s, rowNo, colNo);
	}

return data;
}


	private static String getCellValue(Sheet sheet, int row, int col) {
		String value = "";
		if (sheet.getRow(row).getCell(col) == null) {
			value = "";
		} else if (sheet.getRow(row).getCell(col).getCellType() == Cell.CELL_TYPE_STRING) {
			value = sheet.getRow(row).getCell(col).getStringCellValue();
		} else {
			sheet.getRow(row).getCell(col).setCellType(Cell.CELL_TYPE_STRING);
			value = sheet.getRow(row).getCell(col).getStringCellValue();
		}
		return value;
	}



}
