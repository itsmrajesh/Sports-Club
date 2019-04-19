package com.sportsclub.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Mail {
	private String hostmailaddress;
	private String recipientmailaddress;
	private String message;
	private String hostpassword;
	private String messageid;
}
