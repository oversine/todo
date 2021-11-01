<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-3">
	<div class="d-flex justify-content-start mb-3"></div>
	<div class="d-flex justify-content-center mb-3">
		<div class="card align-middle" style="width:30rem; border-radius:30px;">
		<div class="card-body">
			<h3>로그인</h3>
			<form id="joinForm" action="${pageContext.request.contextPath}/login" method="post">
				<table class="table table-striped" style="text-align: center;">
					<tr>
						<td>아이디</td>
						<td><input type="text" name="memberId" id="memberId"></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="password" name="memberPw" id="memberPw"></td>
					</tr>
				</table>
				<button id="btn" type="button" class="btn btn-primary">로그인</button>
				<a class="btn float-right btn-primary" href="${pageContext.request.contextPath}/insertMember">회원가입</a>
			</form>
		</div>	
		</div>
		</div>
		
		<div class="d-flex justify-content-around mb-3">
		<h2>공지사항<a href="${pageContext.request.contextPath}/noticeList">전체보기</a></h2>
		<table border="1">
			<tr>
				<td>noticeTitle</td>
				<td>createDate</td>
			</tr>
			<c:forEach var="n" items="${noticeList}">
				<tr>
					<td><a href="${pageContext.request.contextPath}/noticeOne?noticeNo=${n.noticeNo}">${n.noticeTitle}</a></td>
					<td>${n.createDate}</td>
				</tr>
			</c:forEach>
		</table>
		</div>
		<div class="d-flex justify-content-around mb-3">
			<a class="btn float-right btn-primary" href="${pageContext.request.contextPath}/adminLogin">관리자 로그인</a>
			<!-- 1) /adminLogin, AdminLoginController.doget(), adminLogin.jsp -->
			<!-- 1) /adminLogin, AdminLoginController.doPost(), /admin/adminIndex, AdminIndexController -->
		</div>
		
		<script>
			$('#btn').click(function(){
				if($('#memberId').val() == '') {
					alert('ID를 입력해주세요');
					return;
				}
				if($('#memberPw').val() == '') {
					alert('비밀번호를 입력해주세요');
					return;
				}
				$('#joinForm').submit();
			});
		</script>
</div>
</body>
</html>