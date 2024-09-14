package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);// will envoke the parent class constructor{it means we are passing the driver
					  // to the parent class constructor}
	}

	@FindBy(xpath = "//*[@id='input-firstname']")
	WebElement txtFirstname;

	@FindBy(xpath = "//*[@id='input-lastname']")
	WebElement txtLastname;

	@FindBy(xpath = "//*[@id='input-email']")
	WebElement txtEmail;

	@FindBy(xpath = "//*[@id='input-telephone']")
	WebElement txtTelephone;

	@FindBy(xpath = "//*[@id='input-password']")
	WebElement txtPassword;

	@FindBy(xpath = "//*[@id='input-confirm']")
	WebElement txtConfirmPassword;

	@FindBy(xpath = "//*[@id='content']/form/div/div/input[1]")
	WebElement chkdPolicy;

	@FindBy(xpath = "//*[@id='content']/form/div/div/input[2]")
	WebElement btnContinue;

	@FindBy(xpath = "//*[@id='content']/h1")
	WebElement msgConfirmation;

	public void setFirstName(String fname) {
		txtFirstname.sendKeys(fname);
	}

	public void setLastName(String lname) {
		txtLastname.sendKeys(lname);
	}

	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}

	public void setTelephone(String tel) {
		txtTelephone.sendKeys(tel);
	}

	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}

	public void setConfirmPassword(String pwd) {
		txtConfirmPassword.sendKeys(pwd);
	}

	public void setPrivacyPolicy() {
		chkdPolicy.click();
	}

	public void clickContinue() {
		btnContinue.click();
//		btnContinue.submit();
//		Actions act=new Actions(driver);
//		act.moveToElement(btnContinue);
//		JavascriptExecutor js=(JavascriptExecutor)driver;
//		js.executeScript("arguments[0].click()", btnContinue);
		
		
		
	}

	public String getConfirmationMsg() {
		try {
			return (msgConfirmation.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
	}

}
