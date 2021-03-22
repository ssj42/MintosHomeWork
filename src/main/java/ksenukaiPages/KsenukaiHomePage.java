package ksenukaiPages;

import ksenukaiPagePromises.Promise;
import org.openqa.selenium.By;
import pageComponents.PageComponents;
import selenium.TestContext;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class KsenukaiHomePage extends PageComponents {

    private static final By SEARCH_INPUT_FIELD = By.id("q");
    private static final By FOUND_ITEM = By.xpath(".//div[@class = 'sn-suggest-doc-title sn-is-oneLine']");
    private static final By PRODUCT_TYPES = By.xpath(".//ul[@class = 'submenu-lvl1__list']/li");

    public KsenukaiHomePage(TestContext context) {
        super(context);
        assertTrue(isElementPresentOnPage(PRODUCT_TYPES), "There are no product types on the page");
    }

    public KsenukaiHomePage fillSearchField(String item) {
        sendKeys(SEARCH_INPUT_FIELD, item);
        return this;
    }

    public Promise clickOnFoundItem() {
        click(FOUND_ITEM);
        return new Promise(getContext());
    }
}
