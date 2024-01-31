package com.poseidon.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.AdminDAO;
import com.poseidon.dao.CommentDAO;
import com.poseidon.dto.CommentDTO;
import com.poseidon.util.Util;

@WebServlet("/comment")
public class Comment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Comment() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String commentcontent = request.getParameter("commentcontent"); //댓글내용
		
		//HTML에서 태그를 특수기호로 변경하기
		commentcontent = Util.removeTag(commentcontent);
		
		//줄바꿈 처리해주기(엔터) /r /n /nr
		commentcontent = Util.addBR(commentcontent);
		
		String bno = request.getParameter("bno"); //글번호
		
		if(session.getAttribute("mid") != null && commentcontent != null && bno != null) {
			CommentDTO dto = new CommentDTO();
			
			dto.setComment(commentcontent);
			dto.setBoard_no(Util.str2Int2(bno));
			dto.setMid((String) session.getAttribute("mid"));
			dto.setIp(Util.getIP(request));
			
			CommentDAO dao = new CommentDAO();
			int result = dao.commentWrite(dto);
			
			if(result == 1) {
				//System.out.println("성공");
				response.sendRedirect("./detail?no=" + bno);			
			} else {
				//System.out.println("실패");
				response.sendRedirect("./error.jsp");
			}
		} else {
			response.sendRedirect("./login");
		}
		//한글처리
		//오는 값 받기

		//System.out.println(commentcontent + " : " + bno);

		//저장해주세요
		
		
		//이동해주세요
		//response.sendRedirect("./detail?no=" + bno);
	}

}
