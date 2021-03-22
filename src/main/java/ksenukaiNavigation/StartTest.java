package ksenukaiNavigation;

import ksenukaiPages.KsenukaiHomePage;
import org.openqa.selenium.By;
import pageComponents.PageComponents;
import selenium.TestContext;

import static enums.DirectLinks.*;

public class StartTest {
    private static final By ACCEPT_PRIVACY_MESSAGE = By.id("CybotCookiebotDialogBodyButtonAccept");
    final TestContext context;

    public StartTest(TestContext context) {
        this.context = context;
    }

    public KsenukaiHomePage onKsenukaiHomePage() {
        context.openPage(KSENUKAI.link());
        new KsenukaiHomePage(context.getContext());
        PageComponents pageComponents = new PageComponents(context);
        if (pageComponents.isElementPresentOnPage(ACCEPT_PRIVACY_MESSAGE))
            new PageComponents(context).click(ACCEPT_PRIVACY_MESSAGE);
        return new KsenukaiHomePage(context.getContext());
    }
}