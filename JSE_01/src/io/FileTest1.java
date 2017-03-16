package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Scanner;

import javax.xml.soap.Text;

public class FileTest1 {

	public static final String TEST2 = File.separator + "Users" + File.separator + "EvaZis/test2.txt";
	public static final String TEST22 = File.separator + "Users" + File.separator + "EvaZis/test22.txt";
	private static FileTest1 fileHander;

	public static FileTest1 getFileHander() {
		if (fileHander == null) {
			FileTest1 f1 = new FileTest1();
			fileHander = f1;
			return fileHander;
		} else {
			return fileHander;
		}
	}

	// 默认的new方法只能内部调用
	private FileTest1() {
	}

	/**
	 * 将终端输入信息输出到文件
	 * 
	 * @param filepath
	 * @throws IOException
	 */
	public void writeFileWithConsoleInput(String filepath) throws IOException {

		File file = new File(filepath);
		Writer out = new FileWriter(file);

		try (Scanner in = new Scanner(System.in);) {
			String text = in.nextLine();
			out.write(text);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}

	}

	/**
	 * 读取字符数据
	 * 
	 * @param filepath
	 */
	public void readFile(String filepath) {
		File file = new File(filepath);
		try {

			// reader1的方法和reader等价
			// BufferedReader reader1 = new BufferedReader(new
			// InputStreamReader(new FileInputStream(file)));
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String text = null;
			while ((text = reader.readLine()) != null) {
				System.out.println(text);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建文件
	 */
	public void createFile() {
		// File.pathSeparator 在mac下是:
		// File.separator 在mac下是\
		// /Users/EvaZis
		String filepath = File.separator + "Users" + File.separator + "EvaZis/test2.txt";
		File f = new File(filepath);
		try {
			f.createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除文件
	 */
	public void delFile() {
		String filepath = File.separator + "Users" + File.separator + "EvaZis/test2.txt";
		File f = new File(filepath);
		if (f.exists()) {
			f.delete();
		} else {
			System.err.println("文件不存在");
		}
	}

	/**
	 * 复制文件 复制到的文件如果不存在，会创建
	 * 
	 */
	public void copyFile() {

		String filepath1 = File.separator + "Users" + File.separator + "EvaZis/test2.txt";
		String filepath2 = File.separator + "Users" + File.separator + "EvaZis/test3.txt";

		try (InputStream inputStream = new FileInputStream(new File(filepath1));
				OutputStream outputStream = new FileOutputStream(new File(filepath2));) {

			if (inputStream != null && outputStream != null) {
				int temp = 0;
				while ((temp = inputStream.read()) != -1) {
					outputStream.write(temp);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 向文件写入字符串，用的是字节流，无缓存，速度可能慢点
	 */
	public void writeStrToFile_Byte(String content, String filepath) {
		String str = "";
		String _filepath;
		if (filepath == null || filepath.length() == 0) {
			_filepath = TEST2;
		} else {
			_filepath = filepath;
		}
		File f = new File(_filepath);
		try (OutputStream outputStream = new FileOutputStream(f, true)) {
			if (content == null) {
				str = "ABC哈哈";
			} else {
				str = content;
			}
			byte[] b = str.getBytes();
			outputStream.write(b);
			// byte[] c = new byte[] { -27, -109, -120 };
			// System.out.println(new String(c));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 向文件写入字符串，用字符流，有缓存，速度快
	 */
	public void writeStrToFile_buffer(String content) {
		String str = "";
		File f = new File(TEST22);
		try (BufferedWriter out = new BufferedWriter(new FileWriter(f, true))) {
			if (content == null) {
				str = "ABC哈哈";
			} else {
				str = content;
			}
			out.write(str);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 使用字符流写文件
	 * 
	 * @param content
	 * @param filepath
	 */
	public void writeToFile_noBuffer(String content, String filepath) {
		File file;
		if (filepath == null || filepath.length() == 0) {
			file = new File(TEST22);
		} else {
			file = new File(filepath);
		}
		try (Writer writer = new FileWriter(file, true)) {

			writer.write(content);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 使用OutputStreamWriter将字节流转换为字符流
	 * 
	 * @param content
	 * @param filepath
	 */
	public void writerToFileII(String content, String filepath) {

		File file;
		if (filepath == null || filepath.length() == 0) {
			file = new File(TEST22);
		} else {
			file = new File(filepath);
		}

		try (Writer out = new OutputStreamWriter(new FileOutputStream(file, true))) {
			out.write(content);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 字节流方式读取文件<br>
	 * 知道文件大小
	 * 
	 * @param filepath
	 */
	public void readFile_Stream_test1(String filepath) {

		File file;

		if (filepath == null || filepath.length() == 0) {
			file = new File(TEST2);
		} else {
			file = new File(filepath);
		}

		try (InputStream inputStream = new FileInputStream(file)) {
			long fileLen = file.length();
			System.out.println("文件长度:" + fileLen);
			byte[] b = new byte[(int) fileLen];

			// 逐个字节地读取
			// for(int i=0;i<b.length;i++){
			// b[i]=(byte)inputStream.read();
			// }
			int len = inputStream.read(b);
			System.out.println("读取文件内容长度=" + len);
			System.out.println(new String(b, 0, len));
			System.out.println(new String(b)); // 简易版，这样会输出整个byte[]的内容，包括后面的空白
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 字节流方式读取文件<br>
	 * 不知道文件大小/内容长度的情况下
	 * 
	 * @param filepath
	 */
	public void readFile_Stream_test2(String filepath) {

		File file;

		if (filepath == null || filepath.length() == 0) {
			file = new File(TEST2);
		} else {
			file = new File(filepath);
		}
		try (InputStream inputStream = new FileInputStream(file)) {
			byte[] b = new byte[9999999];

			int temp = 0; // 每次读取回来的byte
			int count = 0;

			while ((temp = inputStream.read()) != -1) {
				b[count++] = (byte) temp;
			}

			System.out.println(new String(b, 0, count));
			System.out.println(new String(b)); // 简易版，这样会输出整个byte[]的内容，包括后面的空白
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 字节流方式读取文件<br>
	 * 知道文件大小
	 * 
	 * @param filepath
	 */
	public String readFile_Stream(String filepath) {

		File file;

		if (filepath == null || filepath.length() == 0) {
			file = new File(TEST2);
		} else {
			file = new File(filepath);
		}

		try (InputStream inputStream = new FileInputStream(file)) {
			long fileLen = file.length();
			System.out.println("文件长度:" + fileLen);
			byte[] b = new byte[(int) fileLen];

			int len = inputStream.read(b);
			System.out.println("读取文件内容长度=" + len);
			System.out.println(new String(b)); // 简易版，这样会输出整个byte[]的内容，包括后面的空白

			return new String(b);

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 字符流方式读取文件内容<br>
	 * 知道文件大小
	 * 
	 * @param filepath
	 */
	public String readFile_Writer(String filepath) {
		File file;
		if (filepath == null || filepath.length() == 0) {
			file = new File(TEST2);
		} else {
			file = new File(filepath);
		}

		try (Reader reader = new FileReader(file)) {

			char[] content = new char[(int) file.length()];
			reader.read(content);
			System.out.println(content.toString()); // char[]长度刚好才能用toString转换，否则用new
													// String(char[])
			return content.toString();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 字符流方式读取文件内容<br>
	 * 不知道文件大小
	 * 
	 * @param filepath
	 */
	public String readFile_WriterII(String filepath) {
		File file;
		if (filepath == null || filepath.length() == 0) {
			file = new File(TEST2);
		} else {
			file = new File(filepath);
		}

		try (Reader reader = new FileReader(file)) {

			char[] content = new char[10000]; // 每次读取大小
			int readCount = 0;
			int readChar = 0;

			while ((readChar = reader.read()) != -1) {
				content[readCount++] = (char) readChar;
			}

			System.out.println("读取字符数=" + readCount);

			String retContent = new String(content);

			System.out.println(retContent);
			return new String(retContent);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将字节输入流转换为字符输入流
	 * 
	 * @param filepath
	 */
	public String readFile_WriterIII(String filepath) {

		File file;

		if (filepath == null || filepath.length() == 0) {
			file = new File(TEST2);
		} else {
			file = new File(filepath);
		}

		char[] readContent = new char[(int) file.length()];

		try {
			InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));

			inputStreamReader.read(readContent);

			String retString = new String(readContent);

			// System.out.println(retString);

			inputStreamReader.close();

			return retString;

		} catch (IOException e) {

			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 使用内存操作流
	 */
	public void readAndWrite_CacheTest() {
		String str = readFile_WriterIII(TEST2);
		ByteArrayInputStream bis = new ByteArrayInputStream(str.getBytes());
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int temp = 0;
		while ((temp = bis.read()) != -1) {
			bos.write(temp);
		}
		
		// 上面写完之后，再将流转换为String即可
		String outString = bos.toString();
		try {
			bis.close();
			bos.close();
			System.out.println(outString);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		String pathname = "/Users/EvaZis/Documents/NewBird^^/Missions/bugs/1111.txt";
		FileTest1 f1 = new FileTest1();
		StringBuilder temp = new StringBuilder();
		// f1.readFile(pathname);
		// f1.createFile();
		// f1.delFile();
		// f1.copyFile();
		// f1.writeStrToFile_Byte(null);
		// f1.writeStrToFile_buffer("用缓存了!");
		// f1.readFile_Stream2(FILETOWRITE);

		// 读取文件内容--写到某个文件
		// temp = temp.append(f1.readFile_Stream(TEST2));
		// f1.writeToFile_noBuffer(temp.toString(),TEST22);

		// 字符流方式读取文件
		// String read = f1.readFile_WriterII(TEST2);
		// f1.writerToFileII(read, TEST22);

		// InputStreamReader方式读取文件
		// f1.readFile_WriterIII(TEST2);

		// 使用ByteArrayInputStream/ByteArrayOutputStream
		f1.readAndWrite_CacheTest();

	}

}
