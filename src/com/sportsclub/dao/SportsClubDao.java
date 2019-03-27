package com.sportsclub.dao;

import com.sportsclub.domain.Profile;

public interface SportsClubDao {
	int getUsersCount();
	int getSportsCount();
	boolean adduser(Profile p);
	boolean validateuser(String email,String password);
	boolean changePassword(String uId,String newPassword);
	boolean updateEmail(String uId,String newEmail);
	boolean updatePhone(String uId,String newPhone);
}
