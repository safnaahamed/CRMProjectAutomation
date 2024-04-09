package test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import constants.Constants;
import utility.ElementUtility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

public class BaseTest {
	WebDriver driver;
	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(@Optional("chrome") String browser) {
		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equals("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(200));
		driver.get(ElementUtility.getPropertyValue("baseURL"));
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException { 
		if (result.getStatus() == ITestResult.FAILURE) {
			takeScreenshotOnFailure(result.getName());
			
		}

		driver.quit();
	}

	public void takeScreenshotOnFailure(String name) throws IOException {
		String dateformat = new SimpleDateFormat("yyyy_MM_dd_hh_mm").format(new Date()); 
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destination = Constants.screenshot_path + name + dateformat + ".png"; 
		File finaldestination = new File(destination);
		FileUtils.copyFile(source, finaldestination);

	}
}
