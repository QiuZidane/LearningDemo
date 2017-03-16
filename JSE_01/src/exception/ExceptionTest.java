package exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

public class ExceptionTest {

	public void threadTest() {

		Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
		for (Thread t : map.keySet()) {
			StackTraceElement[] frames = map.get(t);
			System.out.println(frames.toString());

		}

	}

	/**
	 * 分析堆栈跟踪元素
	 */

	public static void analyTest() {
		Throwable t = new Throwable();
		StringWriter out = new StringWriter();
		t.printStackTrace(new PrintWriter(out));
		String desc = out.toString();
		System.out.println("desc : " + desc);

		StackTraceElement[] frames = t.getStackTrace();
		for (StackTraceElement frame : frames) {
			System.out.println(frame.toString());
		}
	}

	public void diyException() {

		try {
			throw new FileFormatException("哈哈哈"); // 这个是自定义异常
		} catch (FileFormatException e) {

			StringWriter out = new StringWriter();
			e.printStackTrace(new PrintWriter(out));
			String desc = out.toString();
			System.out.println("desc : " + desc);

			e.printStackTrace();
			// StackTraceElement[] frames1 = e.getStackTrace();
			// for(StackTraceElement frame: frames1){
			// System.out.println(frame.toString());
			//
			// }

		}
	}

	public static void main(String[] args) {
		ExceptionTest eTest = new ExceptionTest();
		// eTest.diyException();
		// eTest.threadTest();

		// 带资源的try
		HasResource hr = new HasResource();
		hr.tryWithResource();
		
		analyTest();		

	}

}
