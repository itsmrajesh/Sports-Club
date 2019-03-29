package com.sportsclub.webuser;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sportsclub.domain.Profile;
import com.sportsclub.idservice.MemberIDGenerator;
import com.sportsclub.service.SportsClubService;
import com.sportsclub.service.SportsClubServiceImpl;
import com.sportsclub.usersession.UserSession;

/**
 * Servlet implementation class UserActions
 */
@WebServlet(urlPatterns = { "/signup", "/login", "/logout" })
public class UserActions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberIDGenerator idgenerator = new MemberIDGenerator();
	private SportsClubService sportsClubService = new SportsClubServiceImpl();
	private UserSession userSession = UserSession.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURI();
		{

		}
		if (url.endsWith("signup")) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			int dob = Integer.parseInt(request.getParameter("dob"));
			long mobile = Long.parseLong(request.getParameter("mobile"));
			String address = request.getParameter("address");
			String userId = idgenerator.getID(); // System Generated
			Profile profile = Profile.builder().name(name).email(email).password(password).dob(dob).mobile(mobile)
					.address(address).userId(userId).build();
			sportsClubService.addUser(profile);
		}

		else if (url.endsWith("login")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			if (email.equalsIgnoreCase("admin@ncet") && password.equals("ncet")) {
				// Admin Login
			} else if (sportsClubService.validateUser(email, password)) {
				String uid = userSession.getUserID();
				HttpSession hs = request.getSession();
				hs.setAttribute("userid", uid);
				// User Login goes here
			}
		}

		else if (url.endsWith("logout")) {
			HttpSession hs = request.getSession();
			hs.removeAttribute("userid");
		}

		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
