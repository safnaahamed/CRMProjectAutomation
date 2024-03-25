package utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import constants.Constants;

public class ElementUtility {
	
	WebDriver driver;
		
	
	public ElementUtility(WebDriver driver) {
		
		this.driver=driver;
	}
	
	public static String getPropertyValue(String key) //to invoke the config file
	{

		File src=new File(Constants.propertyConfig_File);
		Properties pro=new Properties();
		try {
			FileInputStream fis = new FileInputStream (src);
			
			pro.load(fis); //to load the properties from the config file
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		String value=pro.get(key).toString();//this will pass the key value from the config file
		return value;
	}
	
	public int getTableDataRowCount(List<WebElement> tableRowData ,String expectedValue)
	{
		
	        int counter=0;
		for(int i=0;i<tableRowData.size();i++)
		{
			String value=tableRowData.get(i).getText();
			if(expectedValue.equalsIgnoreCase(value))
			{
				counter=i+1;
				break;
			}
			
		}
		return counter;
	}

	
	public void scrollElement(WebElement element) {
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}
	
	public void selectElementFromDropdown(WebElement element, String type, String value) {
		Select oselect = new Select(element);
		if(type.equalsIgnoreCase("index")) {
			oselect.selectByIndex(Integer.parseInt(value));
		}else if(type.equalsIgnoreCase("value")) {
			oselect.selectByValue(value);
		}else if(type.equalsIgnoreCase("visible text")) {
			oselect.selectByVisibleText(value);
		}
	}
	public void clearAndSendKey(WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
	}
	public void dateSelect(WebElement element,String dateValue) {

		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('value','"+dateValue+"');", element);

	}
}
