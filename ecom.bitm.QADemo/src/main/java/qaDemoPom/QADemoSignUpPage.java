package qaDemoPom;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QADemoSignUpPage {
	WebDriver driver;
	WebDriverWait wait;

    public QADemoSignUpPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Sign Up link
    @FindBy(xpath = "//a[contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'sign up')]")
    private WebElement signUpLink;

    

	// Form fields - verify locators against the actual DOM
    

    @FindBy(xpath="//input[@data-testid='email-input']")
    private WebElement emailField;
    
    @FindBy(xpath="//input[@data-testid='phone-input']")
    private WebElement phoneField;

    @FindBy(name="password")
    private WebElement passwordField;

    @FindBy(name="confirmPassword")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//button[@data-testid='signup-submit-button']")
    private WebElement signUpButton;
    
    @FindBy(xpath="//p[text()='Please enter a valid email address']")
    private WebElement errorEmail;
    
    @FindBy(xpath="//p[text()='Password must contain at least one uppercase letter']")
    private WebElement errorPassword;
    
    
    public WebElement getErrorEmail() {
		return errorEmail;
	}

	public WebElement getErrorPassword() {
		return errorPassword;
	}

	public WebElement getSignUpLink() {
		return wait.until(ExpectedConditions.elementToBeClickable(signUpLink));
	}

	public WebElement getPhoneField() {
		return  wait.until(ExpectedConditions.visibilityOf(phoneField));
	}

	public WebElement getEmailField() {
		return wait.until(ExpectedConditions.visibilityOf(emailField));
	}

	public WebElement getPasswordField() {
		return wait.until(ExpectedConditions.visibilityOf(passwordField));
	}

	public WebElement getConfirmPasswordField() {
		return confirmPasswordField;
	}

	public WebElement getSignUpButton() {
		return signUpButton;
	}
	
	public void registerUser(String email, String phoneno,  String password, String confirmPassword) {
		getSignUpLink().click();
		getEmailField().clear();
		getEmailField().sendKeys(email);
		getPhoneField().clear();
		getPhoneField().sendKeys(phoneno);
		getPasswordField().clear();
		getPasswordField().sendKeys(password);
		getConfirmPasswordField().clear();
		getConfirmPasswordField().sendKeys(confirmPassword);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Scroll the button into the visible area
		js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);"
		);

		js.executeScript( "arguments[0].scrollIntoView({block:'center'});", signUpButton);

		// Wait until the button is within the viewport
		wait.until(d -> (Boolean) js.executeScript(
		    "var r = arguments[0].getBoundingClientRect();" +
		    "return r.top >= 0 && r.bottom <= window.innerHeight;",
		    signUpButton
		));

		wait.until(ExpectedConditions.elementToBeClickable(signUpButton));
		signUpButton.click();
 
}
    
    

}
