package com.sportsclub.webadmin;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sportsclub.admindao.AdminDao;
import com.sportsclub.admindao.AdminDaoImpl;
import com.sportsclub.domain.BookingSports;
import com.sportsclub.domain.Sports;
import com.sportsclub.domain.SportsClubs;
import com.sportsclub.idservice.SportsIDGenerator;
import com.sportsclub.shared_dao.SharedDaoImpl;

@WebServlet(urlPatterns = { "/addnewsport", "/viewsports", "/searchsports", "/addresults", "/sportsclubs",
		"/addnewsportclub", "/add", "/showbookinggraph", "/searchbookings", "/viewbookingclubwise" })
public class AdminServices extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminDao adminDao = new AdminDaoImpl();
	private SharedDaoImpl sharedDao = new SharedDaoImpl();
	private SportsIDGenerator sid = new SportsIDGenerator();
	private Validations validations = new Validations();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = request.getRequestURI();
		System.out.println("url is " + url);
		if (url.endsWith("addnewsport")) {
			String sId = sid.getSportID(); // Auto generated ID
			String sName = request.getParameter("sname").toUpperCase();
			HttpSession hs = request.getSession();
			String scID = hs.getAttribute("scid").toString();
			int scid = Integer.parseInt(scID);
			String sClub = sharedDao.getSportClubName(scid);
			System.out.println("SCNAME is " + sClub);
			int sPrice = Integer.parseInt(request.getParameter("sprice"));
			int players = Integer.parseInt(request.getParameter("players"));
			String sType = request.getParameter("stype").toUpperCase();
			if (!validations.isSportPresent(scid, sName, players)) {
				Sports sports = Sports.builder().sid(sId).sname(sName).sclub(sClub).sprice(sPrice).players(players)
						.stype(sType).scid(scid).build();
				if (adminDao.addSport(sports)) {
					request.setAttribute("status", "SUCCESS");
					RequestDispatcher rd = request.getRequestDispatcher("sportaddedmessage.jsp");
					rd.forward(request, response);
				} else {
					request.setAttribute("status", "FAILURE");
					RequestDispatcher rd = request.getRequestDispatcher("sportaddedmessage.jsp");
					rd.forward(request, response);
				}
			} else {
				String message = "Its Seems that " + sName + " already Present in " + sClub
						+ " Club DataBase \n Thank You... :-)";
				request.setAttribute("status", message);
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
			RequestDispatcher rd = request.getRequestDispatcher("sportsclubs.jsp");
			rd.forward(request, response);

		} else if (url.endsWith("addnewsportclub")) {
			String scname = request.getParameter("scname");
			String location = request.getParameter("location");
			String contactNumber = request.getParameter("contactnumber");
			if (adminDao.addSportsClub(scname, location, contactNumber)) {
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

		else if (url.endsWith("showbookinggraph")) {
			Map<String, Integer> map = adminDao.getBookings();
			/*
			 * int totalCount = 0; Set<Entry<String, Integer>> mapentry = map.entrySet();
			 * for (Entry<String, Integer> entry : mapentry) { totalCount +=
			 * entry.getValue(); } HttpSession session = request.getSession();
			 * session.setAttribute("sum", totalCount);
			 */
			request.setAttribute("bookingmap", map);
			RequestDispatcher rd = request.getRequestDispatcher("bookinggraph.jsp");
			rd.forward(request, response);
		} else if (url.endsWith("searchbookings")) {
			String bid = request.getParameter("bid");
			List<BookingSports> searchList = adminDao.searchBookings(bid);
			request.setAttribute("booking", searchList);
			RequestDispatcher rd = request.getRequestDispatcher("viewbooking.jsp");
			rd.forward(request, response);
		}

		else if (url.endsWith("viewbookingclubwise")) {
			List<BookingSports> bookingList = adminDao.getUserBookingByClubWise();
			request.setAttribute("booking", bookingList);
			RequestDispatcher rd = request.getRequestDispatcher("viewbooking.jsp");
			rd.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
