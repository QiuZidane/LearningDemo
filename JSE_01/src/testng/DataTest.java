package testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import file.FileUtil;

/**
 * 
 * 该测试案例不通过XML调起 <br>
 * 1、使用DataProvider传递数据
 * 2、使用Factory传递数据
 *
 */
public class DataTest {
	// @Test(dataProvider = "dp") 两种写法都可以，用name指定或者方法名指定dataProvider
	@Test(dataProvider = "simpletest")
	public void f(Integer n, String s) {
		System.out.println("Integer=" + n);
		System.out.println("String=" + s);
		FileUtil.getClassPathByUri("io.FileTest1");

	}
	
	@Test(enabled = false)
	public void disableFunc() {
		System.out.println("该测试被忽略，不运行");

	}
	
	/**
	 * Object[][]有多少个数据就循环几次 <br>
	 * @return
	 */
	@DataProvider(name = "simpletest")
	public Object[][] dp() {
		return new Object[][] { new Object[] { 1, "a" }, new Object[] { 2, "b" }, };
	}
	
	@Test(dependsOnMethods={"被依赖方法1","被依赖方法0"})
	public void 依赖别人的方法(){
		System.out.println("运行-依赖别人的方法");
	}
	
	/**
	 * 依赖测试
	 */
	@Test(dependsOnMethods={"被依赖方法0"})
	public void 被依赖方法1(){
		
		System.out.println("运行-被依赖方法1");
		
	}
	
	@Test
	public void 被依赖方法0(){
		
		System.out.println("运行-被依赖方法0");
		
	}
	

	
}
