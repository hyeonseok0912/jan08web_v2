package com.poseidon.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.MemberDAO;
import com.poseidon.dto.MemberDTO;

@WebServlet("/myInfo")
public class MyInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyInfo() {
    	super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("mid") != null) {
			//mid를 데이터베이스에 질의
			MemberDTO dto = new MemberDTO();
			dto.setMid((String) session.getAttribute("mid"));
			
			MemberDAO dao = new MemberDAO();
			//DTO에 담아서
			dto = dao.myInfo(dto);
			//myInfo.jsp에 찍어주도록 해주세요.
			request.setAttribute("myInfo", dto);
			
			//2024-01-23
			List<Map<String, Object>>readData = dao.readData(dto);
			request.setAttribute("readData", readData);
			
			RequestDispatcher rd = request.getRequestDispatcher("myInfo.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect("./login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDTO dto = new MemberDTO();
		MemberDAO dao = new MemberDAO();
		String newPW = request.getParameter("newPW");
		//세션에 있는 mid랑 newpw를 dto에 담아서 dao에 대기
		
		dto.setMid((String) session.getAttribute("mid"));
		dto.setMpw(newPW);
		int result = dao.changePW(dto);
		
		if(result == 1) {
			response.sendRedirect("./board");
		} else {
			response.sendRedirect("./error.jsp");
		}

	}

}
