package com.demo;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetBBSData
 */
@WebServlet("/GetBBSData")
public class GetBBSData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetBBSData() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html; charset=utf-8");
		
		// 获取当前web应用的绝对路径 
//		String absolutePath = request.getServletContext().getRealPath("/");
////		System.err.println("absolutePath="+absolutePath+"\n");
//		// 在此路径上面创建文件
//		File bbsdataFile = new File(absolutePath+"bbsdata.txt");
		String retJsonStr;
		try {
			retJsonStr = ""
					+"{"
					+ "\"1900-04-11:22:11:33\" : { "
					+"\"imgsrc\": \"http://127.0.0.1:8020/WebSiteSrc/bbs/img/face1.png\",  "
					+"\"username\": \"zidane\" , "
					+"\"msginfo\": \"很好很好啊哈哈哈哈哈！！！！\" , "
					+"\"smtimes\": \"03月28日 21:22\"  "
					+ "}"
					+ ","
					+ "\"1900-04-11:22:11:43\" :  { "
					+"\"imgsrc\": \"http://127.0.0.1:8020/WebSiteSrc/bbs/img/face2.png\",  "
					+"\"username\": \"even\",  "
					+"\"msginfo\": \"的路口附近的谁离开房间了大开杀戒哈哈！！！！\",  "
					+"\"smtimes\": \"03月29日 21:22\"  "
					+ "}"
					+ ","
					+ "\"1900-04-11:22:11:53\" :  { "
					+"\"imgsrc\": \"http://127.0.0.1:8020/WebSiteSrc/bbs/img/face8.gif\",  "
					+"\"username\": \"even\",  "
					+"\"msginfo\": \"的路口附近的似懂非懂是辅导费！！\" , "
					+"\"smtimes\": \"03月30日 21:22\"  "
					+ "}"				
					+ "}"
					+ "";
			PrintWriter out = response.getWriter();
			out.flush();
			out.write(retJsonStr.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		System.out.println("获取bbs数据");
	}

	/** 
	 * 读TXT文件内容 
	 * @param fileName 
	 * @return 
	 */  
	public static String readTxtFile(File fileName)throws Exception{  
		String result="";  
		FileReader fileReader=null;  
		BufferedReader bufferedReader=null;  
		try{  
			fileReader=new FileReader(fileName);  
			bufferedReader=new BufferedReader(fileReader);  
			try{  
				String read=null;  
				while((read=bufferedReader.readLine())!=null){  
					result=result+read+"\r\n";  
				}  
			}catch(Exception e){  
				e.printStackTrace();  
			}  
		}catch(Exception e){  
			e.printStackTrace();  
		}finally{  
			if(bufferedReader!=null){  
				bufferedReader.close();  
			}  
			if(fileReader!=null){  
				fileReader.close();  
			}  
		}  
		System.out.println("读取出来的文件内容是："+"\r\n"+result);  
		return result;  
	} 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
