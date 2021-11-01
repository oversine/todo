package dao;

public class MemberQuery {
	public static final String INSERT_MEMBER;
	public static final String LOGIN;
	public static final String DELETE_MEMBER;
	static {
		INSERT_MEMBER = "INSERT INTO member(member_id, member_pw, create_date, update_date) VALUES(?, ?, NOW(), NOW())";
		LOGIN = "SELECT member_id memberId FROM member WHERE member_id=? AND member_pw =?";
		DELETE_MEMBER = "DELETE FROM member WHERE member_id=? AND member_pw=?";
	}
}
