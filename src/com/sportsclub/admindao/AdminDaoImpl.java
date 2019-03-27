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
		String addSports = "INSERT INTO SPORTSDATA VALUES (?,?,?,?,?,?)";
		try {
			con = dbutil.getConnection();
			pstmt = con.prepareStatement(addSports);
			pstmt.setString(1, s.getSId());
			pstmt.setString(2, s.getSName());
			pstmt.setString(3, s.getSClub());
			pstmt.setDouble(4, s.getSPrice());
			pstmt.setInt(5, s.getPlayers());
			pstmt.setString(6, s.getSType());
			int i = pstmt.executeUpdate();
			if (i > 0) {
				System.out.println("new Sport inserted.... for " + s.getSName());
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("failed to add.. for " + s.getSName());
		return false;
	}

	@Override
	public List<Sports> getAllSports() {
		String view = "SELECT * FROM SPORTSDATA";
		Sports sports = null;
		List<Sports> sportsList = new ArrayList<>();
		try {
			con = dbutil.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(view);
			while (rs.next()) {
				String sId = rs.getString("sid");
				String sName = rs.getString("sname");
				String sClub = rs.getString("sclub");
				double sPrice = rs.getDouble("sprice");
				int players = rs.getInt("players");
				String sType = rs.getString("stype");
				sports = Sports.builder().sId(sId).sName(sName).sClub(sClub).sPrice(sPrice).players(players)
						.sType(sType).build();
				sportsList.add(sports);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sportsList;
	}

	@Override
	public List<Sports> searchSports(String sID) {
		String searchView = "SELECT * FROM SPORTSDATA WHERE SID=?";
		Sports sports = null;
		List<Sports> searchList = new ArrayList<>();
		try {
			con = dbutil.getConnection();
			pstmt = con.prepareStatement(searchView);
			pstmt.setString(1, sID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String sId = rs.getString("sid");
				String sName = rs.getString("sname");
				String sClub = rs.getString("sclub");
				double sPrice = rs.getDouble("sprice");
				int players = rs.getInt("players");
				String sType = rs.getString("stype");
				sports = Sports.builder().sId(sId).sName(sName).sClub(sClub).sPrice(sPrice).players(players)
						.sType(sType).build();
				searchList.add(sports);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchList;
	}

}
