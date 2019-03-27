package com.sportsclub.usersession;

import java.util.ArrayList;
import java.util.List;

import com.sportsclub.domain.Profile;

public class UserSession {

	private UserSession() {

	}

	private static UserSession obj;

	public static UserSession getInstance() {
		if (obj == null) {
			return new UserSession();
		}
		return obj;
	}
	List<Profile> userList = new ArrayList<>();
	
	
}
