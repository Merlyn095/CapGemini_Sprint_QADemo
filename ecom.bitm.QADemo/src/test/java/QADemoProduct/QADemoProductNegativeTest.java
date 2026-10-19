package QADemoProduct;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
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

public class QADemoProductNegativeTest  extends BaseClass{
@Test(groups = {"Negative"},dataProvider = "systemData",dataProviderClass = DataProviderUtility.class)
	
	public void verifyProductAddedToCart(String Username, String Password) {
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(15));
        QADemoLoginPage login = new QADemoLoginPage(driver);
        QADemoPoductCatalog product = new QADemoPoductCatalog(driver);
        QADemoOrderPage order = new QADemoOrderPage(driver);
        QADemoCartPage cart = new QADemoCartPage(driver);
        
		login.login(Username, Password);
		wait.until(ExpectedConditions.urlContains("/catalog"));
		product.getProduct().click();
		
		product.getCartIcon().click();
		AssertJUnit.assertEquals(cart.getEmptyCartMessage().isDisplayed(), true);
		Reporter.log("Negative test passed: Product was not added without clicking Add to Car",true);
		
		order.getLogoutButton().click();
		
	}
	
	

}
