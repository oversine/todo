package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberService;
import vo.Member;

@WebServlet("/member/removeMember")
public class RemoveController extends HttpServlet {
	private MemberService memberService;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 비밀번호 확인 페이지로 이동
		request.getRequestDispatcher("/WEB-INF/view/passwordCheck.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션에서 로그인 중인 회원의 ID값
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		String memberId = loginMember.getMemberId();
		// System.out.println(memberId + "<-- session memberId");
		
		// 확인 페이지에서 입력되어 넘어온 비밀번호 값
		String memberPw = request.getParameter("memberPw");
		System.out.println(memberPw + "<-- remove memberPw");
		
		memberService = new MemberService();
		
		// ID, 비밀번호가 일치하는 경우 true, 일치하지 않은 경우 false 반환
		boolean result = memberService.removeMember(memberId, memberPw);
		// System.out.println(result + "<-- result true or false");
		
		
		// 일치한 경우 세션을 지우고 로그인 페이지로 이동
		// 일치하지 않아 false 값이 반환된 경우 다시 calendar 페이지로 이동
		if (result == true) {
			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath()+"/login");
			System.out.println("탈퇴 완료");
		} else {
			response.sendRedirect(request.getContextPath()+"/member/calendar");
			System.out.println("탈퇴 실패");
		}
	}

}
