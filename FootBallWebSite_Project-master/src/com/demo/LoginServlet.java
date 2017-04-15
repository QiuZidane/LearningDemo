package com.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html; charset=utf-8");
		if (request.getParameter("name").equals("zidane".toString())) {	
//			if (request.getParameter("password").equals("1234".toString())) {	
			
			if (true) {	//加密了，这里不做处理	
		
				System.out.println("正确");						
				String jsonStr = ""
						+ "{\"retcode\":\"0\",\"photo\":\"face11.png\"}";
				PrintWriter out = response.getWriter();
				out.flush();
				out.write(jsonStr.toString());			
			} else {
				System.out.println("密码错误");
				String jsonStr = ""
						+ "{\"retcode\":\"1\"}";
				PrintWriter out = response.getWriter();
				out.flush();
				out.write(jsonStr.toString());	
			}
		} else {			
			System.err.println("不存在用户名"+request.getParameter("name"));
			String jsonStr = ""
					+ "{\"retcode\":\"2\"}";
			PrintWriter out = response.getWriter();
			out.flush();
			out.write(jsonStr.toString());	

		}

	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
