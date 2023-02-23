package PageObjects;

import common.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.logs.Log;

import static common.Utility.*;

public class LoginAndLogout_PageObjects extends BasePage {

    @FindBy(xpath = "//ion-label[text()='Login']")
    public WebElement loginBTN;

    @FindBy(xpath = "//input[@placeholder='your@email.address']")
    public WebElement emailText;

    @FindBy(xpath = "//ion-label[contains(text(), 'Should be a valid email address!')]")
    public WebElement emailInvalidErrorMsg;

    @FindBy(xpath = "//ion-label[contains(text(), 'Should be at least 8 characters long!')]")
    public WebElement passwordInvalidErrorMsg;

    @FindBy(xpath = "//ion-icon[@name= 'eye']")
    public WebElement loginPasswordEyeIcon;

    @FindBy(xpath = "//ion-icon[@name= 'eye-off']")
    public WebElement loginPasswordEyeIcon_Off;

    @FindBy(xpath = "//ion-icon[@name='eye']")
    public WebElement loginPasswordEyeIcon_On;

    @FindBy(xpath = "//input[@placeholder='Password']")
    public WebElement passwordText;

    @FindBy(xpath = "//ion-button[text()=' Login ']")
    public WebElement submitBTN;

    @FindBy(xpath = "//h6[contains(text(),'Welcome Dr.')]")
    public WebElement drName;

    @FindBy(xpath = "//*[@id= 'main']/app-patient/ion-row/ion-col[1]/app-side-menu/div/div/div[2]/ion-label/h1")
    public WebElement ptName;

    @FindBy(xpath = "//*[text()='Logout']")
    public WebElement logoutBTN;

    @FindBy(xpath = "//*[@id= 'main']/app-patient/ion-row/ion-col[1]/app-side-menu/div/ion-list/div/ion-item[2]/ion-label")
    public WebElement ptLogoutBTN;

    @FindBy(xpath = "//h2[contains(text(), 'Are you sure you want to log out?')]")
    public WebElement sureLogoutPopup_Heading;

    @FindBy(xpath = "//button[contains(@class, 'alert-button-role-confirm')]")
    public WebElement sureLogoutPopup_ConfirmBTN;

    @FindBy(xpath = "//button[contains(@class, 'alert-button-role-cancel')]")
    public WebElement sureLogoutPopup_CancelBTN;



    public LoginAndLogout_PageObjects(){
        PageFactory.initElements(driver,this);
    }

