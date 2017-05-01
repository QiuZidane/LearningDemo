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
 * Servlet implementation class A2UpdatePlayer
 */
@WebServlet("/A2UpdatePlayer")
public class A2UpdatePlayer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public A2UpdatePlayer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setHeader("Access-Control-Allow-Origin", "*");
		 Enumeration<String> params =  request.getParameterNames();
		 while (params.hasMoreElements()) {
			String name = (String) params.nextElement();
			String value = request.getParameter(name);
			System.out.println(name+" = "+value);
		}
		 response.setContentType("text/html; charset=utf-8");
			//测试数据，如果是zidane，返回0-注册成功, 否则返回1-注册失败
			if (true) {
				// loginFlag = 1
				HttpSession session = request.getSession();
				session.setAttribute("loginFlag", "1");
				session.setAttribute("username", "zidane");
				session.setMaxInactiveInterval(10);
				// retcode = 0
				String jsonStr = "{\"retcode\":\"0\"}";
				PrintWriter out = response.getWriter();
				out.flush();
				out.write(jsonStr.toString());			
			} else {			
				// retcode = 1
				String jsonStr = "{\"retcode\":\"1\"}";
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
