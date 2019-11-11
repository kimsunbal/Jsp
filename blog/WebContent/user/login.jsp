<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/include/nav.jsp"%>


<!--================Contact Area =================-->
<section class="contact_area">
	<div class="container">
		<div class="col-lg-12">
			<form class="row contact_form" action="/blog/user?cmd=login" method="post">
				<div class="col-md-12">
					<div class="form-group">
						<c:choose>
							<c:when test="${empty cookie.username.value}">
								<input type="text" class="form-control" id="username" name="username" placeholder="아이디를 입력하세요">
							</c:when>
							<c:otherwise>
								<input type="text" class="form-control" id="username" name="username" placeholder="아이디를 입력하세요" value="${cookie.username.value}">
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="col-md-12">
					<div class="form-group">
						<input type="password" class="form-control" id="password" name="password" placeholder="비밀번호를 입력하세요">
					</div>
				</div>

				<div class="col-md-12 text-right">
					<label><input type="checkbox" name="rememberMe" /> Remember Me</label>
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



<%@ include file="/include/footer.jsp"%>