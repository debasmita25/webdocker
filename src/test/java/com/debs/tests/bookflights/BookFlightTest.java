package com.debs.tests.bookflights;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.debs.dataproviders.DataProviders;
import com.debs.pages.bookflights.CustomerRegistration;
import com.debs.records.bookflights.UserData;

public class BookFlightTest extends BaseTest {

	private static final Logger logger = LogManager.getLogger(BookFlightTest.class);

	@Test(dataProvider = "jsonData", dataProviderClass = DataProviders.class)
	public void validUser(UserData data) {
		// System.out.println("Passengers: " + data.passengersCount());
		CustomerRegistration custRegister = new CustomerRegistration();
		custRegister.goTo();
		custRegister.enterUserFirstLastName(data.firstName(), data.lastName());
		custRegister.enterUserCredentials(data.email(), data.password());
		custRegister.enterUserAddress(data.street(), data.city(), data.zip(),data.state());
		custRegister.register();
	}

	@Test(dataProvider = "jsonData", dataProviderClass = DataProviders.class)
	public void invalidUser(UserData data) {
		// System.out.println("Passengers: " + data.passengersCount());
		CustomerRegistration custRegister = new CustomerRegistration();
		custRegister.goTo();
		custRegister.enterUserFirstLastName(data.firstName(), data.lastName());
		custRegister.enterUserCredentials(data.email(), data.password());
		custRegister.enterUserAddress(data.street(), data.city(), data.zip(),data.state());
		custRegister.register();
	}
	
	

}
