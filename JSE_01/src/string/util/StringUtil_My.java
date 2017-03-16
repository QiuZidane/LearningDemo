package string.util;

public class StringUtil_My {
	
	/**
	 * 格式化字符串
	 */
	public static void formatString() {
		
		String str1 = "111";
		int int1 = 123;
		String s = String.format("string1=%s and int1=%s", str1, int1);
		System.out.println(s);
		
	}

	public static void main(String[] args) {

		formatString();
		
	}

}
