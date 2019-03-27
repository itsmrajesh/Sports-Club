package com.sportsclub.initdata;

import java.util.ArrayList;
import java.util.List;

import com.sportsclub.admindao.AdminDao;
import com.sportsclub.admindao.AdminDaoImpl;
import com.sportsclub.domain.Sports;

public class InitServices {
	
	private static AdminDao adi = new AdminDaoImpl();

	private InitServices() {

	}

	private static InitServices obj;

	public static InitServices getInstance() {
		if (obj == null) {
			return new InitServices();
		}
		return obj;
	}

	static {
		loadAllSports();
	}

	public static List<Sports> loadAllSports() {
		List<Sports> list = new ArrayList<>();
		list = adi.getAllSports();
		return list;
	}
}
