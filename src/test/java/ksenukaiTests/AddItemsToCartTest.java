package ksenukaiTests;

import ksenukaiPages.CartConfirmationPopup;
import ksenukaiPages.CartPage;
import ksenukaiPages.UniqueItemPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import selenium.TestContext;

import static enums.Items.PLAYSTATION_CAMERA;
import static org.junit.jupiter.api.Assertions.*;

public class AddItemsToCartTest extends TestContext {

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    @Test
    public void testAddItemsToCart() {

        LOGGER.info("Start test on Ksenukai home page, fill search field and get Unique Item page");
        UniqueItemPage uniqueItemPage = startTest().onKsenukaiHomePage()
                .fillSearchField(PLAYSTATION_CAMERA.item())
                .clickOnFoundItem()
                .andGetUniqueItemPage();

        LOGGER.info("Collect Items price, press 'Add to cart' button and get Cart Confirmation popup");
        final Double itemsPrice = uniqueItemPage.getItemsPrice();
        CartConfirmationPopup cartConfirmationPopup = uniqueItemPage
                .pressAddToCartButton()
                .andGetCartConfirmationPopup();

        LOGGER.info("Press Main button and get Carts page");
        CartPage cartPage = cartConfirmationPopup.pressMainButton()
                .andGetCartPage();

        LOGGER.info("Check that information in cart is correct");
        assertAll("Some information is not correct",
                () -> assertEquals(itemsPrice, cartPage.totalPrice()),
                () -> assertEquals(PLAYSTATION_CAMERA.item(), cartPage.itemInCart()));
    }
}
