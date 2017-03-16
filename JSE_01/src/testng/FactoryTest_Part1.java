package testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

/**
 * 
 * Factory的思路就是通过调用Test类的构造函数返回Test类对象，放入Object[]内，返回给TestNG框架做处理
 * 
 */
/**
 * 
 * 通过DataProvider给Factory提供数据
 * 
 *
 */
public class FactoryTest_Part1 {

	@DataProvider(name = "db111")
	public Object[] createData() {
		return new Object[][] { { "Zidane", 30 }, { "Eva", 26 } };
	}

	@Factory(dataProvider = "db111")
	public Object[] createInstance(String name, int age) {

		Object[] result = new Object[] { new FactoryTest_PartII(name, age) };

		return result;

	}

}
