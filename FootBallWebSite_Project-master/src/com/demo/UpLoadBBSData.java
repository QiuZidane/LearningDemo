package com.demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpLoadBBSData
 */
@WebServlet("/UpLoadBBSData")
public class UpLoadBBSData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpLoadBBSData() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html; charset=utf-8");
		System.err.println(request.getParameter("data"));
		
//		System.err.println(request.getServletContext().getRealPath("/"));
//		
//		// 获取当前web应用的绝对路径 
//		String absolutePath = request.getServletContext().getRealPath("/");
//		// 在此路径上面创建文件
//		File bbsdataFile = new File(absolutePath+"bbsdata.txt");
//		
////		System.out.println("bbsdataFile="+bbsdataFile);
//		
//		try {
//			createFile(bbsdataFile);
//			writeTxtFile(bbsdata, bbsdataFile);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		

	}


	/** 
	 * 创建文件 
	 * @param fileName 
	 * @return 
	 */  
	public static boolean createFile(File fileName)throws Exception{  
		boolean flag=false;  
//		System.out.println("开始创建文件");
		try{  
			if(!fileName.exists()){  
				fileName.createNewFile();  
				flag=true;  
//				System.out.println("创建文件成功");
			}  
		}catch(Exception e){  
			e.printStackTrace();  
//			System.err.println("创建文件失败");
		}  
		System.out.println("flag="+flag);
		return flag;  
	} 

	/** 
	 * 读TXT文件内容 
	 * @param fileName 
	 * @return 
	 */  
	public static String readTxtFile(File fileName)throws Exception{  
		String result=null;  
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

	// 写文件
	public static boolean writeTxtFile(String content,  File fileName)throws Exception{  
		RandomAccessFile mm=null;  
		boolean flag=false;  
		FileOutputStream o=null;
		try {  
			o = new FileOutputStream(fileName, true);		
			o.write(content.getBytes("UTF-8"));
			o.close();  
			//   mm=new RandomAccessFile(fileName,"rw");  
			//   mm.writeBytes(content);  
			flag=true;  
		} catch (Exception e) {  
			// TODO: handle exception  
			e.printStackTrace();  
		}finally{  
			if(mm!=null){  
				mm.close();  
			}  
		}  
		return flag;  
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