    public void doctorLogin() throws InterruptedException {
        try{
            pageLoadWait();
            try{
                if(!loginBTN.isDisplayed()){
                    refreshWebPage();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            waitAndClickElement(loginBTN);
            Log.info("Clicked on Login");

            verifyInvalidLoginCredentials();

            waitForElementVisible(emailText);
            emailText.sendKeys(prop.getProperty("drEmail"));
            Log.info("Entered Valid Email for Doctor");
            waitForElementVisible(passwordText);
            passwordText.sendKeys(prop.getProperty("drPassword"));
            Log.info("Entered Valid Password for Doctor");

            clickOnLoginPasswordEyeIcon_NewVersion();
//            clickOnLoginPasswordEyeIcon_OldVersion();

            waitAndClickElement(submitBTN);
            Log.info("Clicked on Login for Doctor");
            waitForElementVisible(drName);
            Log.info("Logged in Doctor is: " + drName.getText());
            pageLoadWait();
        }catch (Exception e){e.printStackTrace();}
    }

    public void doctorLogout(String logOutOption_Confirm_Cancel) {
        try{
            pageLoadWait();
            waitForElementVisible(logoutBTN);
            moveToThenSlowClickElement(logoutBTN, 500);
            Log.info("Clicked on Logout for Doctor");
            waitForElementVisible(sureLogoutPopup_Heading);
            String sureLogoutPopupTxt = "Are you sure you want to log out?";
            Assert.assertEquals(sureLogoutPopup_Heading.getText(), sureLogoutPopupTxt);
            Log.info("Verified the text in popup alert as: " + sureLogoutPopupTxt);
            switch (logOutOption_Confirm_Cancel){
                case "Confirm":
                    try{
                        waitForElementVisible(sureLogoutPopup_ConfirmBTN);
                        if(sureLogoutPopup_ConfirmBTN.isDisplayed()){
                            moveToThenSlowClickElement(sureLogoutPopup_ConfirmBTN, 500);
                            Log.info("Clicked on sureLogoutPopup_ConfirmBTN");
                            waitForElementVisible(loginBTN);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case "Cancel":
                    try{
                        waitForElementVisible(sureLogoutPopup_CancelBTN);
                        if(sureLogoutPopup_CancelBTN.isDisplayed()){
                            moveToThenSlowClickElement(sureLogoutPopup_CancelBTN, 500);
                            Log.info("Clicked on sureLogoutPopup_CancelBTN");
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
            }
        }catch (Exception e){e.printStackTrace();}
    }

    public void patientLogin() throws InterruptedException {
        try {
            pageLoadWait();
            try{
                if(!loginBTN.isDisplayed()){
                    refreshWebPage();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            waitAndClickElement(loginBTN);
            Log.info("Clicked on Login");

            verifyInvalidLoginCredentials();

            waitForElementVisible(emailText);
            emailText.sendKeys(prop.getProperty("ptEmail"));
            Log.info("Entered Valid Email for Patient");
            waitForElementVisible(passwordText);
            passwordText.sendKeys(prop.getProperty("ptPassword"));
            Log.info("Entered Valid Password for Patient");

            clickOnLoginPasswordEyeIcon_NewVersion();
//            clickOnLoginPasswordEyeIcon_OldVersion();

            waitAndClickElement(submitBTN);
            Log.info("Clicked on Login for Patient");
            waitForElementVisible(ptName);
            Log.info("Logged in Patient is: " + ptName.getText());
        }catch (Exception e){
            e.printStackTrace();}
    }

    public void patientLogout(String logOutOption_Confirm_Cancel) throws InterruptedException {
        try {
            pageLoadWait();
            waitForElementVisible(ptLogoutBTN);
            moveToThenSlowClickElement(ptLogoutBTN, 500);
            Log.info("Clicked on Logout for Patient");
            waitForElementVisible(sureLogoutPopup_Heading);
            String sureLogoutPopupTxt = "Are you sure you want to log out?";
            Assert.assertEquals(sureLogoutPopup_Heading.getText(), sureLogoutPopupTxt);
            Log.info("Verified the text in popup alert as: " + sureLogoutPopupTxt);
            switch (logOutOption_Confirm_Cancel){
                case "Confirm":
                    try{
                        waitForElementVisible(sureLogoutPopup_ConfirmBTN);
                        if(sureLogoutPopup_ConfirmBTN.isDisplayed()){
                            moveToThenSlowClickElement(sureLogoutPopup_ConfirmBTN, 500);
                            Log.info("Clicked on sureLogoutPopup_ConfirmBTN");
                            waitForElementVisible(loginBTN);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case "Cancel":
                    try{
                        waitForElementVisible(sureLogoutPopup_CancelBTN);
                        if(sureLogoutPopup_CancelBTN.isDisplayed()){
                            moveToThenSlowClickElement(sureLogoutPopup_CancelBTN, 500);
                            Log.info("Clicked on sureLogoutPopup_CancelBTN");
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void verifyInvalidLoginCredentials() {
        try {
            waitAndClickElement(emailText);
            emailText.sendKeys("InvalidEmail@gmail");
            Log.info("Entered InvalidEmail@gmail");
            pressTab(emailText);
            waitForElementVisible(emailInvalidErrorMsg);
            String emailInvalidErrorMessage = emailInvalidErrorMsg.getText();
            Assert.assertEquals(emailInvalidErrorMsg.getText(), emailInvalidErrorMessage);
            Log.info("Verified emailInvalidErrorMessage as: " + emailInvalidErrorMessage);
            waitAndClickElement(passwordText);
            passwordText.sendKeys("WrngP@s");
            Log.info("Entered WrngP@s");

            clickOnLoginPasswordEyeIcon_NewVersion();
//            clickOnLoginPasswordEyeIcon_OldVersion();

            waitAndClickElement(emailText);
            waitForElementVisible(passwordInvalidErrorMsg);
            String passwordInvalidErrorMessage = passwordInvalidErrorMsg.getText();
            Assert.assertEquals(passwordInvalidErrorMsg.getText(), passwordInvalidErrorMessage);
            Log.info("Verified passwordInvalidErrorMessage as: " + passwordInvalidErrorMessage);
            emailText.clear();
            passwordText.clear();
        }catch (Exception e){e.printStackTrace();}
    }

    public void clickOnLoginPasswordEyeIcon_NewVersion() {
        try {
            if(loginPasswordEyeIcon_Off.isDisplayed() || loginPasswordEyeIcon_On.isDisplayed()){
                if(loginPasswordEyeIcon_Off.isDisplayed()){
                    waitAndClickElement(loginPasswordEyeIcon_Off);
                    Log.info("Clicked on loginPasswordEyeIcon_Off");
                }else {
                    waitAndClickElement(loginPasswordEyeIcon_On);
                    Log.info("Clicked on loginPasswordEyeIcon_On");
                }
            }
        }catch (Exception e){e.printStackTrace();}
    }

    public void clickOnLoginPasswordEyeIcon_OldVersion() {
        try {
            for(int i=0; i<2; i++){
                waitForElementVisible(loginPasswordEyeIcon);
                moveToThenSlowClickElement(loginPasswordEyeIcon, 500);
                Log.info("Clicked on loginPasswordEyeIcon");
            }
        }catch (Exception e){e.printStackTrace();}
    }
}
