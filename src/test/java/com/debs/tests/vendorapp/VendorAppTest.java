package com.debs.tests.vendorapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.debs.dataproviders.DataProviders;
import com.debs.records.vendorapp.VendorPortalTestData;

public class VendorAppTest extends BaseTest {
	
	
	private static final Logger logger = LogManager.getLogger(VendorAppTest.class);

	@Test(dataProvider = "jsonData", dataProviderClass = DataProviders.class)
	public void vendorData(VendorPortalTestData data) {
		
		System.out.println(data.username());
		
		
	}
	

}
