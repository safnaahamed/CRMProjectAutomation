package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import page.ClientsPage;
import page.LoginPage;
import utility.FakerUtility;

public class ClientsTest extends BaseTest {
  @Test(priority=1)
  public void verifyAddClients() throws Exception{
	  
	  LoginPage lp = new LoginPage(driver);
	  lp.doLogin("admin@admin.com", "12345678");
	  
	  ClientsPage cp = new ClientsPage(driver);
	  cp.clickOnClientsLink();
	  cp.clickOnAddClients();
	  String actual=cp.enterClientDetails("MariApps", "SmartCity,Kochi", "Ernakulam", "Kochi", "682042", "India", FakerUtility.phoneNumber(), FakerUtility.emailID(), "012SAWE", "USD", "$");
	  String expected="MariApps";
	  Assert.assertEquals(actual, expected);
	  System.out.println("Test 10 : verifyAddClients : The client is newly created and added in the page - Execution Completed");
  }
  @Test(priority=1)
  public void verifySearchClient() throws Exception{
	  
	  LoginPage lp = new LoginPage(driver);
	  lp.doLogin("admin@admin.com", "12345678");
	  
	  ClientsPage cp = new ClientsPage(driver);
	  cp.clickOnClientsLink();
	  String actual=cp.searchClient("MariApps");
	  String expected="MariApps";
	  Assert.assertEquals(actual, expected);
	  System.out.println("Test 11 : verifyAddClients : The created client is verified in the page - Execution Completed");
}
}
