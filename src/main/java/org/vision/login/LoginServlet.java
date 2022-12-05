package org.vision.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.vision.tmember.MemberDAO;
import org.vision.tmember.MemberVO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doHandle(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}
	private void doHandle(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String user_id = request.getParameter("user_id");
		String user_pwd = request.getParameter("user_pwd");
		MemberVO memberVO = new MemberVO();
		memberVO.setId(user_id);
		memberVO.setPwd(user_pwd);
		MemberDAO dao = new MemberDAO();
		boolean result = dao.isExisted(memberVO);
		HttpSession session = request.getSession();
		
//		if (session.getAttribute("login.id")!=null) {
//			session.removeAttribute("login.id");
//			session.removeAttribute("login.pwd");
//			session.removeAttribute("isLogon");
//			session.invalidate();
//			response.sendRedirect("/MyBoard/");
			
//			session.setAttribute("pass", !result);
//			response.sendRedirect("/MyBoard/wait_login.jsp");
//		} else {
		
		if (result) {
			
			session.setAttribute("isLogon", true);
			session.setAttribute("login_id", user_id);
			session.setAttribute("mem_id", user_id);
			session.setAttribute("login.pwd", user_pwd);
			MemberVO vo = dao.findMember(user_id);
			session.setAttribute("memInfo",vo);
			
//			out.print("<html><body style='display: inline-block;text-align: center;'>");
//			out.print("안녕하세요 " + user_id + "님!!!<br>");
//			out.print("<a href='Main.jsp'>메인가기</a>");
//			out.print("<script>setTimeout(() => { onload=window.location='/MyBoard/';}, 2000);</script>");
//			out.print("</body></html>");
			
//			response.sendRedirect("/MyBoard/");
			
			session.setAttribute("pass", result);
			response.sendRedirect("/MyBoard/wait_login.jsp");			

		} else {
//			out.print("<html><body style='display: inline-block;text-align: center;'>회원 아이디가 틀립니다.<br>");
//			out.print("<a href='login/formLogin.jsp'> 다시 로그인하기</a>");
//			out.print("다시 로그인하세요!");
//			out.print("<script>setTimeout(() => { onload=window.location='login/formLogin.jsp';}, 2000);</script>");
//			out.print("</body></html>");
			
//			response.sendRedirect("/MyBoard/");
			
//			request.setAttribute("pass", "false");
//			RequestDispatcher dispatcher = servletContext().getRequestDispatcher("/MyBoard/wait_login.jsp");
//			dispatcher.forward(request, response);
			Boolean emptyLogin = false;
			if (user_id.equals("")||user_pwd.equals("")) {emptyLogin=true;}
		
			session.setAttribute("pass", result);
			session.setAttribute("emptyLogin", emptyLogin);
			response.sendRedirect("/MyBoard/wait_login.jsp");
		}
//		}

	}
}