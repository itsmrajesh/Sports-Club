package com.sportsclub.service;

import com.sportsclub.domain.Profile;

public interface SportsClubService {
	boolean addUser(Profile p);
	boolean validateuser(String email, String password);
}
