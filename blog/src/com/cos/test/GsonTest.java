package com.cos.test;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import blog.model.Comment;

public class GsonTest {
	@Test
	public void gsonTest1() {
		Comment comment = new Comment();
		comment.setBoardId(1);
		comment.setUserId(3);
		comment.setId(2);
		comment.setContent("댓글입니다");
		comment.setCreateDate(null);
		comment.getResponseData().setStatus("ok");
		comment.getResponseData().setStatusCode(1);
		comment.getResponseData().setStatusMsg("success");
		
		Gson gson = new Gson();
		String commentJson = gson.toJson(comment);
		System.out.println(commentJson);
	}
}
