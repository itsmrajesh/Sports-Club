package com.sportsclub.webuser;

import java.io.IOException;
import java.util.ArrayList;
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
import com.sportsclub.domain.SportsClubs;
import com.sportsclub.idservice.MemberIDGenerator;
import com.sportsclub.mailer.MailService;
import com.sportsclub.service.SportsClubService;
import com.sportsclub.service.SportsClubServiceImpl;
import com.sportsclub.shared_dao.SharedDaoImpl;
import com.sportsclub.usersession.UserSession;

/**
 * Servlet implementation class UserActions
 */
@WebServlet(urlPatterns = { "/signup", "/login", "/logout", "/changepwd", "/changeemail", "/changephone", "/dashboard",
		"/viewprofile" })
public class UserActions extends HttpServlet implements Runnable {
	private static final long serialVersionUID = 1L;
	private MemberIDGenerator idgenerator = new MemberIDGenerator();
	private SportsClubService sportsClubService = new SportsClubServiceImpl();
	private UserAccountDao userdao = new UserAccountDaoImpl();
	private AdminDao adminDao = new AdminDaoImpl();
	private SharedDaoImpl sharedDao = new SharedDaoImpl();

	private String email;
	private String message;
	private String subject;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserActions useractions = new UserActions();
		HttpSession session = request.getSession();
		Thread thread = new Thread(useractions);
		String url = request.getRequestURI();
		if (url.endsWith("signup")) {
			String name = request.getParameter("name").toUpperCase();
			String email = request.getParameter("email");
			session.setAttribute("email", email);
			String password = request.getParameter("password");
			String dob = request.getParameter("dob");
			long mobile = Long.parseLong(request.getParameter("mobile"));
			String address = request.getParameter("address");
			String userId = idgenerator.getID(); // System Generated
			Profile profile = Profile.builder().name(name).email(email).password(password).dob(dob).mobile(mobile)
					.address(address).userid(userId).build();
			boolean status = sportsClubService.addUser(profile);
			if (status) {
				useractions.setEmail(email);
				String message="Welcome to Sports Club System! <br> Book and Play Away. Thanks For Signing Up ";
				useractions.setMessage(message);
				String subject="SignUp Successfull ";
				useractions.setSubject(subject);
				thread.start();
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
				String uid = userdao.getUserID(email);
				hs.setAttribute("uid", uid);
				hs.setAttribute("email", email);
				Profile p = userdao.getUserDetails(uid);
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
				status = userdao.changePassword(uid, newPassword, oldPassword);
			}
			if (status) {
				String email=session.getAttribute("email").toString();
				useractions.setEmail(email);
				String message="<h2>Hi User</h2>,<br> <p>The Password For Sports Club Account has recently Changed</p>";
				useractions.setMessage(message);
				String subject="Your Password Changed ";
				useractions.setSubject(subject);
				thread.start();
				request.setAttribute("status", "SUCCESS");
				RequestDispatcher rd = request.getRequestDispatcher("status.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("status", "FAILURE");
				RequestDispatcher rd = request.getRequestDispatcher("status.jsp");
				rd.forward(request, response);
			}

		} else if (url.endsWith("changeemail")) {
			String uid = session.getAttribute("uid").toString();
			String newEmail = request.getParameter("newemail");
			if (userdao.updateEmail(uid, newEmail)) {
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
			if (userdao.updatePhone(uid, newNumber)) {
				request.setAttribute("status", "SUCCESS");
				RequestDispatcher rd = request.getRequestDispatcher("status.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("status", "FAILURE");
				RequestDispatcher rd = request.getRequestDispatcher("status.jsp");
				rd.forward(request, response);
			}

		} else if (url.endsWith("dashboard")) {
			List<SportsClubs> allSportsClubs = sharedDao.getAllSportsClubs();
			request.setAttribute("allSportsclubs", allSportsClubs);
			RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
			rd.forward(request, response);
		} else if (url.endsWith("viewprofile")) {
			String uid = session.getAttribute("uid").toString();
			Profile profile = userdao.getUserDetails(uid);
			List<Profile> list = new ArrayList<>();
			list.add(profile);
			request.setAttribute("userdetails", list);
			RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
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

	@Override
	public void run() {
		System.out.println("Your email is:-" + getEmail());
		MailService.sendMailService(getEmail(), getMessage(), getSubject());
	}

}
