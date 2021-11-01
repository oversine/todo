package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commons.DBUtil;
import dao.TodoDao;
import vo.Todo;

public class CalendarService {
	private TodoDao todoDao;
	
	// option : pre, next
	public Map<String, Object> getTargetCalendar(String memberId, String currentYear, String currentMonth, String option) {
		System.out.println(memberId);
		System.out.println(currentYear);
		System.out.println(currentMonth);
		System.out.println(option);
		
		Map<String, Object> map = new HashMap<>();
		Calendar c = Calendar.getInstance(); // 오늘 날짜의 연도 월을 가짐
		
		int y = 0;
		int m = 0;
		if(currentYear != null && currentMonth != null) {
			y = Integer.parseInt(currentYear);
			m = Integer.parseInt(currentMonth);
			
			if(option != null && option.equals("pre")) {
				m = m-1;
				if(m == 0) { // 0 = 12월 / 1~11
					m = 12;
					y--;
				}
			} else if (option != null && option.equals("next")) {
				m = m+1;
				if (m == 13) {
					m = 1;
					y++;
				}
			}
			
			c.set(Calendar.YEAR, y);
			c.set(Calendar.MONTH, m-1); // 캘린더가 가지고 있는 월값은 0~11 이므로 받은 값에서 -1 해야함
		}
	
		
		c.set(Calendar.DATE, 1); // c객체 오늘의 정보 -> 같은 달 1일로 변경
		
		// 달력에 필요한 데이터
		int targetYear = c.get(Calendar.YEAR);
		int targetMonth = c.get(Calendar.MONTH) + 1;
		int endDay = c.getActualMaximum(Calendar.DATE);
		// 달력 앞,뒤 공백의 개수
		int startBlank = 0; // 타켓이 되는 달의 1일의 요일 -> 일요일이면 0, 월요일 1.... 토요일이면 6이 필요
		startBlank = c.get(Calendar.DAY_OF_WEEK) - 1;
		
		int endBlank = 0; // 전체의 <td>개수 = startBlank+endDay+endBlnk <- 이값이 7로 나누어 떨어지도록
		endBlank = 7 - (startBlank+endDay)%7;
		if(endBlank == 7) {
			endBlank = 0;
		}
		
		map.put("targetYear", targetYear);
		map.put("targetMonth", targetMonth);
		map.put("endDay", endDay);
		map.put("startBlank", startBlank);
		map.put("endBlank", endBlank);
		
		List<Todo> list = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://127.0.0.1:3306/todo", "root", "java1004");			
			todoDao = new TodoDao();
			Todo todo = new Todo(); // memberId, todoDate의 년, 월
			todo.setMemberId(memberId);
			String strYear = ""+targetYear;
			String strMonth = ""+targetMonth;
			if(targetMonth < 10) {
				strMonth = "0"+targetMonth;
			}
			
			System.out.println(strYear);
			System.out.println(strMonth);
			
			todo.setTodoDate(strYear+"-"+strMonth);
			list = todoDao.selectTodoListByMonth(conn, todo);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		map.put("todoList", list);
		
		return map;
	}
}
