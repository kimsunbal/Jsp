<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script>
	let replyJS ={
			id:null,
			boardId:5,
			userId:3,
			content:"글이 참 좋습니다.",
			createDate: null
	}
	
	$.ajax({
		type : "POST",
		url : "http://localhost:8000/blog/test/reply",
		dataType : "text",
		contentType : "application/json; charset=utf-8",
		data : JSON.stringify(replyJS),
		success : function(data) {
			alert('data 전송 성공'+data);},
		error : function() {
			alert('실패');}
	});
</script>
</head>
<body>

</body>
</html>