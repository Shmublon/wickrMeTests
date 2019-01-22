package utils;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestPropertiesLoader {
    private static final Logger LOGGER = Logger.getLogger(TestPropertiesLoader.class);

    public static Properties getProperties() {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = TestPropertiesLoader.class.getClassLoader().getResourceAsStream("main.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            return prop;

        } catch (IOException ex) {
            LOGGER.error(ex);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    LOGGER.error(e);
                }
            }
        }
        return null;
    }
}
