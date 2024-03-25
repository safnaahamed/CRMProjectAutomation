package page;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import utility.ElementUtility;
import utility.WaitUtility;

public class ClientsPage {
	WebDriver driver;
	ElementUtility elementutility;
	WaitUtility waitutility;
	
	@FindBy (xpath="//span[text()='Clients']//parent::a") WebElement clientslink;
	@FindBy (xpath="//a[@title='Add client']") WebElement addClientBtn;
	@FindBy (xpath="//input[@name='company_name']") WebElement companyNameField;
	@FindBy (xpath="//textarea[@name='address']") WebElement addressField;
	@FindBy (xpath="//input[@name='city']") WebElement cityField;
	@FindBy (xpath="//input[@name='state']") WebElement stateField;
	@FindBy (xpath="//input[@name='zip']") WebElement zipcodeField;
	@FindBy (xpath="//input[@name='country']") WebElement countryField;
	@FindBy (xpath="//input[@name='phone']") WebElement phoneField;
	@FindBy (xpath="//input[@name='website']") WebElement websiteField;
	@FindBy (xpath="//input[@name='vat_number']") WebElement vatNumberField;
	@FindBy (xpath="//span[@id='select2-chosen-5']") WebElement currencyField;
	@FindBy (xpath="//input[@id='s2id_autogen5_search']") WebElement typeCurrency;
	@FindBy (xpath="//span[@class='select2-match']") WebElement currencyselected;
	@FindBy (xpath="//input[@name='currency_symbol']") WebElement currencySymbol;
	@FindBy (xpath="//input[@id='disable_online_payment']") WebElement disableField;
	@FindBy (xpath="//button[@type='submit']") WebElement saveBtn;
	@FindBy(xpath = "//button[@class='close']") WebElement closeBtn;
	@FindBy (xpath="//input[@placeholder='Search']") WebElement searchBtn;
	@FindBy (xpath="//table[@id='client-table']//tbody//tr//td[2]") WebElement searchText;
	
	
	public ClientsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		elementutility = new ElementUtility(driver);
		waitutility = new WaitUtility(driver);
		
	}
	
	public void clickOnClientsLink() {
		waitutility.waitForVisibility(clientslink);
		clientslink.click();
		
	}
	
	public void clickOnAddClients() {
		waitutility.waitForVisibility(addClientBtn);
		addClientBtn.click();
		
	}
	public String enterClientDetails(String companyname, String address,String city, String state, String zipcode, String country, String phone, String website, String VAT, String currency, String currencysymbol) {
		waitutility.waitForVisibility(companyNameField);
		companyNameField.sendKeys(companyname);
		waitutility.waitForVisibility(addressField);
		addressField.sendKeys(address);
		waitutility.waitForVisibility(cityField);
		cityField.sendKeys(city);
		waitutility.waitForVisibility(stateField);
		elementutility.scrollElement(stateField);
		stateField.sendKeys(state);
		waitutility.waitForVisibility(zipcodeField);
		zipcodeField.sendKeys(zipcode);
		waitutility.waitForVisibility(countryField);
		countryField.sendKeys(country);
		waitutility.waitForVisibility(phoneField);
		phoneField.sendKeys(phone);
		waitutility.waitForVisibility(websiteField);
		websiteField.sendKeys(website);
		waitutility.waitForVisibility(vatNumberField);
		elementutility.scrollElement(vatNumberField);
		vatNumberField.sendKeys(VAT);
		waitutility.waitForElementClick(currencyField);
		currencyField.click();
		waitutility.waitForVisibility(typeCurrency);
		typeCurrency.sendKeys(currency);
		currencyselected.click();
		waitutility.waitForVisibility(currencySymbol);
		currencySymbol.sendKeys(currencysymbol);
		disableField.click();
		saveBtn.click();
		closeBtn.click();
		elementutility.clearAndSendKey(searchBtn, companyname);
		String actual=searchText.getText();
		return actual;
		
	}
	public String searchClient(String searchvalue){
		elementutility.clearAndSendKey(searchBtn, searchvalue);
		By verifynotes=By.xpath("//table[@id='client-table']//tbody//tr//td//a[contains(text(),'"+searchvalue+"')]");
		waitutility.waitForVisibility(verifynotes);
		List<WebElement> clienttable=driver.findElements(By.xpath("//table[@id='client-table']//tbody//tr//td//a[contains(text(),'"+searchvalue+"')]"));
		waitutility.waitForVisibility(clienttable);
		int row=elementutility.getTableDataRowCount(clienttable, searchvalue);
		String message="";
		if(row!=0) 
		{
			WebElement tableRow=driver.findElement(By.xpath("//table[@id='client-table']//tbody//tr["+row+"]//td[2]"));
			message=tableRow.getText();
			System.out.println(message);
			
		}
		return message;
	}
	
}
