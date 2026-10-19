package QADemoSignUp;

import org.testng.annotations.Test;


import org.testng.Assert;
import org.testng.Reporter;


import genericUtility.BaseClass;
import genericUtility.DataProviderUtility;
import genericUtility.JavaUtility;

import qaDemoPom.QADemoSignUpPage;

public class QADemoSignUpTest extends BaseClass {
	
	@Test(groups = "smoke")
	public void signUpPageSmokeTest() {
		Reporter.log("Smoke: verify Sign Up page elements", true);
		QADemoSignUpPage sp = new QADemoSignUpPage(driver);
 
		Assert.assertTrue(sp.getSignUpLink().isDisplayed(), "Sign Up link is not displayed");
		sp.getSignUpLink().click();
		Reporter.log("Clicked Sign Up link", true);
        Assert.assertEquals(sp.getEmailField().isDisplayed(), true);
        Assert.assertEquals(sp.getPhoneField().isDisplayed(), true);
        Assert.assertEquals(sp.getPasswordField().isDisplayed(), true);
        Assert.assertEquals(sp.getConfirmPasswordField().isDisplayed(), true);
        Assert.assertEquals(sp.getSignUpButton().isDisplayed(),true);
	
		
		Reporter.log("Smoke passed: all Sign Up elements are displayed", true);
	}
	
	@Test(
	        groups = {"Smoke", "Functionality"},
	        dataProvider ="data",
	        dataProviderClass = DataProviderUtility.class
	    )
	    public void verifySignUp(String email,String phoneno,
	            String password,
	            String confirmPassword) throws InterruptedException {

	        QADemoSignUpPage signup = new QADemoSignUpPage(driver);
	        JavaUtility ju=new JavaUtility();

	        String generatedEmail =ju.generateRandomNumber(email);
	        String generatedPhone=ju.generateNumber(phoneno);

	        signup.registerUser( generatedEmail, generatedPhone,password, confirmPassword);
	        Thread.sleep(4000);
	        Assert.assertTrue(driver.getCurrentUrl().contains("catalog"));

	        Reporter.log("Sign-up form submitted with positive data", true);
	         
	    }
	
	
		
		
	
	
	 
	
   
}