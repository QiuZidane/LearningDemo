package testng;

import org.testng.annotations.Test;

public class TestngFactoryTest {
	public int m_numberOfTimes;

	public TestngFactoryTest(int numberOfTimes) {
		this.m_numberOfTimes = numberOfTimes;
	}

	public static int num;
	
	@Test
	public void testServer2() {
		System.out.printf("num=%s m_numberOfTimes=%s\n",num,m_numberOfTimes);
	}

	@Test
	public void testServer1() {
		num++;
		System.out.println(
				"num " + num + " m_numberOfTimes ：" + m_numberOfTimes + " instance is " + this);
	}
	

	
	
}
