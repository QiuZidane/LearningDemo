package com.demo;

import java.io.IOException;
import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.sun.org.apache.xpath.internal.functions.Function;

/**
 * Servlet implementation class A3GetPlayerData
 */
@WebServlet("/A3GetPlayerData")
public class A3GetPlayerData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public A3GetPlayerData() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html; charset=utf-8");
		String remotehost = request.getRemoteHost();
		try {

			if (request.getParameter("joinflag").equals("0")){ // 获取球员数量
				String retJsonStr = ""
						+ "{\"retCode\" : \"0\","
						+ "\"totalnum\" : \"99\"}";

				PrintWriter out = response.getWriter();
				out.flush();
				out.write(retJsonStr.toString());
				System.out.println(remotehost+":获取球员数量");

			} else if (request.getParameter("joinflag").equals("1")){ // 获取参加比赛的球员			
				String jsonStr = createData(70);
				String retJsonStr = ""
						+ "{\"retCode\" : \"0\","
						+ "\"totalnum\" : \"99\","
						+ "\"playerlist\" : " + jsonStr
						+ "}";			

				PrintWriter out = response.getWriter();
				out.flush();
				out.write(retJsonStr.toString());
				System.out.println(remotehost+":获取参加比赛的球员");
			} else {
				System.out.println(remotehost+":请求所有球员数据");
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			// TODO: handle exception
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

	protected String createData(int totalplayers) {

		String playerDataJson = "{";
		String team = "";
		int captain = (int)(Math.random()*100) > 90 ? 1 : 0;
		for (int i = 0 ; i<totalplayers; i++) {
			captain = (int)(Math.random()*100) > 90 ? 1 : 0;
			String name = "player"+ i; 
			String department = "";
			if (i%11==0){
				department="广州测试部";
				team="A队";
			}else if (i%23==0){
				department="其他机构";
				team="B队";
			}else if (i%33==0){
				department="广州海外支持部";
				team="C队";
			}else if (i%13==0){
				department="广州研发支持部";
				team="D队";
			}else if (i%16==0){
				department="北京研发部";
				team="A队";
			}else if (i%6==0){
				department="广州测试部";
				team="B队";
			} else if(i%2==0) {
				department="广州开发一部";
				team="C队";
			}else if(i%3==0) {
				department="广州开发三部";
				team="D队";
			}else if(i%5==0) {
				department="广州开发二部";
				team="A队";
			}else{
				department="广州研发支持部";
				team="D队";
			}




			playerDataJson += ""
					+ "\""+name+"\" : {" 	
					+ "\"body_abi\":\""+(int)(Math.random()*100)+"\","
					+ "\"tech_abi\":\""+(int)(Math.random()*100)+"\","
					+ "\"spec_abi\":\""+(int)(Math.random()*100)+"\","
					+ "\"attack_abi\":\""+(int)(Math.random()*100)+"\","
					+ "\"defence_abi\":\""+(int)(Math.random()*100)+"\","
					+ "\"team\":\""+team+"\","
					+ "\"captain\":\""+captain+"\","	// 队长标志
					+ "\"ability\":\""+(int)(Math.random()*100)+"\","	// 总实力
					+ "\"speed\":\""+(int)(Math.random()*100)+"\","
					+ "\"strength\":\""+(int)(Math.random()*100)+"\","
					+ "\"stamina\":\""+(int)(Math.random()*100)+"\","
					+ "\"health\":\""+(int)(Math.random()*100)+"\","
					+ "\"passing\":\""+(int)(Math.random()*100)+"\","
					+ "\"touching\":\""+(int)(Math.random()*100)+"\","
					+ "\"dribbling\":\""+(int)(Math.random()*100)+"\","
					+ "\"heading\":\""+(int)(Math.random()*100)+"\","
					+ "\"minding\":\""+(int)(Math.random()*100)+"\","
					+ "\"rating\":\""+(int)(Math.random()*100)+"\","
					+ "\"teamwork\":\""+(int)(Math.random()*100)+"\","
					+ "\"shoot\":\""+(int)(Math.random()*100)+"\","
					+ "\"offtheball\":\""+(int)(Math.random()*100)+"\","
					+ "\"creativity\":\""+(int)(Math.random()*100)+"\","
					+ "\"taking\":\""+(int)(Math.random()*100)+"\","
					+ "\"marking\":\""+(int)(Math.random()*100)+"\","
					+ "\"positioning\":\""+(int)(Math.random()*100)+"\","
					+ "\"department\":\""+department+"\"},";

		}

		playerDataJson += ""
				+ "\"zidane\" : {" 
				+ "\"body_abi\":\""+(int)(Math.random()*100)+"\","
				+ "\"tech_abi\":\""+(int)(Math.random()*100)+"\","
				+ "\"spec_abi\":\""+(int)(Math.random()*100)+"\","
				+ "\"attack_abi\":\""+(int)(Math.random()*100)+"\","
				+ "\"defence_abi\":\""+(int)(Math.random()*100)+"\","
				+ "\"team\":\""+team+"\","
				+ "\"captain\":\""+captain+"\","	// 队长标志
				+ "\"ability\":\""+(int)(Math.random()*100)+"\","	// 总实力
				+ "\"speed\":\""+(int)(Math.random()*100)+"\","
				+ "\"strength\":\""+(int)(Math.random()*100)+"\","
				+ "\"stamina\":\""+(int)(Math.random()*100)+"\","
				+ "\"health\":\""+(int)(Math.random()*100)+"\","
				+ "\"passing\":\""+(int)(Math.random()*100)+"\","
				+ "\"touching\":\""+(int)(Math.random()*100)+"\","
				+ "\"dribbling\":\""+(int)(Math.random()*100)+"\","
				+ "\"heading\":\""+(int)(Math.random()*100)+"\","
				+ "\"minding\":\""+(int)(Math.random()*100)+"\","
				+ "\"rating\":\""+(int)(Math.random()*100)+"\","
				+ "\"teamwork\":\""+(int)(Math.random()*100)+"\","
				+ "\"shoot\":\""+(int)(Math.random()*100)+"\","
				+ "\"offtheball\":\""+(int)(Math.random()*100)+"\","
				+ "\"creativity\":\""+(int)(Math.random()*100)+"\","
				+ "\"taking\":\""+(int)(Math.random()*100)+"\","
				+ "\"marking\":\""+(int)(Math.random()*100)+"\","
				+ "\"positioning\":\""+(int)(Math.random()*100)+"\","
				+ "\"department\":\"广州研发支持部\"}}";


		return playerDataJson;

	}

}
