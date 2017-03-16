package file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import exception.ExceptionTest;

public class FileUtil {

	/**
	 * 根据类名返回类路径-在电脑中的路径
	 * 
	 * @param classname
	 *            记得连包名一起传递进来
	 * @return
	 */
	@Deprecated
	public static String getClassPath(String classname) {

		String classPath;

		try {
			classPath = Class.forName(classname).getResource("").getFile();
			return classPath;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 通过URI方式查找路径<br>
	 * 根据类名返回类路径-在电脑中的路径<br>
	 * 注意！！这个获得的是bin路径，即编译后.clss对应目录的路径
	 * 
	 * @param classname
	 *            记得连包名一起传递进来
	 * @return
	 */
	public static String getClassPathByUri(String classname) {

		String classPath;

		try {

			URI uri = new URI(Class.forName(classname).getResource("").getFile());
			classPath = uri.getPath();

			return classPath;

		} catch (Exception e) {

			StringWriter out = new StringWriter();
			e.printStackTrace(new PrintWriter(out));

			// 记录到log.txt中
			String logpath = getCurrentProjectPath("logs/log.txt");
			writeStrToFile_buffer(out.toString(), logpath);
			System.err.println("classname-->" + classname + "|获取失败，请查看log/log.txt");
			// e.printStackTrace();

			return null;
		}

	}

	/**
	 * 
	 * 向文件写入字符串<br>
	 * 增量写入
	 * 
	 */
	public static void writeStrToFile_buffer(String content, String filepath) {
		String str = "";
		File f = new File(filepath);
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
	 * 获得当前项目路径
	 */
	public static String getCurrentProjectPath(String absolutePath) {
		// 两种方法：都是返回/Users/EvaZis/Java/Projects/JSE_Demo01
		// 这个方法最简单
		// System.out.println(System.getProperty("user.dir"));

		// 这个方法也可以
		File file = new File(absolutePath); // 获取当前项目下+相对路径的路径
		try {

			// System.out.println(file.getCanonicalPath()); //获取标准的路径
			return file.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(getClassPathByUri("file.FileUtil"));
		// getCurrentProjectPath("log/log.txt");
		
		File file = new File("temp/1.jpg");
//		System.out.println(file.lastModified());
		Date date = new Date(file.lastModified());
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		System.out.println(format.format(date));
		

	}

}
