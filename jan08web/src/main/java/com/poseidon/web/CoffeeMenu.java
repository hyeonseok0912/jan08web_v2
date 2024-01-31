package com.poseidon.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.BoardDAO;
import com.poseidon.dao.CoffeeDAO;
import com.poseidon.dto.BoardDTO;
import com.poseidon.dto.CoffeeDTO;
import com.poseidon.util.Util;

@WebServlet("/order")
public class CoffeeMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CoffeeMenu() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		int result = 0;
		String menu = request.getParameter("menu");
		 
		if (session.getAttribute("mid") == null || session.getAttribute("mname") == null) {
			// 로그인 하지 않았다면 login으로 가게 하겠습니다.
			response.sendRedirect("./login?login=nologin");
		} else {
			CoffeeDTO dto = new CoffeeDTO();
			CoffeeDAO dao = new CoffeeDAO();
			
			if (menu != null) {
				// 여기에서 메뉴에 따라 다른 처리를 수행
				if ("icec".equals(menu)) {
					// 아이스 아메리카노 선택 시의 처리
					dto.setIcec(1);
					result = dao.order(dto);
				} else if ("hotc".equals(menu)) {
					// 뜨거운 아메리카노 선택 시의 처리
					dto.setHotc(1);
					result = dao.order(dto);
				} else if ("icet".equals(menu)) {
					// 차가운 차 선택 시의 처리
					dto.setIcet(1);
					result = dao.order(dto);
				} else if ("hott".equals(menu)) {
					// 따뜻한 차 선택 시의 처리
					dto.setHott(1);
					result = dao.order(dto);
				} else if ("none".equals(menu)) {
					// 안먹어요 선택 시의 처리
					dto.setNone(1);
					result = dao.order(dto);
				}
				//System.out.println("result 값은 ? : " + result);
				if(result == 1) {
					RequestDispatcher rd = request.getRequestDispatcher("coffee.jsp");
					rd.forward(request, response);
				}else {
					response.sendRedirect("./error.jsp");
				}
			}
		}
	}
}
