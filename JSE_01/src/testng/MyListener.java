package testng;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class MyListener extends TestListenerAdapter {
	
	@Override
	public void onTestSuccess(ITestResult tr) {
		
		System.out.println(tr.getName()+"运行成功");
	
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
