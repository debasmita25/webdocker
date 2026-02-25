package com.debs.utils;

import java.util.HashMap;
import java.util.Map;

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
			// Disable password manager
			Map<String, Object> prefs = new HashMap<>();

			// 🔐 Disable password manager
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);

			// 🚫 Disable breach detection
			prefs.put("profile.password_manager_leak_detection", false);

			// 🚫 Disable Safe Browsing password protection
			prefs.put("safebrowsing.enabled", false);
			prefs.put("safebrowsing.disable_download_protection", true);
			chromeOptions.addArguments("--disable-notifications");
			chromeOptions.addArguments("--disable-infobars");
			chromeOptions.addArguments("--disable-save-password-bubble");
			chromeOptions.addArguments("--disable-extensions");
			// Disable Chrome notifications
			prefs.put("profile.default_content_setting_values.notifications", 2);

			chromeOptions.setExperimentalOption("prefs", prefs);

			// Chrome security services off
			chromeOptions.addArguments("--disable-features=PasswordLeakDetection");
			chromeOptions.addArguments("--disable-features=AutofillServerCommunication");
			chromeOptions.addArguments("--disable-features=SafeBrowsingEnhancedProtection");
			chromeOptions.addArguments("--disable-features=PasswordManagerOnboarding");

			// UI suppressions
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

	public static void quitDriver() {
		if (driver.get() != null) {
			driver.get().quit();
			driver.remove();
			logger.info("Driver quit successfully");
		}
	}
}