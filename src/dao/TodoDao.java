package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.Todo;

public class TodoDao {
	// 일정 내용 수정
	public void updateTodo(Connection conn, Todo todo) throws ClassNotFoundException, SQLException {
		// System.out.println(todo.toString());
		
		System.out.println(todo.toString() + " <-- updateTodo");
		
		String sql = TodoQuery.UPDATE_TODO;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, todo.getTodoContent());
		stmt.setInt(2, todo.getTodoNo());
		stmt.executeUpdate();
		
		System.out.println(stmt);
		
		stmt.close();
	}	
	
	// 선택된 특정 년, 월의 일정 조회
	public List<Todo> selectTodoListByMonth(Connection conn, Todo todo) throws SQLException {
		// System.out.println(todo.toString());
		
		List<Todo> list = new ArrayList<Todo>();
		String sql = TodoQuery.SELECT_TODO_LIST_BY_MONTH;
		PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setString(1, todo.getTodoDate().substring(0, 7));
	    stmt.setString(2, todo.getMemberId());
	    ResultSet rs = stmt.executeQuery();
	    while(rs.next()) {
	    	Todo t = new Todo();
		    t.setTodoDate(rs.getString("todoDate"));
		    t.setFontColor(rs.getString("fontColor"));
		    t.setTodoContent(rs.getString("todoContent5"));
		    list.add(t);
	    }
		return list;
	}
	
	// 특정 날짜 일정 추가
	public void insertTodo(Connection conn, Todo todo) throws SQLException {
		// System.out.println(todo.toString());
		
		String sql = TodoQuery.INSERT_TODO;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, todo.getMemberId());
		stmt.setString(2, todo.getTodoContent());
		stmt.setString(3, todo.getTodoDate());
		stmt.setString(4, todo.getFontColor());
		stmt.executeUpdate();
		
		stmt.close();
	}
	
	// 특정 회원이 특정 날짜에 작성해 저장한 모든 일정 조회
	public List<Todo> selectTodoListByDate(Connection conn, Todo todo) throws SQLException {
		// System.out.println(todo.toString());
		
		List<Todo> list = new ArrayList<Todo>();
		String sql = TodoQuery.SELECT_TODO_LIST_BY_DATE;
		PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setString(1, todo.getTodoDate());
	    stmt.setString(2, todo.getMemberId());
	    ResultSet rs = stmt.executeQuery();
	    while(rs.next()) {
	    	Todo t = new Todo();
		    t.setTodoNo(rs.getInt("todoNo"));
		    t.setTodoDate(rs.getString("todoDate"));
		    t.setTodoContent(rs.getString("todoContent"));
		    t.setCreateDate(rs.getString("createDate"));
		    t.setUpdateDate(rs.getString("updateDate"));
		    list.add(t);
	    }
		return list;
	}
	
	// 특정 일정 선택 삭제
	public void removeTodo(Connection conn, int todoNo) throws SQLException {
		// System.out.println(todoNo + "<-- removeTodo todoNo");
		
		String sql = TodoQuery.REMOVE_TODO;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, todoNo);
		stmt.executeUpdate();
		stmt.close();
	}
	
	
	// 회원 탈퇴 시 일정 전체 삭제
	public void deleteTodo(Connection conn, String memberId) throws SQLException {
		// System.out.println(memberId + "<-- deleteTodo memberId");
		
		String sql = TodoQuery.DELETE_TODO;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, memberId);
		stmt.executeUpdate();
		stmt.close();
	}
}
