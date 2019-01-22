package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class PageObject {
    protected AndroidDriver driver;

    public PageObject(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public AndroidDriver getDriver() {
        return driver;
    }

    public WebElementFacade element(WebElement webElement) {
        return WebElementFacade.wrapWebElement(driver, webElement);
    }
}

