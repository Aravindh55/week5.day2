package week5.day2excel;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelClass {
	public String[][] readExcel() throws IOException {
		XSSFWorkbook wb=new XSSFWorkbook("./exceldata/excel.xlsx");
		XSSFSheet sheet = wb.getSheet("Sheet1");
		int rowCount = sheet.getLastRowNum();
		short columnCount = sheet.getRow(0).getLastCellNum();
		String[][] data=new String[rowCount][columnCount];
		for (int i = 1; i <= rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				String text = sheet.getRow(i).getCell(j).getStringCellValue();
				data[i-1][j]=text;			
			}
			
		}
		wb.close();
return data;
	}

}
