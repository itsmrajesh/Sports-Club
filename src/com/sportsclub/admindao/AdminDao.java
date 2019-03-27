package com.sportsclub.admindao;

import java.util.List;

import com.sportsclub.domain.Sports;

public interface AdminDao {
	boolean addSport(Sports s);
	List<Sports> getAllSports();
	List<Sports> searchSports(String sId);
	
}
