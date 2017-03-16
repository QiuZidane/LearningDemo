package exception;

import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

public class HasResource {

	public void tryWithResource() {

		try (Scanner in = new Scanner(new FileInputStream("/Users/EvaZis/test.txt"));
				PrintWriter out = new PrintWriter("out.txt");) {
			while (in.hasNext()) {
				System.out.println(in.next());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
