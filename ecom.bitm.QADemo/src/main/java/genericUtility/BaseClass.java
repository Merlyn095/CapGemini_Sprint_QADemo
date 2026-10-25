
package genericUtility;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;






public class BaseClass {
	public WebDriver driver=null;

	public WebDriverUtility wu=new WebDriverUtility();
	public FileUtility  fu=new FileUtility();
	
	@BeforeSuite
	public void bS() {
		Reporter.log("Open Database Connectivity",true);
	}
	@BeforeTest
	public void bT() {
		Reporter.log("pre-condition", true);
	}

	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void bC(@Optional("chrome")String browser)throws Exception {
		Reporter.log("Before Class",true);
		driver = wu.launchBrowser(browser);
		driver.manage().window().maximize();
		
	}

	@BeforeMethod(alwaysRun = true)
	public void bM() throws Exception {
		Reporter.log("Before Method",true);
		driver.get(fu.getDataFromPropertyFile("url"));

	}


	
	@AfterMethod(alwaysRun = true)
	public void aM() {
		 Reporter.log("After Method", true);
		 //logout 

	    
	}

	@AfterClass(alwaysRun = true)
	public void aC() {
		driver.quit();
		Reporter.log("Close Browser", true);
	}

	@AfterTest
	public void aT() {
		Reporter.log("post-condition", true);
	}


	@AfterSuite
	public void aS() {
		Reporter.log("Close - DataBase Connectivity", true);
	}


}