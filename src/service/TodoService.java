package service;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.List;

import commons.DBUtil;
import dao.TodoDao;
import vo.Todo;

public class TodoService {
	private TodoDao todoDao;
	
	public void updateToda(Todo todo) {
		Connection conn = null;

		try {
			conn = DBUtil.getConnection("jdbc:mariadb://127.0.0.1:3306/todo", "root", "java1004");
			todoDao = new TodoDao();
			todoDao.updateTodo(conn, todo);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void removeTodo(int todoNo) {
		Connection conn = DBUtil.getConnection("jdbc:mariadb://127.0.0.1:3306/todo", "root", "java1004");
		try {
			todoDao = new TodoDao();
			todoDao.removeTodo(conn, todoNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}		
	
	public void insertTodo(Todo todo) {
		Connection conn = null;

		try {
			conn = DBUtil.getConnection("jdbc:mariadb://127.0.0.1:3306/todo", "root", "java1004");
			todoDao = new TodoDao();
			todoDao.insertTodo(conn, todo);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public List<Todo> getTodoListByDate(Todo todo) {
		List<Todo> list = null;   
		Connection conn = null;
		try {
			 conn = DBUtil.getConnection("jdbc:mariadb://127.0.0.1:3306/todo", "root", "java1004");
		     todoDao = new TodoDao();
		     list = todoDao.selectTodoListByDate(conn, todo);
		 } catch(Exception e) {
			 e.printStackTrace();
		 } finally {
		    try {
		       conn.close();
		    } catch (SQLException e) {
		       e.printStackTrace();
		    }
		}
		return list;
	}
}	
	
