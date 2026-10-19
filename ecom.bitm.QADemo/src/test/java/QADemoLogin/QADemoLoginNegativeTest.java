package QADemoLogin;
import org.testng.annotations.Test;

import org.testng.Assert;
import org.testng.Reporter;


import genericUtility.BaseClass;
import genericUtility.DataProviderUtility;
import qaDemoPom.QADemoLoginPage;



public class QADemoLoginNegativeTest extends BaseClass {

    @Test(
        groups = {"Negative"},
        dataProvider = "invaliddata",
        dataProviderClass = DataProviderUtility.class
    )
    public void verifyInvalidLogin(String username, String password) throws InterruptedException {

        Reporter.log("Starting Negative Login Test", true);

        QADemoLoginPage login = new QADemoLoginPage(driver);
        Reporter.log("Login page initialized", true);
        login.login(username, password);
        Reporter.log("Invalid credentials entered", true);
        Thread.sleep(3000);
        Assert.assertFalse(driver.getCurrentUrl().contains("/catalog"),"Negative login failed: Invalid credentials allowed login");
        Reporter.log("Negative login test passed: Invalid credentials did not open the catalog",true);
    }
}