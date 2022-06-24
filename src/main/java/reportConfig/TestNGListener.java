package reportConfig;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import commons.BaseTest;
import commons.GlobalConstants;

public class TestNGListener implements ITestListener {
	WebDriver driver;
	String filePath = GlobalConstants.PROJECT_PATH + "/Screenshots/";

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult result) {
		// try {
		driver = ((BaseTest) result.getInstance()).getWebDriver();
		String methodName = result.getName().toString().trim();
		takeScreenShot_File(methodName);
//		} catch (NoSuchSessionException e) {
//			e.printStackTrace();
//			System.out.println("Take screenshot failure");
//		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void takeScreenShot_Base64(String methodName) {
		// get the driver
		String scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		// File desFile = new File(filePath + methodName + ".png");
		// The below method will save the screen shot in d drive with test method name
//		try {
//			FileUtils.copyFile(scrFile, desFile);
//			System.out.println("***Placed screen shot in " + filePath + " ***");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		byte[] fileContent = FileUtils.readFileToByteArray(desFile);
//		String encodedString = Base64.getEncoder().encodeToString(fileContent);
//		System.out.println(encodedString);
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		// String path = "<td><a href = 'data:image/png;base64, " + scrFile + "'><img src='data:image/png;base64, " + scrFile + "'/></a></td>";
		// String path = "<td><a href = 'data:image/png;base64, " + scrFile + "' target = '_blank' title = 'Click to this link'><img src='data:image/png;base64, " +
		String path = "<td><a href = 'data:image/png;base64, " + scrFile + "' title = 'Click to this link'><img src='data:image/png;base64, " + scrFile + "'/></a></td>";

		Reporter.log(path);
	}

	public void takeScreenShot_File(String methodName) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File desFile = new File(filePath + methodName + "_" + formater.format(calendar.getTime()) + ".png");
		try {
			FileUtils.copyFile(scrFile, desFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		String path = "<td><a href = '" + desFile.getAbsolutePath() + "'><img src = '" + desFile.getAbsolutePath() + "'/></a></td>";
		Reporter.log(path);
	}

}
