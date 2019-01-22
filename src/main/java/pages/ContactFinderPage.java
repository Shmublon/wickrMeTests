package pages;

import base.PageObject;
import base.WebElementFacade;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import utils.Timeout;

public class ContactFinderPage extends PageObject {
    @AndroidFindBy(id = "declineButton")
    private WebElement noThanksButton;

    public ContactFinderPage(AndroidDriver driver) {
        super(driver);
        noThanksButton().waitFor();
    }

    private WebElementFacade noThanksButton() {
        return element(noThanksButton);
    }

    public SearchContactsPage clickNoThanksButton() {
        Timeout.timeout(10);
        noThanksButton().click();
        return new SearchContactsPage(getDriver());
    }
}
