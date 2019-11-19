package com.sportsclub.service;

import com.sportsclub.domain.Profile;

public interface SportsClubService {
	boolean addUser(Profile p);

	boolean validateUser(String email, String password);
}
