package testng;

import org.testng.annotations.Test;

public class FactoryTest_PartII {

	private static int testtime;
	private String name;
	private int age;

	public FactoryTest_PartII(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Test
	public void test1() {
		testtime++;
		String txt = String.format("test time : %s, name=%s age=%s", testtime, name, age);
		System.out.println(txt);
	}

}
