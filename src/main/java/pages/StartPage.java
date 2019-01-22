package pages;

import base.PageObject;
import base.WebElementFacade;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;

import java.io.File;
import java.io.IOException;

/**
 * Created by Nikita on 18.07.2016.
 */
public class StartPage extends PageObject {
    @AndroidFindBy(id = "loginButton")
    private WebElement loginButton;

    public StartPage(AndroidDriver driver) {
        super(driver);
    }

    private WebElementFacade loginButton() {
        return element(loginButton);
    }

    public LoginPage clickLoginButton() {
        loginButton().click();
        return new LoginPage(getDriver());
    }
}