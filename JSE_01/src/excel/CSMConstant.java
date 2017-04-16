package excel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CSMConstant {

	public static final String SHEET_NAME_PRE = "data";
	public static final String DATA_FILE_PRE = "temp/packet";
	public static final int CONTENT_ROWS = 65500;
	public static final int MAX_EXCEL_ROWS = 35530;
	public static final int MAX_SHEET_NUM = 1;
	public static final String[] TITLE_LIST = {"ip","port","mac"};
	public static final String SQLITE_DB_NAME = "csm.db";
	
	private static String CURRENT_DATA_FILE = null;
	
	public static final String getDataFile() {
		if (CURRENT_DATA_FILE == null) {
			createDataFile();
		}
		return CURRENT_DATA_FILE;
	}

	public static final void createDataFile() {

		DateFormat format = new SimpleDateFormat("yyyy-mm-dd_hhmmss");
		String suf = format.format(new Date());
		String newFile = DATA_FILE_PRE + "-" + suf + ".xls";
		CURRENT_DATA_FILE = newFile;
		

	}

}
