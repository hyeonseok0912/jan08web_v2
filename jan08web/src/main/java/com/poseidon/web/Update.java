package com.poseidon.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.SendResult;

import com.poseidon.dao.BoardDAO;
import com.poseidon.dto.BoardDTO;
import com.poseidon.util.Util;

@WebServlet("/update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Update() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("mid") != null) {
			//세션이 있을때 = 정상작업 하기
			int no = Util.str2Int(request.getParameter("no"));
			BoardDAO dao = new BoardDAO();
			BoardDTO dto = dao.detail(no);
			request.setAttribute("update", dto);
			//System.out.println(dto.getMid());
			if (((String) session.getAttribute("mid")).equals(dto.getMid())) {
				request.setAttribute("update", dto);
				RequestDispatcher rd = request.getRequestDispatcher("update.jsp");
				rd.forward(request, response);
			} else {
				response.sendRedirect("./error.jsp");
			}
		} else {
			response.sendRedirect("./login?login=nologin");//로그인하지 않았을시 로그인 페이지로 이동
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		if(request.getParameter("title")!= null
				&& request.getParameter("content") != null
				&& Util.intCheck(request.getParameter("no")) && session.getAttribute("mid") != null) {
			//진짜 수정	
			BoardDTO dto = new BoardDTO();
			dto.setContent(request.getParameter("content"));
			dto.setTitle(request.getParameter("title"));
			dto.setNo(Util.str2Int(request.getParameter("no")));
			dto.setMid((String) session.getAttribute("mid"));
			
			BoardDAO dao = new BoardDAO();
			int result = dao.update(dto);
			//System.out.println(result);
			response.sendRedirect("./detail?no=" + request.getParameter("no"));
		}else {
			//error
			response.sendRedirect("./error.jsp");
		}
//		String title = request.getParameter("title");
//		String content = request.getParameter("content");
//		String no = request.getParameter("no");
//		response.sendRedirect("./detail?no=");
	}

}
