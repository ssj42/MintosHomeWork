package ksenukaiPagePromises;

import ksenukaiPages.*;
import selenium.TestContext;

public class Promise {
    private final TestContext context;

    public Promise(TestContext context) {
        this.context = context;
    }

    public UniqueItemPage andGetUniqueItemPage() {
        return new UniqueItemPage(context);
    }

    public CartConfirmationPopup andGetCartConfirmationPopup() {
        return new CartConfirmationPopup(context);
    }

    public CartPage andGetCartPage() {
        return new CartPage(context);
    }
}
