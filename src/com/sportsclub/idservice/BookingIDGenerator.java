package com.sportsclub.idservice;

import com.sportsclub.bookingdao.BookingDao;
import com.sportsclub.bookingdao.BookingDaoImpl;
import com.sportsclub.dao.UserAccountDao;
import com.sportsclub.dao.UserAccountDaoImpl;

public class BookingIDGenerator {
	private String prefix = "BID";
	private int count = 100;
	private BookingDao bookindDao = new BookingDaoImpl();

	public String getBookingID() {
		int c = bookindDao.getBookingCount();
		if (c == 0) {
			return prefix + (count + 1);
		} else {
			return prefix + (count + c + 1);
		}
	}

}
