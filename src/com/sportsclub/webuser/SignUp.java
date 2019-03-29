package com.sportsclub.webuser;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sportsclub.domain.Profile;
import com.sportsclub.idservice.MemberIDGenerator;
import com.sportsclub.service.SportsClubService;
import com.sportsclub.service.SportsClubServiceImpl;

@WebServlet("/signup")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberIDGenerator idgenerator = new MemberIDGenerator();
	private SportsClubService sportsClubService = new SportsClubServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		int dob = Integer.parseInt(request.getParameter("dob"));
		long mobile = Long.parseLong(request.getParameter("mobile"));
		String address = request.getParameter("address");
		String userId = idgenerator.getID();
		Profile profile = Profile.builder().name(name).email(email).password(password).dob(dob).mobile(mobile)
				.address(address).userId(userId).build();
		sportsClubService.addUser(profile);
		
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
