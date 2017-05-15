package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import static excel.CSMConstant.*;

public final class CSMExcelWriter {

	private HSSFWorkbook wb;
	private HSSFSheet lastSheet; // 最后页
	private InputStream in;
	private OutputStream out;
	private int sheetNum; // sheet数量
	private int contentSize; // 数据大小
	private String filePath;
	private boolean isNewFile = false;
	private int firstAvailableRow;
	private int hasRecordRow = 0; // LIST已记录的行数
	private static Logger logger;

	private List<String[]> contentList;

	/**
	 * 对外的入口方法
	 * 
	 * @param contentList
	 * @throws IOException
	 */
	public static void saveToFile(List<String[]> contentList) throws IOException {

		// ************* 新建一个实例来处理 *************
		CSMExcelWriter writer = new CSMExcelWriter();
		writer.contentList = contentList;

		// 获取当前excle文件
		String dataFile = getDataFile();
		writer.filePath = dataFile;

		File file = new File(dataFile);

		if (!file.exists()) {
			logger.info("文件不存在, 创建:" + dataFile);
			
			file.createNewFile();
			// 文件不存在, 走新建流程
			writer.saveToNewFile(contentList);

		} else {

			// 文件不存在, 走新建流程
			writer.saveToExistFile(contentList);

		}

	};

	private CSMExcelWriter() {
		logger = Logger.getLogger("CSMExcelWriter_Logger");
		logger.setLevel(Level.INFO);
		logger.info("开始");
	}

	/**
	 * 向一个新文件写入list
	 * 
	 * @throws IOException
	 */
	public void saveToNewFile(List<String[]> contentList) throws IOException {

		in = new FileInputStream(new File(filePath));
		wb = new HSSFWorkbook();
		isNewFile = true;

		// 创建首页
		HSSFSheet sheet = wb.createSheet(SHEET_NAME_PRE + "1");
		sheetNum++;
		lastSheet = sheet;
		// 创建行和title
		HSSFRow row0 = sheet.createRow(0);
		// 赋值title行内容
		for (int i = 0; i < TITLE_LIST.length; i++) {
			HSSFCell cell = row0.createCell(i);
			cell.setCellValue(TITLE_LIST[i]);
		}

		// 设置首可用行
		firstAvailableRow = 1; // 新文件的首可用行为1
		logger.fine("xls可用首行=" + firstAvailableRow);

		// 获取原始数据大小
		contentSize = contentList.size();

		// 分支判断处理
		switchDetail();

	}

	/**
	 * 存入到存在的excel
	 * 
	 * @param contentList
	 * @throws IOException
	 */
	public void saveToExistFile(List<String[]> contentList) throws IOException {

		in = new FileInputStream(new File(filePath));
		wb = new HSSFWorkbook(in);

		sheetNum = wb.getNumberOfSheets();
		logger.fine("当前sheet数目=" + sheetNum);
		// 获取当前sheet
		lastSheet = wb.getSheetAt(sheetNum - 1);
		logger.fine(lastSheet.getSheetName());

		// 设置首可用行
		firstAvailableRow = lastSheet.getPhysicalNumberOfRows();
		logger.fine("xls可用首行=" + firstAvailableRow);

		// 获取原始数据大小
		contentSize = contentList.size();

		// 分支判断处理
		switchDetail();

	}

	private void switchDetail() throws IOException {

		logger.fine("***********当前页=" + lastSheet.getSheetName() + "**************");

		int availableRows = MAX_EXCEL_ROWS - firstAvailableRow; // 实际可写入行数为availableRows-1

		logger.fine("xls可用行数=" + availableRows); //
		logger.fine("data size=" + contentSize);

		// ============ 剩余可用行数大于contentSize, 直接存入 ============
		if (availableRows > contentSize) {
			saveToFileDirect(firstAvailableRow, contentList, hasRecordRow);
			return;
		}

		// ============ 剩余可用行数小于contentSize, 则分两步: ============
		if (availableRows <= contentSize) {

			// 1.先存入一部分()
			savePartToFile(firstAvailableRow, contentList, hasRecordRow);

			// 1.1.更新已记录行数
			hasRecordRow += availableRows;

			// 2.剩余部分处理：下面两种情况都是从第二行开始记录：
			firstAvailableRow = 1; // 即第2行开始

			// 计算剩余未处理行数
			contentSize = contentSize - availableRows;

			logger.fine("剩余未存行数=" + contentSize);

			// 判断当前excel的sheet是否已经用完
			if (sheetNum < MAX_SHEET_NUM) {
				// 2.1.若未用完, 新建sheet,并设置title,
				createSheetAndSetTitle(wb);
				// 2.2.然后再次做判断--->直到saveToFileDirect才停止
				switchDetail();
				// ******* 结束 ********
				return;

			} else {
				// 2.3.若已用完, 用剩余未处理的记录创建List
				List<String[]> newContentList = getNewContentList(contentSize, contentList);
				// 2.4.获取新文件名(路径)
				createDataFile();
				String newXls = getDataFile();

				// 2.5.写入新excel
				CSMExcelWriter.saveToFile(newContentList);

			}
		}
	}

