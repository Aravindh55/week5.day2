package week5.day2assign;

import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelbaseClass {

	public String[][] dataread(String fileName) throws IOException {
		XSSFWorkbook wb = new XSSFWorkbook("./exceldata/"+fileName+".xlsx");
		XSSFSheet sheet = wb.getSheet("Sheet1");
		int rowcount = sheet.getLastRowNum();
		short columncount = sheet.getRow(0).getLastCellNum();
		String[][] data=new String[rowcount][columncount];
		for (int i = 1; i<=rowcount; i++) {
			for (int j = 0; j < columncount; j++) {
				String value = sheet.getRow(i).getCell(j).getStringCellValue();
				data[i-1][j]=value;
				
				
				
			}
			
		}
		return data;

	}

}
