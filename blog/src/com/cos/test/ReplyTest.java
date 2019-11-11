package com.cos.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import blog.model.Comment;

@WebServlet("/test/reply")
public class ReplyTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReplyTest() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain; charset=UTF-8");// MINE

//		1. json data 받기, getReader()
		BufferedReader in = request.getReader();
		String line = null;
		StringBuffer sb = new StringBuffer();
		while ((line = in.readLine()) != null) {
			sb.append(line);
		}
		String jsonString = sb.toString();
//		2. json data sysout으로 출력해보기
		System.out.println(jsonString);
//		3. json data를 java object로 변화(Gson lib, fromJson())
		Gson gson = new Gson();
		Comment r = gson.fromJson(jsonString, Comment.class);
//		4. java object를 sysout으로 출력해보기
		System.out.println(r.getId());
		System.out.println(r.getBoardId());
		System.out.println(r.getUserId());
		System.out.println(r.getContent());
		System.out.println(r.getCreateDate());
		PrintWriter out = response.getWriter();
		out.println(50);
		out.flush();
	}

}
