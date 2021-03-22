package ksenukaiPages;

import ksenukaiPagePromises.Promise;
import org.openqa.selenium.By;
import pageComponents.PageComponents;
import selenium.TestContext;

import static java.lang.Double.parseDouble;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UniqueItemPage extends PageComponents {

    private static final By ITEMS_PRICE = By.xpath(".//span[@class='price']/span[1]");
    private static final By ADD_TO_CART_BUTTON = By.id("add_to_cart_btn");
    private static final By PRODUCT_ID = By.xpath(".//p[@class = 'product-id']");

    public UniqueItemPage(TestContext context) {
        super(context);
        assertTrue(isElementPresentOnPage(PRODUCT_ID), "There is no product ID on the page");
    }

    public double getItemsPrice() {
        return parseDouble(getText(ITEMS_PRICE).replace(",", "."));
    }

    public Promise pressAddToCartButton() {
        click(ADD_TO_CART_BUTTON);
        return new Promise(getContext());
    }
}
