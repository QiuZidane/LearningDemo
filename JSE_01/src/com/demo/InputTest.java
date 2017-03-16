package com.demo;

import java.io.File;

public class InputTest {

	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//
//		System.out.println("What is your name?");
//
//		String name = in.nextLine();
//
//		System.out.println("How old are you?");
//		int age = in.nextInt();
//
//		System.out.println("Hello, " + name + ". you are " + age + " years old");
		
		String dir = System.getProperty("user.dir");
		System.out.println(dir);
		
		
		try {
//			Scanner in = new Scanner(Paths.get("C:/workspace/jse_study/P01/myfile.txt"));
			File f1 = new File("C:/workspace/jse_study/P01/myfile.txt");
		} catch (Exception e) {
			System.out.println("exception1="+e.toString());
		}
		
		
		
	}

}
