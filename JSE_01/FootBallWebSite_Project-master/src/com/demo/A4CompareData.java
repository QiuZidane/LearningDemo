package com.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class A4CompareData
 */
@WebServlet("/A4CompareData")
public class A4CompareData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public A4CompareData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html; charset=utf-8");
		System.out.println("请求compare数据");						
		String jsonStr = ""
				+ "{\"retCode\" : \"0\","
				+ "\"player0\" : "				
				+ "{\"totalabi\":\"81\","
				+ "\"body_abi\":\"71\","
				+ "\"tech_abi\":\"81\","
				+ "\"spec_abi\":\"91\","
				+ "\"attack_abi\":\"81\","
				+ "\"defence_abi\":\"61\","
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
				+ "\"positioning\":\"60\"},"
				+ ""				
				+ "\"player6\" : "				
				+ "{\"totalabi\":\"84\","
				+ "\"body_abi\":\"87\","
				+ "\"tech_abi\":\"75\","
				+ "\"spec_abi\":\"84\","
				+ "\"attack_abi\":\"81\","
				+ "\"defence_abi\":\"88\","
				+ "\"speed\":\"81\","
				+ "\"strength\":\"71\","
				+ "\"stamina\":\"75\","
				+ "\"health\":\"81\","
				+ "\"passing\":\"65\","
				+ "\"touching\":\"95\","
				+ "\"dribbling\":\"65\","
				+ "\"heading\":\"79\","
				+ "\"minding\":\"80\","
				+ "\"rating\":\"94\","
				+ "\"teamwork\":\"84\","
				+ "\"shoot\":\"84\","
				+ "\"offtheball\":\"75\","
				+ "\"creativity\":\"75\","
				+ "\"taking\":\"70\","
				+ "\"marking\":\"50\","
				+ "\"positioning\":\"65\"}}";
		PrintWriter out = response.getWriter();
		out.flush();
		out.write(jsonStr.toString());
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
