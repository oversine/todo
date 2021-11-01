package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberService;
import service.TodoService;
import vo.Member;

@WebServlet("/member/removeTodo")
public class RemoveTodoController extends HttpServlet {
	private TodoService todoService;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int todoNo = Integer.parseInt(request.getParameter("todoNo"));
		String todoDate = request.getParameter("todoDate");
		// System.out.println(todoNo + "<-- todoNo");
		// System.out.println(todoDate + "<-- todoDate");
		
		todoService = new TodoService();
		
		// 해당 번호의 일정 삭제처리 후 이전 일정 목록 페이지로 이동
		todoService.removeTodo(todoNo);
		
		String y = todoDate.substring(0,4);
		String m = todoDate.substring(5,7);
		String d = todoDate.substring(8,10);
		
		System.out.println("일정 삭제 성공");
		response.sendRedirect(request.getContextPath()+"/member/todoList?y="+y+"&m="+m+"&d="+d);
	}
}
