<%@page import="java.io.PrintWriter"%>
<%@page import="blog.util.SHA256"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//code값 받기
	String code = request.getParameter("code");
	String email = request.getParameter("email");

	System.out.println("email");
	System.out.println(email);
	//return code 값이랑 send code 값을 비교해서 동일하면
	System.out.println("code");
	System.out.println(code);
	boolean rightCode = SHA256.getEncrypt(email, "cos").equals(code) ? true : false;
	System.out.println("rightCode");
	System.out.println(SHA256.getEncrypt(email, "cos"));
	System.out.println(rightCode);

	PrintWriter script = response.getWriter();
	if (rightCode) {
		//DB에 칼럼 emailCheck(Number)    ->      1 = 인증, 0 = 미인증     인증되면 1을 update
		script.println("<script>");
		script.println("alert('이메일 인증에 성공하였습니다.')");
		script.println("location.href='/blog/api/gmailCheck?email="+email+"'"); //DB 업데이트 필요
		script.println("</script>");
	} else {
		script.println("<script>");
		script.println("alert('이메일 인증을 실패하였습니다.')");
		script.println("location.href='error.jsp'"); //login 페이지로 보내자
		script.println("</script>");
	}

	//인증완료  ->  로그인 페이지 이동

	//미인증  ->  에러페이지 이동
%>