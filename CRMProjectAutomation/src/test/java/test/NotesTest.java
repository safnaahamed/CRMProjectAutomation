package test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.LoginPage;
import page.NotesPage;
import utility.FakerUtility;

import org.testng.Assert;

public class NotesTest extends BaseTest {

	@Test(priority = 1, retryAnalyzer = generaltests.Retry.class, groups = { "smoke", "regression" })
	public void verifyNotesCreation() throws Exception {
		SoftAssert softassert = new SoftAssert();
		LoginPage lp = new LoginPage(driver);
		String actual = lp.doLogin("admin@admin.com", "12345678");
		String expected = "Dashboard";
		softassert.assertEquals(actual, expected);
		NotesPage np = new NotesPage(driver);
		np.clickOnNotes();
		String actual1=np.doCreateNotes("This is a Test", FakerUtility.emailID());
		String expected1 = "This is a Test";
		softassert.assertEquals(actual1, expected1);
		softassert.assertAll();
		System.out.println("Test 2 : verifyNotesCreation :The note is added in the page - Execution Completed");
		
	}

	@Test(priority = 2, groups = { "regression" })
	public void verifySearchNote() throws Exception {
		LoginPage lp = new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");
		NotesPage np = new NotesPage(driver);
		np.clickOnNotes();
		String actual=np.doSearchNote("This is a Test");
		String expected = "This is a Test";
		Assert.assertEquals(actual, expected);
		System.out.println("Test 3 : verifySearchNote : The added note is verfied in the page - Execution Completed");
	}

	@Test(priority = 3, groups = { "smoke", "regression" })
	public void verifyEditedNote() throws Exception {
		LoginPage lp = new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");

		NotesPage np = new NotesPage(driver);
		np.clickOnNotes();
		np.doSearchNote("This is a Test");
		np.doEditNote("The Test is edited and need to delete");
		String actual1 = np.doSearchNote("The Test is edited and need to delete");
		String expected1 = "The Test is edited and need to delete";
		Assert.assertEquals(actual1, expected1);
		System.out.println("Test 4 : verifyEditedNote : The edited note is verified in the page - Execution Completed");
	}

	@Test(priority = 4, groups = { "smoke", "regression" })
	public void verifyDeleteNote() throws Exception {
		LoginPage lp = new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");

		NotesPage np = new NotesPage(driver);
		np.clickOnNotes();
		np.doDeleteNote("The Test is edited and need to delete");
		String actual2 = np.doVerifyDeleteNotes("The Test is edited and need to delete");
		String expected2 = "No record found.";
		Assert.assertEquals(actual2, expected2);
		System.out.println("Test 5 : verifyDeleteNote : The created and edited note is deleted from the page - Execution Completed");
	}
}
