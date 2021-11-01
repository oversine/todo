package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TodoService;
import vo.Member;
import vo.Todo;

@WebServlet("/member/todoList")
public class TodoController extends HttpServlet {
	private TodoService todoService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   String y = request.getParameter("y");
		   String m = request.getParameter("m");
		   String d = request.getParameter("d");
		   // System.out.println(y + "<-- yaer");
		   // System.out.println(m + "<-- month");
		   // System.out.println(d + "<-- day");
		   
		   // 일수가 두자리 이하인 경우 앞에 0이 붙도록 처리해주는 코드 01, 02...
		   if(d.length()<2) {
		      d = "0"+d;
		   }
		   
		   String todoDate = y+"-"+m+"-"+d;
		   String memberId = ((Member)(request.getSession().getAttribute("loginMember"))).getMemberId();
		   // System.out.println(todoDate + "<--todoDate");
		   // System.out.println(memberId + "<--memberId");
		   
		   // 생성된 todo 객체에 로그인 중인 회원 ID와 날짜 데이터를 저장
		   Todo todo = new Todo();
		   todo.setTodoDate(todoDate);
		   todo.setMemberId(memberId);
		      
		   // 로그인 중인 회원이 선택한 일수에 해당하면서 DB에 존재하는 일정 데이터를 리스트에 저장함
		   todoService = new TodoService();
		   List<Todo> todoList = todoService.getTodoListByDate(todo);
		   
		   request.setAttribute("todoDate", todoDate);
		   request.setAttribute("todoList", todoList);
		   request.getRequestDispatcher("/WEB-INF/view/todoList.jsp").forward(request, response);
		}

}
