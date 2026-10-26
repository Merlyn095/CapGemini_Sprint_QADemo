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
import qaDemoPom.QADemoCheckOutPage;
import qaDemoPom.QADemoLoginPage;
import qaDemoPom.QADemoOrderPage;
import qaDemoPom.QADemoPoductCatalog;

public class QADemoCartTest  extends BaseClass{
	
    @Test(groups = {"Smoke"},dataProvider = "systemData",dataProviderClass = DataProviderUtility.class,priority=1)
	
	public void verifyProductAddedToCart(String Username, String Password) {
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(15));
        QADemoLoginPage login = new QADemoLoginPage(driver);
        QADemoPoductCatalog product = new QADemoPoductCatalog(driver);
        QADemoOrderPage order = new QADemoOrderPage(driver);
        QADemoCartPage cart = new QADemoCartPage(driver);
        
		login.login(Username, Password);
		wait.until(ExpectedConditions.urlContains("/catalog"));
		product.clickProduct(9);
		product.getAddToCart().click();
		product.getCartIcon().click();
		Assert.assertEquals(cart.getShoppingCartHeading().isDisplayed(), true);
		Reporter.log("Navigated to cart Page Sucesssfully",true);
		cart.getClearCart().click();
		Assert.assertEquals(cart.getEmptyCartMessage().isDisplayed(),true);
		wait.until(ExpectedConditions.visibilityOf(cart.getEmptyCartMessage()));
        Reporter.log("Cart cleared successfully", true);
		order.getLogoutButton().click();
		
	}
    @Test(groups = {"Functionality"},dataProvider = "systemData",dataProviderClass = DataProviderUtility.class,priority=2)
    public void verifyProductIncrease(String Username, String Password) throws InterruptedException {
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(15));
        QADemoLoginPage login = new QADemoLoginPage(driver);
        QADemoPoductCatalog product = new QADemoPoductCatalog(driver);
        QADemoOrderPage order = new QADemoOrderPage(driver);
        QADemoCartPage cart = new QADemoCartPage(driver);
        
		login.login(Username, Password);
		wait.until(ExpectedConditions.urlContains("/catalog"));
		product.clickProduct(1);
		product.getAddToCart().click();
		product.getCartIcon().click();
		Assert.assertEquals(cart.getShoppingCartHeading().isDisplayed(), true);
		cart.getIncreaseProduct().click();
		Assert.assertEquals(cart.getNumber().getText(),"2");
		Reporter.log("Product item sucessfully increased ",true);
		cart.getClearCart().click();
		Assert.assertEquals(cart.getEmptyCartMessage().isDisplayed(),true);
		wait.until(ExpectedConditions.visibilityOf(cart.getEmptyCartMessage()));
        Reporter.log("Cart cleared successfully", true);
		Thread.sleep(2000);
		order.getLogoutButton().click();
		
	}
    
    @Test(groups = {"Integration"},dataProvider = "systemData",dataProviderClass = DataProviderUtility.class,priority=3)
    public void verifyCountinueShopping(String Username, String Password) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        
		QADemoLoginPage login = new QADemoLoginPage(driver);
        QADemoPoductCatalog product = new QADemoPoductCatalog(driver);
        QADemoOrderPage order = new QADemoOrderPage(driver);
        QADemoCartPage cart = new QADemoCartPage(driver);
        
		login.login(Username, Password);
		wait.until(ExpectedConditions.urlContains("/catalog"));
		product.clickProduct(13);
		product.getAddToCart().click();
		product.getCartIcon().click();
		Assert.assertEquals(cart.getShoppingCartHeading().isDisplayed(), true);		
		
		cart.getContinueshopping().click();
		product.clickProduct(3);
		product.getAddToCart().click();
		product.getCartIcon().click();
		Assert.assertEquals(product.getCartIcon().getText(),"2");
		Reporter.log("Continued Shopping Successfully",true);
		wait.until(ExpectedConditions.urlContains("/cart"));
		cart.getClearCart().click();
		Assert.assertEquals(cart.getEmptyCartMessage().isDisplayed(),true);
		wait.until(ExpectedConditions.visibilityOf(cart.getEmptyCartMessage()));
		Reporter.log("Cart cleared successfully", true);
		Thread.sleep(2000);
        order.getLogoutButton().click();
			
	}
    
    @Test(groups = {"Integration"},dataProvider = "systemData",dataProviderClass = DataProviderUtility.class,priority=4)
    public void verifyProceedToCheckout(String Username, String Password) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        
		QADemoLoginPage login = new QADemoLoginPage(driver);
        QADemoPoductCatalog product = new QADemoPoductCatalog(driver);
        QADemoOrderPage order = new QADemoOrderPage(driver);
        QADemoCartPage cart = new QADemoCartPage(driver);
        QADemoCheckOutPage checkout = new QADemoCheckOutPage(driver);
        
		login.login(Username, Password);
		wait.until(ExpectedConditions.urlContains("/catalog"));
		product.clickProduct(10);
		product.getAddToCart().click();
		product.getCartIcon().click();
		Assert.assertEquals(cart.getShoppingCartHeading().isDisplayed(), true);	
		cart.getProceedToCheckoutButton().click();
		Assert.assertEquals(driver.getCurrentUrl().contains("/checkout"), true);
		Reporter.log("Navigated to Checkout Page Sucessfully",true);
		checkout.getBackToCartLink().click();
		cart.getClearCart().click();
		Thread.sleep(2000);
		order.getLogoutButton().click();
		
			
	}
    
	

}
