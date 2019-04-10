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

public class AdminDaoImpl implements AdminDao {
	DBUtil dbutil = DBUtil.obj;
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	@Override
	public boolean addSport(Sports s) {
		String addSportsQuery = "INSERT INTO SPORTSDATA VALUES (?,?,?,?,?,?)";
		try {
			con = dbutil.getConnection();
			pstmt = con.prepareStatement(addSportsQuery);
			pstmt.setString(1, s.getSid());
			pstmt.setString(2, s.getSname());
			pstmt.setString(3, s.getSclub());
			pstmt.setInt(4, s.getSprice());
			pstmt.setInt(5, s.getPlayers());
			pstmt.setString(6, s.getStype());
			int i = pstmt.executeUpdate();
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
	public List<Sports> getAllSports() {
		String viewQuery = "SELECT * FROM SPORTSDATA";
		
		List<Sports> sportsList = new ArrayList<>();
		try {
			con = dbutil.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(viewQuery);
			while (rs.next()) {
				String sId = rs.getString("sid");
				String sName = rs.getString("sname");
				String sClub = rs.getString("sclub");
				int sPrice = rs.getInt("sprice");
				int players = rs.getInt("players");
				String sType = rs.getString("stype");
				Sports sports = Sports.builder().sid(sId).sname(sName).sclub(sClub).sprice(sPrice).players(players)
						.stype(sType).build();
				sportsList.add(sports);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sportsList;
	}

	@Override
	public List<Sports> searchSports(String sID) {
		String searchViewQuery = "SELECT * FROM SPORTSDATA WHERE SID=?";
		Sports sports = null;
		List<Sports> searchList = new ArrayList<>();
		try {
			con = dbutil.getConnection();
			pstmt = con.prepareStatement(searchViewQuery);
			pstmt.setString(1, sID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String sId = rs.getString("sid");
				String sName = rs.getString("sname");
				String sClub = rs.getString("sclub");
				int sPrice = rs.getInt("sprice");
				int players = rs.getInt("players");
				String sType = rs.getString("stype");
				sports = Sports.builder().sid(sId).sname(sName).sclub(sClub).sprice(sPrice).players(players)
						.stype(sType).build();
				searchList.add(sports);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchList;
	}

}
