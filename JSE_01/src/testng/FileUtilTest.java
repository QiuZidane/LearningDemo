package testng;

import org.testng.annotations.Test;

import file.FileUtil;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Factory;
import org.testng.annotations.Parameters;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;

/**
 * 
 * BeforeClass/AfterClass,BeforeTest/AfterTest 这两对比较接近 <br>
 * Class是当前类中所有测试方法运行后运行 <br>
 * Test是所有<test>标签的方法运行后运行 <br>
 * 
 * 该案例通过xml方式调起，通过xml传递数据: FileTest.xml
 *
 */
public class FileUtilTest {

	// 整个测试集合运行前后执行
	@BeforeSuite
	public void beforeClass() {
		System.out.println("@BeforeSuite");
	}

	@AfterSuite
	public void afterClass() {
		System.out.println("@AfterSuite");
	}

	// 被注释的方法将在当前类的第一个测试方法调用前运行
	@BeforeClass()
	public void BeforeClass() {
		System.out.println("@BeforeClass");
	}

	// 被注释的方法将在当前类的所有测试方法调用后运行
	@AfterClass
	public void AfterClass() {
		System.out.println("@AfterClass");
	}

	@BeforeTest()
	public void BeforeTest() {
		System.out.println("@BeforeTest");
	}

	@AfterTest
	public void AfterTest() {
		System.out.println("@AfterTest");
	}

	@BeforeMethod
	public void BeforeMethod() {

		System.out.println("BeforeMethod");
	}

	@BeforeGroups(groups = { "exceptiontest" })
	public void BeforeGroups() {

		System.out.println("@BeforeGroups");

	}

	@AfterGroups(groups = { "exceptiontest" })
	public void AfterGroups() {

		System.out.println("@AfterGroups");
	}
	
	
	
	/**
	 * ******** 具体测试案例 ******** 
	 */

	@Test(groups = { "functest","fftt"}) // 定义该类属于多个group
	public void getClassPathByUri1() {
		String classPath = FileUtil.getClassPathByUri("excel.ExcelTest");
		System.out.println(">>>>getClassPathByUri()--111");
	}

	@Test(groups = { "functest" })
	public void getClassPathByUri2() {
		System.out.println(">>>>getClassPathByUri()--222");
	}

	/**
	 * 异常测试<br>
	 * 期待异常类型和异常描述
	 */
	@Test(expectedExceptions = IOException.class, expectedExceptionsMessageRegExp = "Nullll", groups = {
			"exceptiontest" })
	public void exceptionTest1() throws IllegalAccessException {
		System.out.println("exceptionTest>>>");
		throw new IllegalAccessException("Nullll");
	}

	@Test(groups = { "paramtest" })
	@Parameters({"classname1","classname2"})
	public void paramTest1(String classname1, String classname2) {

		System.out.println("classPath=" + FileUtil.getClassPathByUri(classname1));
		System.out.println("classPath=" + FileUtil.getClassPathByUri(classname2));
	}

	public void nothing() {
		System.out.println(">>>nothing>>>");

	}
}
