package com.sportsclub.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Profile {
	private String name;
	private String email;
	private String password;
	private int dob;
	private long mobile;
	private String address;
	private String userId;
	
}
