package com.sportsclub.webadmin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sportsclub.admindao.AdminDao;
import com.sportsclub.admindao.AdminDaoImpl;
import com.sportsclub.domain.Sports;
import com.sportsclub.domain.SportsClubs;
import com.sportsclub.idservice.SportsIDGenerator;
import com.sportsclub.shared_dao.SharedDaoImpl;

@WebServlet(urlPatterns = { "/addnewsport", "/viewsports", "/searchsports", "/addresults", "/sportsclubs",
		"/addnewsportclub", "/add" })
public class AdminServices extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminDao adminDao = new AdminDaoImpl();
	private SharedDaoImpl sharedDao = new SharedDaoImpl();
	private SportsIDGenerator sid = new SportsIDGenerator();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = request.getRequestURI();
		System.out.println("url is " + url);
		if (url.endsWith("addnewsport")) {
			String sId = sid.getSportID(); // Auto generated ID
			String sName = request.getParameter("sname");
			HttpSession hs = request.getSession();
			String scID = hs.getAttribute("scid").toString();
			int scid = Integer.parseInt(scID);
			String sClub = sharedDao.getSportClubName(scid);
			System.out.println("SCNAME is " + sClub);
			int sPrice = Integer.parseInt(request.getParameter("sprice"));
			int players = Integer.parseInt(request.getParameter("players"));
			String sType = request.getParameter("stype");
			Sports sports = Sports.builder().sid(sId).sname(sName).sclub(sClub).sprice(sPrice).players(players)
					.stype(sType).scid(scid).build();
			String status;
			if (adminDao.addSport(sports)) {
				request.setAttribute("status", "SUCCESS");
				RequestDispatcher rd = request.getRequestDispatcher("sportaddedmessage.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("status", "FAILURE");
				RequestDispatcher rd = request.getRequestDispatcher("sportaddedmessage.jsp");
				rd.forward(request, response);
			}

		} else if (url.endsWith("viewsports")) {
			List<Sports> allSports = sharedDao.getAllSports();
			request.setAttribute("allSports", allSports);
			RequestDispatcher rd = request.getRequestDispatcher("viewsports.jsp");
			rd.forward(request, response);

		} else if (url.endsWith("searchsports")) {
			String sId = request.getParameter("sid");
			List<Sports> searchSports = sharedDao.searchSports(sId);
			request.setAttribute("allSports", searchSports);
			RequestDispatcher rd = request.getRequestDispatcher("viewsports.jsp");
			rd.forward(request, response);
			
			
		} else if (url.endsWith("sportsclubs")) {
			List<SportsClubs> sportsClubList = sharedDao.getAllSportsClubs();
			request.setAttribute("sportsclubs", sportsClubList);
			RequestDispatcher rd = request.getRequestDispatcher("addsportsclub.jsp");
			rd.forward(request, response);

		} else if (url.endsWith("addnewsportclub")) {
			String scname = request.getParameter("scname");
			String location = request.getParameter("location");
			String contactNumber=request.getParameter("contactnumber");
			if (adminDao.addSportsClub(scname,location,contactNumber)) {
				response.sendRedirect("sportsclubs");
			} else {
				response.getWriter().append("status : failure");
			}
		} else if (url.endsWith("add")) {
			HttpSession hs = request.getSession();
			String scid = request.getParameter("scid");
			hs.setAttribute("scid", scid);
			response.sendRedirect("addsports.html");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
