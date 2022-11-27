package persistence;

import model.Login;
import model.Logins;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonLoginReaderTest {
    @Test
    void testReaderNonExistentFile() {
        JsonLoginReader reader = new JsonLoginReader("./data/noSuchFile.json");
        try {
            Logins logins = reader.readLogin();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderNoUsers() {
        JsonLoginReader reader = new JsonLoginReader("./data/testReaderNoUsers.json");
        try {
            Logins logins = reader.readLogin();
            assertEquals(0, logins.getNumberOfUsers());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderUsersAdded() {
        JsonLoginReader reader = new JsonLoginReader("./data/testReaderUsersAdded.json");
        try {
            Logins logins = reader.readLogin();
            Map<Login,String> usersInformation = logins.getLoginInfo();
            assertEquals(2, usersInformation.size());
            for (Map.Entry<Login, String> entry : usersInformation.entrySet()) {
                if(entry.getKey().getUserName() == "mwalji24") {
                    assertEquals("maliha", entry.getValue());
                    assertEquals("M786110", entry.getKey().getPassword());
                }
                if(entry.getKey().getUserName() == "zmerali") {
                    assertEquals("zaahid", entry.getValue());
                    assertEquals("Z786110", entry.getKey().getPassword());
                }
            }
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
