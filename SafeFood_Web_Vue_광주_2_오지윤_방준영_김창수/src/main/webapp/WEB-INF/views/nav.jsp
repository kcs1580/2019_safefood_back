<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/mystyle.css">
<script type="text/javascript">
$(document).ready(function(){
	$('#modalbtn').on('click', function(){
		$.ajax({
			type : "post",
			url : "recommendfood",
			dataType : "html",
			success : sFunc,
			error : eFunc
		});
		
		function sFunc(data) {
			$('.modal-body').html(data);
			$('#recommendModal').modal();
		};
		
		function eFunc(e){
			console.log(e)
		};
	});
	
	
})
</script>
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="mainfood" style="padding: 5px;">
						<img alt="Brand" src="img/ssafy_logo.png"
						style="max-height: 40px;">
					</a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li><a href="listnotice">공지 사항 <span
								class="sr-only">(current)</span></a></li>
						
						<li class="dropdown" id="menuFood"><a
									class="btn btn-sm dropdown-toggle" href="#"
									data-toggle="dropdown" id="navFood"><span>&nbsp;상품</span>
								</a>
									<div class="dropdown-menu" id="dropdownFood" >
										<ul>
											<li><a href="listfood" >상품 정보</a></li>
											<li><a href="#" >상품 입력</a></li>
										</ul>
									</div>
						</li>
						
						
						<li>
						<a href="listfood">상품 정보</a></li>

						<li><a href="http://localhost:8080">질문 게시판</a></li>
						<li><a href="/listboard">질문 게시판</a></li>

					</ul>

				

				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>
		<div class="modal fade" id="recommendModal" tabindex="-1" role="dialog" aria-labelledby="recommendModalLabel" aria-hidden="true">
		  <div class="modal-dialog modal-dialog-centered" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">추천 음식</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <form action="infofood" method="post">
		      <div class="modal-body">
		       	
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
		        <button type="submit" class="btn btn-primary">상품 정보</button>
		      </div>
		      </form>
		    </div>
		  </div>
		</div>

		<div class="jumbotron">
			<h1>
				WHAT WE <strong style="color: #4d7d5a;">PROVIDE</strong>
			</h1>
			<hr>
			<p>건강한 삶을 위한 먹거리 프로젝트</p>
		</div>

		<!-- <div class="panel panel-default">
			<div class="panel-body">
				<div class="row productRow"></div>
			</div>
		</div> -->
	</div>
	
	<%-- <% 
		String view = (String)request.getAttribute("view"); 
	%>
	<c:set var="view" scope="request" value="view"/>
	${view }
	<c:choose>
		<c:when test='${view eq "findpwview" }'>
			<jsp:include page="findPassword.jsp"></jsp:include>
		</c:when>
		<c:when test='${view eq "signupview" }'>
			<jsp:include page="signUp.jsp"></jsp:include>
		</c:when>
		<c:when test='${view eq "infomemview" }'>
			<jsp:include page="updatepersonalinfo.jsp"></jsp:include>
		</c:when>
		<c:when test='${view eq "productinfoview" }'>
			<jsp:include page="productinfo.jsp"></jsp:include>
		</c:when>
		<c:when test='${view eq "productdetailview" }'>
			<jsp:include page="productdetail.jsp"></jsp:include>
		</c:when>
		<c:when test='${view eq "notice_listview" }'>
			<jsp:include page="notice_list.jsp"></jsp:include>
		</c:when>
		<c:when test='${view eq "notice_viewview" }'>
			<jsp:include page="notice_view.jsp"></jsp:include>
		</c:when>
		<c:when test='${view eq "notice_insertview" }'>
			<jsp:include page="notice_insert.jsp"></jsp:include>
		</c:when>
		<c:when test='${view eq "bestintakeinfoview" }'>
			<jsp:include page="bestintakeinfo.jsp"></jsp:include>
		</c:when>
	</c:choose> --%>
	

	<!-- <footer style="background: #ececec; padding: 50px;">
		<h1>Find Us</h1>
		<hr>
		<p>
			<i class="glyphicon glyphicon-envelope"></i> (SSAFY) 서울시 강남구 테헤란로
			멀티스퀘어
		</p>
		<p>
			<i class="glyphicon glyphicon-envelope"></i> 1544-9001
		</p>
		<p>
			<i class="glyphicon glyphicon-envelope"></i> admin@ssafy.com
		</p>
	</footer> -->
</body>
</html>