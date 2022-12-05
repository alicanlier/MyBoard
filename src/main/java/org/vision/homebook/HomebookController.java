package org.vision.homebook;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.vision.tmember.MemberDAO;
import org.vision.tmember.MemberVO;
/// 수정이 안되는 문제가 (버그) 있음 (2022 년 8월 5일 현재)


@WebServlet("/homebook/*")
public class HomebookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HomebookDAO homebookDAO; 
	String mem_id=null;
  	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		homebookDAO  = new HomebookDAO();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doHandle(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doHandle(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void doHandle(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException, SQLException {
	//	HttpSession session = request.getSession();
		String nextPage = null;
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getPathInfo();
		System.out.println("action:" + action);
		mem_id = (String)session.getAttribute("login_id");
		System.out.println(mem_id);
		if(mem_id==null) {
			mem_id = "guest";
		}
		List<HomebookVO> homebookList=null;
		long serialno = 0;
		Date day = null;
		String section = null;
		String accounttitle = null;
		String remark = null;
		int revenue = 0;
		int expense = 0;
	//	String mem_id = null;
		////////////////////////////
	    HomebookVO homebookVO = null;
	    
		switch(action) {
		case "":
		case "/listHomebooks.do":// 등록된 가계부 정보 보기
//			request.setAttribute("mem_id", mem_id);
			homebookList = homebookDAO.selectAll(mem_id);
			request.setAttribute("homebookList", homebookList);
			nextPage = "/thomebook/listHomebooks.jsp";
			break; 
		case "/addHomebook.do": // 새로운 가계부 등록하기 
		//	serialno = Long.parseLong(request.getParameter("serialno"));
		//	day = Date.parseDate(request.getParameter("day"));
			section = request.getParameter("section");
			accounttitle = request.getParameter("accounttitle");
			remark = request.getParameter("remark");
			revenue = Integer.parseInt(request.getParameter("revenue"));
			expense = Integer.parseInt(request.getParameter("expense"));
			//mem_id = session.getId();//***********
			homebookVO = new HomebookVO(section,accounttitle,remark,revenue,expense,mem_id);
			homebookDAO.insert(homebookVO);
			request.setAttribute("msg", "addHomebook");

			nextPage = "/homebook/listHomebooks.do";
			break;
			
		case "/homebookForm.do": // 가계부 폼 열기  
			nextPage = "/thomebook/homebookForm.jsp";
			break; 
		case "/modHomebookForm.do": // 회원 정보 수정 폼 열기 
			serialno=Integer.parseInt(request.getParameter("serialno"));
		    HomebookVO bookInfo = homebookDAO.select(serialno);
		    request.setAttribute("bookInfo", bookInfo);
		    nextPage="/thomebook/modHomebookForm.jsp";
		    break;
		case "/modHomebook.do": // 수정폼에서 수정한 정보 디비로 확정짓기 
			 serialno = Long.parseLong(request.getParameter("serialno"));
			 String[] dayStr = (request.getParameter("day")).split("-");
			 day = new Date(Integer.parseInt(dayStr[0]),
					 Integer.parseInt(dayStr[1]),
					 Integer.parseInt(dayStr[2]));

			 section=request.getParameter("section");
		     accounttitle=request.getParameter("accounttitle");
		     remark= request.getParameter("remark");
	         revenue= Integer.parseInt(request.getParameter("revenue"));
	         expense= Integer.parseInt(request.getParameter("expense"));
	         //String section, String accounttitle, String remark, int revenue, int expense, String mem_id
		     homebookVO = new HomebookVO(serialno,section,accounttitle,remark,revenue,expense,mem_id);
		     homebookVO.setDay(day);
		     homebookDAO.update(homebookVO);
		     request.setAttribute("msg", "modified");
		     nextPage="/homebook/listHomebooks.do";
		     break;
		case "/delHomebook.do": // 회원 삭제 하기 
			 serialno=Long.parseLong(request.getParameter("serialno"));
		     homebookDAO.delete(serialno,mem_id);
		     request.setAttribute("msg", "deleted");
		     nextPage="/homebook/listHomebooks.do";
		     break;
		default:  // 유알엘 패턴이 위에 열거한것과 틀리거나 없을 때 
			homebookList = homebookDAO.selectAll(mem_id);
			request.setAttribute("homebookList", homebookList);
			nextPage = "/thomebook/listHomebooks.jsp";
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}

}
