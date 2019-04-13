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
import com.sportsclub.dao.UserAccountDao;
import com.sportsclub.dao.UserAccountDaoImpl;
import com.sportsclub.domain.Profile;
import com.sportsclub.domain.Sports;
import com.sportsclub.idservice.MemberIDGenerator;
import com.sportsclub.service.SportsClubService;
import com.sportsclub.service.SportsClubServiceImpl;
import com.sportsclub.shared_dao.SharedDaoImpl;
import com.sportsclub.usersession.UserSession;

/**
 * Servlet implementation class UserActions
 */
@WebServlet(urlPatterns = { "/signup", "/login", "/logout", "/changepwd", "/changeemail", "/changephone",
		"/dashboard" })
public class UserActions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberIDGenerator idgenerator = new MemberIDGenerator();
	private SportsClubService sportsClubService = new SportsClubServiceImpl();
	private UserAccountDao sportsdao = new UserAccountDaoImpl();
	private AdminDao adminDao = new AdminDaoImpl();
	private SharedDaoImpl sharedDao = new SharedDaoImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String url = request.getRequestURI();
		if (url.endsWith("signup")) {
			String name = request.getParameter("name").toUpperCase();
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String dob = request.getParameter("dob");
			long mobile = Long.parseLong(request.getParameter("mobile"));
			String address = request.getParameter("address");
			String userId = idgenerator.getID(); // System Generated
			Profile profile = Profile.builder().name(name).email(email).password(password).dob(dob).mobile(mobile)
					.address(address).userId(userId).build();
			boolean status = sportsClubService.addUser(profile);
			if (status) {
				response.sendRedirect("signupsuccess.html");
			} else {
				response.sendRedirect("login.html#toregister");
			}
		}

		else if (url.endsWith("login")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			if (email.equalsIgnoreCase("admin@ncet.com") && password.equals("ncet")) {
				response.sendRedirect("admin.html");
			} else if (sportsClubService.validateUser(email, password)) {
				HttpSession hs = request.getSession();
				String uid = sportsdao.getUserID(email);
				hs.setAttribute("uid", uid);
				Profile p = sportsdao.getUserDetails(uid);
				hs.setAttribute("name", p.getName());
				response.sendRedirect("dashboard");
			} else {
				response.sendRedirect("invalid.html");
			}
		} else if (url.endsWith("changepwd")) {
			String uid = (String) session.getAttribute("uid");
			String newPassword = request.getParameter("newpwd");
			String newcPassword = request.getParameter("newcpwd");
			String oldPassword = request.getParameter("oldpwd");
			boolean status = false;
			if (newPassword.equals(newcPassword)) {
				status = sportsdao.changePassword(uid, newPassword, oldPassword);
			}
			if (status) {
				request.setAttribute("status", "SUCCESS");
				RequestDispatcher rd = request.getRequestDispatcher("status.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("status", "FAILURE");
				RequestDispatcher rd = request.getRequestDispatcher("status.jsp");
				rd.forward(request, response);
			}

		} else if (url.endsWith("changeemail")) {
			String uid = (String) session.getAttribute("uid");
			String newEmail = request.getParameter("newemail");
			if (sportsdao.updateEmail(uid, newEmail)) {
				request.setAttribute("status", "SUCCESS");
				RequestDispatcher rd = request.getRequestDispatcher("status.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("status", "FAILURE");
				RequestDispatcher rd = request.getRequestDispatcher("status.jsp");
				rd.forward(request, response);
			}
		} else if (url.endsWith("changephone")) {
			String uid = (String) session.getAttribute("uid");
			long newNumber = Long.parseLong(request.getParameter("newphone"));
			if (sportsdao.updatePhone(uid, newNumber)) {
				request.setAttribute("status", "SUCCESS");
				RequestDispatcher rd = request.getRequestDispatcher("status.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("status", "FAILURE");
				RequestDispatcher rd = request.getRequestDispatcher("status.jsp");
				rd.forward(request, response);
			}

		} else if (url.endsWith("dashboard")) {
			List<Sports> allSports = sharedDao.getAllSports();
			request.setAttribute("allSports", allSports);
			RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
			rd.forward(request, response);
		} else if (url.endsWith("logout")) {
			HttpSession h = request.getSession();
			h.removeAttribute("userid");
			// h.invalidate();
			response.sendRedirect("home.html");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
