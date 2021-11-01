<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일정 수정</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
	<h1>일정 수정</h1>
	<form id="updateTodoForm" action="${pageContext.request.contextPath}/member/updateTodo" method="post">
		<div class="form-group">
		<label for="todoNo">번호 : </label>
			<input type="text" class="form-control" name="todoNo" value="${todoNo}" readonly="readonly">
		</div>
		<div class="form-group">
		<label for="todoDate">작성일 : </label>
			<input type="text" class="form-control" name="todoDate" value="${todoDate}" readonly="readonly">
		</div>
		<div class="form-group">
		<label for="todoContent">내용 : </label>
			<textarea rows="3" cols="50" class="form-control" id="todoContent" name="todoContent">${todoContent}</textarea>
		</div>	
		<button id="btn" type="button" class="btn btn-primary">수정</button>
	</form>
	
	<script>
		$('#btn').click(function(){
			if($('#todoContent').val() == '') {
				alert('내용을 입력해주세요');
				return;
			}
			$('#updateTodoForm').submit();
		});
	</script>
</div>
</body>
</html>