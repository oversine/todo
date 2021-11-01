package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;
import vo.Member;

@WebServlet("/insertMember")
public class InsertMemberController extends HttpServlet {
	private MemberService memberService;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원가입 페이지로 이동
		request.getRequestDispatcher("/WEB-INF/view/insertMember.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 회원가입 페이지에서 넘어온 ID, PW값을 변수에 저장하고 member 객체에 저장
		memberService = new MemberService();
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		
		System.out.println(memberId + "<-- insert memberId");
		System.out.println(memberPw + "<-- insert memberPw");
		
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		// INSERT문을 사용해 객체 안의 데이터를 DB에 저장하고 로그인 페이지로 다시 이동
		memberService.insertMember(member);
		
		// System.out.println(member + "<-- member");
		
		
		response.sendRedirect(request.getContextPath()+"/login");
	}

}
