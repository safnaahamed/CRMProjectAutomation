package page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.ElementUtility;
import utility.WaitUtility;

public class ProjectsPage {
	WebDriver driver;
	WaitUtility waitutility;
	ElementUtility elementutility;

	@FindBy(id = "email")
	WebElement email;
	@FindBy(id = "password")
	WebElement password;
	@FindBy(xpath = "//button[text()='Sign in']")
	WebElement signinBtn;
	@FindBy(xpath = "//span[text()='Projects']")
	WebElement projectsBtn;
	@FindBy(xpath = "//span[text()='All Projects']")
	WebElement allProjectsBtn;
	@FindBy(xpath = "//a[@title='Add project']")
	WebElement addProjectBtn;
	@FindBy(id = "title")
	WebElement addProjectTitle;
	@FindBy(xpath = "//textarea[@id='description']")
	WebElement projectdescription;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement submitBtn;
	@FindBy(xpath = "//button[@class='close']")
	WebElement closeBtn;
	@FindBy(xpath = "//input[@placeholder='Search']")
	WebElement searchBtn;
	@FindBy(xpath = "//table[@id='project-table']//tbody//td[2]//a")
	WebElement projecttitle;
	@FindBy(xpath = "(//table[@id='project-table']//tbody//td[9]//a[@title='Edit project']//i)[1]")
	WebElement editProject;

	@FindBy(xpath = "(//table[@id='project-table']//tbody//td[9]//a[2]//i)[1]")
	WebElement deleteProject;
	@FindBy(id = "confirmDeleteButton")
	WebElement confrmDelBtn;
	@FindBy(xpath = "//div[@class='app-alert-message']")
	WebElement confrmDelMessage;
	@FindBy(xpath = "//table[@id='project-table']//tbody//td[@class='dataTables_empty']")
	WebElement norecordsmessage;
	@FindBy(xpath = "//input[@id='start_date']")
	WebElement startdate;

	public ProjectsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitutility = new WaitUtility(driver);
		elementutility = new ElementUtility(driver);
	}

	public void doClickOnProjects() throws Exception {
		waitutility.waitForVisibility(projectsBtn);
		projectsBtn.click();
		waitutility.waitForVisibility(allProjectsBtn);
		allProjectsBtn.click();
	}

	public String addProject(String title, String description, String startdatevalue) {

		waitutility.waitForVisibility(addProjectBtn);
		addProjectBtn.click();
		waitutility.waitForVisibility(addProjectTitle);
		addProjectTitle.sendKeys(title);
		waitutility.waitForVisibility(projectdescription);
		projectdescription.click();
		projectdescription.sendKeys(description);
		elementutility.dateSelect(startdate, startdatevalue);
		submitBtn.click();
		waitutility.waitForVisibility(closeBtn);
		closeBtn.click();
		String message = projecttitle.getText();
		return message;

	}

	public String doSearchProjects(String searchvalue) {
		waitutility.waitForVisibility(searchBtn);
		elementutility.clearAndSendKey(searchBtn, searchvalue);
		By verifynotes=By.xpath("//table[@id='project-table']//tbody//td[2]//a[contains(text(),'"+searchvalue+"')]");
		waitutility.waitForVisibility(verifynotes);
		List<WebElement> projecttable=driver.findElements(By.xpath("//table[@id='project-table']//tbody//td[2]//a[contains(text(),'"+searchvalue+"')]"));
		waitutility.waitForVisibility(projecttable);
		int row=elementutility.getTableDataRowCount(projecttable, searchvalue);
		String message="";
		if(row!=0) 
		{
			WebElement tableRow=driver.findElement(By.xpath("//table[@id='project-table']//tbody//tr["+row+"]//td[2]"));
			message=tableRow.getText();
			System.out.println(message);
			
		}
		return message;
	}

	public void doEditTitle(String edittitle) {
		waitutility.waitForVisibility(editProject);
		editProject.click();
		addProjectTitle.clear();
		addProjectTitle.sendKeys(edittitle);
		submitBtn.click();
		closeBtn.click();
	}

	public String doSearchEditedProjects(String title) {
		if(closeBtn.isDisplayed()) {
			closeBtn.click();
			doSearchProjects(title);
		}else {
			doSearchProjects(title);
		}
		return title;
	}

	public void doDeleteProject(String searchvalue) {
		doSearchProjects(searchvalue);
		searchBtn.clear();
		waitutility.waitForVisibility(deleteProject);
		deleteProject.click();
		confrmDelBtn.click();
		String deletemessage = confrmDelMessage.getText();
		System.out.println(deletemessage);
	}

	public String doVerifyDeleteProject() {
		waitutility.waitForVisibility(norecordsmessage);
		String message3 = norecordsmessage.getText();
		return message3;

	}

}
