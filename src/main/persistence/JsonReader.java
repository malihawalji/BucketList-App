package persistence;

import model.Goal;
import model.BucketList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public BucketList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseList(jsonObject);
    }


    //MODIFIES:
    //EFFECTS:
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses listOfGoals from JSON object and returns it
    private BucketList parseList(JSONObject jsonObject) {
        BucketList l = new BucketList();
        addLists(l, jsonObject);
        return l;
    }

    // MODIFIES: l
    // EFFECTS: parses goals from JSON object and adds them to List
    private void addLists(BucketList l, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("list of goals");
        for (Object json : jsonArray) {
            JSONObject nextGoal = (JSONObject) json;
            addList(l, nextGoal);
        }
    }

    //MODIFIES: goal
    //EFFECTS: retrieves JSON objects and adds them back as goals
    private void addList(BucketList l, JSONObject jsonObject) {
        String goalName = jsonObject.getString("goalName");
        String notes = jsonObject.getString("notes");
        String date = jsonObject.getString("date");
        String experience = jsonObject.getString("experience");
        Goal goal = new Goal(goalName, notes, date, experience);
        l.addGoal(goal);
    }
}
