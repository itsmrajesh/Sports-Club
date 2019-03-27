package com.sportsclub.idservice;

import com.sportsclub.dao.SportsClubDao;
import com.sportsclub.dao.SportsClubDaoImpl;

public class SportsIDGenerator {
	private String prefix = "S";
	private static int count = 100;
	private SportsClubDao sportsclubdao = new SportsClubDaoImpl();

	public String getSportID() {
		int localCount = SportsIDGenerator.count;
		localCount++;
		String id = prefix + localCount;
		int c = sportsclubdao.getSportsCount();
		if (c == 0) {
			return id;
		} else {
			localCount = c++;
		}
		id = prefix + localCount;
		return id;
	}
}
