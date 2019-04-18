package com.sportsclub.admindao;

import java.util.List;

import com.sportsclub.domain.Sports;
import com.sportsclub.domain.SportsClubs;

public interface AdminDao {
	boolean addSport(Sports s);

	int getSportsCount();

	int getSportPrice(String sID);

	boolean addSportsClub(String scname,String loaction,String contactNumber);
	
	List<Sports> getSports(int scid);
}
