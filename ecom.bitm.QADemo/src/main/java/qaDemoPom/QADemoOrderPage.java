package qaDemoPom;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QADemoOrderPage {

    WebDriver driver;
    WebDriverWait wait;

    public QADemoOrderPage(WebDriver driver) {
        this.driver = driver;

        wait = new WebDriverWait(
                driver, Duration.ofSeconds(15));

        PageFactory.initElements(driver, this);
    }

	@FindBy(xpath = "//h1[text()='Order Confirmed!']")
    private WebElement orderConfirmedMessage;

    @FindBy(xpath = "//button[contains(.,'Logout')] | //a[contains(.,'Logout')] | //*[@aria-label='Logout']")
    private WebElement logoutButton;
    
    @FindBy(xpath = "//span[text()='standard_user']")
    private WebElement profilebutton;
    
    @FindBy(xpath="//h1[text()='My Orders']")
    private WebElement myorders;
    
    @FindBy(xpath="//button[text()='Continue Shopping']")
    private WebElement orderspagecontinueshopping;
    
    public WebElement getOrderspagecontinueshopping() {
		return orderspagecontinueshopping;
	}

	public WebElement getMyorders() {
		return wait.until(ExpectedConditions.elementToBeClickable(myorders));
	}

	public WebElement getProfilebutton() {
		return profilebutton;
	}

	@FindBy(xpath="//h1[@class='text-3xl font-bold text-slate-900']")
    private WebElement myorderspage;
    
    

	public WebElement getMyorderstext() {
		return wait.until(ExpectedConditions.visibilityOf(myorderspage));
	}
	
	@FindBy(xpath = "//a[contains(@href,'/orders/')]")
	private WebElement orderDetailsLink;

	public WebElement getOrderDetailsLink() {
	    return orderDetailsLink;
	}

	public WebElement getOrderConfirmedMessage() {
		return orderConfirmedMessage;
	}

	public WebElement getLogoutButton() {
		return logoutButton;
	}


    

	
	public void clickContinueShopping() {

	    

	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    // Scroll to the bottom of the document
	    js.executeScript(
	        "window.scrollTo(0, document.documentElement.scrollHeight);"
	    );

	    // Wait until the button enters the viewport
	    wait.until(d -> {
	        Number top = (Number) ((JavascriptExecutor) d)
	            .executeScript(
	                "return arguments[0].getBoundingClientRect().top;",
	                orderspagecontinueshopping
	            );

	        return top.doubleValue() <
	            d.manage().window().getSize().getHeight();
	    });

	    // Position the button in the center of the viewport
	    js.executeScript(
	        "arguments[0].scrollIntoView({block:'center'});",
	        orderspagecontinueshopping
	    );

	    System.out.println("Continue Shopping button position: " +
	        js.executeScript(
	            "return arguments[0].getBoundingClientRect().top;",
	            orderspagecontinueshopping
	        ));

	    wait.until(
	        ExpectedConditions.elementToBeClickable(
	            orderspagecontinueshopping
	        )
	    ).click();
	}
	
}
	


    
