package persistence;

import model.Login;
import model.Logins;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonLoginWriterTest {
    @Test
    void testWriterInvalidFile() {
        try {
            Logins logins = new Logins();
            JsonLoginWriter writer = new JsonLoginWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyList() {
        try {
            Logins logins = new Logins();
            JsonLoginWriter writer = new JsonLoginWriter("./testNoUsersAdded.json");
            writer.open();
            writer.write(logins);
            writer.close();

            JsonLoginReader reader = new JsonLoginReader("./testNoUsersAdded.json");
            logins = reader.readLogin();
            assertEquals(0, logins.getNumberOfUsers());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterNotEmptyList() {
        try {
            Logins logins = new Logins();
            Login login1 = new Login("mwalji24", "M786110");
            logins.addLogin(login1, "maliha");
            Login login2 = new Login("zmerali", "Z786110");
            logins.addLogin(login2, "zaahid");
            JsonLoginWriter writer = new JsonLoginWriter("./data/testUsersAdded.json");
            writer.open();
            writer.write(logins);
            writer.close();

            JsonLoginReader reader = new JsonLoginReader("./data/testUsersAdded.json");
            logins = reader.readLogin();
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
            fail("Exception should not have been thrown");
        }
    }

}
