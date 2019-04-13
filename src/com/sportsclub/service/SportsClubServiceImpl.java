package com.sportsclub.service;

import com.sportsclub.dao.UserAccountDao;
import com.sportsclub.dao.UserAccountDaoImpl;
import com.sportsclub.domain.Profile;

public class SportsClubServiceImpl implements SportsClubService {
	private UserAccountDao sportsclubdao = new UserAccountDaoImpl();

	@Override
	public boolean addUser(Profile p) {

		if (p != null) {
			return sportsclubdao.adduser(p);
		}
		return false;
	}

	public boolean validateUser(String email, String password) {
		if (email != " " && password != " ") {
			return sportsclubdao.validateUser(email, password);
		}
		return false;
	}
}
