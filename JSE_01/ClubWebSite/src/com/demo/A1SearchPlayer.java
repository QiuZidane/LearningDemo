package com.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 测试数据，用于测试A1接口返回
 */
@WebServlet("/A1SearchPlayer")
public class A1SearchPlayer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public A1SearchPlayer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html; charset=utf-8");
		//		验密码是否正确即可
		if (request.getParameter("name").equals("zidane".toString()) || request.getParameter("name").equals("kfzx".toString())) {
			System.out.println("请求zidane的数据");						
			String jsonStr = ""
					+ "{\"playername\":\"zidane\","
					+ "\"speed\":\"81\","
					+ "\"strength\":\"61\","
					+ "\"stamina\":\"85\","
					+ "\"health\":\"91\","
					+ "\"passing\":\"85\","
					+ "\"touching\":\"85\","
					+ "\"dribbling\":\"85\","
					+ "\"heading\":\"75\","
					+ "\"minding\":\"90\","
					+ "\"rating\":\"90\","
					+ "\"teamwork\":\"80\","
					+ "\"shoot\":\"80\","
					+ "\"offtheball\":\"85\","
					+ "\"creativity\":\"85\","
					+ "\"taking\":\"60\","
					+ "\"marking\":\"40\","
					+ "\"positioning\":\"60\","
					+ "\"department\":\"广州研发支持部\"}";
			PrintWriter out = response.getWriter();
			out.flush();
			out.write(jsonStr.toString());
			//			System.out.println("输出="+jsonStr.toString());	

		} else if (request.getParameter("name").equals("player0".toString())) {
			System.out.println("请求player0的数据");						
			String jsonStr = ""
					+ "{\"playername\":\"player0\","
					+ "\"speed\":\"71\","
					+ "\"strength\":\"71\","
					+ "\"stamina\":\"75\","
					+ "\"health\":\"71\","
					+ "\"passing\":\"75\","
					+ "\"touching\":\"75\","
					+ "\"dribbling\":\"65\","
					+ "\"heading\":\"65\","
					+ "\"minding\":\"60\","
					+ "\"rating\":\"60\","
					+ "\"teamwork\":\"66\","
					+ "\"shoot\":\"70\","
					+ "\"offtheball\":\"75\","
					+ "\"creativity\":\"65\","
					+ "\"taking\":\"70\","
					+ "\"marking\":\"60\","
					+ "\"positioning\":\"70\","
					+ "\"department\":\"广州开发一部\"}";
			PrintWriter out = response.getWriter();
			out.flush();
			out.write(jsonStr.toString()); 
		} else {

			System.err.println("请求用户名错误");

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
