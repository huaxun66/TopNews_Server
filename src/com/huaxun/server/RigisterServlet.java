package com.huaxun.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huaxun.dao.UserDao;

public class RigisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("---------register-get-------------");
		// 处理中文乱码问题解决办法
		request.setCharacterEncoding("utf-8");
		String username = new String(request.getParameter("username").getBytes("iso-8859-1"), "UTF-8");
		String password = new String(request.getParameter("password").getBytes("iso-8859-1"), "UTF-8");
		String email = new String(request.getParameter("email").getBytes("iso-8859-1"), "UTF-8");
		String gender = new String(request.getParameter("gender").getBytes("iso-8859-1"), "UTF-8");
		String hasIcon = new String(request.getParameter("hasIcon").getBytes("iso-8859-1"), "UTF-8");
		System.out.println("-------" + username + password + email + gender + hasIcon + "--------");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/xml; charset=utf-8");
		PrintWriter out = response.getWriter();
		UserDao dao = new UserDao();
		try {
			if (dao.findUserByName(username)!=null) {
				out.print("用户已经存在");
			} else {
				boolean containUserIcon = hasIcon.equals("true")? true:false;
				String result = dao.createUser(username, password, email, gender, containUserIcon);
				out.print(result);
			}
		} catch (Exception e) {
			out.print("注册失败");
			e.printStackTrace();
		}
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("---------register-post-------------");
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String gender = new String(request.getParameter("gender").getBytes("iso-8859-1"), "UTF-8");
		String hasIcon = request.getParameter("hasIcon");
		System.out.println("-------" + username + password + email + gender + hasIcon + "--------");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/xml; charset=utf-8");
		PrintWriter out = response.getWriter();
		UserDao dao = new UserDao();
		try {
			if (dao.findUserByName(username)!=null) {
				out.print("用户已经存在");
			} else {
				boolean containUserIcon = hasIcon.equals("true")? true:false;
				String result = dao.createUser(username, password, email, gender, containUserIcon);
				out.print(result);
			}
		} catch (Exception e) {
			out.print("注册失败");
			e.printStackTrace();
		}
		out.flush();
		out.close();
	}

}
