package com.poseidon.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseidon.dao.AdminDAO;
import com.poseidon.dto.BoardDTO;

@WebServlet("/admin/ip")
public class Ip extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Ip() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println(request.getParameter("ip"));
		//System.out.println("ipsearch : " + request.getParameter("search"));
		
		//ip검색 기능
		AdminDAO dao = new AdminDAO();
		List<Map<String, Object>> list = null;
		if(request.getParameter("search") != null && !request.getParameter("search").equals("")) {
			list = dao.ipSearch(request.getParameter("search"));
		} else {			
			list = dao.ipList();
		}
		request.setAttribute("list", list);
		
		//List<Map<String, Object>> list = dao.ipList();
		//List<Map<String, Object>> list2 = dao.ipList2();
		//List<Map<String, Object>> list3 = dao.ipList3();
		//List<Map<String, Object>> list4 = dao.ipList4();
		//List<Map<String, Object>> list = null;
		
//		if(request.getParameter("ip") != null && !request.getParameter("ip").equals("")) {
//			list = dao.ipList(request.getParameter("ip"));
//		}else {
//			list = dao.ipList();
//		}
		
		request.setAttribute("list", list);
		request.setAttribute("list2", dao.ipList2());
		request.setAttribute("list3", dao.ipList3());
		request.setAttribute("list4", dao.ipList4());
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/ip.jsp");//파일 있는 경로
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
