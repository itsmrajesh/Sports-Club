package com.sportsclub.webuser;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sportsclub.dao.SportsClubDao;
import com.sportsclub.dao.SportsClubDaoImpl;
import com.sportsclub.domain.Profile;
import com.sportsclub.idservice.MemberIDGenerator;
import com.sportsclub.service.SportsClubService;
import com.sportsclub.service.SportsClubServiceImpl;
import com.sportsclub.usersession.UserSession;

/**
 * Servlet implementation class UserActions
 */
@WebServlet(urlPatterns = { "/signup", "/login", "/logout", "/changepwd", "/changeemail", "/changephone" })
public class UserActions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberIDGenerator idgenerator = new MemberIDGenerator();
	private SportsClubService sportsClubService = new SportsClubServiceImpl();
	private SportsClubDao sportsdao = new SportsClubDaoImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String message;
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
			}
		}

		else if (url.endsWith("login")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			if (email.equalsIgnoreCase("admin@ncet") && password.equals("ncet")) {
				response.sendRedirect("admin.html");
			} else if (sportsClubService.validateUser(email, password)) {
				HttpSession hs = request.getSession();
				String uid = sportsdao.getUserID(email);
				hs.setAttribute("uid", uid);
				response.sendRedirect("dashboard.html");
			} else {
				response.sendRedirect("invalid.html");
			}
		} else if (url.endsWith("changepwd")) {
			String uid = (String) session.getAttribute("uid");
			String newPassword = request.getParameter("newcpwd");
			String oldPassword = request.getParameter("oldpwd");
			boolean status = sportsdao.changePassword(uid, newPassword, oldPassword);
			if (status) {
				message = "SUCCESS";
			}

		} else if (url.endsWith("changeemail")) {
			String uid = (String) session.getAttribute("uid");
			String newEmail = request.getParameter("newemail");
			sportsdao.updateEmail(uid, newEmail);
		} else if (url.endsWith("changephone")) {
			String uid = (String) session.getAttribute("uid");
			long newNumber = Long.parseLong(request.getParameter("newphone"));
			sportsdao.updatePhone(uid, newNumber);

		} else if (url.endsWith("logout")) {

			HttpSession h = request.getSession();
			h.removeAttribute("userid");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
