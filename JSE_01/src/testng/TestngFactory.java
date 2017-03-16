package testng;

import java.util.Arrays;

import org.testng.annotations.Factory;

public class TestngFactory {
	
	@Factory
	public Object[] createInstances() {
		Object[] result = new Object[10];
		for (int i = 0; i < 10; i++) {
			result[i] = new TestngFactoryTest(i * 10);	
			// TestngFactoryTest test = (TestngFactoryTest)result[i];
			// System.out.println("result"+i+".m_number="+test.m_numberOfTimes);
			// System.out.println("result"+i+".num="+test.num);
			
		}
		return result;
	}

}
