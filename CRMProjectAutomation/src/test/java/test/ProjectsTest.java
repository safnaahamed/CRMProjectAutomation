package test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import constants.Constants;
import page.LoginPage;
import page.ProjectsPage;
import utility.ExcelRead;

public class ProjectsTest extends BaseTest {

	@Test(priority = 1, groups = { "smoke", "regression" })
	public void verifyProjectsCreation() throws Exception {
		
		SoftAssert softassert = new SoftAssert();
		LoginPage lp = new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");

		ProjectsPage pp = new ProjectsPage(driver);
		pp.doClickOnProjects();
		String actual=pp.addProject("This is a test",
				ExcelRead.getDataFromExcel(Constants.crmtestdata, "Sheet2", 1, 0),"2024-03-19");
		String expected="This is a test";
		softassert.assertEquals(actual, expected);
		System.out.println("Test 6 : verifyProjectsCreation : The project is created in the page - Execution Completed");
		softassert.assertAll();
		
	}

	@Test(priority = 2, groups = { "regression" })
	public void verifySearchProjects() throws Exception {
		SoftAssert softassert = new SoftAssert();
		LoginPage lp = new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");

		ProjectsPage pp = new ProjectsPage(driver);
		pp.doClickOnProjects();
		String actual = pp.doSearchProjects("This is a test");
		String expected = "This is a test";
		softassert.assertEquals(actual, expected);
		System.out.println("Test 7 : verifySearchProjects : The created project is verified in the page - Execution Completed");
		softassert.assertAll();
	}

	@Test(priority = 3, groups = { "smoke", "regression" })
	public void verifyEditProjects() throws Exception {
		SoftAssert softassert = new SoftAssert();
		LoginPage lp = new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");

		ProjectsPage pp = new ProjectsPage(driver);
		pp.doClickOnProjects();
		pp.doEditTitle("The Project title is edited for confirmation");
		String actual1 = pp.doSearchEditedProjects("The Project title is edited for confirmation");
		String expected1 = "The Project title is edited for confirmation";
		softassert.assertEquals(actual1, expected1);
		System.out.println("Test 8 : verifyEditProjects : The project is edited and verified in the page - Execution Completed");
		softassert.assertAll();
	}

	@Test(priority = 4, groups = { "smoke", "regression" })
	public void verifyDeletecProjects() throws Exception {
		SoftAssert softassert = new SoftAssert();
		LoginPage lp = new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");

		ProjectsPage pp = new ProjectsPage(driver);
		pp.doClickOnProjects();
		pp.doDeleteProject("The Project title is edited for confirmation");
		String actual2 = pp.doVerifyDeleteProject();
		String expected2 = "No record found.";
		softassert.assertEquals(actual2, expected2);
		System.out.println("Test 9 : verifyDeletecProjects : The project is deleted from the page - Execution Completed");
		softassert.assertAll();
	}
}
