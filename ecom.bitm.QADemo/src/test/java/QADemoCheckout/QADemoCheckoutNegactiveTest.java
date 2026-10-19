package QADemoCheckout;

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



public class QADemoCheckoutNegactiveTest extends BaseClass {

    @Test(groups = {"Negative"},dataProvider = "negativeCheckoutData",dataProviderClass = DataProviderUtility.class)
    public void verifyCheckoutWithEmptyDetails(
            String username, String password, String firstName,
            String lastName,
            String shippingAddress,
            String cardNumber,
            String expiryDate,
            String cvv,
            String nameOnCard) {

        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(15));

        QADemoLoginPage login = new QADemoLoginPage(driver);
        QADemoPoductCatalog product = new QADemoPoductCatalog(driver);
        QADemoCartPage cart = new QADemoCartPage(driver);
        QADemoCheckOutPage checkout = new QADemoCheckOutPage(driver);
        QADemoOrderPage order = new QADemoOrderPage(driver);
        login.login(username, password);
        wait.until(ExpectedConditions.urlContains("/catalog"));
        Reporter.log("Login successful", true);
        product.getProduct().click();
        wait.until(ExpectedConditions.elementToBeClickable(product.getAddToCart())).click();
        Reporter.log("Product added to cart", true);
        wait.until(ExpectedConditions.elementToBeClickable(product.getCartIcon())).click();
        wait.until(ExpectedConditions.urlContains("/cart"));
        wait.until(ExpectedConditions.elementToBeClickable(cart.getProceedToCheckoutButton())).click();
        wait.until(ExpectedConditions.visibilityOf(checkout.getCheckoutHeading()));
        Reporter.log("Checkout page opened", true);
        
        checkout.shippingInfo(firstName,lastName,shippingAddress);
        checkout.paymentInfo(cardNumber,expiryDate,cvv,nameOnCard);

        Reporter.log("Checkout fields left empty", true);

        checkout.clickPlaceOrderButton();
        Reporter.log("Place Order button clicked", true);
        Assert.assertTrue(driver.getCurrentUrl().contains("/checkout"),"Checkout was submitted despite empty required fields");
//        checkout.getCartcc().click();
//        cart.getDeleteicon().click();
        order.getLogoutButton().click();
    }
}