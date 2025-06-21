package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
	
	
	public void clickMyAccount() {
		lnkMyaccount.click();
	}
	
	public void clickRegister() {
		lnkRegister.click(); 
	}
	
	public void clickLogin() {
		linkLogin.click(); 
	}


}
