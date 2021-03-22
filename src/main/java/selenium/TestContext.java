package selenium;

import ksenukaiNavigation.StartTest;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import static java.lang.System.setProperty;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestContext {
    private static Properties properties;
    private static RemoteWebDriver driver;
    private final Wait<WebDriver> wait;
    private final JavascriptExecutor jse;
    private final String os;
    private Actions actions;

    public TestContext() {
        String chromeWebDriver = "webdriver.chrome.driver";
        String propertyMac = "user.home";
        String chromeDriverMac = "chromedriver";
        os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            System.setProperty(chromeWebDriver, "C://chromedriver.exe");
            driver = new ChromeDriver();
        } else if (os.contains("mac")) {
            System.setProperty(chromeWebDriver, new File(System.getProperty(propertyMac), chromeDriverMac)
                    .getAbsolutePath());
            driver = new ChromeDriver();
        } else if (os.contains("linux")) {
            setProperty("webdriver.chrome.driver", "src/main/resources/webDrivers/chromedriver");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(BrowserType.CHROME);
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--no-sandbox");
            try {
                driver = new RemoteWebDriver(new URL(getProperty("selenium.grid.hub.url")), chromeOptions);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else throw new AssertionError("We have no webDriver for " + os + " OS");

        driver.manage().window().maximize();
        wait = new FluentWait<WebDriver>(driver)
                .withTimeout(ofSeconds(15));

        actions = new Actions(driver);
        jse = driver;
    }

    public static String getProperty(String propertyName) {
        try {
            properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/selenium.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(propertyName);
    }

    public StartTest startTest() {
        return new StartTest(getContext());
    }

    public void openPage(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "https://" + url;
        }
        driver.get(url);
    }

    public WebElement getWebElement(By locator) {
        assertFalse(getWebElements(locator).isEmpty(), "Element is not present on the page");
        return driver.findElement(locator);
    }

    public List<WebElement> getWebElements(By locator) {
        assertFalse(driver.findElements(locator).isEmpty(), "Elements are not present on the page");
        return driver.findElements(locator);
    }

    public void waitForElement(By locator) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public boolean isElementPresentOnPage(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public TestContext getContext() {
        return this;
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }
}