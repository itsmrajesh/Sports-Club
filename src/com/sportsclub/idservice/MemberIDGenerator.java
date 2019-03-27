package com.sportsclub.idservice;

import com.sportsclub.dao.SportsClubDao;
import com.sportsclub.dao.SportsClubDaoImpl;

public class MemberIDGenerator {
	private String prefix = "SCM";
	private int year = 19;
	private String userType = "U";
	private static int count = 100;
	private SportsClubDao sportsclubdao = new SportsClubDaoImpl();

	public String getID() {
		int localCount = MemberIDGenerator.count;
		localCount++;
		String id = prefix + year + userType + localCount;
		int c = sportsclubdao.getUsersCount();
		if (c == 0) {
			return id;
		} else {
			localCount = c++;
		}
		id = prefix + year + userType + localCount;
		return id;
	}
}
