package com.equalexperts.utils;

/**
 * @author Siddu
 */
import java.util.List;
import org.apache.log4j.Logger;
import io.restassured.path.json.JsonPath;

public class JsonUtils {
	// This class has utility methods to deal with json response
	Logger logger = Logger.getLogger(JsonUtils.class.getName());

	public int getBookingsCount(JsonPath jsonPath) {

		try {
			List<Integer> allBookingIds = jsonPath.get("bookingid");
			/*
			 * for (int eachBookingId : allBookingIds) { logger.info(
			 * "Booking Id :" + eachBookingId); }
			 */
			return allBookingIds.size();
		} catch (Exception e) {
			e.getMessage();
			return 0;
		}
	}

	public int getLatestBookingId(JsonPath jsonPath) {
		int bookingIdOfLatestBooking;
		List<Integer> allBookingIds = jsonPath.get("bookingid");
		/*
		 * for (int eachBookingId : allBookingIds) { logger.info("Booking Id :"
		 * + eachBookingId); }
		 */
		try {
			bookingIdOfLatestBooking = allBookingIds.get(allBookingIds.size() - 1);
			return bookingIdOfLatestBooking;
		} catch (Exception e) {
			logger.info("Case of empty reponse=> No bookings in the system");
			return 0;
		}

	}
}