	private final void createXls(String filePath) throws IOException {
		File file = new File(filePath);
		file.createNewFile();
	}

	/**
	 *
	 * 用剩余未处理的记录创建List
	 * 
	 */
	private List<String[]> getNewContentList(int rowLeft, List<String[]> contentList) {

		List<String[]> newList = new ArrayList<String[]>(rowLeft);
		for (int i = contentList.size() - rowLeft; i < contentList.size(); i++) {
			newList.add(contentList.get(i));
		}
		return newList;
	}

	/**
	 * 新建sheet,并设置title
	 */
	private void createSheetAndSetTitle(HSSFWorkbook wb) {

		sheetNum++;
		String sheetName = SHEET_NAME_PRE + sheetNum;
		HSSFSheet newSheet = wb.createSheet(sheetName);
		lastSheet = newSheet;
		// 创建首行title
		HSSFRow row0 = newSheet.createRow(0);
		// 赋值行内容
		for (int i = 0; i < TITLE_LIST.length; i++) {
			HSSFCell cell = row0.createCell(i);
			cell.setCellValue(TITLE_LIST[i]);
		}

	}

	/**
	 * 剩余可用行数大于contentSize, 直接存入(从firstAvailableRow开始写起)
	 * 
	 * @param startIndex
	 *            : list开始记录行数 说明:结束行=firstAvaRow + list.size()-startIndex;
	 */
	private void saveToFileDirect(int firstAvaRow, List<String[]> list, int startIndex) {

		int beginRow = firstAvaRow;
		int endRow = firstAvaRow + list.size() - startIndex;
		writeRows(beginRow, endRow, list, startIndex);

		// 写入文件
		if (isNewFile) {
			commitWrite_newFile();
		} else {
			commitWrite();
		}

	}

	/**
	 * 剩余可用行数小于contentSize, 先存入一部分()
	 */
	private void savePartToFile(int firstAvaRow, List<String[]> list, int startIndex) {

		int beginRow = firstAvaRow;
		int endRow = MAX_EXCEL_ROWS;
		writeRows(beginRow, endRow, list, startIndex);

		// 写入文件
		if (isNewFile) {
			commitWrite_newFile();
		} else {
			commitWrite();
		}

	}

	/**
	 * 提取简单写入方法
	 */
	private void writeRows(int beginRow, int endRow, List<String[]> list, int startIndex) {

		for (int rownum = beginRow; rownum < endRow; rownum++) {

			HSSFRow row = lastSheet.createRow(rownum);

			// 获取contentList第0行开始的数据，写入xls
			String[] currentRow = list.get(startIndex++);
			for (int colIndex = 0; colIndex < currentRow.length; colIndex++) {
				HSSFCell cell = row.createCell(colIndex);
				cell.setCellValue(currentRow[colIndex]);
			}

		}

	}

	/**
	 * 写文件:对存量文件
	 */
	private void commitWrite() {
		try {
			out = new FileOutputStream(new File(filePath));
			wb.write(out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	/**
	 * 写文件:新建excel文件
	 */
	private void commitWrite_newFile() {
		try {
			wb.write(new File(filePath));;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {

		try {
			List<String[]> contentList = new ArrayList<>();
			for (int i = 0; i < CONTENT_ROWS; i++) {
				String item = " 23243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356";
				String[] row = { String.valueOf(i), 
						"23232323232323dsfdsfdsfdsfdsf", 
						"23232323232323dsfdsfdsfdsfdsf", 
						"23232323232323dsfdsfdsfdsfdsf", 
						"23232323232323dsfdsfdsfdsfdsf",
						"23232323232323dsfdsfdsfdsfdsf", 
						"23232323232323dsfdsfdsfdsfdsf", 
						"23232323232323dsfdsfdsfdsfdsf", 
						"23232323232323dsfdsfdsfdsfdsf", 
						item };

				contentList.add(row);
			}
			
			System.out.println("111"+contentList.size());
			
			createDataFile();
			CSMExcelWriter.saveToFile(contentList);
			
			
			logger.info("结束");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
