package qaDemoPom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QADemoLoginPage {
	WebDriver driver;

    public QADemoLoginPage(WebDriver driver) {
        this.driver = driver;
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
		return signInButton;
	}
	
	 public void login(String email, String password) {
		 signInLink.click();
		 emailField.clear();
		 emailField.sendKeys(email);
		 passwordField.clear();
		 passwordField.sendKeys(password);
		 signInButton.click();
		   
	    }


}
