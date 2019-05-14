package com.sportsclub.admindao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sportsclub.dbutil.DBUtil;
import com.sportsclub.domain.Mail;
import com.sportsclub.domain.Sports;
import com.sportsclub.domain.SportsClubs;

public class AdminDaoImpl implements AdminDao {
	DBUtil dbutil = DBUtil.obj;
	private Connection con;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;

	@Override
	public boolean addSport(Sports s) {
		String addSportsQuery = "INSERT INTO SPORTSDATA VALUES (?,?,?,?,?,?,?)";
		try {
			con = dbutil.getConnection();
			pst = con.prepareStatement(addSportsQuery);
			pst.setString(1, s.getSid());
			pst.setString(2, s.getSname());
			pst.setString(3, s.getSclub());
			pst.setInt(4, s.getSprice());
			pst.setInt(5, s.getPlayers());
			pst.setString(6, s.getStype());
			pst.setInt(7, s.getScid());
			int i = pst.executeUpdate();
			if (i > 0) {
				System.out.println("new Sport inserted.... for " + s.getSname());
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("failed to add.. for " + s.getSname());
		return false;
	}

	@Override
	public int getSportPrice(String sID) {
		String searchViewQuery = "SELECT sprice FROM SPORTSDATA WHERE SID=?";
		int sPrice = 0;
		try {
			con = dbutil.getConnection();
			pst = con.prepareStatement(searchViewQuery);
			pst.setString(1, sID);
			rs = pst.executeQuery();
			while (rs.next()) {
				sPrice = rs.getInt("sprice");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sPrice;
	}

	@Override
	public int getSportsCount() {
		String userCountQuery = "SELECT COUNT(SID) FROM SPORTSDATA ";
		int count = 0;
		try {
			con = dbutil.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(userCountQuery);
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("total count of sports is " + count);
		return count;
	}

	@Override
	public boolean addSportsClub(String scname, String location, String contactNumber) {
		String insertSportsClubQuery = "INSERT INTO SPORTSCLUBS (SCNAME,LOCATION,CONTACTNUMBER) VALUES (?,?,?)";
		try {
			con = dbutil.getConnection();
			pst = con.prepareStatement(insertSportsClubQuery);
			pst.setString(1, scname);
			pst.setString(2, location);
			pst.setString(3, contactNumber);
			int i = pst.executeUpdate();
			if (i == 1) {
				System.out.println("Sports Club added Successfully with name :- " + scname);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Sports> getSports(int scid) {
		String getSportQuery = "SELECT SNAME,SPRICE,PLAYERS FROM SPORTSDATA WHERE SCID=?";
		List<Sports> sportslist = new ArrayList<>();
		try {
			con = dbutil.getConnection();
			pst = con.prepareStatement(getSportQuery);
			pst.setInt(1, scid);
			rs = pst.executeQuery();
			while (rs.next()) {
				sportslist.add(
						Sports.builder().sname(rs.getString(1)).sprice(rs.getInt(2)).players(rs.getInt(3)).build());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sportslist;
	}

	@Override
	public Mail getMailAuthentication() {
		String selectMailDetailsQuery = "SELECT * FROM MAIL";
		Mail mail = null;
		try {
			con = dbutil.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(selectMailDetailsQuery);
			while (rs.next()) {
				mail = Mail.builder().hostmailaddress(rs.getString(1)).hostpassword(rs.getString(2)).build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mail;
	}

	@Override
	public Map<String, Integer> getBookings() {
		Map<String, Integer> map = new HashMap<>();
		String getBookings = "select e.scname,count(s.scid) from sportsclubs e inner join booking s on e.scid=s.scid group by s.scid";
				//"select scid,count(scid) as bookingcount from booking group by scid";
		try {
			con = dbutil.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(getBookings);
			while (rs.next()) {
				String scname = rs.getString(1);
				int bookingCount = rs.getInt(2);
				map.put(scname, bookingCount);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

}
