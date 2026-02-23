package com.debs.dataproviders.bookflights;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.debs.utils.bookflights.TestDataLoader;
import com.fasterxml.jackson.core.type.TypeReference;

public class DataProviders {
	
	 @DataProvider(name = "jsonData")
	    public static Object[][] getData(Method method) {

	        Class<?> testClass = method.getDeclaringClass();
	        String testName = method.getName().toLowerCase();

	        Map<String, List<Object>> data =
	                TestDataLoader.load(
	                        testClass,
	                        new TypeReference<Map<String, List<Object>>>() {}
	                );

	        List<Object> testData = data.get(testName);

	        if (testData == null) {
	            throw new RuntimeException("No data for test: " + testName);
	        }

	        Object[][] result = new Object[testData.size()][1];

	        for (int i = 0; i < testData.size(); i++) {
	            result[i][0] = testData.get(i);
	        }

	        return result;
	    }

}
