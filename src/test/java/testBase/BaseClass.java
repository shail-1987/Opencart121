package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;



public class BaseClass {
	public static WebDriver driver;
	public Logger logger;
	public Properties p;

	@BeforeClass(groups= {"Sanity","Regression","Master"})

	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException {
		FileInputStream fis=new FileInputStream("./src//test//resources//config.properties");
		p=new Properties();
		p.load(fis);
		logger=LogManager.getLogger(this.getClass());//this always represent class

		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities=new DesiredCapabilities();

			if(os.equalsIgnoreCase("windows"))
			{
			capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else {
				System.out.println("no matching os");
				return;
			}

			switch(br.toLowerCase())
			{
			case "chrome":capabilities.setBrowserName("chrome");break;
			case "edge":capabilities.setBrowserName("MicrosoftEdge");break;
			case "firefox":capabilities.setBrowserName("firefox");break;
			default:System.out.println("no matching browser");return;

			}
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		}
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) 
		{
			switch(br.toLowerCase()) {
			case "chrome":
				org.openqa.selenium.chrome.ChromeOptions chromeOptions = new org.openqa.selenium.chrome.ChromeOptions();
				chromeOptions.addArguments("--headless=new");
				driver = new ChromeDriver(chromeOptions);
				break;
			case "edge" :
				org.openqa.selenium.edge.EdgeOptions edgeOptions = new org.openqa.selenium.edge.EdgeOptions();
				edgeOptions.addArguments("--headless=new");
				driver = new EdgeDriver(edgeOptions);
				break;
			case "firefox":
				org.openqa.selenium.firefox.FirefoxOptions firefoxOptions = new org.openqa.selenium.firefox.FirefoxOptions();
				firefoxOptions.addArguments("--headless");
				driver = new FirefoxDriver(firefoxOptions);
				break;
			default: System.out.println("invalid browser");return;
			}
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(p.getProperty("appURL"));

	}

	@AfterClass(groups={"Sanity","Regression","Master"})
	public void tearDown() {
		//driver.quit();

	}

		public String randomString() 
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomNumbers() 
	{
		String generatedNumbers=RandomStringUtils.randomNumeric(10);
		return generatedNumbers;
	}
	
	public String randomAlphaNumeric() 
	{
		String generatedString=RandomStringUtils.randomAlphabetic(3);
		String generatedNumbers=RandomStringUtils.randomNumeric(3);
		return (generatedString+"@"+generatedNumbers);
	}
	
	public String capturescreen(String tname)throws IOException {
		
	/*	SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss"); 
		Date dt = new Date(); 
		String currentdatetimestamp = df.format(dt);*/
		
		String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesscreenshot=(TakesScreenshot)driver;
		File sourceFile=takesscreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp+".png";
		File targetFile=new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}


}
