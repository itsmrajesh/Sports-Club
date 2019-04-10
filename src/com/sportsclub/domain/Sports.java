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
	private String sid;
	private String sname;
	private String sclub;
	private int sprice;
	private int players;
	private String stype; // indoor or outdoor
}
