<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<form action="uploadAction.jsp" method="POST" enctype="multipart/form-data">
		<div>
		<img id="img__wrap">
		</div>
		username=<input type="text" name="username" /><br> userProfile=<input id="img__input" type="file" name="userProfile" /><br> <input type="submit" value="확인" /><br>
	</form>
<script src="/blog/js/jquery-3.2.1.min.js"></script>
<script>
	$("#img__input").on("change", handleImgFile);
	function handleImgFile(e){
		console.log(e);
		console.log(e.target);
		console.log(e.target.files);
		console.log(e.target.files[0]);
		var f = e.target.files[0];
		
		if(!f.type.match("image.*")){
			console.log("이미지 타입입니다.");
			return;
		}
		
		var reader = new FileReader();
		reader.onload = function(e){
			console.log("========================");
			console.log(e.target);
			console.log(e.target.result);
			$("#img__wrap").attr("src",e.target.result);
		}
		reader.readAsDataURL(f);
	}
</script>
</body>
</html>