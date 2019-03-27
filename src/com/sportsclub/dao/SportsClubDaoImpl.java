package com.sportsclub.dao;

import java.sql.*;

import com.sportsclub.dbutil.DBUtil;
import com.sportsclub.domain.Profile;

public class SportsClubDaoImpl implements SportsClubDao {
	DBUtil dbutil = DBUtil.obj;
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	@Override
	public int getUsersCount() {
		String userCount = "SELECT COUNT(UID) FROM SIGNUPDATA ";
		int count = 0;
		try {
			con = dbutil.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(userCount);
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("total count of users is " + count);
		return count;
	}

	@Override
	public boolean adduser(Profile p) {
		String addUser = "INSERT INTO SIGNUPDATA VALUES (?,?,?,?,?,?,?)";
		try {
			con = dbutil.getConnection();
			pstmt = con.prepareStatement(addUser);
			pstmt.setString(1, p.getUserId());
			pstmt.setString(2, p.getName());
			pstmt.setString(3, p.getEmail());
			pstmt.setInt(4, p.getDob());
			pstmt.setLong(5, p.getMobile());
			pstmt.setString(6, p.getAddress());
			pstmt.setString(7, p.getPassword());
			int i = pstmt.executeUpdate();
			if (i > 0) {
				System.out.println("Data inserted.... for " + p.getName());
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("failed to add.. for " + p.getName());
		return false;
	}

	@Override
	public boolean validateuser(String email, String password) {
		String validateuser = "SELECT UID,NAME,EMAIL,ADDRESS,MOBILE FROM SIGNUPDATA WHERE EMAIL = ? AND PASSWORD = ? ";
		try {
			con = dbutil.getConnection();
			pstmt = con.prepareStatement(validateuser);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("user login in : " + email);
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Invalid login : " + email);
		return false;
	}

	@Override
	public int getSportsCount() {
		String userCount = "SELECT COUNT(SID) FROM SPORTSDATA ";
		int count = 0;
		try {
			con = dbutil.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(userCount);
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
	public boolean changePassword(String uId, String password) {
		String changePassword = "UPDATE TABLE SIGNUPDATA SET PASSWORD = ? WHERE UID = ?";
		try {
			con = dbutil.getConnection();
			pstmt = con.prepareStatement(changePassword);
			pstmt.setString(1, password);
			pstmt.setString(2, uId);
			int i = pstmt.executeUpdate();
			if (i > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateEmail(String uId, String newEmail) {
		String updateEmail = "UPDATE TABLE SIGNUPDATA SET EMAIL = ? WHERE UID = ?";
		try {
			con = dbutil.getConnection();
			pstmt = con.prepareStatement(updateEmail);
			pstmt.setString(1, newEmail);
			pstmt.setString(2, uId);
			int i = pstmt.executeUpdate();
			if (i > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updatePhone(String uId, String newPhone) {
		String updatePhone = "UPDATE TABLE SIGNUPDATA SET MOBILE = ? WHERE UID = ?";
		try {
			con = dbutil.getConnection();
			pstmt = con.prepareStatement(updatePhone);
			pstmt.setString(1, newPhone);
			pstmt.setString(2, uId);
			int i = pstmt.executeUpdate();
			if (i > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
