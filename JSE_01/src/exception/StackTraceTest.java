package exception;

import java.util.Scanner;

public class StackTraceTest {

	private int abc = 0;

	public static int factorial(int n) {
		System.out.println("factorial(" + n + "):");
		Throwable t = new Throwable();
		// t.printStackTrace();
		StackTraceElement[] frames = t.getStackTrace();
		for (StackTraceElement f : frames) {
//			System.out.println(f);
			System.out.println("FileName="+f.getFileName());
			System.out.println("LineNumber="+f.getLineNumber());
			System.out.println("ClassName="+f.getClassName());
			System.out.println("MethodName="+f.getMethodName());
			System.out.println("toString:"+f.toString());
		}

		int r;
		if (n <= 1) {
			r = 1;
		} else {
			r = n * factorial(n - 1);
		}
		System.out.println("return " + r);
		return r;

	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		System.out.println("Enter n: ");
		int n = in.nextInt();
		factorial(n);

	}

}
