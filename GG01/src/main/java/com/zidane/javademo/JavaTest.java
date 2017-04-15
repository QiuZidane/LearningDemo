package com.zidane.javademo;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class JavaTest {

	public static int IA;
	public int AA;
	private String name;

	private static final Logger logger = Logger.getLogger(JavaTest.class);

	public static void print() {

		System.out.println("1234");

		try {
			FileUtils.readLines(new File("temp/test.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("1111");
	}

	public static void main(String[] args) {

		print();

	}

}
