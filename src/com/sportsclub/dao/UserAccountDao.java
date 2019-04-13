package com.sportsclub.dao;

import java.util.List;

import com.sportsclub.domain.BookingSports;
import com.sportsclub.domain.Profile;

public interface UserAccountDao {
	int getUsersCount();
	
	boolean adduser(Profile p);

	String getUserID(String email);

	boolean validateUser(String email, String password);

	boolean changePassword(String uId, String newPassword,String oldPassword);

	boolean updateEmail(String uId, String newEmail);

	boolean updatePhone(String uId, long newPhone);
	
	Profile getUserDetails(String uid);
	
	
}
