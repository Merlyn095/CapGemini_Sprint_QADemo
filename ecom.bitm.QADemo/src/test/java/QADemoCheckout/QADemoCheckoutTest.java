package QADemoCheckout;

import org.testng.annotations.Test;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
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



public class QADemoCheckoutTest extends BaseClass {

    @Test(
        groups = {"Smoke","Functionality"},
        dataProvider = "systemData",
        dataProviderClass = DataProviderUtility.class
    )
    public void verifyCheckoutFields(
            String Username, String Password) throws InterruptedException {

        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(15));
        QADemoLoginPage login = new QADemoLoginPage(driver);
        QADemoPoductCatalog product = new QADemoPoductCatalog(driver);
        QADemoOrderPage order = new QADemoOrderPage(driver);
        QADemoCartPage cart = new QADemoCartPage(driver);
        QADemoCheckOutPage checkout = new QADemoCheckOutPage(driver);

        login.login(Username, Password);
        wait.until(ExpectedConditions.urlContains("/catalog"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/catalog"),"Login failed");
        Reporter.log("Login credentials entered", true);
        
        product.clickProduct(13);
        
        product.getAddToCart().click();
        product.getCartIcon().click();
        cart.getProceedToCheckoutButton().click();
        checkout.getCheckoutHeading();
        Reporter.log("Checkout page opened", true);
        Assert.assertTrue(checkout.getBackToCartLink().isDisplayed(), "Back to Cart link is not displayed");
		Assert.assertTrue(checkout.getFirstNameField().isDisplayed(), "First name field is not displayed");
		Assert.assertTrue(checkout.getLastNameField().isDisplayed(), "Last name field is not displayed");
		Assert.assertTrue(checkout.getShippingAddressField().isDisplayed(), "Address field is not displayed");
		Assert.assertTrue(checkout.getCardNumberField().isDisplayed(), "Card number field is not displayed");
		Assert.assertTrue(checkout.getExpiryDateField().isDisplayed(), "Expiry field is not displayed");
		Assert.assertTrue(checkout.getCvvField().isDisplayed(), "CVV field is not displayed");
		Assert.assertTrue(checkout.getNameOnCardField().isDisplayed(), "Name on card field is not displayed");
		
		Assert.assertTrue(checkout.getPlaceOrderButton().isDisplayed(), "Place Order button is not displayed");
		Reporter.log("Smoke passed: all Checkout elements are displayed", true);
		
		checkout.getBackToCartLink().click();
		
		cart.getClearCart().click();
		Assert.assertEquals(cart.getEmptyCartMessage().isDisplayed(),true);
		wait.until(ExpectedConditions.visibilityOf(cart.getEmptyCartMessage()));
        Reporter.log("Cart cleared successfully", true);
	    //cart.getClearCart().click();
        
        order.getLogoutButton().click();
        
        
        
    }
    
    @Test( groups = {"System","Integration"},
            dataProvider = "checkoutdata",
            dataProviderClass = DataProviderUtility.class
        )
        public void verifyCompleteOrderPlacement(
        		String Username,
        		String Password,
                String firstName,
                String lastName,
                String shippingAddress,
                String cardNumber,
                String expiryDate,
                String cvv,
                String nameOnCard) throws InterruptedException {

            WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(15));
            QADemoLoginPage login = new QADemoLoginPage(driver);
            QADemoPoductCatalog product = new QADemoPoductCatalog(driver);
            QADemoCartPage cart = new QADemoCartPage(driver);
            QADemoCheckOutPage checkout = new QADemoCheckOutPage(driver);
            QADemoOrderPage order = new QADemoOrderPage(driver);
            JavascriptExecutor js = (JavascriptExecutor) driver;

            login.login(Username,Password);
            wait.until(ExpectedConditions.urlContains("/catalog"));
            Reporter.log("Login successful", true);
            product.clickProduct(1);
            
            product.getAddToCart().click();
            Reporter.log("Product added to cart", true);

            product.getCartIcon().click();
            wait.until(ExpectedConditions.urlContains("/cart"));

            cart.getProceedToCheckoutButton().click();
            checkout.getCheckoutHeading();

            Reporter.log("Checkout page opened", true);

            checkout.shippingInfo(firstName,lastName,shippingAddress);
            checkout.paymentInfo(cardNumber,expiryDate,cvv,nameOnCard);

            checkout.clickPlaceOrderButton();
            Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(order.getOrderConfirmedMessage())).isDisplayed(),"Order confirmation message is not displayed");
            Reporter.log("Order confirmed successfully", true);
            order.getLogoutButton().click();
            
        }

    @Test(
            groups = {"System","Integration"},
            dataProvider = "checkoutdata",
            dataProviderClass = DataProviderUtility.class
        )
        public void verifybackwardnavigation(
        		String Username,
        		String Password,
                String firstName,
                String lastName,
                String shippingAddress,
                String cardNumber,
                String expiryDate,
                String cvv,
                String nameOnCard) throws InterruptedException {

            WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(15));
            QADemoLoginPage login = new QADemoLoginPage(driver);
            QADemoPoductCatalog product = new QADemoPoductCatalog(driver);
            QADemoCartPage cart = new QADemoCartPage(driver);
            QADemoCheckOutPage checkout = new QADemoCheckOutPage(driver);
            QADemoOrderPage order = new QADemoOrderPage(driver);

            login.login(Username,Password);
            wait.until(ExpectedConditions.urlContains("/catalog"));
            Reporter.log("Login successful", true);
            
            product.clickProduct(10);
            product.getAddToCart().click();
            Reporter.log("Product added to cart", true);

            product.getCartIcon().click();
            wait.until(ExpectedConditions.urlContains("/cart"));
            
            cart.getContinueshopping().click();
            product.clickProduct(3);
            product.getAddToCart().click();
            product.getCartIcon().click();
            cart.getProceedToCheckoutButton().click();

            checkout.shippingInfo(firstName,lastName,shippingAddress);
            checkout.paymentInfo(cardNumber,expiryDate,cvv,nameOnCard);
         

            checkout.clickPlaceOrderButton();
            Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(order.getOrderConfirmedMessage())).isDisplayed(),"Order confirmation message is not displayed");
            Reporter.log("Order confirmed successfully", true);
            order.getLogoutButton().click();

        }

}