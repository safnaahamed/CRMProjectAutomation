package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.ElementUtility;
import utility.WaitUtility;

public class LoginPage {
	WebDriver driver;
	WaitUtility wait;
	ElementUtility elementutility;

	@FindBy(id = "email")
	WebElement email;
	@FindBy(id = "password")
	WebElement password;
	@FindBy(xpath = "//button[text()='Sign in']")
	WebElement signinBtn;
	@FindBy(xpath = "//span[text()='Dashboard']//preceding-sibling::i//parent::a")
	WebElement dashboard;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WaitUtility(driver);
		elementutility = new ElementUtility(driver);
	}

	public String doLogin(String crmemail, String crmpassword) {

		wait.waitForVisibility(email);
		email.sendKeys(crmemail);
		password.sendKeys(crmpassword);
		signinBtn.click();
		String loginverification = dashboard.getText();
		return loginverification;

	}

}
