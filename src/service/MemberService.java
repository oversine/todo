package service;

import java.sql.Connection;
import java.sql.SQLException;

import commons.DBUtil;
import dao.*;
import vo.Member;
import vo.Todo;

public class MemberService {
	private MemberDao memberDao;
	private TodoDao todoDao;
	
	// 회원가입에 필요한 ID, PW값
	public void insertMember(Member member) {
		Connection conn = null;

		try {
			conn = DBUtil.getConnection("jdbc:mariadb://127.0.0.1:3306/todo", "root", "java1004");
			memberDao = new MemberDao();
			memberDao.insertMember(conn, member);
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
	
	// 탈퇴 시 확인 절차에 필요한 회원의 ID, PW 값
	public boolean removeMember(String memberId, String memberPw) {
		boolean result = false;
		Connection conn = DBUtil.getConnection("jdbc:mariadb://127.0.0.1:3306/todo", "root", "java1004");
		try {
			conn.setAutoCommit(false);
			todoDao = new TodoDao();
			memberDao = new MemberDao();
			
			todoDao.deleteTodo(conn, memberId);
			
			// 확인 처리 중 비밀번호가 틀린 경우 강제 예외를 발생시켜 탈퇴 처리를 중단하고 삭제된 회원의 일정 데이터까지 rollback 처리
			if(memberDao.deleteMember(conn, memberId, memberPw) != 1) {
				throw new Exception();
			}
			
			// 비밀번호 확인 후 맞는 경우 회원의 일정 데이터와 회원 로그인 정보를 삭제후 true 반환
			conn.commit();
			result = true;
		} catch(Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	// 로그인 시도 시 넘어오는 ID, PW 값
	public Member login(Member member) {
		Member loginMember = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://127.0.0.1:3306/todo", "root", "java1004");
			memberDao = new MemberDao();
			loginMember = memberDao.login(conn, member);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return loginMember;
	}
}
