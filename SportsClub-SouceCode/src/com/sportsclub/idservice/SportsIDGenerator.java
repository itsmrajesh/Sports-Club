package com.sportsclub.idservice;

import com.sportsclub.admindao.AdminDaoImpl;
import com.sportsclub.dao.UserAccountDao;
import com.sportsclub.dao.UserAccountDaoImpl;

public class SportsIDGenerator {
	private String prefix = "S";
	private static int count = 100;
	private UserAccountDao sportsclubdao = new UserAccountDaoImpl();
	private AdminDaoImpl adminDao = new AdminDaoImpl();

	public String getSportID() {
		int localCount = SportsIDGenerator.count;
		localCount++;
		String id = prefix + localCount;
		int c = adminDao.getSportsCount();
		if (c == 0) {
			return id;
		} else {
			localCount += c;
			return prefix + localCount;
		}
	}
}
