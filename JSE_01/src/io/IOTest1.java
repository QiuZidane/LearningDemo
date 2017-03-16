package io;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

public class IOTest1 {

	public void fileTest() throws FileNotFoundException {

		InputStream f = new FileInputStream("test.txt");
	}

	public static void main(String args[]) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		Exception exception = new Exception("testEX");

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);

		exception.printStackTrace(ps);
		exception.printStackTrace(pw);

		System.out.println(sw.toString());
		System.out.println(new String(os.toByteArray()));

	}

}
