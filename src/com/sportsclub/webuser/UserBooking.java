package com.sportsclub.webuser;

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
import com.sportsclub.booking.BookingService;
import com.sportsclub.bookingdao.BookingDao;
import com.sportsclub.bookingdao.BookingDaoImpl;
import com.sportsclub.dao.UserAccountDao;
import com.sportsclub.dao.UserAccountDaoImpl;
import com.sportsclub.domain.BookingSports;
import com.sportsclub.domain.Sports;
import com.sportsclub.domain.SportsClubs;
import com.sportsclub.shared_dao.SharedDaoImpl;

/**
 * Servlet implementation class UserBooking
 */
@WebServlet(urlPatterns = { "/viewsportsforbooking", "/booksport", "/viewbooking", "/dobooking" })
public class UserBooking extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminDao adminDao = new AdminDaoImpl();
	private BookingService bookingservice = new BookingService();
	private UserAccountDao sd = new UserAccountDaoImpl();
	private SharedDaoImpl sharedDao = new SharedDaoImpl();
	private BookingDao bookingDao = new BookingDaoImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURI();
		RequestDispatcher rd;
		HttpSession session = request.getSession();
		String uid = session.getAttribute("uid").toString();

		if (url.endsWith("viewsportsforbooking")) {
			int scid = Integer.parseInt(request.getParameter("scid"));
			session.setAttribute("scid", scid);
			List<Sports> viewSportsIndoor = sharedDao.getAllSportsByType(scid, "INDOOR");
			request.setAttribute("allSportsindoor", viewSportsIndoor);
			List<Sports> viewSportsOutdoor = sharedDao.getAllSportsByType(scid, "OUTDOOR");
			request.setAttribute("allSportsoutdoor", viewSportsOutdoor);
			List<SportsClubs> clubDetails = sharedDao.searchSportsClubs(scid);
			request.setAttribute("clubdetails", clubDetails);
			rd = request.getRequestDispatcher("booking.jsp");
			rd.forward(request, response);

		} else if (url.endsWith("booksport")) {
			int scid = Integer.parseInt(session.getAttribute("scid").toString());
			String sid = request.getParameter("sid");
			session.setAttribute("sid", sid);
			List<Sports> searchSportList = sharedDao.searchSports(sid);
			request.setAttribute("viewsport", searchSportList);
			List<SportsClubs> clubDetails = sharedDao.searchSportsClubs(scid);
			request.setAttribute("clubdetails", clubDetails);
			rd = request.getRequestDispatcher("dobooking.jsp");
			rd.forward(request, response);

		} else if (url.endsWith("dobooking")) {
			int scid = Integer.parseInt(session.getAttribute("scid").toString());
			String sid = session.getAttribute("sid").toString();
			String date = request.getParameter("date");
			String startTime = request.getParameter("time");
			if(bookingservice.doBooking(sid, uid, date, startTime, scid)) {
				response.sendRedirect("bookingsuccess.html");
			}
			else {
				
			}
		}

		else if (url.endsWith("viewbooking")) {
			uid = session.getAttribute("uid").toString();
			List<BookingSports> bookinglist = bookingDao.getUserBooking(uid);
			request.setAttribute("booking", bookinglist);
			rd = request.getRequestDispatcher("viewbooking.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
