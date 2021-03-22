package ksenukaiPages;

import ksenukaiPagePromises.Promise;
import org.openqa.selenium.By;
import pageComponents.PageComponents;
import selenium.TestContext;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartConfirmationPopup extends PageComponents {

    private static final By MAIN_BUTTON = By.xpath(".//a[@class = 'main-button']");
    private static final By CART_POPUP = By.id("cart-popup-container");


    public CartConfirmationPopup(TestContext context) {
        super(context);
        assertTrue(isElementPresentOnPage(CART_POPUP), "There is no pop up on the page");
    }

    public Promise pressMainButton() {
        click(MAIN_BUTTON);
        return new Promise(getContext());
    }
}
