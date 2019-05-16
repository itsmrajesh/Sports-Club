package com.sportsclub.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.sportsclub.dbutil.DBUtil;
import com.sportsclub.domain.BookingSports;
import com.sportsclub.domain.Profile;
import com.sportsclub.usersession.UserSession;

public class UserAccountDaoImpl implements UserAccountDao {
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
	public boolean adduser(Profile p) {
		String addUser = "INSERT INTO SIGNUPDATA VALUES (?,?,?,?,?,?,?)";
		try {
			con = dbutil.getConnection();
			pstmt = con.prepareStatement(addUser);
			pstmt.setString(1, p.getUserid());
			pstmt.setString(2, p.getName());
			pstmt.setString(3, p.getEmail());
			pstmt.setString(4, p.getDob());
			pstmt.setLong(5, p.getMobile());
			pstmt.setString(6, p.getAddress());
			pstmt.setString(7, p.getPassword());
			int i = pstmt.executeUpdate();
			if (i == 1) {
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
				long mobile = rs.getLong("mobile");
				String address = rs.getString("address");
				Profile profileLogin = Profile.builder().userid(userId).name(name).email(email_1).address(address)
						.mobile(mobile).build();
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
	public boolean changePassword(String uId, String newpassword, String oldpassword) {
		String changePasswordQuery = "UPDATE SIGNUPDATA SET PASSWORD = ? WHERE UID = ? AND PASSWORD = ?";
		try {
			con = dbutil.getConnection();
			pstmt = con.prepareStatement(changePasswordQuery);
			pstmt.setString(1, newpassword);
			pstmt.setString(2, uId);
			pstmt.setString(3, oldpassword);
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
		String updateEmailQuery = "UPDATE SIGNUPDATA SET EMAIL = ? WHERE UID = ?";
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
	public boolean updatePhone(String uId, long newPhone) {
		String updatePhoneQuery = "UPDATE SIGNUPDATA SET MOBILE = ? WHERE UID = ?";
		try {
			con = dbutil.getConnection();
			pstmt = con.prepareStatement(updatePhoneQuery);
			pstmt.setLong(1, newPhone);
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
	public String getUserID(String email) {
		String getUserIDQuery = "SELECT UID FROM SIGNUPDATA WHERE EMAIL=?";
		try {
			con = dbutil.getConnection();
			pstmt = con.prepareStatement(getUserIDQuery);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public Profile getUserDetails(String uid) {
		String getUserQuery = "SELECT UID,NAME,EMAIL,DOB,MOBILE,ADDRESS FROM SIGNUPDATA WHERE UID=?";
		Profile profile = null;
		try {
			con = dbutil.getConnection();
			pstmt = con.prepareStatement(getUserQuery);
			pstmt.setString(1, uid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String userId = rs.getString("uid");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String dob = rs.getString("dob");
				long mobile = rs.getLong("mobile");
				String address = rs.getString("address");
				profile = Profile.builder().userid(userId).name(name).email(email).address(address).mobile(mobile)
						.dob(dob).build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return profile;
	}

}
