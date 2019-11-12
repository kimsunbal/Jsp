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

<c:if test="${empty sessionScope.user.id}">
	<script>
		location.href = "/blog/error/NoLogin.jsp"
	</script>
</c:if>
<c:if test="${sessionScope.user.emailCheck ne 1}">
	<script>
		location.href = "/blog/error/NoEmailAuth.jsp"
	</script>
</c:if>

<!--================Contact Area =================-->
<section class="contact_area">
	<div class="container">
		<div class="col-lg-12">
			<form class="row contact_form" action="/blog/board?cmd=write"
				method="post" id="contactForm" novalidate="novalidate">
				<div class="col-md-12">
					<div class="form-group">
						<input type="text" class="form-control" id="subject" name="title"
							placeholder="Title">
					</div>
				</div>
				<div class="col-md-12">
					<div class="form-group">
						<textarea class="form-control" name="content" id="summernote"
							rows="20" placeholder="Content"></textarea>
					</div>
				</div>
				<div class="col-md-12 text-right">
					<button type="submit" value="submit" class="btn submit_btn">Posting</button>
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