package testng;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

@Listeners({MyListener.class})
public class DataProviderTest {

	@DataProvider(name = "dp1")
	public Object[][] dp() {
		return new Object[][] { { 1 }, { 2 }, { 3 }, { 4 } };
	}
	
	@Test
	public void test1() {
		System.out.println("运行test1");
	}

	@Test(dataProvider = "dp1")	
	public void test2(int times) {
		System.out.println("access time = " + times);
	}


	
	
}
