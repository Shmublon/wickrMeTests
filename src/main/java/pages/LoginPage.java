package pages;

import base.PageObject;
import base.WebElementFacade;
import beans.Account;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LoginPage extends PageObject {
    @AndroidFindBy(id = "usernameInput")
    private WebElement usernameInput;

    @AndroidFindBy(id = "passwordInput")
    private WebElement passwordInput;

    @AndroidFindBy(id = "signInButton")
    private WebElement signInButton;

    public LoginPage(AndroidDriver driver) {
        super(driver);
    }

    public WebElementFacade usernameInput() {
        return element(usernameInput);
    }

    public WebElementFacade passwordInput() {
        return element(passwordInput);
    }

    public WebElementFacade signInButton() {
        return element(signInButton);
    }

    public void typeCredentials(Account account) {
        usernameInput().typeAndEnter(account.getUsername());
        passwordInput().typeAndEnter(account.getPassword());
    }

    public ContactFinderPage clickSignInButton() {
        signInButton().click();
        return new ContactFinderPage(getDriver());
    }
}
