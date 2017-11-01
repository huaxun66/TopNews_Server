package com.huaxun.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.huaxun.bean.UserBean;
import com.huaxun.dao.UserDao;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("---------login-get-------------");
		// 处理中文乱码问题解决办法
		request.setCharacterEncoding("utf-8");
		String username = new String(request.getParameter("username").getBytes("iso-8859-1"), "UTF-8");
		String password = new String(request.getParameter("password").getBytes("iso-8859-1"), "UTF-8");
		System.out.println("-------" + username + password + "--------");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		UserDao dao = new UserDao();
		try {
			UserBean bean = dao.userLogin(username, password);
			String json;
			if (bean != null) {
				Gson gson = new Gson();
				json = gson.toJson(bean);
			} else {
				json = "登录失败";
			}
			out.print(json);
		} catch (Exception e) {
			out.print("登录失败");
			e.printStackTrace();
		}
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("---------login-post-------------");
		// 处理中文乱码问题解决办法
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("-------" + username + password + "--------");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/xml; charset=UTF-8");
		PrintWriter out = response.getWriter();
		UserDao dao = new UserDao();
		try {
			UserBean bean = dao.userLogin(username, password);
			String json;
			if (bean != null) {
				Gson gson = new Gson();
				json = gson.toJson(bean);
			} else {
				json = "登录失败";
			}
			out.print(json);
		} catch (Exception e) {
			out.print("登录失败");
			e.printStackTrace();
		}
		out.flush();
		out.close();
	}

}
