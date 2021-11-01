package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.Member;
import vo.Todo;

public class MemberDao {
	// 회원 가입
	public void insertMember(Connection conn, Member member) throws SQLException {
		// System.out.println(member.toString());
		
		String sql = MemberQuery.INSERT_MEMBER;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, member.getMemberId());
		stmt.setString(2, member.getMemberPw());
		stmt.executeUpdate();
		
		stmt.close();
	}
	
	// 회원 탈퇴
	public int deleteMember(Connection conn, String memberId, String memberPw) throws SQLException {
		// System.out.println(memberId + "<-- deleteMember memberId");
		// System.out.println(memberPw + "<-- deleteMember memberPw");
		
		String sql = MemberQuery.DELETE_MEMBER;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, memberId);
		stmt.setString(2, memberPw);
		int row = stmt.executeUpdate();
		stmt.close();
		
		return row;
	}
	
	// 회원 로그인
	public Member login(Connection conn, Member paramMember) throws SQLException {
		// System.out.println(paramMember.toString());
		
		Member loginMember = null;
		String sql = MemberQuery.LOGIN;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, paramMember.getMemberId());
		stmt.setString(2, paramMember.getMemberPw());
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			loginMember = new Member();
			loginMember.setMemberId(rs.getString("memberId"));
		}
		rs.close();
		stmt.close();
		return loginMember;
	}
}
