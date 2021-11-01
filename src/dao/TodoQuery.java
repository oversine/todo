package dao;

public class TodoQuery {
	public static final String DELETE_TODO;
	public static final String REMOVE_TODO;
	public static final String SELECT_TODO_LIST_BY_DATE;
	public static final String INSERT_TODO;
	public static final String SELECT_TODO_LIST_BY_MONTH;
	public static final String UPDATE_TODO;
	static {
		DELETE_TODO = "DELETE FROM todo WHERE member_id=?";
		REMOVE_TODO = "DELETE FROM todo WHERE todo_no=?";
		SELECT_TODO_LIST_BY_DATE = "SELECT todo_no todoNo, todo_date todoDate, todo_content todoContent, create_date createDate, update_date updateDate FROM todo WHERE todo_date=? AND member_id=?";
		INSERT_TODO = "INSERT INTO todo(member_id, todo_content, todo_date, font_color, create_date, update_date) VALUES(?, ?, ?, ?, NOW(), NOW())";
		SELECT_TODO_LIST_BY_MONTH = "SELECT todo_date todoDate, SUBSTR(todo_content, 1, 5) todoContent5, font_color fontColor FROM todo WHERE SUBSTR(todo_date, 1, 7)=? AND member_id=? ORDER BY todo_date ASC";
		UPDATE_TODO = "UPDATE todo SET todo_content = ?, update_date=NOW() WHERE todo_no = ?";
	}
}
