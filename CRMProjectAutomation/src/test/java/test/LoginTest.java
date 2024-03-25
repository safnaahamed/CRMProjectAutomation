package test;

import org.testng.annotations.Test;

import constants.Constants;
import page.LoginPage;
import utility.ExcelRead;

import org.testng.annotations.DataProvider;

import java.io.IOException;

import org.testng.Assert;

public class LoginTest extends BaseTest {

	@Test(priority = 1, dataProvider = "userData", groups = { "regression" })

	public void verifyLogin(String username, String password) throws IOException {

		LoginPage lp = new LoginPage(driver);
		String actual = lp.doLogin(username, password);
		String expected = "Dashboard";
		Assert.assertEquals(actual, expected);
		System.out.println("Test 1 : VerifyLogin : The dashboard icon is visible in the page - Execution Completed");
	}

	@DataProvider

	public Object[][] userData() throws Exception {

		Object[][] data = ExcelRead.getDataFromExcel(Constants.crmtestdata, "Sheet1");

		return data;
	}

}
