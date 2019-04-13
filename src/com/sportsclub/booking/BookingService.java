package com.sportsclub.booking;

import com.sportsclub.admindao.AdminDaoImpl;
import com.sportsclub.bookingdao.BookingDao;
import com.sportsclub.bookingdao.BookingDaoImpl;
import com.sportsclub.domain.BookingSports;
import com.sportsclub.idservice.BookingIDGenerator;

public class BookingService {
	BookingIDGenerator bid = new BookingIDGenerator();
	private AdminDaoImpl adminDao = new AdminDaoImpl();
	private BookingDao bookingDao = new BookingDaoImpl();

	public boolean doBooking(String sid, String uid, String date, String time) {
		String bookingID = bid.getBookingID();
		int price = adminDao.getSportPrice(sid);
		BookingSports bs = BookingSports.builder().bookingid(bookingID).sid(sid).userid(uid).bookingdate(date)
				.bookingtime(time).bookingprice(price).build();
		return bookingDao.bookSport(bs);
	}

}
