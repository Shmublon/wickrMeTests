import base.BaseTest;
import beans.Account;
import beans.Message;
import beans.User;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.ChatPage;
import steps.ContactsSearchingSteps;
import steps.InterimSteps;
import steps.LoginSteps;
import steps.MessagingSteps;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Chat service")
@Feature("Messaging")
public class SnapchatSendMessageTest extends BaseTest {
    private Account account = new User().initFromFile("testData.json").getAccount();
    private static final String SIMPLE_MESSAGE = "SOME RANDOM MESSAGE";
    private Message message = new Message().initFromAPI("https://randomuser.me/api/");
    private String complexMessage = message.getEmail() + " " + message.getTimeZone();

    private LoginSteps loginSteps;
    private InterimSteps interimSteps;
    private ContactsSearchingSteps contactsSearchingSteps;
    private MessagingSteps messagingSteps;

    @Override
    @BeforeEach
    public void preConditions() throws MalformedURLException {
        super.preConditions();
        loginSteps = new LoginSteps(getDriver());
        interimSteps = new InterimSteps(getDriver());
        contactsSearchingSteps = new ContactsSearchingSteps(getDriver());
        messagingSteps = new MessagingSteps(getDriver());
    }

    @Test
    @Story("Send message to the test user")
    @Severity(SeverityLevel.BLOCKER)
    public void successfulScenario() {
        loginSteps.login(account);
        interimSteps.skipContactsAdding();
        contactsSearchingSteps.searchUserContact("test");
        messagingSteps.sendMessage(complexMessage);
        assertEquals(complexMessage.toLowerCase(), new ChatPage(getDriver()).getMessageByIndex(0).toLowerCase());
    }
}
