package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CalendarService;
import vo.Member;

/**
 * Servlet implementation class CalendarController
 */
@WebServlet("/member/calendar")
public class CalendarController extends HttpServlet {
	private CalendarService calendarService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String currentYear = request.getParameter("currentYear");
		String currentMonth = request.getParameter("currentMonth");
		String option = request.getParameter("option");
		
		// System.out.println(currentYear + "<--currentYear");
		// System.out.println(currentMonth + "<--currentMonth");
		// System.out.println(option + "<--option");
		
		calendarService = new CalendarService();
		String memberId = ((Member)request.getSession().getAttribute("loginMember")).getMemberId();
		Map<String, Object> map = calendarService.getTargetCalendar(memberId, currentYear, currentMonth, option);
		
		// System.out.println(memberId + "<-- CalendarController.memberId");
		
		// 모델
		request.setAttribute("targetYear", map.get("targetYear"));
		request.setAttribute("targetMonth", map.get("targetMonth"));
		request.setAttribute("endDay", map.get("endDay"));
		// 달력을 출력할 때 앞/뒤 필요한 공백<td>
		request.setAttribute("startBlank", map.get("startBlank"));
		request.setAttribute("endBlank", map.get("endBlank"));
		
		// 캘린더에 출력할 todo 모델
		request.setAttribute("todoList", map.get("todoList"));
		
		request.getRequestDispatcher("/WEB-INF/view/calendar.jsp").forward(request, response);
	}
}
