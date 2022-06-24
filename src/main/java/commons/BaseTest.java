package commons;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	protected WebDriver driver;
	protected final Log log;

	protected BaseTest() {
		log = LogFactory.getLog(getClass());
	}

	protected WebDriver getBrowserName(String browserName) {
		if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			File extensionFile = new File(GlobalConstants.PROJECT_PATH + File.separator + "browserExtensions" + File.separator + "to_google_translate-4.2.0-fx.xpi");
			FirefoxProfile profile = new FirefoxProfile();
			profile.addExtension(extensionFile);
			FirefoxOptions options = new FirefoxOptions();
			options.setProfile(profile);
			driver = new FirefoxDriver(options);
		} else if (browserName.equals("h_firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.setHeadless(true);
			options.addArguments("--headless");
			options.addArguments("--window-size=1920x1080");
			driver = new FirefoxDriver(options);
		} else if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			File extensionFile = new File(GlobalConstants.PROJECT_PATH + File.separator + "browserExtensions" + File.separator + "selectorshub-3.5.crx");
			ChromeOptions options = new ChromeOptions();
			options.addExtensions(extensionFile);
			driver = new ChromeDriver(options);
		} else if (browserName.equals("h_chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.setHeadless(true);
			options.addArguments("--headless");
			options.addArguments("--window-size=1920x1080");
			driver = new ChromeDriver(options);
		} else if (browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browserName.equals("coccoc")) {
			WebDriverManager.chromedriver().driverVersion("94.0.4606.41").setup();
			ChromeOptions options = new ChromeOptions();
			options.setBinary("C:\\Program Files (x86)\\CocCoc\\Browser\\Application\\browser.exe");
			driver = new ChromeDriver(options);
		} else if (browserName.equals("brave")) {
			WebDriverManager.chromedriver().driverVersion("95.0.4638.69").setup();
			ChromeOptions options = new ChromeOptions();
			options.setBinary("C:\\Program Files (x86)\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
			driver = new ChromeDriver(options);
		} else if (browserName.equals("safari")) {
			driver = new SafariDriver();
		} else if (browserName.equals("ie")) {
			WebDriverManager.iedriver().arch32().driverVersion("3.141.59").setup();
			driver = new InternetExplorerDriver();
		} else {
			throw new RuntimeException();
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		// driver.get(GlobalConstants.USER_DEV_URL);
		return driver;
	}

	protected WebDriver getBrowserName(String browserName, String url) {
		if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
//			File extensionFile = new File(GlobalConstants.PROJECT_PATH + File.separator + "browserExtensions" + File.separator + "to_google_translate-4.2.0-fx.xpi");
//			FirefoxProfile profile = new FirefoxProfile();
//			profile.addExtension(extensionFile);
			FirefoxOptions options = new FirefoxOptions();
			options.addPreference("intL.accept_languages", "vi-vn,vi,en-us,en");
//			options.setProfile(profile);
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, GlobalConstants.PROJECT_PATH + File.separator + "browserLogs" + File.separator + "Firefox.log");
			options.addArguments("--private");
			driver = new FirefoxDriver(options);
		} else if (browserName.equals("h_firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.setHeadless(true);
			options.addArguments("--headless");
			options.addArguments("--window-size=1920x1080");
			driver = new FirefoxDriver(options);
		} else if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
//			File extensionFile = new File(GlobalConstants.PROJECT_PATH + File.separator + "browserExtensions" + File.separator + "selectorshub-3.5.crx");
//			ChromeOptions options = new ChromeOptions();
//			options.addArguments("--incognito");
//			options.addArguments("--lang=vi");
//			options.setExperimentalOption("useAutomationExtension", false);
//			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
//			Map<String, Object> prefs = new HashMap<String, Object>();
//			prefs.put("credentials_enable_service", false);
//			prefs.put("profile.password_manager_enabled", false);
//			options.setExperimentalOption("prefs", prefs);
//			options.addExtensions(extensionFile);
//			System.setProperty("webdriver.chrome.args", "--disable-logging");
//			System.setProperty("webdriver.chrome.silentOutput", "true");
			driver = new ChromeDriver();
		} else if (browserName.equals("h_chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.setHeadless(true);
			options.addArguments("--headless");
			options.addArguments("--window-size=1920x1080");
			driver = new ChromeDriver(options);
		} else if (browserName.equals("edge chromium")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browserName.equals("edge legacy")) {
			driver = new EdgeDriver();
		} else if (browserName.equals("coccoc")) {
			WebDriverManager.chromedriver().driverVersion("94.0.4606.41").setup();
			ChromeOptions options = new ChromeOptions();
			options.setBinary("C:\\Program Files (x86)\\CocCoc\\Browser\\Application\\browser.exe");
			driver = new ChromeDriver(options);
		} else if (browserName.equals("brave")) {
			WebDriverManager.chromedriver().driverVersion("95.0.4638.69").setup();
			ChromeOptions options = new ChromeOptions();
			options.setBinary("C:\\Program Files (x86)\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
			driver = new ChromeDriver(options);
		} else if (browserName.equals("safari")) {
			driver = new SafariDriver();
		} else if (browserName.equals("ie")) {
			WebDriverManager.iedriver().arch32().driverVersion("3.141.59").setup();
			driver = new InternetExplorerDriver();
		} else if (browserName.equals("opera")) {
			WebDriverManager.operadriver().driverVersion("96.0.4664.45").setup();
			driver = new OperaDriver();
		} else {
			throw new RuntimeException();
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(url);
		return driver;
	}
//	protected WebDriver getBrowserName(String browserName, String environment) {
//		if (browserName.equals("firefox")) {
//			WebDriverManager.firefoxdriver().setup();
//			driver = new FirefoxDriver();
//		} else if (browserName.equals("h_firefox")) {
//			WebDriverManager.firefoxdriver().setup();
//			FirefoxOptions options = new FirefoxOptions();
//			options.setHeadless(true);
//			options.addArguments("--headless");
//			options.addArguments("--window-size=1920x1080");
//			driver = new FirefoxDriver(options);
//		} else if (browserName.equals("chrome")) {
//			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver();
//		} else if (browserName.equals("h_chrome")) {
//			WebDriverManager.chromedriver().setup();
//			ChromeOptions options = new ChromeOptions();
//			options.setHeadless(true);
//			options.addArguments("--headless");
//			options.addArguments("--window-size=1920x1080");
//			driver = new ChromeDriver(options);
//		} else if (browserName.equals("edge")) {
//			WebDriverManager.edgedriver().setup();
//			driver = new EdgeDriver();
//		} else if (browserName.equals("coccoc")) {
//			WebDriverManager.chromedriver().driverVersion("94.0.4606.41").setup();
//			ChromeOptions options = new ChromeOptions();
//			options.setBinary("C:\\Program Files (x86)\\CocCoc\\Browser\\Application\\browser.exe");
//			driver = new ChromeDriver(options);
//		} else if (browserName.equals("brave")) {
//			WebDriverManager.chromedriver().driverVersion("95.0.4638.69").setup();
//			ChromeOptions options = new ChromeOptions();
//			options.setBinary("C:\\Program Files (x86)\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
//			driver = new ChromeDriver(options);
//		} else if (browserName.equals("safari")) {
//			driver = new SafariDriver();
//		}
//		} else {
//			throw new RuntimeException();
//		}
//		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
//		driver.get(getEnvironment(environment));
//		return driver;
//	}
//
//	protected String getEnvironment(String env) {
//		switch (env) {
//		case "DEV":
//			return GlobalConstants.USER_DEV_URL;
//		case "STAGING":
//			return GlobalConstants.USER_STAGING_URL;
//		default:
//			throw new IllegalArgumentException("Unexpected value: " + env);
//		}
//	}

	protected int generateRandomNumber() {
		Random rnd = new Random();
		return rnd.nextInt();
	}

	private boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
			if (condition == true) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;

			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkTrue(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
			if (condition == false) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}

	public WebDriver getWebDriver() {
		return this.driver;
	}

	@BeforeTest
	public void deleteAllFileInFolder() {
		try {
			String workingDir = System.getProperty("user.dir");
			String pathFolderDownload = workingDir + "\\Screenshots";
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					System.out.println(listOfFiles[i].getName());
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	public void closeBrowserAndDriver() {
		String cmd = "";
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			String driverInstanceName = driver.toString().toLowerCase();
			log.info("Driver instance name = " + osName);

			if (driverInstanceName.contains("chrome")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				} else {
					cmd = "pkill chromedriver";
				}
			} else if (driverInstanceName.contains("internetexplorer")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (driverInstanceName.contains("firefox")) {
				if (osName.contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				} else {
					cmd = "pkill geckodriver";
				}
			} else if (driverInstanceName.contains("edge")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
				} else {
					cmd = "pkill msedgedriver";
				}
			} else if (driverInstanceName.contains("opera")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
				} else {
					cmd = "pkill operadriver";
				}
			} else if (driverInstanceName.contains("safari")) {
				if (osName.contains("mac")) {
					cmd = "pkill safaridriver";
				}
			}
			if (driver != null) {
				System.out.println("Driver before delete cookies" + driver.toString());
				driver.manage().deleteAllCookies();
				System.out.println("Driver after delete cookies" + driver.toString());
				System.out.println("Session ID before quit" + ((FirefoxDriver) driver).getSessionId());
				driver.quit();
				System.out.println("Driver after quit" + driver.toString());
				System.out.println("Session ID after quit" + ((FirefoxDriver) driver).getSessionId());
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			System.out.println("Catch of try-catch block is executed");
		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
				System.out.println("Finally block is executed");
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Catch of finally block is executed");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	protected void showBrowserConsoleLogs(WebDriver driver) {
		if (driver.toString().contains("chrome")) {
			LogEntries logs = driver.manage().logs().get("browser");
			List<LogEntry> logList = logs.getAll();
			for (LogEntry logging : logList) {
				log.info("-----------" + logging.getLevel().toString() + "-----------\n" + logging.getMessage());
			}
		}
	}

}
