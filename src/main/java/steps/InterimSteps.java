package steps;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import pages.ContactFinderPage;

public class InterimSteps {
    private static final Logger LOGGER = Logger.getLogger(InterimSteps.class);

    private ContactFinderPage contactFinderPage;

    public InterimSteps(AndroidDriver driver) {
        contactFinderPage = new ContactFinderPage(driver);
    }

    @Step("Interim steps: Skip adding contacts from the contacts book")
    public void skipContactsAdding() {
        LOGGER.info("Skip adding contacts from the contacts book");
        contactFinderPage.clickNoThanksButton();
    }
}
