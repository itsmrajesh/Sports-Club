package com.sportsclub.webadmin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sportsclub.admindao.AdminDao;
import com.sportsclub.admindao.AdminDaoImpl;
import com.sportsclub.domain.Sports;
import com.sportsclub.idservice.SportsIDGenerator;

/**
 * Servlet implementation class AddSports
 */
@WebServlet("/addsports")
public class AddSports extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminDao adminDao = new AdminDaoImpl();

	private SportsIDGenerator sid=new SportsIDGenerator();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sId=sid.getSportID();
		String sName = request.getParameter("sname");
		String sClub = request.getParameter("sclub");
		double sPrice = Double.parseDouble(request.getParameter("sprice"));
		int players = Integer.parseInt(request.getParameter("players"));
		String sType = request.getParameter("stype");
		Sports sports= Sports.builder().sId(sId).sName(sName).sClub(sClub).sPrice(sPrice).players(players).sType(sType).build();
		adminDao.addSport(sports);
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
