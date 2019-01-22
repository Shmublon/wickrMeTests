package beans;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class User {
    private static final Logger LOGGER = Logger.getLogger(User.class);

    private Account account;

    public Account getAccount() {
        return account;
    }

    public User initFromFile(String path) {
        InputStream inputStream = null;
        String resourceName = "testData.json";
        try {
            LOGGER.info("Looking for " + resourceName);
            inputStream = getClass().getClassLoader().getResourceAsStream(resourceName);

            if (inputStream != null) {
                LOGGER.info("Got " + inputStream.toString());
                return new ObjectMapper().readValue(inputStream, User.class);
            } else {
                throw new FileNotFoundException("JSON file '" + resourceName+ "' not found in the classpath");
            }

        } catch (JsonParseException e) {
            LOGGER.error("Can't parse " + resourceName, e);
        } catch (JsonMappingException e) {
            LOGGER.error("Can't map " + resourceName, e);
        } catch (FileNotFoundException e) {
            LOGGER.error("File " + resourceName + " not found", e);
        } catch (IOException e) {
            LOGGER.error("IOException " + resourceName, e);
        } finally {
            try {
                assert inputStream != null;
                inputStream.close();
            } catch (IOException e) {
                // Do nothing
                //e.printStackTrace();
            }
        }
        return null;
    }
}
