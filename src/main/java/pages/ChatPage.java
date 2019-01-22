package pages;

import base.PageObject;
import base.WebElementFacade;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ChatPage extends PageObject {
    @AndroidFindBy(id = "messageInput")
    private WebElement messageInput;

    @AndroidFindBy(id = "sendButton")
    private WebElement sendButton;

    public ChatPage(AndroidDriver driver) {
        super(driver);
    }

    public String getMessageByIndex(int index) {
        return ((WebElement)getDriver().findElements(By.id("messageText")).get(index)).getText();
    }

    public WebElementFacade messageInput() {
        return element(messageInput);
    }

    public WebElementFacade sendButton() {
        return element(sendButton);
    }

    public void sendMessage(String message) {
        messageInput().type(message);
        sendButton().click();
    }
}
