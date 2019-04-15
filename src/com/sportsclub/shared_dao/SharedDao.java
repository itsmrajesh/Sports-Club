package com.sportsclub.shared_dao;

import java.util.List;

import com.sportsclub.domain.Sports;
import com.sportsclub.domain.SportsClubs;

public interface SharedDao {
	
	List<Sports> getAllSports();
	
	List<Sports> searchSports(String sID);
	
	List<SportsClubs> getAllSportsClubs();
	
	String getSportClubName(int scid);
	
	List<Sports> getAllSportsByType(int scid,String stype);

}
