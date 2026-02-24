package com.debs.tests.vendorapp;

import org.testng.annotations.AfterMethod;

import com.debs.utils.DriverFactory;

public class BaseTest {
	
	@AfterMethod
	public void tearDown()
	{
		if(DriverFactory.getDriver()!=null)
		{
			DriverFactory.quitDriver();
		}
	}

}
