package pageComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.TestContext;

public class PageComponents {
    final TestContext context;

    public PageComponents(TestContext context) {
        this.context = context;
    }

    public TestContext getContext() {
        return context.getContext();
    }

    public void click(By locator) {
        waitForElementToBePresent(locator);
        getElement(locator).click();
    }

    public String getText(By locator) {
        waitForElementToBePresent(locator);
        return getElement(locator).getText();
    }

    public WebElement getElement(By locator) {
        return context.getWebElement(locator);
    }

    public boolean isElementPresentOnPage(By locator) {
        return context.isElementPresentOnPage(locator);
    }

    public void sendKeys(By locator, String keys) {
        waitForElementToBePresent(locator);
        getElement(locator).sendKeys(keys);
    }

    public void waitForElementToBePresent(By locator) {
        context.waitForElement(locator);
    }
}