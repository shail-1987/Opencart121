package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage
{
	
	
	public HomePage(WebDriver driver) 
	{
		super(driver);//will envoke the parent class constructor{it means we are passing the driver to the parent class constructor}
	}
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement lnkMyaccount;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(xpath="//a[text()='Login']")
	WebElement linkLogin;
	

	private WebDriverWait getWait() {
		return new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	public void clickMyAccount() {
		getWait().until(ExpectedConditions.visibilityOf(lnkMyaccount));
		getWait().until(ExpectedConditions.elementToBeClickable(lnkMyaccount)).click();
	}
	
	public void clickRegister() {
		getWait().until(ExpectedConditions.visibilityOf(lnkRegister));
		getWait().until(ExpectedConditions.elementToBeClickable(lnkRegister)).click();
	}
	
	public void clickLogin() {
		getWait().until(ExpectedConditions.visibilityOf(linkLogin));
		getWait().until(ExpectedConditions.elementToBeClickable(linkLogin)).click();
	}


}
