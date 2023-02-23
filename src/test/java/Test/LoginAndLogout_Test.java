package Test;

import PageObjects.LoginAndLogout_PageObjects;
import common.BasePage;
import org.testng.annotations.*;
import utils.logs.Log;

import java.lang.reflect.Method;

import static utils.extentreports.ExtentTestManager.startTest;

public class LoginAndLogout_Test extends BasePage {
    LoginAndLogout_PageObjects loginAndLogout;

    @BeforeClass
    public void setup() {
        initialize();
        loginAndLogout = new LoginAndLogout_PageObjects();
    }

    @Test
    public void loginAndLogoutTest(Method method) throws InterruptedException {
        Log.info("******************************");
        startTest(method.getName(), "Testing Login and Logout");
        loginAndLogout.doctorLogin();
        loginAndLogout.doctorLogout("Cancel");
        loginAndLogout.doctorLogout("Confirm");
        loginAndLogout.patientLogin();
        loginAndLogout.patientLogout("Cancel");
        loginAndLogout.patientLogout("Confirm");
        Log.info("******************************");
    }

    @AfterClass
    public void tearDown(){
        closeDriver();
    }
}
