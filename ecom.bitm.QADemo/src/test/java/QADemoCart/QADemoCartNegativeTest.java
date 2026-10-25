package QADemoCart;

import org.testng.annotations.Test;


import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;


import genericUtility.BaseClass;
import genericUtility.DataProviderUtility;
import qaDemoPom.QADemoCartPage;
import qaDemoPom.QADemoLoginPage;
import qaDemoPom.QADemoOrderPage;
import qaDemoPom.QADemoPoductCatalog;

public class QADemoCartNegativeTest extends BaseClass{
	@Test(groups = {"Negative"},dataProvider = "systemData",dataProviderClass = DataProviderUtility.class)
    public void verifyProductIncrease(String Username, String Password) throws InterruptedException {
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(15));
        QADemoLoginPage login = new QADemoLoginPage(driver);
        QADemoPoductCatalog product = new QADemoPoductCatalog(driver);
        QADemoOrderPage order = new QADemoOrderPage(driver);
        QADemoCartPage cart = new QADemoCartPage(driver);
        
		login.login(Username, Password);
		wait.until(ExpectedConditions.urlContains("/catalog"));
		product.clickProduct(1);
	    Assert.assertEquals(product.getCartIcon().getText(),"");
	    Reporter.log("Negative test passed: Empty cart does not show any product",true);
		
		
		
	}

}
