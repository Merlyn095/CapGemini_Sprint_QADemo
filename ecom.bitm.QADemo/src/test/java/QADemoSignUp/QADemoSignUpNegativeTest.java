package QADemoSignUp;

import org.testng.annotations.Test;


import org.testng.Assert;
import org.testng.Reporter;


import genericUtility.BaseClass;
import genericUtility.DataProviderUtility;
import genericUtility.JavaUtility;
import qaDemoPom.QADemoSignUpPage;

public class QADemoSignUpNegativeTest extends BaseClass {
	@Test(groups = {"Negative"}, dataProvider ="data1",dataProviderClass = DataProviderUtility.class
	    )
	    public void verifySignUp(String email,String phoneno,
	            String password,
	            String confirmPassword) throws InterruptedException {

	        QADemoSignUpPage signup = new QADemoSignUpPage(driver);
	        JavaUtility ju=new JavaUtility();

	        

	        signup.registerUser(email,phoneno,password, confirmPassword);
	        Thread.sleep(4000);
	        Assert.assertEquals(signup.getErrorEmail().isDisplayed(),true);
	        Assert.assertEquals(signup.getErrorPassword().isDisplayed(),true);

	        Reporter.log("Negative test passes: Signup failed with invalid credentials ", true);
	         
	    }
	

}
