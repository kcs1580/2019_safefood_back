<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>질문 게시판</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
/* 	$('#updateBoard').on('click', function(){
		$('#boardForm').attr('action', 'updateview');
		$('#boardForm').submit();
	});
	
	$('#deleteBoard').on('click', function(){
		$('#boardForm').attr('action', 'deleteboard');
		$('#boardForm').submit();
	}); */
})
</script>
</head>
<body>
	<jsp:include page="../nav.jsp"></jsp:include>
	<h1 style="text-align: center; font-size: 3em; margin-bottom:">질문 게시판</h1>
		<hr style="margin-bottom: 40px;">
		<form id="boardForm" action="#" method="post">
		<div class="panel panel-info">
			<div class="panel-heading">
				<strong>${board.btitle }</strong>
				<input type="hidden" name="user_id" value="${currentId }"/>
				<input type="hidden" name="bid" value="${board.bid }" />
				<input type="hidden" name="btitle" value="${board.btitle }" />
			</div>
			<div class="panel-body">
				${board.bcontent }
				<input type="hidden" name="bcontent" value="${board.bcontent }" />
			</div>
			<div class="panel-footer text-right">
				<a href="/updateview?bid=${board.bid}&btitle=${board.btitle }&bcontent=${board.bcontent }" class="btn btn-info btn-flat">수정</a>
				<a href="/deleteboard?bid=${board.bid }&user_id=${board.user_id }" class="btn btn-danger btn-flat">삭제</a>
				<a href="/listboard" class="btn btn-primary btn-flat">목록</a>
			</div>
		</div>
		</form>
		<footer style="background: #ececec; padding: 50px;">
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
	</footer>
</body>
</html>