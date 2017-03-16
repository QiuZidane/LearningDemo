package log;

import java.io.ByteArrayOutputStream;
import java.io.File;
//import java.util.logging.Handler;
//import java.util.logging.Level;
//import java.util.logging.LogRecord;
//import java.util.logging.Logger;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;

import io.FileTest1;
//import org.apache.log4j.xml.DOMConfigurator;
import org.apache.log4j.*;

/**
 *
 * log4j三大组件：Loggers/Appenders/Layouts <br>
 * Loggers:日志处理器 <br>
 * Appenders:日志输出的地方 <br>
 * Layouts:日志输出格式 <br>
 *
 */
public class Log4jTest {

	static Logger logger = Logger.getLogger(Log4jTest.class.getName());

	// 试验系统自带
	public void sysLogger() {

		java.util.logging.Logger syslogger = java.util.logging.Logger.getLogger("logtest"); // 获取Logger
		syslogger.setLevel(Level.ALL); // 设置级别
		// 增加处理器
//		Handler handler1 = new MyHandler();	
		//logger.addHandler(handler1); // logger.removeHandler(handler1);

		Exception e = new Exception("exceptiontest");
		syslogger.log(Level.WARNING, "Exception in echo protocol", e);
		syslogger.info("hahaha");
		syslogger.finer("this is finer");

	}

	public void log4jTest() {

		// logger.log(Level.WARNING, logger.getClass().getName());
		// logger.log(Level.WARNING, Log4jTest.class.getName());

		// 使用缺省Log4j环境-这行貌似不是必须
		BasicConfigurator.configure();

		// 从外部获取配置文件
		PropertyConfigurator.configure("./src/log/Log4j.properties"); // 使用Java配置文件格式进行配置
		// DOMConfigurator.configure(""); // 读取XML形式的配置文件

		// Logger logger = Logger.getRootLogger();
		logger.debug("debug");
		logger.error("error");
		logger.error(getStackTrace());

	}

	public String getStackTrace() {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		Exception exception = new Exception("this is an Exception");

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);

		exception.printStackTrace(ps);
		exception.printStackTrace(pw);

		// System.out.println(sw.toString());
		// System.out.println(new String(os.toByteArray()));

		return new String(os.toByteArray());
	}

	public static void main(String[] args) {

		Log4jTest log4jTest = new Log4jTest();
		log4jTest.log4jTest();
		// log4jTest.sysLogger();

	}

}

/*
 * class MyHandler extends Handler {
 * 
 * @Override public void publish(LogRecord record) { // TODO Auto-generated
 * method stub System.out.println("loggername=" + record.getLoggerName());
 * System.out.println("message=" + record.getMessage());
 * 
 * FileTest1 f1 = FileTest1.getFileHander();
 * f1.writeStrToFile_Byte(record.getMessage(), null);
 * 
 * }
 * 
 * @Override public void flush() {
 * 
 * }
 * 
 * @Override public void close() throws SecurityException { // TODO
 * Auto-generated method stub
 * 
 * }
 * 
 * }
 */
