package com.sportsclub.usersession;

import java.util.ArrayList;
import java.util.List;

import com.sportsclub.domain.Profile;

public class UserSession {

	
	private UserSession() {

	}

	private static UserSession obj;

	public static synchronized UserSession getInstance() {
		if (obj == null) {
			return new UserSession();
		}
		return obj;
	}

	private List<Profile> userList = new ArrayList<>();	
	
	public String getUserID() {
		String userID = null;
		for(Profile pro:userList) {
			userID=pro.getUserid();
		}
		return userID;
	}
	
	public void logedUser(Profile p) {
		userList.add(p);
	}
	
	
	
}
