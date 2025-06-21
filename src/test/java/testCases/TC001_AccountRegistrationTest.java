package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups={"Regression","Master"})
	
	public void verify_account_registration() {
		logger.info("*********Test Starting TC001_AccountRegistrationTest*************");
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on my Account");
		hp.clickRegister();
		logger.info("Clicked on Register");

		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		logger.info("Providing Customer details...");
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString()+"@gmail.com");
		regpage.setTelephone(randomNumbers());
		
		String password=randomAlphaNumeric();
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		String confMsg = regpage.getConfirmationMsg();
		Assert.assertEquals(confMsg,"Your Account Has Been Created!");
		}catch(Exception e) {
			logger.error("Test Failed...");
			logger.debug("Debug Logs");
			Assert.fail("Test failed: " + e.getMessage());
		}
		logger.info("***************Test Completed**********************");
		

	}
	
	
}
