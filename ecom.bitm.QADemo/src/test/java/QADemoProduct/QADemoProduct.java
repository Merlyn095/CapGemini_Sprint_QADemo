package QADemoProduct;

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

public class QADemoProduct extends BaseClass {
	@Test(groups = {"Negative"},dataProvider = "systemData",dataProviderClass = DataProviderUtility.class)
	    public void verifyProductCatalog(String Username, String Password) {

	        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(15));
	        QADemoLoginPage login = new QADemoLoginPage(driver);
	        QADemoPoductCatalog product = new QADemoPoductCatalog(driver);
	        QADemoOrderPage order = new QADemoOrderPage(driver);

	        login.login(Username, Password);
	        Reporter.log("Login credentials entered", true);
	        wait.until(ExpectedConditions.urlContains("/catalog"));
	        Assert.assertTrue(driver.getCurrentUrl().contains("/catalog"),"Login failed");
	        Reporter.log("Login successful", true);
	        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(product.getProductCatalogHeading())).isDisplayed(),"Product Catalog is not displayed");
	        Reporter.log("Product Catalog displayed successfully", true);
	        order.getLogoutButton().click();
	        
	  }
	
	@Test(groups = {"Functionality"},dataProvider = "systemData",dataProviderClass = DataProviderUtility.class)
	
	public void verifyProduct(String Username, String Password) {
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(15));
        QADemoLoginPage login = new QADemoLoginPage(driver);
        QADemoPoductCatalog product = new QADemoPoductCatalog(driver);
        QADemoOrderPage order = new QADemoOrderPage(driver);
		
		login.login(Username, Password);
		wait.until(ExpectedConditions.urlContains("/catalog"));
		product.getProduct().click();
		Assert.assertEquals(driver.getCurrentUrl().contains("products/bluetooth-speaker"), true);
		Reporter.log("Product Bluetooth Speaker displayed",true);
		order.getLogoutButton().click();
		
	}
	
@Test(groups = {"Integration"},dataProvider = "systemData",dataProviderClass = DataProviderUtility.class)
	
	public void verifyProductAddedToCart(String Username, String Password) {
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(15));
        QADemoLoginPage login = new QADemoLoginPage(driver);
        QADemoPoductCatalog product = new QADemoPoductCatalog(driver);
        QADemoOrderPage order = new QADemoOrderPage(driver);
        QADemoCartPage cart = new QADemoCartPage(driver);
        
		login.login(Username, Password);
		wait.until(ExpectedConditions.urlContains("/catalog"));
		product.getProduct().click();
		product.getAddToCart().click();
		product.getCartIcon().click();
		Assert.assertEquals(cart.getCartItem().isDisplayed(), true);
		Reporter.log("Sucessfully added product added in cart",true);
		cart.getDeleteicon().click();
		order.getLogoutButton().click();
		
	}
	

}
