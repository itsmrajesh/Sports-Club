package com.sportsclub.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class BookingSports {
	private String sid;
	private String userid;
	private String bookingdate;
	private String bookingtime;
	private String bookingid;
	private int bookingprice;
	private int scid;
}
