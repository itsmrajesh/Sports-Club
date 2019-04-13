package com.sportsclub.bookingdao;

import java.util.List;

import com.sportsclub.domain.BookingSports;

public interface BookingDao {
	
	int getBookingCount();

	boolean bookSport(BookingSports bs);
	
	List<BookingSports> getUserBooking(String uid);
	
	


}
