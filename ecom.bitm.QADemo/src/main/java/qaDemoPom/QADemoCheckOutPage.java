package qaDemoPom;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QADemoCheckOutPage {
	WebDriver driver;
    WebDriverWait wait;

    
    public QADemoCheckOutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Checkout heading
    @FindBy(xpath = "//h1[@data-testid='checkout-heading']")
    private WebElement checkoutHeading;

    // Back to Cart
    @FindBy(xpath = "//a[@data-testid='back-to-cart-link']")
    private WebElement backToCartLink;

    @FindBy(xpath = "//input[@data-testid='checkout-first-name']")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@data-testid='checkout-last-name']")
    private WebElement lastNameField;

    // Verify the address field's data-testid in DevTools
    @FindBy(name = "address")
    private WebElement shippingAddressField;

    @FindBy(xpath = "//input[@data-testid='checkout-card-number']")
    private WebElement cardNumberField;

    
    @FindBy(name="expiry")
    private WebElement expiryDateField;

    @FindBy(name="cvv")
    private WebElement cvvField;

    @FindBy(name="nameOnCard")
    private WebElement nameOnCardField;

    // Place Order button - confirmed from your screenshot
    @FindBy(xpath = "//button[@data-testid='place-order-button']")
    private WebElement placeOrderButton;
    
    @FindBy(xpath = "//*[contains(normalize-space(.), 'Cart is empty')]")
    private WebElement emptyCartMessage;
    
    @FindBy(xpath="//a[@class=\"text-sm hover:text-brand-400 transition-colors\" and text()='Cart']")
    private WebElement cartcc;

    public WebElement getCartcc() {
		return  wait.until(ExpectedConditions.elementToBeClickable(cartcc));
	}


	public WebElement getEmptyCartMessage() {
        return emptyCartMessage;
    }


	public WebElement getCheckoutHeading() {
		return checkoutHeading;
	}

	public WebElement getBackToCartLink() {
		return wait.until(ExpectedConditions.elementToBeClickable(backToCartLink));
	}

	public WebElement getFirstNameField() {
		return firstNameField;
	}

	public WebElement getLastNameField() {
		return lastNameField;
	}

	public WebElement getShippingAddressField() {
		return shippingAddressField;
	}

	public WebElement getCardNumberField() {
		return cardNumberField;
	}

	public WebElement getExpiryDateField() {
		return expiryDateField;
	}

	public WebElement getCvvField() {
		return cvvField;
	}

	public WebElement getNameOnCardField() {
		return nameOnCardField;
	}

	
    
    
	public WebElement getPlaceOrderButton() {
		return wait.until(
		        ExpectedConditions.visibilityOf(placeOrderButton));
		
	}
	
	public void clickPlaceOrderButton() {

	    WebElement button = wait.until(
	        ExpectedConditions.visibilityOf(placeOrderButton)
	    );

	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    // Scroll the document close to the bottom
	    js.executeScript(
	        "window.scrollTo(0, document.documentElement.scrollHeight);"
	    );

	    // Wait briefly for the page layout to settle
	    wait.until(d -> {
	        Number top = (Number) ((JavascriptExecutor) d)
	            .executeScript(
	                "return arguments[0].getBoundingClientRect().top;",
	                button
	            );

	        return top.doubleValue() < d.manage()
	            .window().getSize().getHeight();
	    });

	    // Scroll the button to the center
	    js.executeScript(
	        "arguments[0].scrollIntoView({block:'center'});",
	        button
	    );

	    // Verify the actual position
	    System.out.println("Button position after scrolling: " +
	        js.executeScript(
	            "return arguments[0].getBoundingClientRect().top;",
	            button
	        ));

	    // Use Selenium's normal click
	    wait.until(ExpectedConditions.elementToBeClickable(button));
	    button.click();
	}
	






	public void shippingInfo(String fn,String ln,String add) {
		firstNameField.clear();
		firstNameField.sendKeys(fn);
		lastNameField.clear();
		lastNameField.sendKeys(ln);
		shippingAddressField.clear();
		shippingAddressField.sendKeys(add);
		
  }
	
	public void paymentInfo(String cn,String expiry,String cvv,String name) {
		cardNumberField.clear();
		cardNumberField.sendKeys(cn);
		expiryDateField.clear();
		expiryDateField.sendKeys(expiry);
		cvvField.clear();
		cvvField.sendKeys(cvv);
		nameOnCardField.clear();
		nameOnCardField.sendKeys(name);
	}

}
