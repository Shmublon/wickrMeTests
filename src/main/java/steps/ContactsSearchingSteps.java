package steps;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import pages.SearchContactsPage;

public class ContactsSearchingSteps {
    private static final Logger LOGGER = Logger.getLogger(ContactsSearchingSteps.class);

    private SearchContactsPage searchContactsPage;

    public ContactsSearchingSteps(AndroidDriver driver) {
        searchContactsPage = new SearchContactsPage(driver);
    }

    @Step("Search for user {contact}")
    public void searchUserContact(String contact) {
        LOGGER.info("Search for user " + contact);
        searchContactsPage.searchContacts(contact);
        searchContactsPage.clickContactByIndex(0);
    }
}
