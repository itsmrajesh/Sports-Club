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
import com.sportsclub.shared_dao.SharedDaoImpl;

/**
 * Servlet implementation class UserBooking
 */
@WebServlet(urlPatterns = { "/book", "/booksport", "/viewbooking" })
public class UserBooking extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminDao adminDao = new AdminDaoImpl();
	private BookingService bs = new BookingService();
	private UserAccountDao sd = new UserAccountDaoImpl();
	private SharedDaoImpl sharedDao = new SharedDaoImpl();
	private BookingDao bookingDao = new BookingDaoImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURI();
		RequestDispatcher rd;
		HttpSession hs = request.getSession();
		String sid = request.getParameter("sid");
		String uid = hs.getAttribute("uid").toString();
		if (url.endsWith("book")) {
			hs.setAttribute("sid", sid);
			List<Sports> searchSports = sharedDao.searchSports(sid);
			request.setAttribute("allSports", searchSports);
			rd = request.getRequestDispatcher("booking.jsp");
			rd.forward(request, response);
		} else if (url.endsWith("booksport")) {
			String sId = hs.getAttribute("sid").toString();
			String date = request.getParameter("date"); // input from user
			String time = request.getParameter("time"); // input from user
			if (bs.doBooking(sId, uid, date, time)) {
				request.setAttribute("status", "SUCCESS");
				rd = request.getRequestDispatcher("status.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("status", "FAILURE");
				rd = request.getRequestDispatcher("status.jsp");
				rd.forward(request, response);
			}
		} else if (url.endsWith("viewbooking")) {
			uid = hs.getAttribute("uid").toString();
			List<BookingSports> bookinglist = bookingDao.getUserBooking(uid);
			request.setAttribute("booking", bookinglist);
			rd = request.getRequestDispatcher("vewbooking.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
