package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	@Test(groups={"Sanity","Master"})
	public void verify_login() {
		logger.info("******Starting TC_002_LoginTest ********");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		
		hp.clickLogin();
		logger.info("clicked on login button");
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		Thread.sleep(3000);
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExists();
		
		Assert.assertEquals(targetPage, true,"Login Failed");
		}catch(Exception e) {
			Assert.fail();
		}
		logger.info("**********Test Case Done********");
	}

}
