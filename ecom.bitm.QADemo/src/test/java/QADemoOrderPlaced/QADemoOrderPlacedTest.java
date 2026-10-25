package QADemoOrderPlaced;

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



public class QADemoOrderPlacedTest extends BaseClass {

    @Test(
        groups = {"Functionality", "Integration","System"},
        dataProvider = "checkoutdata",
        dataProviderClass = DataProviderUtility.class
    )
    public void verifyMyOrders(
            String username,
            String password,
            String firstName,
            String lastName,
            String shippingAddress,
            String cardNumber,
            String expiryDate,
            String cvv,
            String nameOnCard) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        QADemoLoginPage login = new QADemoLoginPage(driver);
        QADemoPoductCatalog product = new QADemoPoductCatalog(driver);
        QADemoCartPage cart = new QADemoCartPage(driver);
        QADemoCheckOutPage checkout = new QADemoCheckOutPage(driver);
        QADemoOrderPage order = new QADemoOrderPage(driver);

        login.login(username, password);
        wait.until(ExpectedConditions.urlContains("/catalog"));
        Reporter.log("Login successful", true);
        product.clickProduct(1);
        product.getAddToCart().click();
        Reporter.log("Product added to cart", true);
        Assert.assertEquals(driver.getCurrentUrl().contains("products/"),true);
        product.getCartIcon().click();
        wait.until(ExpectedConditions.urlContains("/cart"));
        cart.getProceedToCheckoutButton().click();
        checkout.getCheckoutHeading();
        checkout.shippingInfo(firstName, lastName, shippingAddress);
        checkout.paymentInfo(cardNumber, expiryDate, cvv, nameOnCard);
        checkout.clickPlaceOrderButton();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(order.getOrderConfirmedMessage())).isDisplayed(),"Order confirmation was not displayed");
        Reporter.log("Order confirmed successfully", true);
        order.getProfilebutton().click();
        Reporter.log("Profile/account button clicked", true);
        order.getMyorders().click();
        wait.until(ExpectedConditions.urlContains("/orders"));
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(order.getMyorderstext())).isDisplayed(),"My Orders heading is not displayed");
        Reporter.log("My Orders page displayed successfully", true);
        
        order.getLogoutButton().click();

    }
    @Test(
            groups = {"Functionality", "Integration"},
            dataProvider = "checkoutdata",
            dataProviderClass = DataProviderUtility.class
        )
        public void verifyContinueShopping(
                String username,
                String password,
                String firstName,
                String lastName,
                String shippingAddress,
                String cardNumber,
                String expiryDate,
                String cvv,
                String nameOnCard) throws InterruptedException {

            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(15));

            QADemoLoginPage login = new QADemoLoginPage(driver);
            QADemoPoductCatalog product = new QADemoPoductCatalog(driver);
            QADemoCartPage cart = new QADemoCartPage(driver);
            QADemoCheckOutPage checkout = new QADemoCheckOutPage(driver);
            QADemoOrderPage order = new QADemoOrderPage(driver);

            login.login(username, password);
            wait.until(ExpectedConditions.urlContains("/catalog"));
            product.clickProduct(1);
            product.getAddToCart().click();
            product.getCartIcon().click();
            wait.until(ExpectedConditions.urlContains("/cart"));
            cart.getProceedToCheckoutButton().click();
            checkout.getCheckoutHeading();
            checkout.shippingInfo(firstName, lastName, shippingAddress);
            checkout.paymentInfo(cardNumber, expiryDate, cvv, nameOnCard);
            checkout.clickPlaceOrderButton();
            order.getOrderConfirmedMessage();

            Reporter.log("Order confirmed successfully", true);
            Thread.sleep(2000);
            order.clickContinueShopping();
            
            wait.until(ExpectedConditions.urlContains("/catalog"));
            Assert.assertTrue(driver.getCurrentUrl().contains("/catalog"),"Continue Shopping did not navigate to the catalog");
            Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(product.getProductCatalogHeading())).isDisplayed(),"Product Catalog heading is not displayed");
            Reporter.log("Continue Shopping opened Product Catalog", true);
            order.getLogoutButton().click();
        }
    }