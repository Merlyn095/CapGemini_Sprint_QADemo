
package genericUtility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class WebDriverUtility {
	WebDriver driver=null;
	public WebDriver launchBrowser(String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
		ChromeOptions settings = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.password_manager_leak_detection", false);
		
		prefs.put("autofill.credit_card_enabled", false);

        settings.setExperimentalOption("prefs", prefs);
//        settings.addArguments("--start-maximized");
//        settings.addArguments("--force-device-scale-factor=0.8");
		driver=new ChromeDriver(settings);
		}

		else if(browser.equalsIgnoreCase("edge"))
			 driver = new EdgeDriver();
		
		else if(browser.equalsIgnoreCase("safari"))
			 driver = new SafariDriver();
		
		
		else if(browser.equalsIgnoreCase("firefox")) {
			 driver = new FirefoxDriver();
		}
			 
		else
			driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));


		return driver;
	}
	
	public String takeScreenshot(WebDriver driver, String testName) throws IOException {

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String path = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + timestamp + ".png";

        File destination = new File(path);
        destination.getParentFile().mkdirs();

        File source =((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(source, destination);

        return path;
    }
}