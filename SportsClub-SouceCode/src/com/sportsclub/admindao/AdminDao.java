package com.sportsclub.admindao;

import java.util.List;
import java.util.Map;

import com.sportsclub.domain.BookingSports;
import com.sportsclub.domain.Mail;
import com.sportsclub.domain.Sports;
import com.sportsclub.domain.SportsClubs;

public interface AdminDao {
	boolean addSport(Sports s);

	int getSportsCount();

	int getSportPrice(String sID);

	boolean addSportsClub(String scname, String loaction, String contactNumber);

	List<Sports> getSports(int scid);

	Mail getMailAuthentication();

	Map<String, Integer> getBookings();
	
	List<BookingSports> getUserBookingByClubWise();
	
	List<BookingSports> searchBookings(String bid);
}
