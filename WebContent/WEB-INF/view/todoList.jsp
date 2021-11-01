<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일정 내역</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
	<h1>${todoDate} 일정 목록</h1>
	<table class="table table-striped" style="text-align: center;">
		<tr>
			<td>날짜</td>
			<td>작성 내용</td>
			<td>생성일</td>
			<td>수정일</td>
			<td>수정</td>
			<td>삭제</td>
		</tr>
		<c:forEach var="t" items="${todoList}">
			<tr>
				<td style="width: 10%">${todoDate}</td>
				<td style="width: 40%">${t.todoContent}</td>
				<td style="width: 10%">${t.createDate}</td>
				<td style="width: 10%">${t.updateDate}</td>
				<td style="width: 15%"><a class="btn btn-primary" href="${pageContext.request.contextPath}/member/updateTodo?todoNo=${t.todoNo}&todoDate=${todoDate}&todoContent=${t.todoContent}">수정</a></td>
				<td style="width: 15%"><a class="btn btn-danger" href="${pageContext.request.contextPath}/member/removeTodo?todoNo=${t.todoNo}&todoDate=${todoDate}">삭제</a></td>
			</tr>
		</c:forEach>	
	</table>
	
	<h1>일정 추가</h1>
	<form id="insertTodoForm" action="${pageContext.request.contextPath}/member/insertTodo" method="post">
		<div class="form-group">
		<label for="todoDate">작성일 : </label>
			<input type="text" class="form-control" name="todoDate" value="${todoDate}" readonly="readonly">
		</div>
		<div class="form-group">
		<label for="todoContent">내용 : </label>
			<textarea rows="3" cols="50" class="form-control" id="todoContent" name="todoContent"></textarea>
		</div>
		<div>
			<input type="color" name="fontColor">
		</div>
		<button id="btn" type="button" class="btn btn-primary">추가</button>
	</form>
	
	<script>
		$('#btn').click(function(){
			if($('#todoContent').val() == '') {
				alert('내용을 입력해주세요');
				return;
			}
			$('#insertTodoForm').submit();
		});
	</script>
</div>	
</body>
</html>