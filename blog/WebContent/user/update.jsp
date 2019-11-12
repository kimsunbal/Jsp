<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script src="/blog/js/jquery-3.2.1.min.js"></script>
<%@ include file="/include/nav.jsp"%>
<!--================Contact Area =================-->
<section class="contact_area">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<form class="row contact_form" action="/blog/user?cmd=update" method="POST" onsubmit="return validateCheck()" enctype="multipart/form-data">
					<input type="hidden" name="id" value="${sessionScope.user.id}" />
					<!-- 아이디 시작 -->
					<div class="col-md-1"></div>
					<div class="col-md-2">아이디</div>
					<div class="col-md-5">
						<input type="hidden" name="username" value="${sessionScope.user.username}" />
						<div class="form-group">${sessionScope.user.username}</div>
					</div>
					<div class="col-md-4">
						<span id="username_input" style="font-size: 10px; color: red; font-weight: bold;"></span>
					</div>

					<!-- 아이디 끝 -->

					<!-- 한칸 띄우기 -->
					<div class="col-md-12">
						<br>
					</div>
					<!-- 한칸 띄우기 -->

					<div class="col-md-1"></div>
					<div class="col-md-2">비밀번호</div>
					<div class="col-md-5">
						<div class="form-group">
							<input type="password" class="form-control" id="password" name="password" placeholder="비밀번호를 입력하세요." required="required" maxlength="20" onkeyup="passwordcheck()">
						</div>
					</div>
					<div class="col-md-4"></div>

					<div class="col-md-1"></div>
					<div class="col-md-2">비밀번호 확인</div>
					<div class="col-md-5">
						<div class="form-group">
							<input type="password" class="form-control" id="passwordCheck" name="passwordCheck" placeholder="동일한 비밀번호를 입력하세요." required="required" maxlength="20" onkeyup="passwordcheck()">
						</div>

					</div>
					<div class="col-md-4">
						<span id="password_input" style="font-size: 12px; font-weight: bold;"></span>
					</div>

					<!-- 한칸 띄우기 -->
					<div class="col-md-12">
						<br>
					</div>
					<!-- 한칸 띄우기 -->

					<!-- 이메일 시작 -->

					<div class="col-md-1"></div>
					<div class="col-md-2">이메일</div>
					<div class="col-md-5">
						<input type="hidden" name="email" value="${sessionScope.user.email}" />
						<div class="form-group">${sessionScope.user.email}</div>
					</div>
					<div class="col-md-4"></div>
					<!-- 이메일 끝 -->

					<!-- 한칸 띄우기 -->
					<div class="col-md-12">
						<br>
					</div>
					<!-- 한칸 띄우기 -->

					<div class="col-md-1"></div>
					<div class="col-md-2">프로필 사진</div>
					<div class="col-md-2">
						<img src="${sessionScope.user.userProfile}" id="img__wrap" width="100px" height="100px">
					</div>
					<div class="col-md-7"></div>


					<!-- 한칸 띄우기 -->
					<div class="col-md-12">
						<br>
					</div>
					<!-- 한칸 띄우기 -->

					<div class="col-md-3"></div>
					<div class="col-md-2">
						<input id="img__input" type="file" name="userProfile" />
					</div>
					<div class="col-md-7"></div>

					<!-- 한칸 띄우기 -->
					<div class="col-md-12">
						<br>
					</div>
					<!-- 한칸 띄우기 -->

					<!-- 도로명 주소 시작 -->

					<div class="col-md-1"></div>
					<div class="col-md-2">주소</div>
					<div class="col-md-7">
						<div class="form-group">
							<input type="text" value="${sessionScope.user.address}" class="form-control" id="roadFullAddr" name="address" placeholder="도로명 주소가 자동 입력됩니다." readonly>
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-group float-right">
							<a style="cursor: pointer;" class="blog_btn" onClick="goPopup()">주소찾기</a>
						</div>
					</div>
					<!-- 도로명 주소 끝 -->

					<div class="col-md-12 text-right">
						<button type="submit" value="submit" class="btn submit_btn">Update</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>
<!--================Contact Area =================-->
<br />
<br />

<script>
	function goPopup() {
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		var pop = window.open("/blog/juso/jusoPopup.jsp", "pop",
				"width=570,height=420, scrollbars=yes, resizable=yes");
	}

	//주소 입력 버튼을 클릭하면 콜백된다.
	function jusoCallBack(roadFullAddr) {
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		var juso = document.querySelector('#roadFullAddr');
		juso.value = roadFullAddr;
	}

	function validateCheck() {
		var password = document.querySelector('#password').value;
		var passwordCheck = document.querySelector('#passwordCheck').value;

		var roadFullAddr = document.querySelector('#roadFullAddr').value;

		if (roadFullAddr == '') {
			alert('주소를 입력하세요.');
			return false;
		}
		if (password === passwordCheck) {
			console.log('비밀번호가 동일합니다.');
			return true;
		} else {
			console.log('비밀번호가 틀렸습니다.');
			alert('비밀번호가 동일하지 않습니다. 다시 입력해주세요.');
			return false;
		}

	}

	function passwordcheck() {
		var password = document.querySelector('#password').value;
		var passwordCheck = document.querySelector('#passwordCheck').value;
		if (password === passwordCheck) {
			document.querySelector("#password_input").innerHTML = "<span style='color:Green'>비밀번호가 동일합니다</span>";
			return true;
		} else {
			console.log('비밀번호가 틀렸습니다.');
			document.querySelector("#password_input").innerHTML = "<span style='color:Red'>비밀번호가 동일하지 않습니다. 다시 입력해주세요</span>";
			return false;
		}
	}

	$("#img__input").on("change", handleImgFile);
	$("#img__input").val($(sessionScope.user.userProfilepath));

	function handleImgFile(e) {
		console.log(e);
		console.log(e.target);
		console.log(e.target.files);
		console.log(e.target.files[0]);
		var f = e.target.files[0];

		if (!f.type.match("image.*")) {
			console.log("이미지 타입입니다.");
			return;
		}

		var reader = new FileReader();
		reader.onload = function(e) {
			console.log("========================");
			console.log(e.target);
			console.log(e.target.result);
			$("#img__wrap").attr("src", e.target.result);
		}
		reader.readAsDataURL(f);
	}
</script>


<%@ include file="/include/footer.jsp"%>