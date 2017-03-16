package properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;

import file.FileUtil;

/**
 * 
 * 通过Properties可以读取和设置xx.properties文件，并做持久化处理
 * 
 * @author QZidane
 *
 */
public class PropertiesUtil {

//	private static final String filepath = "config/123.properties";
	private static final String filepath = "config/123.txt";

	public static void getProperties() {

		Properties pp = new Properties();
		try {
			pp.load(new FileInputStream(new File(filepath)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 根据key获取properties值
		String name = pp.getProperty("name");
		System.out.println(pp.getProperty("car", "1"));
		System.out.println(name);

		// 得到properties的所有名字
		Set<String> keys = pp.stringPropertyNames();
		System.out.println("keys=" + keys);

		// 遍历1
		for (String key : keys) {
			System.out.println(String.format("%s = %s", key, (String) pp.get(key)));
		}

		// 遍历2
		Enumeration<?> namekeys = pp.propertyNames();
		while (namekeys.hasMoreElements()) {
			String key = (String) namekeys.nextElement();
			System.out.println(key + " = " + pp.getProperty(key));
		}

		// 清除当前properties而已，不影响持久化的内容
		// pp.clear();
		// pp.list(System.out);

	}

	public static void storeProperties() {

		Properties pp = new Properties();
		try {
			pp.load(new FileReader(new File(filepath)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		pp.setProperty("daughter", "jane");
		pp.setProperty("son", "poter");

		OutputStream out;
		try {
			out = new FileOutputStream(new File(filepath));
			pp.store(out, "Update daughter/son");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		pp.list(System.out);
		System.out.println(filepath);

	}

	public static void main(String[] args) throws FileNotFoundException, IOException {

		storeProperties();
		getProperties();
		
		System.setProperty("a1", "111");
		System.out.println(System.getProperty("a1"));
		System.out.println(System.getProperties());
		File file = new File("config/123.txt");
		System.getProperties().load(new FileInputStream(file));  // --> 这其实等同于new了一个Properties对象
		System.out.println("daughter=" + System.getProperty("daughter"));
		System.out.println("user.home="+System.getProperty("user.home"));

	}

}
