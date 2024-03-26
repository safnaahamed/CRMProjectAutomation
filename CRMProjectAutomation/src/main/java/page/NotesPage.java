package page;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.ElementUtility;
import utility.WaitUtility;

public class NotesPage {
	WebDriver driver;
	WaitUtility waitutility;
	ElementUtility elementutility;
	@FindBy(id = "email")
	WebElement email;
	@FindBy(id = "password")
	WebElement password;
	@FindBy(xpath = "//button[text()='Sign in']")
	WebElement signinBtn;
	@FindBy(xpath = "//span[text()='Notes']")
	WebElement notelink;
	@FindBy(className = "btn-default")
	WebElement addNoteBtn;
	@FindBy(id = "title")
	WebElement addNoteTitle;
	@FindBy(id = "description")
	WebElement description;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement submitBtn;
	@FindBy(xpath = "//button[@class='close']")
	WebElement closeBtn;
	@FindBy(xpath = "//input[@placeholder='Search']")
	WebElement searchBtn;
	@FindBy(xpath = "//table[@id='note-table']//tbody//td[2]//a")
	WebElement titlecolumn;
	@FindBy(xpath = "(//table[@id='note-table']//tbody//td[4]//a[@class='edit']//i)[1]")
	WebElement editNote;
	@FindBy(xpath = "(//table[@id='note-table']//tbody//td[4]//a[@class='delete']//i)[1]")
	WebElement deleteNote;
	@FindBy(id = "confirmDeleteButton")
	WebElement confrmDelBtn;
	@FindBy(xpath = "//div[@class='app-alert-message']")
	WebElement confrmDelMessage;
	@FindBy(xpath = "//table[@id='note-table']//tbody//td[@class='dataTables_empty']")
	WebElement norecordsmessage;

	public NotesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitutility = new WaitUtility(driver);
		elementutility = new ElementUtility(driver);
	}

	public void clickOnNotes() {
		waitutility.waitForElementClick(notelink);
		notelink.click();
	}

	public String doCreateNotes(String title, String descnote) throws Exception {
		waitutility.waitForVisibility(addNoteBtn);
		addNoteBtn.click();
		waitutility.waitForVisibility(addNoteTitle);
		addNoteTitle.sendKeys(title);
		waitutility.waitForVisibility(description);
		description.sendKeys(descnote);
		submitBtn.click();
		waitutility.waitForVisibility(closeBtn);
		closeBtn.click();
		String message = titlecolumn.getText();
		return message;

	}

	public String doSearchNote(String searchvalue) {
		waitutility.waitForVisibility(searchBtn);
		elementutility.clearAndSendKey(searchBtn, searchvalue);
		
		List<WebElement> notetable = driver.findElements(
				By.xpath("//table[@id='note-table']//tbody//tr//td//a[contains(text(),'" + searchvalue + "')]"));
		waitutility.waitForVisibility(notetable);
		By verifynotes = By
				.xpath("//table[@id='note-table']//tbody//tr//td//a[contains(text(),'" + searchvalue + "')]");
		waitutility.waitForVisibility(verifynotes);
		int row = elementutility.getTableDataRowCount(notetable, searchvalue);
		String message = "";
		if (row != 0) {
			WebElement tableRow = driver
					.findElement(By.xpath("//table[@id='note-table']//tbody//tr[" + row + "]//td[2]"));
			message = tableRow.getText();
			System.out.println(message);

		}
		return message;
	}

	public void doEditNote(String edittitle) {
		
			if (closeBtn.isDisplayed()) {
			closeBtn.click();
			waitutility.waitForElementClick(editNote);
			editNote.click();
			waitutility.waitForVisibility(addNoteTitle);
			addNoteTitle.clear();
			addNoteTitle.sendKeys(edittitle);
			submitBtn.click();
			closeBtn.click();
		} else {
			waitutility.waitForElementClick(editNote);
			editNote.click();
			waitutility.waitForVisibility(addNoteTitle);
			addNoteTitle.clear();
			addNoteTitle.sendKeys(edittitle);
			submitBtn.click();
			closeBtn.click();

		}
	}

	public void doDeleteNote(String searchvalue) {
		if (closeBtn.isDisplayed()) {
			closeBtn.click();
			doSearchNote(searchvalue);
			waitutility.waitForVisibility(deleteNote);
			deleteNote.click();
			confrmDelBtn.click();
			String message2 = confrmDelMessage.getText();
			System.out.println(message2);
		} else {
			doSearchNote(searchvalue);
			waitutility.waitForVisibility(deleteNote);
			deleteNote.click();
			confrmDelBtn.click();
			String message2 = confrmDelMessage.getText();
			System.out.println(message2);
		}
	}

	public String doVerifyDeleteNotes(String title) {
		waitutility.waitForVisibility(norecordsmessage);
		String message3 = norecordsmessage.getText();
		return message3;

	}
}
