package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {

    String userName;
    String password;
    Login login;

    @BeforeEach()
    void beforeEach() {
        userName = "mwalji24";
        password = "M786110";
        login = new Login(userName, password);
    }

    @Test
    void testConstructor() {
        for(Map.Entry<String, String> entry: login.getLogin().entrySet()) {
            assertEquals("mwalji24", entry.getKey());
            assertEquals("M786110", entry.getValue());
        }
    }

    @Test
    void testLoginToJson() {
        assertEquals("mwalji24", login.loginToJson().getString("userName"));
        assertEquals("M786110", login.loginToJson().getString("password"));
    }

}
