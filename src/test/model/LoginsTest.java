package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class LoginsTest {
    Logins logins;
    Login login;
    Login login2;
    Login login3;

    @BeforeEach
    void beforeEach() {
        logins = new Logins();
        login = new Login("mw", "123");
        login2 = new Login("zm", "345");
        login3 = new Login("sw", "567");
    }

    @Test
    void testAddLogin() {
        logins.addLogin(login, "maliha");
        assertTrue(logins.getLoginInfo().containsKey(login));
        assertTrue(logins.getLoginInfo().containsValue("maliha"));

        logins.addLogin(login2, "zaahid");
        assertTrue(logins.getLoginInfo().containsKey(login2));
        assertTrue(logins.getLoginInfo().containsValue("zaahid"));

        logins.addLogin(login3, "sakina");
        assertTrue(logins.getLoginInfo().containsKey(login3));
        assertTrue(logins.getLoginInfo().containsValue("sakina"));
    }

    @Test
    void testGetNumberOfUsers() {
        logins.addLogin(login, "maliha");
        logins.addLogin(login2, "zaahid");
        logins.addLogin(login3, "sakina");
        assertEquals(3, logins.getNumberOfUsers());
    }

    @Test
    void testUserNameAlreadyExists() {
        logins.addLogin(login2, "zaahid");
        logins.addLogin(login, "maliha");
        assertTrue(logins.userNameAlreadyExists("mw"));
        assertTrue(logins.userNameAlreadyExists("zm"));
    }

    @Test
    void testUserNamePasswordExists() {
        logins.addLogin(login2, "zaahid");
        logins.addLogin(login, "maliha");
        String userName = "mw";
        String password = "123";
        String userName2 = "zm";
        String password2 = "345";
        assertTrue(logins.userNamePasswordExists(userName, password));
        assertTrue(logins.userNamePasswordExists(userName2, password2));
        assertFalse(logins.userNamePasswordExists("sw", "123"));
    }

    @Test
    void testToJson() {
        assertEquals("{\"User info\":[]}", logins.toJson().toString());
    }

    @Test
    void testLoginsToJson() {
        String name = logins.getLoginInfo().get(login);
        JSONObject jsonObject = logins.loginsToJson(name, login);
        assertEquals("{\"password\":\"123\",\"userName\":\"mw\"}", jsonObject.getJSONObject("login").toString());
    }

    @Test
    void testAddToJSONArray() {
        String name = logins.getLoginInfo().get(login);
        logins.addToJsonArray(logins.loginsToJson(name, login));
        assertEquals("{\"login\":{\"password\":\"123\",\"userName\":\"mw\"}}", logins.getJsonArray().get(0).toString());
    }

    @Test
    void testReturnJson() {
        logins.addLogin(login, "maliha");
        logins.addLogin(login2, "zaahid");
        logins.addLogin(login3, "sakina");
        assertEquals("[{\"name\":\"zaahid\",\"login\":{\"password\":\"345\",\"userName\":\"zm\"}},{\"name\":"
                + "\"maliha\",\"login\":{\"password\":\"123\",\"userName\":\"mw\"}},{\"name\":\"sakina\","
                + "\"login\":{\"password\":\"567\",\"userName\":\"sw\"}}]", logins.returnJson().toString());

    }
}
