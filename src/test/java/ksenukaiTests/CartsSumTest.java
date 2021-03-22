package ksenukaiTests;

import ksenukaiPages.CartConfirmationPopup;
import ksenukaiPages.CartPage;
import ksenukaiPages.UniqueItemPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import selenium.TestContext;

import static enums.Items.NINTENDO_MONOPOLY;
import static enums.Items.XBOX_SERIES_X;
import static org.junit.jupiter.api.Assertions.*;

public class CartsSumTest extends TestContext {

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    @Test
    public void testCartsSum() {

        LOGGER.info("Start test on Ksenukai home page, fill search field and get Unique Item page");
        UniqueItemPage uniqueItemPage = startTest().onKsenukaiHomePage()
                .fillSearchField(XBOX_SERIES_X.item())
                .clickOnFoundItem()
                .andGetUniqueItemPage();

        LOGGER.info("Collect first items price, press 'Add to cart' button and get Cart Confirmation popup");
        final Double firstItemsPrice = uniqueItemPage.getItemsPrice();
        CartConfirmationPopup cartConfirmationPopup = uniqueItemPage
                .pressAddToCartButton()
                .andGetCartConfirmationPopup();

        LOGGER.info("Go to Carts page, search for a second item and collect it's price");
        uniqueItemPage = cartConfirmationPopup.pressMainButton()
                .andGetCartPage()
                .fillSearchField(NINTENDO_MONOPOLY.item())
                .clickOnFoundItem()
                .andGetUniqueItemPage();
        final Double secondItemsPrice = uniqueItemPage.getItemsPrice();

        LOGGER.info("Collect total Price in cart");
        CartPage cartPage = uniqueItemPage.pressAddToCartButton()
                .andGetCartConfirmationPopup()
                .pressMainButton()
                .andGetCartPage();
        final Double totalPriceInCart = cartPage.totalPrice();

        LOGGER.info("Check that information in cart is correct");
        assertEquals(firstItemsPrice + secondItemsPrice, totalPriceInCart,
                "Something went wrong, expected sum: " + firstItemsPrice + secondItemsPrice + "But was: " + totalPriceInCart);
    }
}
