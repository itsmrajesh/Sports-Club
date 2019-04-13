package com.sportsclub.idservice;

import com.sportsclub.dao.UserAccountDao;
import com.sportsclub.dao.UserAccountDaoImpl;

public class MemberIDGenerator {
	private String prefix = "SCM";
	private int year = 19;
	private String userType = "U";
	private static int count = 100;
	private UserAccountDao sportsclubdao = new UserAccountDaoImpl();

	public String getID() {
		int localCount = MemberIDGenerator.count;
		localCount++;
		String id = prefix + year + userType + localCount;
		int c = sportsclubdao.getUsersCount();
		if (c == 0) {
			return id;
		} else {
			localCount += c;
			return prefix + year + userType + localCount;
		}

	}
}
