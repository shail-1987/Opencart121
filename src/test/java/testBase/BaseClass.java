package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

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

	@BeforeClass
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/config.properties");
		p = new Properties();
		p.load(fis);
		logger = LogManager.getLogger(this.getClass());

		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			if (os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			} else if (os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			} else {
				System.out.println("no matching os");
				return;
			}
			switch (br.toLowerCase()) {
				case "chrome":
					capabilities.setBrowserName("chrome");
					break;
				case "edge":
					capabilities.setBrowserName("MicrosoftEdge");
					break;
				case "firefox":
					capabilities.setBrowserName("firefox");
					break;
				default:
					System.out.println("no matching browser");
					return;
			}
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		} else if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
            switch (br.toLowerCase()) {
                case "chrome":
                    io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    io.github.bonigarcia.wdm.WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                case "firefox":
                    io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                default:
                    System.out.println("invalid browser");
                    return;
            }
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(p.getProperty("appURL"));
	}

	@AfterClass
	public void tearDown() {
		//driver.quit();
	}

	public String randomString() {
		return java.util.UUID.randomUUID().toString().replaceAll("[^A-Za-z]", "").substring(0, 5);
	}

	public String randomNumbers() {
		return java.util.UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0, 10);
	}

	public String randomAlphaNumeric() {
		String generatedString = java.util.UUID.randomUUID().toString().replaceAll("[^A-Za-z]", "").substring(0, 3);
		String generatedNumbers = java.util.UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0, 3);
		return (generatedString + "@" + generatedNumbers);
	}

	public String capturescreen(String tname) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesscreenshot = (TakesScreenshot) driver;
		File sourceFile = takesscreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);
		boolean renamed = sourceFile.renameTo(targetFile);
		if (!renamed) {
			throw new IOException("Failed to move screenshot to target location: " + targetFilePath);
		}
		return targetFilePath;
	}


}
