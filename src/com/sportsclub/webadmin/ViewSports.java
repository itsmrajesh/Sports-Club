package com.sportsclub.webadmin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sportsclub.admindao.AdminDao;
import com.sportsclub.admindao.AdminDaoImpl;
import com.sportsclub.domain.Sports;

/**
 * Servlet implementation class ViewSports
 */
//@WebServlet("/viewsports")
public class ViewSports extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminDao adminDao = new AdminDaoImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Sports> allSports = adminDao.getAllSports();
		request.setAttribute("allSports",allSports);
		RequestDispatcher rd = request.getRequestDispatcher("viewsports.jsp");
		rd.forward(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
