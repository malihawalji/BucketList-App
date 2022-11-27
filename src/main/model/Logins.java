package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Logins {
    String name;
    Login login;
    JSONArray jsonArray = new JSONArray();
    boolean exists;

    private Map<Login, String> loginsMap;

    public Logins() {
        loginsMap = new HashMap();
    }

    public void addLogin(Login login, String name) {
        this.loginsMap.put(login, name);
    }

    public Map<Login, String> getLoginInfo() {
        return this.loginsMap;
    }

    public int getNumberOfUsers() {
        return this.loginsMap.size();
    }

    public boolean userNameAlreadyExists(String userName) {
        for (Map.Entry<Login, String> entry : this.loginsMap.entrySet()) {
            if (entry.getKey().getUserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    public boolean userNamePasswordExists(String userName, String password) {
//        for (Map.Entry<Login, String> entry : getLoginInfo().entrySet()) {
//            if (entry.getKey().getUserName().equals(userName)) {
//                if (entry.getKey().getPassword().equals(password)) {
//                    return true;
//                }
//            }
//        }
//        return false;
        Iterator<Map.Entry<Login, String>>
                iterator = loginsMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Login, String> entry = iterator.next();
            if (userName == entry.getKey().getUserName()) {
                return true;
            }
        }
        return false;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("User info", this.returnJson());
        return json;
    }

    public JSONObject loginsToJson(String name, Login login) {
        JSONObject json = new JSONObject();
        json.put("login", login.loginToJson());
        json.put("name", name);
        return json;
    }

    public void addToJsonArray(JSONObject json) {
        this.jsonArray.put(json);
    }

    public JSONArray getJsonArray() {
        return jsonArray;
    }

    public JSONArray returnJson() {
        Iterator var1 = this.loginsMap.keySet().iterator();

        while (var1.hasNext()) {
            Login login = (Login)var1.next();
            this.name = (String)this.loginsMap.get(login);
            this.addToJsonArray(this.loginsToJson(this.name, login));
        }

        return this.jsonArray;
    }
}
