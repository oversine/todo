<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>달력</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
	<div class="jumbotron">
		${loginMember.memberId}님 반갑습니다
		<a class="btn btn-primary" href="${pageContext.request.contextPath}/member/logout">로그아웃</a>
		<a class="btn btn-primary" href="${pageContext.request.contextPath}/member/removeMember">회원탈퇴</a>
	</div>
	
	<!-- 달력 + todo -->
	<h1>
		<a class="badge badge-pill badge-primary" href="${pageContext.request.contextPath}/member/calendar?currentYear=${targetYear}&currentMonth=${targetMonth}&option=pre">이전</a>
		${targetYear}년 ${targetMonth}월
		<a class="badge badge-pill badge-primary" href="${pageContext.request.contextPath}/member/calendar?currentYear=${targetYear}&currentMonth=${targetMonth}&option=next">다음</a>
	</h1><br>
	<div>
		이달의 총 일정 : ${todoList.size()}
	</div>
	<table class="table table-striped" style="text-align: center;">
		<tr>
			<td>일</td><td>월</td><td>화</td><td>수</td><td>목</td><td>금</td><td>토</td>
		</tr>
		<tr>
			<!-- JSTL for문 -->
			<c:forEach var="i" begin="1" end="${startBlank+endDay+endBlank}" step="1">
				<c:if test="${i-startBlank >= 1 && i-startBlank<=endDay}">
					<td>
						<a class="btn btn-secondary" href="${pageContext.request.contextPath}/member/todoList?y=${targetYear}&m=${targetMonth}&d=${i-startBlank}">${i-startBlank}</a>
						<div>
							<!-- 날짜별 일정 -->
							<c:forEach var="todo" items="${todoList}">
								<c:if test="${(i-startBlank) == todo.todoDate.substring(8)}">
									<div style="color:${todo.fontColor};">${todo.todoContent}</div>
								</c:if>
							</c:forEach>
						</div>					
					</td>
				</c:if>
				<c:if test="${i-startBlank < 1 || i-startBlank>endDay}">
					<td>&nbsp;</td>
				</c:if>
				<c:if test="${i%7 == 0}">
					<tr></tr>
				</c:if>
			</c:forEach>
		</tr>
	</table>
</div>
</body>
</html>