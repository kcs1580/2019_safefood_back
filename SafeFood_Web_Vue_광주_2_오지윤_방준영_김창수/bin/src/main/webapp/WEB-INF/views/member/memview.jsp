<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보수정</title>
<link rel="stylesheet" type="text/css" href="css/mystyle.css">

</head>
<body>
	<jsp:include page="../nav.jsp"></jsp:include>
	<div class="ex" style="height: 800px">
		<h2>회원 정보 수정</h2>
		<form class="form-horizontal" role="form" method="POST"
			action="memupdate">
			<input type="hidden" name="action" value="signup" />
			<div class="form-group" id="divId">
				<label for="inputId" class="col-lg-2 control-label">아이디</label>
				<div class="col-lg-10">
					<input type="email" class="form-control onlyAlphabetAndNumber"
						id="id" name="id" data-rule-required="true" placeholder=""
						maxlength="30" value="${mem.id }" readonly>

				</div>
			</div>

			<div class="form-group" id="divPassword">
				<label for="inputPassword" class="col-lg-2 control-label">패스워드</label>
				<div class="col-lg-10">
					<input type="password" class="form-control" id="password"
						name="password" data-rule-required="true"
						placeholder="영문 숫자포함  6자리 이상" maxlength="30"
						value="${mem.password }">
				</div>
			</div>
			<div class="form-group" id="divName">
				<label for="inputName" class="col-lg-2 control-label">이름</label>
				<div class="col-lg-10">
					<input type="text" class="form-control onlyHangul" id="mname"
						name="mname" data-rule-required="true" placeholder="이름"
						maxlength="15" value="${mem.mname }">
				</div>
			</div>
			<div class="form-group" id="divAddress">
				<label for="inputAddress" class="col-lg-2 control-label">주소</label>
				<div class="col-lg-10">
					<input type="text" class="form-control" id="addr" name="addr"
						data-rule-required="true" placeholder="주소" maxlength="50"
						value="${mem.addr }">
				</div>
			</div>
			<div class="form-group" id="divPhoneNumber">
				<label for="inputPhoneNumber" class="col-lg-2 control-label">전화번호</label>
				<div class="col-lg-10">
					<input type="tel" class="form-control onlyNumber" id="tel"
						name="tel" data-rule-required="true" placeholder="010-xxx-xxxx"
						maxlength="11" value="${mem.tel }">
				</div>
			</div>



			<div class="form-group" id="divAllergy">
				<label for="inputAllergy" class="col-lg-2 control-label">알레르기</label>
				<div class="col-lg-10">
					<fieldset class="form-group">
								<legend class="col-form-legend col-sm-3"> Check</legend>
								<div class="row">
									<div class="col-md-10">
										<div class="form-check form-check-inline col-md-4">
											<input name="allergy" class="form-check-input" id='대두'
												type="checkbox" value="대두"> <label
												class="form-check-label">대두 </label>
										</div>
										<div class="form-check form-check-inline col-md-4">
											<input name="allergy" class="form-check-input" id='땅콩'
												type="checkbox" value="땅콩"> <label
												class="form-check-label">땅콩 </label>
										</div>
										<div class="form-check form-check-inline col-md-4">
											<input name="allergy" class="form-check-input" id='우유'
												type="checkbox" value="우유"> <label
												class="form-check-label">우유 </label>
										</div>
										<div class="form-check form-check-inline col-md-4">
											<input name="allergy" class="form-check-input" id='게'
												type="checkbox" value="게"> <label
												class="form-check-label">게</label>
										</div>
										<div class="form-check form-check-inline col-md-4">
											<input name="allergy" class="form-check-input" id='새우'
												type="checkbox" value="새우"> <label
												class="form-check-label">새우</label>
										</div>
										<div class="form-check form-check-inline col-md-4">
											<input name="allergy" class="form-check-input" id='참치'
												type="checkbox" value="참치"> <label
												class="form-check-label">참치</label>
										</div>
										<div class="form-check form-check-inline col-md-4">
											<input name="allergy" class="form-check-input" id='연어'
												type="checkbox" value="연어"> <label
												class="form-check-label">연어</label>
										</div>
										<div class="form-check form-check-inline col-md-4">
											<input name="allergy" class="form-check-input" id='쑥'
												type="checkbox" value="쑥"> <label
												class="form-check-label">쑥 </label>
										</div>
										<div class="form-check form-check-inline col-md-4">
											<input name="allergy" class="form-check-input" id='소고기'
												type="checkbox" value="소고기"> <label
												class="form-check-label">소고기</label>
										</div>
										<div class="form-check form-check-inline col-md-4">
											<input name="allergy" class="form-check-input" id='닭고기'
												type="checkbox" value="닭고기"> <label
												class="form-check-label">닭고기 </label>
										</div>
										<div class="form-check form-check-inline col-md-4">
											<input name="allergy" class="form-check-input" id='돼지고기'
												type="checkbox" value="돼지고기"> <label
												class="form-check-label">돼지고기</label>
										</div>
										<div class="form-check form-check-inline col-md-4">
											<input name="allergy" class="form-check-input" id='복숭아'
												type="checkbox" value="복숭아"> <label
												class="form-check-label">복숭아 </label>
										</div>
										<div class="form-check form-check-inline col-md-4">
											<input name="allergy" class="form-check-input" id='민들레'
												type="checkbox" value="민들레"> <label
												class="form-check-label">민들레</label>
										</div>
										<div class="form-check form-check-inline col-md-4">
											<input name="allergy" class="form-check-input" id='계란흰자'
												type="checkbox" value="계란흰자"> <label
												class="form-check-label">계란흰자 </label>
										</div>
										<c:forTokens var="elem" items="${requestScope.mem.allergy }"
											delims=" ">
											<script>
												$("input:checkbox[id=${elem}]")
														.prop("checked", true);
											</script>
										</c:forTokens>
									</div>
								</div>
							</fieldset>

					<c:forTokens var="elem" items="${requestScope.mem.allergy }"
						delims=" ">
						<script>
							$("input:checkbox[id=${elem}]").prop("checked",
									true);
						</script>
					</c:forTokens>
				</div>
			</div>
			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
					<button type="submit" class="btn btn-primary">수정</button>
					<button type="취소" class="btn btn-primary">취소</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>