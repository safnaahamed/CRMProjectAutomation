package utility;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtility {
	
	WebDriver driver;
	WebDriverWait wait;
	FluentWait<WebDriver> fwait;
	
	public WaitUtility(WebDriver driver){
		
		this.driver=driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		this.fwait = new FluentWait<WebDriver>(driver)
			       .withTimeout(Duration.ofSeconds(30))
			       .pollingEvery(Duration.ofSeconds(5))
			       .ignoring(NoSuchElementException.class);
	}
	
	
	public void waitForVisibility(WebElement element) {
		
		
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public void waitForVisibility(List<WebElement> element) {
		
		
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}
	public void waitForElementClick(WebElement element) {
		
		
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
public void waitForVisibility(By Locator) {
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
	}
	
	public void waitForElementClick(By Locator) {
		
		
		wait.until(ExpectedConditions.elementToBeClickable(Locator));
	}

public void waitForVisibilityOfElement(By Locator) {
		
		
		fwait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
	}

}
