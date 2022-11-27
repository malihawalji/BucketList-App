package persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Login;
import model.Logins;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonLoginReader {
    ObjectMapper mapper = new ObjectMapper();
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonLoginReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Logins readLogin() throws IOException {
        String jsonLoginData = readFileLogin(source);
        JSONObject jsonObject = new JSONObject(jsonLoginData);
        return parse(jsonObject);
    }

    //EFFECTS: reads source file and returns it as a string
    //throws IOException if an error occurs reading data from file
    public String readFileLogin(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    private Logins parse(JSONObject jsonObject) {
        Logins l = new Logins();
        addLogins(l, jsonObject);
        return l;
    }


    public void addLogins(Logins l, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("User info");
        for (Object json : jsonArray) {
            JSONObject nextLogin = (JSONObject) json;
            addLogin(l, nextLogin);
        }
    }

    public void addLogin(Logins l, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        JSONObject loginObj = jsonObject.getJSONObject("login");
        String userName = loginObj.getString("userName");
        String password = loginObj.getString("password");
        Login login = new Login(userName, password);
        l.addLogin(login, name);
    }

}
