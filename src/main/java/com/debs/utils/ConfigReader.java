package com.debs.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ConfigReader {
	
	
	private static final Logger logger=LogManager.getLogger(ConfigReader.class);
	private static Properties prop;
	
	static
	{
		try(InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"))
		{
			prop=new Properties();
			if(in == null)
			{
				throw new RuntimeException("config.properties not found in the classpath");
			}
			prop.load(in);
			logger.info("Configuration file loaded successfully");
		}
		catch(IOException e)
		{
			logger.info("Failed to load configuration file: "+e.getMessage());
			throw new RuntimeException("Failed to load configuation file");
		}
			
	}
	
	public static String getProperty(String key)
	{
		String value = System.getProperty(key);
		if(value == null)
		{
			value=prop.getProperty(key);
		}
		
		return value;
	}
	
	public static String getUrl(String project)
	{
		String env=System.getProperty("environment")!=null? System.getProperty("environment") :"qa";
		String projectName=System.getProperty("project")!=null? System.getProperty("project") :project;
		System.out.println(projectName);
		return prop.getProperty(projectName+"."+env+".url");
	}

}
