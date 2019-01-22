package steps;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import pages.ChatPage;
import pages.ContactFinderPage;

public class MessagingSteps {
    private static final Logger LOGGER = Logger.getLogger(MessagingSteps.class);

    private ChatPage chatPage;

    public MessagingSteps(AndroidDriver driver) {
        chatPage = new ChatPage(driver);
    }

    @Step("Send message {message}")
    public void sendMessage(String message) {
        LOGGER.info("Send message " + message);
        chatPage.sendMessage(message);
    }
}
