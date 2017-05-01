package properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * 将若干个同key数据存放到属性中 比如最近使用过的IP，存储为IP=122.138.1.2##122.132.21.11##
 */
public class StoreList {

	private static final Properties pp = new Properties();
	private static final String SEPARATOR = "##"; // 分隔符
	private static final int MAX_RECENT_SIZE = 10; // 最大保留前N条记录

	public StoreList() {

		try {
			pp.load(new FileInputStream(new File("config/config.ini")));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 根据key获取属性数组，最近使用的属性放在[0]位置
	 */
	public static String[] getArrDataFromProperty(String key) {

		// 取出指定key
		String value = pp.getProperty(key);

		// 根据分隔符分割为数组
		String[] keyArray = value.split(SEPARATOR);

//		System.out.println(Arrays.toString(keyArray));

		return keyArray;

	}

	/**
	 * 存储具体多个值得属性
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void putMulProperty_String(String key, String value) throws FileNotFoundException, IOException {

		System.out.println(pp.get("IP"));
		// 排序后的数组
		List<String> newKeyList = null;

		// 获取属性数组
		String[] keyArray = getArrDataFromProperty(key);

		// 转为List处理
		List<String> keyList = new ArrayList<>(Arrays.asList(keyArray));

		// 对比是否有重复	
		if (!keyList.contains(value)) {

			// 不重复则新增
			keyList.add(value);

		}

		newKeyList = sortByValue(keyList, value);
		storeValue(key, newKeyList);

//		pp.list(System.out);

		System.out.println(pp.get("IP"));

	}

	/**
	 * 按新增值排序，新增值排位置0
	 */
	private static List<String> sortByValue(List<String> keyList, String newValue) {

		// 如果newValue已经在0位置，不变
		if (keyList.get(0).equals(newValue)) {

			return keyList;

		} else { // 如果不在0位置，使用临时数组排序

			List<String> tempList = new ArrayList<>(MAX_RECENT_SIZE);
			tempList.add(newValue);

			for (int i = 0; i < keyList.size(); i++) {

				// 将所在位置置空
				if (keyList.get(i).equals(newValue)) {
					continue;
				}

				tempList.add(keyList.get(i));

			}

			if (tempList.size()>MAX_RECENT_SIZE) {
				// 返回前n个属性组成的List
				List<String> newKeyList = new ArrayList<>(MAX_RECENT_SIZE);
				for (int i = 0; i < MAX_RECENT_SIZE; i++) {
					newKeyList.add(tempList.get(i));
				}
				return newKeyList;
			} else {
				return tempList;
			}
			

			

		}

	}

	private static void storeValue(String key, List<String> newKeyList) throws FileNotFoundException, IOException {
		// 拼接成字符串
		StringBuilder newValue = new StringBuilder();
		for (String item : newKeyList) {
			newValue.append(item + SEPARATOR);
		}

		// 存入property
		pp.put(key, newValue.toString());

		pp.store(new FileOutputStream(new File("config/config.ini")), "Insert key=" + key);
	}

	public static void main(String[] args) {

		try {

			new StoreList();
			putMulProperty_String("IP", "192.168.1.11");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
