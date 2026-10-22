package qaDemoPom;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QADemoLoginPage {
	WebDriver driver;
	WebDriverWait wait;

    public QADemoLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    // Sign In link
    @FindBy(xpath = "//a[@href='/login' and  @data-testid='navbar-signin-link']")
    private WebElement signInLink;

    // Form fields - verify locators against the actual DOM
    @FindBy(name="username")
    private WebElement emailField;

    @FindBy(name="password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@data-testid='login-submit-button']")
    private WebElement signInButton;

	public WebElement getSignInLink() {
		return signInLink;
	}

	public WebElement getEmailField() {
		return emailField;
	}

	public WebElement getPasswordField() {
		return passwordField;
	}

	public WebElement getSignInButton() {
		return wait.until(ExpectedConditions.elementToBeClickable(signInButton));
	}
	
	 public void login(String email, String password) {
		 getSignInLink().click();
		 getEmailField().clear();
		 getEmailField().sendKeys(email);
		 getPasswordField().clear();
		 getPasswordField().sendKeys(password);
		 getSignInButton().submit();
		  
	    }


}
