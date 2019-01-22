package pages;

import base.PageObject;
import base.WebElementFacade;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchContactsPage extends PageObject {
    @AndroidFindBy(id = "search_src_text")
    private WebElement searchInput;

    public SearchContactsPage(AndroidDriver driver) {
        super(driver);
    }

    public WebElementFacade searchInput() {
        return element(searchInput);
    }

    public void getContactByIndex(int index) {
        ((WebElement) getDriver().findElements(By.id("contact_row_username")).get(index)).click();
    }

    public void searchContacts(String textToSearch) {
        searchInput().typeAndEnter(textToSearch);
    }

    public ChatPage clickContactByIndex(int index) {
        getContactByIndex(index);
        return new ChatPage(getDriver());
    }
}
