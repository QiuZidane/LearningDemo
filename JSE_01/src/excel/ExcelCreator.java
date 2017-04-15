package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public final class ExcelCreator {
	

	private HSSFWorkbook excelbook;
	
	private static final String excelfile = "temp/工作簿A1.xls";
	
	private static List<String> contentList;
	
	

	
	public ExcelCreator(){};
	
	private static void init(int times) {
		contentList = new ArrayList<>();
		for (int i = 0; i < times; i++) {
			contentList.add(String.valueOf(i));
		}
	}
	
	/**
	 * 存入到存在的excel
	 * @param contentList
	 */
	private static void saveToExistFile(String filePath, List<String> contentList){
		
		int contentSize = contentList.size();
		
		// 获取当前excel
		File xls = new File(filePath);
		
		// 获取当前sheet
//		get
		
		// 剩余可用行数大于contentSize, 直接存入
		saveToFileDirect();
		
		
		// 剩余可用行数小于contentSize, 则分两步:
		// 1.先存入一部分()
		savePartToFile();
		
		// 2.剩余部分处理要判断，是否当前excel的sheet已经用完
		// 2.1.若未用完, 新建sheet, 设置title, 
//		createSheetAndSetTitle(workbook);
		
		// 2.2.若已用完, 新建xls文件,新建sheet
//		createXlsFile();
//		createSheetAndSetTitle(workbook);
		// 2.3.然后存入
//		saveToFileDirect(workbook,sheetname,contentList);
		
		
		
		
	}

	private static void createSheetAndSetTitle(Object workbook) {
		// TODO Auto-generated method stub
		
	}

	private static void savePartToFile() {
		// TODO Auto-generated method stub
		
	}

	private static void saveToFileDirect() {
		// TODO Auto-generated method stub
		
	}

	private void insertvalue() {

		try {

			File file = new File(excelfile);
			if (!file.exists()) {
				file.createNewFile();
			}

			excelbook = new HSSFWorkbook(
					new FileInputStream(new File(excelfile)));

			HSSFSheet sheet = excelbook.getSheet("工作表1111");
			int count = sheet.getPhysicalNumberOfRows();
			System.out.println("总行数=" + count);
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

//		new ExcelCreator().insertvalue();
		
		init(2000);
		

	}

}
