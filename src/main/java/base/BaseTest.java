package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.StartPage;
import utils.TestPropertiesLoader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class BaseTest {
    private static Properties props = TestPropertiesLoader.getProperties();
    private AndroidDriver driver;
    protected StartPage startPage;

    protected AndroidDriver getDriver() {
        return driver;
    }

    @BeforeEach
    public void preConditions() throws MalformedURLException {

        // Prepare Appium session
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (System.getProperty("env") != null && System.getProperty("env").equals("LOCAL")) {
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, props.getProperty("device.name"));
            capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
            capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, props.getProperty("app.package.name"));
            capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, props.getProperty("app.activity.name"));
            capabilities.setCapability(AndroidMobileCapabilityType.BROWSER_NAME, "");
            capabilities.setCapability("autoGrantPermissions", true);
            File file = new File(getClass().getClassLoader().getResource("wickrMe.apk").getFile());
            capabilities.setCapability("app", file.getAbsolutePath());
            // Initialize driver
            driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        } else {
            capabilities.setCapability("sessionDescription", "");
            capabilities.setCapability("deviceOrientation", "portrait");
            capabilities.setCapability("captureScreenshots", true);
            capabilities.setCapability("browserName", "chrome");
            capabilities.setCapability("deviceGroup", "KOBITON");
            capabilities.setCapability("deviceName", "Nexus 5");
            capabilities.setCapability("platformVersion", "5.1");
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("app", "kobiton-store:24397");
            // Initialize driver
            driver = new AndroidDriver(new URL("https://shmublon:81ff2c1e-1e8e-43c6-b122-38673498a441@api.kobiton.com/wd/hub"), capabilities);
        }
        startPage = new StartPage(driver);
    }

    @AfterEach
    public void postConditions() {
        getDriver().quit();
    }
}
