package com.sportsclub.dao;

import com.sportsclub.domain.Profile;

public interface SportsClubDao {
	int getUsersCount();

	int getSportsCount();

	boolean adduser(Profile p);

	String getUserID(String email);

	boolean validateUser(String email, String password);

	boolean changePassword(String uId, String newPassword,String oldPassword);

	boolean updateEmail(String uId, String newEmail);

	boolean updatePhone(String uId, long newPhone);
}
