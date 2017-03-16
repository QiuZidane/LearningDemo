package json;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Field;

import org.json.JSONObject;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class GsonTest {

	private static void createByBean(Object beanObj) {

		// 简单的通过javabean创建json字符串
		Gson gson = new Gson();
		String jsonString = gson.toJson(beanObj);
		System.out.println(jsonString); // {"name":"Zidane","age":25}

		// 如果在bean对象中使用了@SerializedName，则可以定义输出json字符串的键值
		// 比如:@SerializedName("NAME")-->{"NAME":"Zidane","age":25}

	}

	/**
	 * 格式化过的json字符串
	 */
	private static void createByBean_pretty(Object beanObj) {

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();
		// 这里设置返现的策略，优先级比@SerializedName("NAME")低
		gsonBuilder.setFieldNamingStrategy(new FieldNamingStrategy() {

			@Override
			public String translateName(Field f) {
				if (f.getName().equals("age")) {
					return "AGE";
				} else {
					return f.getName();
				}

			}
		});
		Gson gson = gsonBuilder.create();
		String jsonString = gson.toJson(beanObj);
		System.out.println(jsonString);

	}

	/**
	 *
	 * GSON解析 通过文件读取json数据，并序列化为Bean对象
	 * 
	 */
	private static void readJsonFromFile() throws IOException {

		File file = new File(JsonObjectTest.class.getResource("./json1.txt").getFile());
		char[] filechar = new char[(int) file.length()];
		Reader reader = new FileReader(file);
		reader.read(filechar);
		String content = new String(filechar);
		Gson gson = new Gson();
		UserBean userBean = gson.fromJson(content, UserBean.class);
		System.out.println(userBean.getName());
		System.out.println(userBean.getAge());
		System.out.println(userBean.toString());

	}

	/**
	 *
	 * GSON解析 <br>
	 * 通过文件读取json数据，并序列化为Bean对象 <br>
	 * 带有日期属性的情况 <br>
	 * 带有集合属性的情况
	 * 
	 */
	private static void readJsonFromFileHasDate() throws IOException {

		File file = new File(JsonObjectTest.class.getResource("./json1.txt").getFile());
		char[] filechar = new char[(int) file.length()];
		Reader reader = new FileReader(file);
		reader.read(filechar);
		String content = new String(filechar);
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		builder.setDateFormat("YYYY-MM-DD hh:mm:ss");
		Gson gson = new GsonBuilder().create();
		UserBeanComplex userBean = gson.fromJson(content, UserBeanComplex.class);
		System.out.println(userBean.getName());
		System.out.println(userBean.getAge());
		System.out.println(userBean.getBirthDay().toLocaleString());
		System.out.println(userBean.toString());
		System.out.println("major="+userBean.getMajor());
		System.out.println("major="+userBean.getMajor().getClass());
		

	}

	public static void main(String[] args) {

		UserBean ub = new UserBean();
		ub.setName("Zidane");
		ub.setAge((short) 25);
		ub.setIgnore("被忽略的属性");

		// System.out.println(ub);
		// createByBean(ub);
		// createByBean_pretty(ub);

		// 日期属性为字符串类型情况的解析
		// try {
		// readJsonFromFile();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

		// 日期属性为日期类型情况的解析
		try {
			readJsonFromFileHasDate();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
