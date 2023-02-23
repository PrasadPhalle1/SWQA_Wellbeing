package common;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;

import static java.lang.Thread.sleep;

public class Utility extends BasePage{
//    public static WebDriverWait wait = new WebDriverWait(driver, 20);
    public static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    public static WebDriverWait waitForElementInvisibility = new WebDriverWait(driver, Duration.ofSeconds(10));
    public static Wait<WebDriver> fw= new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(5)).pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class,TimeoutException.class);

    public static void waitForElementVisible(WebElement webElem){
        try{
//            wait.until(ExpectedConditions.visibilityOf(webElem));
            fw.until(ExpectedConditions.visibilityOf(webElem));
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void clickElement(WebElement element){
        try{
//            wait.until(ExpectedConditions.elementToBeClickable(element));
//            element.click();
            wait.ignoring(StaleElementReferenceException.class, ElementClickInterceptedException.class).until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void waitForElementToBeClickable(WebElement webElem){
        try{
            fw.until(ExpectedConditions.elementToBeClickable(webElem));
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void waitAndClickElement(WebElement webElem){
        try{
            fw.until(ExpectedConditions.visibilityOf(webElem));
            fw.until(ExpectedConditions.elementToBeClickable(webElem));
            webElem.click();
        }catch (Exception e){
            System.out.println(e);
        }
    }


//    public static void scrollUsingJavaScriptExecutor(WebElement element){
//        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",element);
//    }

    public static void scrollUpTo(WebElement element){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",element);
    }

    public static void pressTab(WebElement element){
        element.sendKeys(Keys.TAB);
    }

    public static void pressEnterKay(WebElement element){
        element.sendKeys(Keys.ENTER);
    }

    public static void pressEscape(WebElement element){
        element.sendKeys(Keys.ESCAPE);
    }

    public static void refreshWebPage(){
        ((JavascriptExecutor)driver).executeScript("history.go(0)");
    }


    public static void pressEnter() throws AWTException {
        Robot enter = new Robot();
        enter.keyPress(KeyEvent.VK_ENTER);
        enter.keyRelease(KeyEvent.VK_ENTER);
    }

    public static void selectAllText() throws AWTException {
        Robot all = new Robot();
        all.keyPress(KeyEvent.VK_CONTROL);
        all.keyPress(KeyEvent.VK_A);
        all.keyRelease(KeyEvent.VK_CONTROL);
        all.keyRelease(KeyEvent.VK_A);
    }

    public static void copyText() throws AWTException {
        Robot copy = new Robot();
        copy.keyPress(KeyEvent.VK_CONTROL);
        copy.keyPress(KeyEvent.VK_C);
        copy.keyRelease(KeyEvent.VK_CONTROL);
        copy.keyRelease(KeyEvent.VK_C);
    }

    public static void pasteText() throws AWTException{
        Robot paste = new Robot();
        paste.keyPress(KeyEvent.VK_CONTROL);
        paste.keyPress(KeyEvent.VK_V);
        paste.keyRelease(KeyEvent.VK_CONTROL);
        paste.keyRelease(KeyEvent.VK_V);
    }

    public static void cutText() throws AWTException {
        Robot cut = new Robot();
        cut.keyPress(KeyEvent.VK_CONTROL);
        cut.keyPress(KeyEvent.VK_X);
        cut.keyRelease(KeyEvent.VK_CONTROL);
        cut.keyRelease(KeyEvent.VK_X);
    }

    public static void deleteText() throws AWTException{
        Robot delete = new Robot();
        delete.keyPress(KeyEvent.VK_DELETE);
        delete.keyRelease(KeyEvent.VK_DELETE);
    }

    public static void pressTAB() throws AWTException {
        Robot tab = new Robot();
        tab.keyPress(KeyEvent.VK_TAB);
        tab.keyRelease(KeyEvent.VK_TAB);
    }

    public static void pressEsc() throws AWTException {
        Robot escape = new Robot();
        escape.keyPress(KeyEvent.VK_ESCAPE);
        escape.keyRelease(KeyEvent.VK_ESCAPE);
    }

    public static void scrollUsingJavaScriptExecutor(WebElement element){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",element);
    }

    public static void waitForElementClickable(WebElement webElem){
        try{
            wait.until(ExpectedConditions.elementToBeClickable(webElem));
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void moveTOElement(WebElement webElem){
        try{
            Actions actions = new Actions(driver);
            actions.moveToElement(webElem, 0, -1 ).build().perform();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static Boolean moveToThenSlowClickElement(final WebElement toElement, final int millisecondsOfWaitTime) throws InterruptedException {
        final Actions clickOnElementAndHold = new Actions(driver);
        final Actions release = new Actions(driver);
        clickOnElementAndHold.moveToElement(toElement).clickAndHold(toElement).perform();

        sleep(millisecondsOfWaitTime);

        release.release(toElement).perform();

        return true;
    }

    public static void waitForInvisibilityOfElement(WebElement webElem){
        try{
            waitForElementInvisibility.until(ExpectedConditions.invisibilityOf(webElem));
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void scrollDownToTheBottomOfTheWebPage() throws AWTException {
        Actions act = new Actions(driver);
        act.sendKeys(Keys.PAGE_DOWN).build().perform();
//        System.out.println("Scroll down performed");
    }

    public static void scrollUpToTheTopOfTheWebPage(){
        Actions act = new Actions(driver);
        act.sendKeys(Keys.PAGE_UP).build().perform();
//        System.out.println("Scroll up performed");
    }

    public static boolean checkExist(WebElement el){
        try {
            el.isDisplayed();
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }
}
