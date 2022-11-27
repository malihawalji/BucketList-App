package model;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login {
    private String userName;
    private String password;

    private Map<String, String> userAndPass;


    public Login(String userName, String password) {
        this.userName = userName;
        this.password = password;
        userAndPass = new HashMap<>();
        userAndPass.put(this.userName, this.password);
    }

    public Map<String, String> getLogin() {
        return userAndPass;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUserName() {
        return this.userName;
    }

    public JSONObject loginToJson() {
        JSONObject json = new JSONObject();
        for (Map.Entry<String, String> entry: userAndPass.entrySet()) {
            json.put("userName", entry.getKey());
            json.put("password", entry.getValue());
        }
        return json;
    }
}
