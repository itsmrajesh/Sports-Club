package com.sportsclub.webadmin;

import java.util.List;
import com.sportsclub.admindao.AdminDao;
import com.sportsclub.admindao.AdminDaoImpl;
import com.sportsclub.domain.Sports;

public class Validations {
	private AdminDao adminDao = new AdminDaoImpl();

	public boolean isSportPresent(int scid, String sName, int players) {
		List<Sports> sportslist = adminDao.getSports(scid);
		for (Sports s : sportslist) {
			if (s.getSname().equalsIgnoreCase(sName) && s.getPlayers() == players) {
				return true;
			}
		}
		return false;
	}
}
