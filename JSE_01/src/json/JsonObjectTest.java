package json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.*;

public class JsonObjectTest {

	private static Map<String, Object> map1 = new HashMap<String, Object>();

	public static void main(String[] args) {

		// jsonObjectToString();
		// createJsonObjectByMap();
		// createByBean();
		try {
			readJsonFromFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 通过put加入json属性
	 */
	private static void jsonObjectToString() {

		map1.put("key1", "value1");
		map1.put("key2", "value2");
		map1.put("key3", "value3");
		map1.put("key4", "value4");

		JSONObject jo = new JSONObject();

		try {
			jo.put("name", "zidane");
			jo.put("null1", JSONObject.NULL); // null使用库提供的空值
			jo.put("null2", JSONObject.NULL);
			jo.put("map1", map1);
			System.out.println(jo);

		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 通过map创建json对象
	 */
	private static void createJsonObjectByMap() {

		Map<String, Object> map = new HashMap<>();
		map.put("name", "zidane");
		map.put("null1", JSONObject.NULL); // null使用库提供的空值
		map.put("null2", JSONObject.NULL);
		map.put("map1", map1);

		JSONObject jo = new JSONObject(map);

		System.out.println(jo);

	}

	/**
	 * 通过javabean构建json对象
	 */

	private static void createByBean() {

		UserBean ub = new UserBean();
		ub.setName("Zidane");
		ub.setAge((short) 25);

		JSONObject jo = new JSONObject(ub);

		System.out.println(jo);

	}

	private static void readJsonFromFile() throws IOException {

		String filename = "J森";
		char[] nchar = filename.toCharArray();
		byte[] nbyte = filename.getBytes();
		System.out.println(new String(nchar));
		
		
		File file = new File(JsonObjectTest.class.getResource("./json1.txt").getFile());
		System.out.println(JsonObjectTest.class.getResource("./json1.txt").getFile());
//		File file = new File(JsonObjectTest.class.getResource("./"+filename).getFile());
//		System.out.println(JsonObjectTest.class.getResource("./"+filename).getFile());
		
		

		FileReader reader = new FileReader(file);
		char[] cbuf = new char[(int) file.length()];
		reader.read(cbuf);
		// System.out.println(new String(cbuf));

		JSONObject jo = new JSONObject(new String(cbuf));
		System.out.println(jo.get("name") == JSONObject.NULL);

		// 非null检测
		if (!jo.isNull("age")) {

			System.out.println("age = " + jo.get("age"));

		}

		JSONArray jsonArray = jo.getJSONArray("members");

		// 遍历1
		for (Object member : jsonArray) {
			System.out.println((String) member);
		}

		// 遍历2
		for (int i = 0; i < jsonArray.length(); i++) {
			System.out.println(jsonArray.get(i));

		}

		JSONObject jsonObject = jo.getJSONObject("obj1");
		System.out.println(jsonObject.get("A"));

	}

}
