package common;

import org.apache.commons.exec.OS;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BasePage {

    public static WebDriver driver;
    public static Properties prop;
    public static WebDriverWait wait;

    public BasePage() {
        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "/src/test/resources/" + "config.properties");
            prop.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialize() {
        String browser = prop.getProperty("browser");
        System.out.println("Browser is: " + browser);
        if (browser.equals("chrome")) {
//            ChromeOptions option = new ChromeOptions();
//            option.addArguments("--disable-notifications");

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("demo_capability", true);

            System.setProperty("webdriver.chrome.whitelistedIps", "");
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "/src/test/resources/driver/" + "chromedriver.exe");
//            System.setProperty("webdriver.chrome.driver", "C:/Program Files/Google/Chrome/Application/chrome.exe");

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
//            options.merge(capabilities);  //this will merge the capabilities to the ChromeOptions
//            options.addArguments("--disable-web-security", "--user-data-dir=true", "--allow-running-insecure-content");
//            String userDataDir = "C:\\Users\\prasa\\AppData\\Local\\Google\\Chrome\\User Data\\";
//            options.addArguments("--disable-web-security", "--user-data-dir=C:/Users/prasa/AppData/Local/Google/Chrome/User Data/Profile 4", "--allow-running-insecure-content");
//            options.addArguments("--disable-web-security", "--allow-running-insecure-content");
//            options.merge(capabilities);  //this will merge the capabilities to the ChromeOptions

            options.addArguments("excludeSwitches", "enable-automation");
            options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko)");
            options.merge(capabilities);  //this will merge the capabilities to the ChromeOptions



            HashMap<String, Integer> conentSettings = new HashMap<String, Integer>();
            HashMap<String, Object> profile = new HashMap<String, Object>();
            HashMap<String, Object> prefs = new HashMap<String, Object>();

            conentSettings.put("geolocation", 1);
            conentSettings.put("notifications", 2);
//            conentSettings.put("geolocation", 1);
//            conentSettings.put("media_stream", 1);
            profile.put("managed_default_content_settings", conentSettings);
            prefs.put("profile", profile);

            options.setExperimentalOption("prefs", prefs);
//            driver = new ChromeDriver();
            driver = new ChromeDriver(options);

        }
//        else if (browser.equals("firefox")) {
////                System.setProperty("webdriver.gecko.driver","D:\\Prasad-SWQA\\SWQA_Wellbeing\\src\\test\\resources\\driver\\geckodriver.exe");
////                driver = new FirefoxDriver();
//        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
//        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//        driver.get(prop.getProperty("URL"));
        driver.get(prop.getProperty("WellbeingURL"));
//        driver.get("https://stackoverflow.com/");

    }

    public void loadUrl(String url) {
        driver.get(url);
//        log.info("Loading URL " + url);
    }

    public static boolean isAlertPresent(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        boolean foundAlert = false;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20) /*timeout in seconds*/);
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            foundAlert = true;
        } catch (TimeoutException eTO) {
            foundAlert = false;
        }
        return foundAlert;
    }

    public static void pageLoadWait(){
//        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
    }

    public static void pageLoadWaitJSExecutor(){
        Boolean readyStateComplete = false;
        while (!readyStateComplete)
        {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
//            executor.executeScript("window.scrollTo(0, document.body.offsetHeight)");
            readyStateComplete = ((String) executor.executeScript("return document.readyState")).equals("complete");
        }
    }

    public void openNewTab(){
//        driver.get("http://test.wellbeingapp.in/#");
        driver.switchTo().newWindow(WindowType.TAB);
    }

    public void openNewWindow(){
//        driver.get("http://test.wellbeingapp.in/#");
        driver.switchTo().newWindow(WindowType.WINDOW);
    }

    public Set<String> getWindows() {
        return driver.getWindowHandles();
    }

    public String getCurrentWindow() {
        return driver.getWindowHandle();
    }

    public void switchToWindow(String windowId) {
        driver.switchTo().window(windowId);
    }

    public void closeCurrentWindow(String windowId) {
        driver.switchTo().window(windowId);
        driver.close();
        for (String w : getWindows()) {
            if (!w.equals(windowId)) {
                switchToWindow(w);
//                log.info("Switched to Base Page");
                break;
            }
        }
    }

    protected void closeDriver(){
        if(driver!= null) {
            assert driver != null;
            driver.quit();
//            driver.close();
        }
    }
}


