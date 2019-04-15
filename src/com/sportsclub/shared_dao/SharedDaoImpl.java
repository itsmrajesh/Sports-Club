package com.sportsclub.shared_dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.sportsclub.dbutil.DBUtil;
import com.sportsclub.domain.Sports;
import com.sportsclub.domain.SportsClubs;

public class SharedDaoImpl implements SharedDao {
	DBUtil dbutil = DBUtil.obj;
	private Connection con;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;

	@Override
	public List<Sports> getAllSports() {
		String viewQuery = "SELECT * FROM SPORTSDATA";
		List<Sports> sportsList = new ArrayList<>();
		try {
			con = dbutil.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(viewQuery);
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
			pst = con.prepareStatement(searchViewQuery);
			pst.setString(1, sID);
			rs = pst.executeQuery();
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

	@Override
	public List<SportsClubs> getAllSportsClubs() {
		String listSportsClubsQuery = "SELECT * FROM SPORTSCLUBS ";
		List<SportsClubs> clubList = new ArrayList<>();
		try {
			con = dbutil.getConnection();
			pst = con.prepareStatement(listSportsClubsQuery);
			rs = pst.executeQuery();
			while (rs.next()) {
				clubList.add(SportsClubs.builder().scid(rs.getInt(1)).scname(rs.getString(2)).build());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clubList;
	}

	@Override
	public String getSportClubName(int scid) {
		String getSportNameQuery = "SELECT SCNAME FROM SPORTSCLUBS WHERE SCID = ?";
		try {
			con = dbutil.getConnection();
			pst = con.prepareStatement(getSportNameQuery);
			pst.setInt(1, scid);
			rs = pst.executeQuery();
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Sports> getAllSportsByType(int scid, String stype) {
		List<Sports> sportsList = new ArrayList<>();
		String viewQuery = "SELECT * FROM SPORTSDATA WHERE SCID = ? AND STYPE =?";
		try {
			con = dbutil.getConnection();
			pst = con.prepareStatement(viewQuery);
			pst.setInt(1, scid);
			pst.setString(2, stype);
			rs = pst.executeQuery();
			while (rs.next()) {
				Sports s = Sports.builder().sid(rs.getString(1)).sname(rs.getString(2)).sclub(rs.getString(3))
						.sprice(rs.getInt(4)).players(rs.getInt(5)).stype(rs.getString(6)).scid(rs.getInt(7)).build();
				sportsList.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sportsList;
	}
}
