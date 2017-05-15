package excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSMXLSTest {

	public static void main(String[] args) {

		try {

			Map<String, String> dataMap = new HashMap<>();
			dataMap.put("ip", "122.138.1.1");
			dataMap.put("port", "9080");
			dataMap.put("name", "zidane");

			genXlsData(dataMap, CSMConstant.TITLE_LIST);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private static void genXlsData(Map<String, String> dataMap, String[] titleList) {

		List<String[]> xlsData = new ArrayList<>();
		String[] dataRow = new String[titleList.length];
		for (int i = 0, len = titleList.length; i < len; i++) {
			dataRow[i] = null != dataMap.get(titleList[i].toLowerCase()) ? dataMap.get(titleList[i]) : "";
		}

	}

}
