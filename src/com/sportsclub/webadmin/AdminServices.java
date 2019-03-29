package com.sportsclub.webadmin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class AdminServices
 */
@WebServlet(urlPatterns = { "/addsports", "/viewsports", "/searchsports", "/addresults" })
public class AdminServices extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminDao adminDao = new AdminDaoImpl();

	private SportsIDGenerator sid = new SportsIDGenerator();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = request.getRequestURI();
		System.out.println("url is "+url);
		if (url.endsWith("addsports")) {
			String sId = sid.getSportID(); // Auto generated ID
			String sName = request.getParameter("sname");
			String sClub = request.getParameter("sclub");
			double sPrice = Double.parseDouble(request.getParameter("sprice"));
			int players = Integer.parseInt(request.getParameter("players"));
			String sType = request.getParameter("stype");
			Sports sports = Sports.builder().sId(sId).sName(sName).sClub(sClub).sPrice(sPrice).players(players)
					.sType(sType).build();
			String status;
			if(adminDao.addSport(sports)) {
				status ="Success";
				response.getWriter().append("status : "+status);
			}
			else {
				status="Failure";
				response.getWriter().append("status : "+status);
			}

		} else if (url.endsWith("viewsports")) {
			List<Sports> allSports = adminDao.getAllSports();
			request.setAttribute("allSports", allSports);
			RequestDispatcher rd = request.getRequestDispatcher("viewsports.jsp");
			rd.forward(request, response);

		} else if (url.endsWith("searchsports")) {
			String sId = request.getParameter("sid");
			List<Sports> searchSports = adminDao.searchSports(sId);
			request.setAttribute("allSports", searchSports);
			RequestDispatcher rd = request.getRequestDispatcher("viewsports.jsp");
			rd.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
