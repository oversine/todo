package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TodoService;
import vo.Todo;

/**
 * Servlet implementation class UpdateTodoController
 */
@WebServlet("/member/updateTodo")
public class UpdateTodoController extends HttpServlet {
	private TodoService todoService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int todoNo = Integer.parseInt(request.getParameter("todoNo"));
		String todoDate = request.getParameter("todoDate");
		String todoContent = request.getParameter("todoContent");
		// System.out.println(todoNo + "<-- updateTodo get todoNo");
		// System.out.println(todoDate + "<--updateTodo get todoDate");
		// System.out.println(todoContent + "<--updateTodo get todoContent");
		
		// 일정을 수정하기 위해 필요한 해당 일정 번호, 날짜, 작성했던 내용을 전달
		request.setAttribute("todoNo", todoNo);
		request.setAttribute("todoDate", todoDate);
		request.setAttribute("todoContent", todoContent);
		request.getRequestDispatcher("/WEB-INF/view/updateTodo.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		todoService = new TodoService();
		int todoNo = Integer.parseInt(request.getParameter("todoNo"));
		String todoDate = request.getParameter("todoDate");
		String todoContent = request.getParameter("todoContent");
		// System.out.println(todoNo + "<-- updateTodo post todoNo");
		// System.out.println(todoDate + "<--updateTodo post todoDate");
		// System.out.println(todoContent + "<--updateTodo post todoContent");
		
		// 사용자가 수정할 일정에 대한 번호와 내용을 todo 객체에 저장
		Todo todo = new Todo();
		todo.setTodoNo(todoNo);
		todo.setTodoContent(todoContent);
		
		// DB에서 작성한 일정 내용을 수정 후 이전 일정 목록 페이지로 이동
		todoService.updateToda(todo);
		
		String y = todoDate.substring(0,4);
		String m = todoDate.substring(5,7);
		String d = todoDate.substring(8,10);
		
		response.sendRedirect(request.getContextPath()+"/member/todoList?y="+y+"&m="+m+"&d="+d);
	}

}
