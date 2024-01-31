package com.poseidon.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.BoardDAO;
import com.poseidon.dto.BoardDTO;
import com.poseidon.util.Util;

@WebServlet("/delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Delete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//글 삭제하기 2024-01-11 + 2024-01-16 로그인 한 사용자 + 내글
		HttpSession session = request.getSession();
		if(Util.intCheck(request.getParameter("no")) && session.getAttribute("mid") != null) {
			int no = Util.str2Int(request.getParameter("no"));
			BoardDAO dao = new BoardDAO();
			// board_no, mid가 같이 있는 클래스는 BoardDTO
			BoardDTO dto = new BoardDTO();
			dto.setNo(no);
			dto.setMid((String) session.getAttribute("mid"));
			int result = dao.delete(dto);
			//System.out.println("삭제 여부 : " + result);
			if(result == 1) {
				response.sendRedirect("./board");
			}else {
				response.sendRedirect("./error.jsp");
			}
			
		}else {
			//숫자가 아니네? -> 에러 표시
			//System.out.println("문자입니다.");
			response.sendRedirect("./error.jsp");
		}
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
