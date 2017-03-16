package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelCreator {

	private HSSFWorkbook excelbook;
	private static final String excelfile = "/Users/EvaZis/Documents/工作簿1.xls";

	//String name, String sex, String age, String dept, String job,	String laborage
	private void insertvalue() {

		try {

			excelbook = new HSSFWorkbook(
					new FileInputStream(new File(excelfile)));

			HSSFSheet sheet = excelbook.getSheet("工作表1");
			int count = sheet.getPhysicalNumberOfRows();
			System.out.println("总行数="+count);
			HSSFRow row = sheet.createRow(0);
			row.createCell(0).setCellValue("1111");
			
			FileOutputStream outputStream = new FileOutputStream(new File(excelfile));
			excelbook.write(outputStream);
			outputStream.flush();
	
		
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void main(String[] args) {

		new ExcelCreator().insertvalue();
		
	}

}
