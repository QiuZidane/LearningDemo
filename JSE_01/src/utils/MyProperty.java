package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 结构为{mainkey, subMap(subkey, subvalue)}, 都是泛型
 * <p>
 * subMap用于记录subkey和subvalue
 * <p>
 * 提供get和set方法
 * <p>
 * @author QZidane # 2017-02-26
 *
 */
public class MyProperty<MK, SK, SV> {

	private Map<MK, HashMap<SK, SV>> mainMap; // 主Map
	private Logger logger;

	public MyProperty() {
		mainMap = new HashMap<>();
		logger = Logger.getLogger("MyProperty");
		
	}

	/**
	 * 将送入的key value加入到MyProperty属性中
	 * <p>
	 * 具体实现是存放到一个Map属性中
	 * 
	 */
	public void set(MK mainKey, SK subKey, SV subValue) {

		if (!mainMap.keySet().contains(mainKey)) { // 不存在则加入map

			HashMap<SK, SV> smap = new HashMap<>();
			smap.put(subKey, subValue);
			mainMap.put(mainKey, smap);

		} else { // 存在，则找到该key对应的value，put进去

			HashMap<SK, SV> smap = mainMap.get(mainKey);
			smap.put(subKey, subValue);

		}

	}

	/**
	 * 根据主key返回map，实际业务中是根据交易返回对应的参数map
	 * 
	 * @param mainKey
	 * @throws Exception
	 */
	public HashMap<SK, SV> get(MK mainKey) throws Exception {

		if (mainMap.get(mainKey) == null) {
			logger.log(Level.WARNING, "不存在的主key:"+mainKey);
		}
		return mainMap.get(mainKey);

	}

	/**
	 * 根据主key+子key返回value，实际业务中是根据交易+字段名返回对应的变量值
	 * @param mainKey
	 * @throws Exception 
	 */
	public SV get(MK mainKey, SK subKey) throws Exception{
		
		if (mainMap.get(mainKey) == null) {
			logger.log(Level.WARNING, "不存在的主key:"+mainKey);
			return null;
		} 
		
		HashMap<SK, SV> submap = (HashMap<SK, SV>) mainMap.get(mainKey);
		
		if(submap.get(subKey) == null){
			logger.log(Level.WARNING, "不存在的子key:"+subKey);
			return null;
		}
		
		SV subvalue = submap.get(subKey);
		logger.log(Level.INFO, "获取到的值="+subvalue);
		return subvalue;
	}

	@Override
	public String toString() {
		return mainMap.toString();
	}

	public static void main(String[] args) throws Exception {

		MyProperty<String, String, String> mp = new MyProperty<>();
		mp.set("A", "AA", "123");
		mp.set("A", "AA", "1234");
		mp.set("A", "AA1", "12xxx34");
		mp.set("B", "AA2", "1233234");
		mp.get("C");
		mp.get("B","A");
		mp.get("B","AA2");
		System.out.println(mp.toString());

	}

}
