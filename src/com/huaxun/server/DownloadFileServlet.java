package com.huaxun.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadFileServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("---------downloadfile-get-------------");
		// 处理中文乱码问题解决办法
		request.setCharacterEncoding("utf-8");
		String filename = new String(request.getParameter("filename").getBytes("iso-8859-1"), "UTF-8");
		System.out.println("-------filename=" + filename + "--------");
		response.setCharacterEncoding("utf-8");
		response.setContentType("image/png; charset=UTF-8");
		OutputStream out = response.getOutputStream();
		File file = new File("e:/topnews/usericon/" + filename);
		/* 取得文件的FileInputStream */
		FileInputStream in = new FileInputStream(file);
		/* 设置每次写入1024bytes */
		int bufferSize = 1024;
		byte[] buffer = new byte[bufferSize];
		int length = -1;
		/* 从文件读取数据至缓冲区 */
		while ((length = in.read(buffer)) != -1) {
			/* 将资料写入DataOutputStream中 */
			out.write(buffer, 0, length);
		}
		/* close streams */
        in.close();  
        out.flush();
        out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("---------downloadfile-post-------------");
		// 处理中文乱码问题解决办法
		request.setCharacterEncoding("utf-8");
		String filename = new String(request.getParameter("filename").getBytes("iso-8859-1"), "UTF-8");
		System.out.println("-------" + filename + "--------");
		response.setCharacterEncoding("utf-8");
	//	response.setContentType("text/json; charset=UTF-8");
		OutputStream out = response.getOutputStream();
		File file = new File("e:/topnews/usericon/" + filename);
		/* 取得文件的FileInputStream */
		FileInputStream in = new FileInputStream(file);
		/* 设置每次写入1024bytes */
		int bufferSize = 1024;
		byte[] buffer = new byte[bufferSize];
		int length = -1;
		/* 从文件读取数据至缓冲区 */
		while ((length = in.read(buffer)) != -1) {
			/* 将资料写入DataOutputStream中 */
			out.write(buffer, 0, length);
		}
		/* close streams */
        in.close();  
        out.flush();
        out.close();
	}


}
