package ksenukaiPages;

import ksenukaiPagePromises.Promise;
import org.openqa.selenium.By;
import pageComponents.PageComponents;
import selenium.TestContext;

import static java.lang.Double.parseDouble;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartPage extends PageComponents {

    private static final By TOTAL_PRICE = By.id("cart-full-total-price");
    private static final By ITEM_IN_CART = By.xpath(".//a[@class = 'detailed-cart-item__name-link']");
    private static final By SEARCH_INPUT_FIELD = By.id("q");
    private static final By FOUND_ITEM = By.xpath(".//div[@class = 'sn-suggest-doc-title sn-is-oneLine']");
    private static final By PAGE_TITLE = By.xpath(".//h1");


    public CartPage(TestContext context) {
        super(context);
        assertEquals("Pirkumu grozs", getText(PAGE_TITLE));
    }

    public double totalPrice() {
        final String totalPrice = getText(TOTAL_PRICE).replace(",", ".");
        final String totalPriceWithoutEuroSign = totalPrice.substring(0, totalPrice.length() - 2);
        return parseDouble(totalPriceWithoutEuroSign);
    }

    public String itemInCart() {
        return getText(ITEM_IN_CART);
    }

    public CartPage fillSearchField(String item) {
        sendKeys(SEARCH_INPUT_FIELD, item);
        return this;
    }

    public Promise clickOnFoundItem() {
        click(FOUND_ITEM);
        return new Promise(getContext());
    }
}
