package com.sportsclub.dao;

import java.sql.*;

import com.sportsclub.dbutil.DBUtil;
import com.sportsclub.domain.Profile;
import com.sportsclub.usersession.UserSession;

public class SportsClubDaoImpl implements SportsClubDao {
	DBUtil dbutil = DBUtil.obj;
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private UserSession userSession = UserSession.getInstance();

	@Override
	public int getUsersCount() {
		String userCountQuery = "SELECT COUNT(UID) FROM SIGNUPDATA ";
		int count = 0;
		try {
			con = dbutil.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(userCountQuery);
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
	public int getSportsCount() {
		String userCountQuery = "SELECT COUNT(SID) FROM SPORTSDATA ";
		int count = 0;
		try {
			con = dbutil.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(userCountQuery);
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
	public boolean validateUser(String email, String password) {
		String validateUserQuery = "SELECT UID,NAME,EMAIL,ADDRESS,MOBILE FROM SIGNUPDATA WHERE EMAIL = ? AND PASSWORD = ? ";
		try {
			con = dbutil.getConnection();
			pstmt = con.prepareStatement(validateUserQuery);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("user login is : " + email);
				String userId = rs.getString("uid");
				String name = rs.getString("name");
				String email_1 = rs.getString("email");
				String password_1 = rs.getString("password");
				long mobile = rs.getLong("mobile");
				String address = rs.getString("address");
				Profile profileLogin = Profile.builder().userId(userId).name(name).email(email_1).password(password_1)
						.address(address).build();
				userSession.logedUser(profileLogin);
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Invalid login : " + email);
		return false;
	}

	@Override
	public boolean changePassword(String uId, String password) {
		String changePasswordQuery = "UPDATE TABLE SIGNUPDATA SET PASSWORD = ? WHERE UID = ?";
		try {
			con = dbutil.getConnection();
			pstmt = con.prepareStatement(changePasswordQuery);
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
		String updateEmailQuery = "UPDATE TABLE SIGNUPDATA SET EMAIL = ? WHERE UID = ?";
		try {
			con = dbutil.getConnection();
			pstmt = con.prepareStatement(updateEmailQuery);
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
		String updatePhoneQuery = "UPDATE TABLE SIGNUPDATA SET MOBILE = ? WHERE UID = ?";
		try {
			con = dbutil.getConnection();
			pstmt = con.prepareStatement(updatePhoneQuery);
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
