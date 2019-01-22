package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.ui.*;

import java.util.List;

public class WebElementFacade implements WrapsElement, WebElement, Locatable {
    private static final int TIME_OUT_IN_SECONDS = 60;
    private static final int WAIT_FOR_ELEMENT_PAUSE_LENGTH = 500;
    private static final Logger LOGGER = Logger.getLogger(WebElementFacade.class);
    protected final AndroidDriver driver;
    private final Sleeper sleeper;
    private final Clock webdriverClock;
    private WebElement webElement;
    private ElementLocator locator;

    private String page;

    private WebElementFacade(final AndroidDriver driver,
                             final ElementLocator locator,
                             final WebElement webElement) {
        this.webElement = webElement;
        this.driver = driver;
        this.locator = locator;
        this.webdriverClock = new SystemClock();
        this.sleeper = Sleeper.SYSTEM_SLEEPER;
    }

    @SuppressWarnings("unused")
    public WebElementFacade(final AndroidDriver driver,
                            final ElementLocator locator,
                            final long timeoutInMilliseconds) {
        this(driver, locator, null);
    }

    public static WebElementFacade wrapWebElement(final AndroidDriver driver,
                                                  final WebElement element) {
        return new WebElementFacade(driver, null, element);

    }

    protected WebElement getElement() {
        if (webElement != null) {
            return webElement;
        }
        if (locator == null) {
            return null;
        }
        return locator.findElement();
    }

    public String getAttribute(String name) {
        return getElement().getAttribute(name);
    }

    public boolean isEnabled() {
        return (getElement() != null) && (getElement().isEnabled());
    }

    /**
     * Type a value into a field
     *
     * @param value which you want to set up in the text area or input
     */

    public WebElementFacade typeAndEnter(final String value) {
        getElement().sendKeys(value);
        driver.pressKeyCode(AndroidKeyCode.ENTER);
        return this;
    }

    /**
     * Type a value into a field and press enter
     *
     * @param value which you want to set up in the text area or input
     */

    public WebElementFacade type(final String value) {
        getElement().sendKeys(value);
        return this;
    }

    private ExpectedCondition<Boolean> elementIsEnabled() {
        LOGGER.debug(String.format("Wait till %s element will be enabled on the page %s", this, this.page));
        return driver1 -> {
            WebElement element = getElement();
            return ((element != null)
                    && element.isEnabled());
        };
    }

    public WebDriverWait waitFor() {
        return new WebDriverWait(driver, webdriverClock, sleeper, TIME_OUT_IN_SECONDS, WAIT_FOR_ELEMENT_PAUSE_LENGTH);
    }

    public WebElementFacade waitUntilPresent() {
        try {
            waitFor().until(elementIsPresent());
            return this;
        } catch (Exception ex) {
            waitFor().until(elementIsPresent());
            return this;
        }
    }


    private ExpectedCondition<Boolean> elementIsPresent() {
        return driver1 -> isPresent();
    }


    public boolean isPresent() {
        try {
            return getElement() != null && !getElement().getTagName().equals("");
        } catch (NoSuchElementException e) {
            return e.getMessage().contains("Element is not usable");
        }
    }

    public boolean isSelected() {
        return getElement().isSelected();
    }


    public String getText() {
        return getElement().getText();
    }

    /**
     * Wait for an element to be visible and enabled, and then click on it.
     */
    public void click() {
        getElement().click();
    }

    public void clear() {
        try {
            getElement().sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
            getElement().clear();
        } catch (StaleElementReferenceException | InvalidElementStateException e) {
            getElement().sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        }
    }

    public String toString() {
        try {
            if (getElement() != null) {
                return getElement().toString();
            }
        } catch (NoSuchElementException e) {/**/}
        return "<Undefined web element>";
    }

    public void submit() {
        getElement().submit();
    }

    /*
     * WebDirver default
     *
     */

    public void sendKeys(CharSequence... keysToSend) {
        getElement().sendKeys(keysToSend);
    }

    public String getTagName() {
        return getElement().getTagName();
    }

    public List<WebElement> findElements(By by) {
        return getElement().findElements(by);
    }

    public WebElement findElement(By by) {
        return getElement().findElement(by);
    }

    public boolean isDisplayed() {
        try {
            return getElement().isDisplayed();
        } catch (StaleElementReferenceException e) {
            return getElement().isDisplayed();
        }
    }

    public Point getLocation() {
        return getElement().getLocation();
    }

    public Dimension getSize() {
        return getElement().getSize();
    }


    public Rectangle getRect() {
        return getElement().getRect();
    }

    public String getCssValue(String propertyName) {
        return getElement().getCssValue(propertyName);
    }

    public WebElement getWrappedElement() {
        return getElement();
    }


    public Coordinates getCoordinates() {
        return ((Locatable) getElement()).getCoordinates();
    }

    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return ((RemoteWebDriver) driver).getScreenshotAs(outputType);
    }
}
