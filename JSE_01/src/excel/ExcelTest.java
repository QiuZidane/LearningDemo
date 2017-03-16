package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import javax.imageio.stream.FileImageInputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import file.FileUtil;

public class ExcelTest {

	private static final String classname = "excel.ExcelTest";
	private static final String filename = "工作簿A.xls";
	private static final String filepath = FileUtil.getClassPathByUri(classname) + filename; // 当前类目录下的文件

	/**
	 * 读取单元格内容
	 * 
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws ClassNotFoundException
	 */
	private static void getCell() throws IOException, URISyntaxException, ClassNotFoundException {

		File file = new File(filepath);

		// 创建POI文件系统对象
		POIFSFileSystem fs = new POIFSFileSystem(file);

		// 获取工作簿对象
		HSSFWorkbook workbook = new HSSFWorkbook(fs);

		// 获取工作表Sheet对象
		int sheetNumbers = workbook.getNumberOfSheets();
		HSSFSheet sheet = workbook.getSheetAt(sheetNumbers - 1);

		// 获取工作表的行
		int lastRow = sheet.getPhysicalNumberOfRows(); // 获取实际的行数，这个是从0开始算
		HSSFRow row = sheet.getRow(lastRow);
		System.out.println("lastRow=" + lastRow);

		// 获取指定单元格
		int lastCellNum = row.getLastCellNum() - 1; // 这个是从1开始算起，所以要减一
		HSSFCell cell = row.getCell(row.getLastCellNum());
		System.out.println("lastCellNum=" + lastCellNum);

		// 打印具体单元格内容
		System.out.println(cell);

	}

	private static void writeCell() throws FileNotFoundException, IOException {

		// 获取工作簿对象
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File(filepath)));
		
		System.out.println("当前文件路径是="+filepath);

		HSSFSheet sheet = workbook.createSheet("生成的SHEET1");

		HSSFRow row1 = sheet.createRow(0);

		row1.createCell(0).setCellValue(1);
		row1.createCell(1).setCellValue(2);
		
		workbook.write(new File(filepath));
		

	}

	public static void main(String[] args)
			throws IOException, URISyntaxException, ClassNotFoundException {

		// getCell();
		writeCell();

	}

}
