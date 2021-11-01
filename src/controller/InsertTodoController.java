package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.TodoService;
import vo.Member;
import vo.Todo;

@WebServlet("/member/insertTodo")
public class InsertTodoController extends HttpServlet {
	private TodoService todoService;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		todoService = new TodoService();
		String todoDate = request.getParameter("todoDate");
		String todoContent = request.getParameter("todoContent");
		String fontColor = request.getParameter("fontColor");
		
		
		// System.out.println(fontColor + "<-- fontColor");
		// System.out.println(todoDate + "<-- todoDate");
		// System.out.println(todoContent + "<-- todoContent");
		
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		String memberId = loginMember.getMemberId();
		
		// System.out.println(memberId + "<-- insertTodo memberId");
		
		// todoList 페이지에서 넘어온 작성일, 일정 내용을 가져오고
		// 세션에서 로그인 중인 회원의 ID값을 가져와 생성한 todo 객체에 저장
		Todo todo = new Todo();
		todo.setMemberId(memberId);
		todo.setTodoDate(todoDate);
		todo.setTodoContent(todoContent);
		todo.setFontColor(fontColor);
		
		// System.out.println(todo + "<-- todo");
		
		// 객체에 담긴 데이터 DB 추가 후 이전 일정 목록 페이지로 이동 
		todoService.insertTodo(todo);
		
		String y = todoDate.substring(0,4);
		String m = todoDate.substring(5,7);
		String d = todoDate.substring(8,10);
		
		response.sendRedirect(request.getContextPath()+"/member/todoList?y="+y+"&m="+m+"&d="+d);
	}
}
