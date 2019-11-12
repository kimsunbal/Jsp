<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String path = application.getRealPath("media");


	MultipartRequest multi = new MultipartRequest(
			request,
			path,
			1024*1024*2,
			"UTF-8",
			new DefaultFileRenamePolicy() //동일한 파일명이 들어오면 파일명 뒤에 숫자를 붙임.
			); 
	
	String username = multi.getParameter("username");
	String filename = multi.getFilesystemName("userProfile");//정책에 의해서 변경된 이름
	String originFilename = multi.getOriginalFileName("userProfile");
	String contextpath = getServletContext().getContextPath();
	String filepath =contextpath+"/media/"+filename;
%>
path: <%= path %><br>
username: <%= username %><br>
filename: <%= filename %><br>
originFilename: <%= originFilename %><br>
contextpath: <%= contextpath %><br>

<img src="<%=filepath%>"/>

</body>
</html>