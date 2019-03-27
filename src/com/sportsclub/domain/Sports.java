package com.sportsclub.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Sports {
	private String sId;
	private String sName;
	private String sClub;
	private double sPrice;
	private int players;
	private String sType; // indoor or outdoor
}
