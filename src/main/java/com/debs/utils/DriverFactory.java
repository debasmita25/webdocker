package com.debs.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

	private static final Logger logger = LogManager.getLogger(DriverFactory.class);
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public static WebDriver getDriver() {
		if (driver.get() == null) {
			driver.set(createDriver());
		}

		return driver.get();

	}

	private static WebDriver createDriver() {
		WebDriver driver = null;
		String browser = ConfigReader.getProperty("browser").toLowerCase();
		driver = createLocalDriver(browser);
		return driver;

	}

	private static WebDriver createLocalDriver(String browser) {
		WebDriver driver;
		switch (browser) {
		case "firefox":
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			if (Boolean.parseBoolean(ConfigReader.getProperty("headless"))) {
				firefoxOptions.addArguments("--headless");
			}
			driver = new FirefoxDriver(firefoxOptions);
			break;

		case "edge":
			EdgeOptions edgeOptions = new EdgeOptions();
			if (Boolean.parseBoolean(ConfigReader.getProperty("headless"))) {
				edgeOptions.addArguments("--headless");
			}
			driver = new EdgeDriver(edgeOptions);
			break;

		case "chrome":
		default:
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--disable-notifications");
			chromeOptions.addArguments("--disable-infobars");
			chromeOptions.addArguments("--disable-save-password-bubble");
			chromeOptions.addArguments("--disable-extensions");
			if (Boolean.parseBoolean(ConfigReader.getProperty("headless"))) {
				chromeOptions.addArguments("--headless");
			}
			driver = new ChromeDriver(chromeOptions);
			break;
		}
 
		logger.info("Driver initialized successfully"); 
		return driver;

	}

	public static void quitDriver()
	{
		if(driver.get() !=null)
		{
			driver.get().quit();
			driver.remove();
			logger.info("Driver quit successfully");
		}
	}
}