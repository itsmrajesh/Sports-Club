package com.sportsclub.admindao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sportsclub.dbutil.DBUtil;
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
	public boolean addSportsClub(String scname,String location,String contactNumber) {
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

}
