<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/include/nav.jsp"%>
<%-- <h1>${param.page}======${count}</h1> --%>
<!--================Blog Area =================-->
<section class="blog_area">
	<div class="container">
		<div class="row">
			<div class="col-lg-8">
				<div class="blog_left_sidebar">

				<h1>${result}건이 검색되었습니다.</h1>
					<c:choose>
						<c:when test="${empty cookie.username.value}">
							<input type="hidden" name="cookieUsername" value="${sessionScope.user.id}" />
						</c:when>
						<c:otherwise>
							<input type="hidden" name="cookieUsername" value="${cookie.username.value}" />
						</c:otherwise>
					</c:choose>
					<c:forEach var="board" items="${boards}">
						<!-- 블로그시작 -->
						<article class="blog_style1">
							<div class="blog_img">
								<img class="img-fluid" src="${board.previewImg}" alt="" style="width: 100%; max-height: 269.347px">
							</div>
							<div class="blog_text">
								<div class="blog_text_inner">
									<div class="cat">
										<a class="cat_btn" href="#">${board.user.username}</a> <a href="#"><i class="fa fa-calendar" aria-hidden="true"></i> ${board.createDate}</a> <a href="#"><i class="fa fa-comments-o"
											aria-hidden="true"></i> ${board.readCount}</a>
									</div>
									<a href="#"><h4>${board.title}</h4></a>
									<div
										style="display: -webkit-box; -webkit-box-orient: vertical; text-align: left; overflow: hidden; text-overflow: ellipsis; white-space: normal; line-height: 1.2; height: 2.4em; -webkit-line-clamp: 2; margin-bottom: 20px; word-break: break-all">${board.content}</div>
									<a class="blog_btn" href="/blog/board?cmd=detail&id=${board.id}">Read More</a>
								</div>
							</div>
						</article>
						<!-- 블로그끝 -->
					</c:forEach>
					
					<!-- 페이징 start-->
					<nav class="blog-pagination justify-content-center d-flex">
						<ul class="pagination">
							<c:if test="${param.page>1}">
								<c:choose>
									<c:when test="${param.page>5}">
										<li class="page-item"><a href="/blog/board?cmd=search&content=${searchContent}&page=${param.page-5}" class="page-link" aria-label="Previous"> <span aria-hidden="true"> <span
													class="lnr lnr-chevron-left"></span>
											</span>
										</a></li>
									</c:when>
									<c:otherwise>
										<li class="page-item"><a href="/blog/board?cmd=search&content=${searchContent}&page=1" class="page-link" aria-label="Previous"> <span aria-hidden="true"> <span
													class="lnr lnr-chevron-left"></span>
											</span>
										</a></li>
									</c:otherwise>
								</c:choose>
							</c:if>

							<c:if test="${param.page==count}">
								<c:if test="${param.page-4>0}">
									<li class="page-item"><a href="/blog/board?cmd=search&content=${searchContent}&page=${param.page-4}" class="page-link">${param.page-4}</a></li>
								</c:if>
							</c:if>
							<c:if test="${param.page>=count-1}">
								<c:if test="${param.page-3>0}">
									<li class="page-item"><a href="/blog/board?cmd=search&content=${searchContent}&page=${param.page-3}" class="page-link">${param.page-3}</a></li>
								</c:if>
							</c:if>
							<c:if test="${param.page>2}">
								<c:if test="${param.page-2>0}">
									<li class="page-item"><a href="/blog/board?cmd=search&content=${searchContent}&page=${param.page-2}" class="page-link">${param.page-2}</a></li>
								</c:if>
							</c:if>
							<c:if test="${param.page>1}">
								<c:if test="${param.page-1>0}">
									<li class="page-item"><a href="/blog/board?cmd=search&content=${searchContent}&page=${param.page-1}" class="page-link">${param.page-1}</a></li>
								</c:if>
							</c:if>

							<li class="page-item active"><a href="/blog/board?cmd=search&content=${searchContent}&page=${param.page}" class="page-link">${param.page}</a></li>


							<c:if test="${param.page<count}">
								<c:if test="${count>=2}">
									<li class="page-item"><a href="/blog/board?cmd=search&content=${searchContent}&page=${param.page+1}" class="page-link">${param.page+1}</a></li>
								</c:if>
							</c:if>
							<c:if test="${param.page<count-1}">
								<c:if test="${count>=3}">
									<li class="page-item"><a href="/blog/board?cmd=search&content=${searchContent}&page=${param.page+2}" class="page-link">${param.page+2}</a></li>
								</c:if>
							</c:if>
							<c:if test="${param.page<3}">
								<c:if test="${count>=4}">
									<li class="page-item"><a href="/blog/board?cmd=search&content=${searchContent}&page=${param.page+3}" class="page-link">${param.page+3}</a></li>
								</c:if>
							</c:if>
							<c:if test="${param.page<2}">
								<c:if test="${count>=5}">
									<li class="page-item"><a href="/blog/board?cmd=search&content=${searchContent}&page=${param.page+4}" class="page-link">${param.page+4}</a></li>
								</c:if>
							</c:if>

							<c:if test="${param.page<count}">
								<li class="page-item"><a href="/blog/board?cmd=search&content=${searchContent}&page=${param.page+5}" class="page-link" aria-label="Next"> <span aria-hidden="true"> <span class="lnr lnr-chevron-right"></span>
									</span>
								</a></li>
							</c:if>
						</ul>
					</nav>
					<!-- 페이징 end-->
				</div>
			</div>
			<div class="col-lg-4">
				<div class="blog_right_sidebar">
					<aside class="single_sidebar_widget search_widget">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="Search Posts" id="searchText"> <span class="input-group-btn">
								<button class="btn btn-default" type="button" onClick="search()">
									<i class="lnr lnr-magnifier"></i>
								</button>
							</span>
						</div>
						<!-- /input-group -->
						<div class="br"></div>
					</aside>

					<aside class="single_sidebar_widget popular_post_widget">
						<h3 class="widget_title">Popular Posts</h3>

						<c:forEach var="board" items="${hotBoards}">
							<div class="media post_item">
								<img src="${board.previewImg}" width="100" height="80" alt="post">
								<div class="media-body">
									<a href="/blog/board?cmd=detail&id=${board.id}"><h3>${board.title}</h3></a>
									<p>${board.createDate}</p>
								</div>
							</div>
							<div class="br"></div>
						</c:forEach>
					</aside>

				</div>
			</div>
		</div>
	</div>
</section>
<!--================Blog Area =================-->
<script>
	var x = 0;
	function plusfive() {
		x = x + 5;
	}
	function plusfive() {
		x = x - 5;
	}
	
	function search(){
		var searchcontent = $('#searchText').val();
		location.href="/blog/board?cmd=search&content="+searchcontent+"&page=1";
	}
</script>

<%@ include file="/include/footer.jsp"%>

</body>
</html>