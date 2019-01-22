package steps;

import beans.Account;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import pages.LoginPage;
import pages.StartPage;

public class LoginSteps {
    private static final Logger LOGGER = Logger.getLogger(LoginSteps.class);

    private StartPage startPage;
    private LoginPage loginPage;

    public LoginSteps(AndroidDriver driver) {
        startPage = new StartPage(driver);
        loginPage = new LoginPage(driver);
    }

    @Step("Login with credentials {account}")
    public void login(Account account) {
        LOGGER.info("Login with credentials " + account.getUsername() + " " + account.getPassword());
        startPage.clickLoginButton();
        loginPage.typeCredentials(account);
        loginPage.clickSignInButton();
    }
}
