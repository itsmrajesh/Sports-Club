package com.sportsclub.service;

import com.sportsclub.dao.SportsClubDao;
import com.sportsclub.dao.SportsClubDaoImpl;
import com.sportsclub.domain.Profile;

public class SportsClubServiceImpl implements SportsClubService {
	private SportsClubDao sportsclubdao = new SportsClubDaoImpl();

	@Override
	public boolean addUser(Profile p) {

		if (p != null) {
			return sportsclubdao.adduser(p);
		}
		return false;
	}

	public boolean validateuser(String email, String password) {
		return sportsclubdao.validateuser(email, password);
	}
}
