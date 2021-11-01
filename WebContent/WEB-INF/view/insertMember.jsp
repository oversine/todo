<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-3">
	<div class="d-flex justify-content-start mb-3"></div>
	<div class="d-flex justify-content-center mb-3">
		<div class="card align-middle" style="width:30rem; border-radius:30px;">
		<div class="card-body">
			<h3>회원가입</h3>
			<form id="insertForm" action="${pageContext.request.contextPath}/insertMember" method="post">
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
				<button id="btn" type="button" class="btn btn-primary">회원가입</button>
			</form>
	
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
				$('#insertForm').submit();
			});
		</script>
		</div>	
		</div>
		</div>
		<div class="d-flex justify-content-around mb-3"></div>
</div>
</body>
</html>