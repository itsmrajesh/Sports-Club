package com.sportsclub.bookingdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sportsclub.admindao.AdminDaoImpl;
import com.sportsclub.dbutil.DBUtil;
import com.sportsclub.domain.BookingSports;

public class BookingDaoImpl extends AdminDaoImpl implements BookingDao {
	DBUtil dbutil = DBUtil.obj;
	private Connection con;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;

	@Override
	public int getBookingCount() {
		String countQuery = "SELECT COUNT(BID) FROM BOOKING";
		try {
			con = dbutil.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(countQuery);
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean bookSport(BookingSports bs) {
		String addBookingQuery = "INSERT INTO BOOKING VALUES (?,?,?,?,?,?)";
		try {
			con = dbutil.getConnection();
			pst = con.prepareStatement(addBookingQuery);
			pst.setString(1, bs.getBookingid());
			pst.setString(2, bs.getSid());
			pst.setString(3, bs.getUserid());
			pst.setString(4, bs.getBookingdate());
			pst.setString(5, bs.getBookingtime());
			pst.setInt(6, bs.getBookingprice());
			int i = pst.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<BookingSports> getUserBooking(String uid) {
		List<BookingSports> bookingList = new ArrayList<>();
		String view = "SELECT * FROM BOOKING WHERE UID=?";
		try {
			con = dbutil.getConnection();
			pst = con.prepareStatement(view);
			pst.setString(1, uid);
			rs = pst.executeQuery();
			while (rs.next()) {
				BookingSports bs = BookingSports.builder().bookingid(rs.getString(1)).sid(rs.getString(2))
						.userid(rs.getString(3)).bookingdate(rs.getString(4)).bookingtime(rs.getString(5))
						.bookingprice(rs.getInt(6)).build();
				bookingList.add(bs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookingList;
	}

}
