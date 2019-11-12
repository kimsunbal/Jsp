<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.css"
	rel="stylesheet">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.js"></script>
<%@ include file="/include/nav.jsp"%>

<c:if test="${empty sessionScope.user}">
	<script>
		alert('인증되지 않은 유저입니다.');
		location.href = "/blog/user/login.jsp"
	</script>
</c:if>

<!--================Contact Area =================-->
<section class="contact_area">
	<div class="container">
		<div class="col-lg-12">
			<form class="row contact_form" action="/blog/board?cmd=update"
				method="post" id="contactForm" novalidate="novalidate">
				<input type="hidden" name="id" value="${board.id}" />
				<div class="col-md-12">
					<div class="form-group">
						<input type="text" class="form-control" id="subject" name="title"
							placeholder="Title" value="${board.title}">
					</div>
				</div>
				<div class="col-md-12">
					<div class="form-group">
						<textarea class="form-control" name="content" id="summernote"
							rows="20" placeholder="Content">${board.content}</textarea>
					</div>
				</div>
				<div class="col-md-12 text-right">
					<button type="submit" value="submit" class="btn submit_btn">Save</button>
				</div>
			</form>
		</div>
	</div>
</section>
<br>
<br>
<!--================Contact Area =================-->

<script>
	$('#summernote').summernote({
		placeholder : 'Hello stand alone ui',
		tabsize : 2,
		height : 100
	});
</script>
<%@ include file="/include/footer.jsp"%>