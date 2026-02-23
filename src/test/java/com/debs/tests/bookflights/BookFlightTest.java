package com.debs.tests.bookflights;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.debs.dataproviders.bookflights.DataProviders;
import com.debs.records.bookflights.UserData;

public class BookFlightTest {
	
	
	private static final Logger logger=LogManager.getLogger(BookFlightTest.class);
	
	@Test(dataProvider = "jsonData", dataProviderClass = DataProviders.class)
	public void validUser(UserData data)
	{
		 System.out.println("Passengers: " + data.passengersCount());
	}

}
