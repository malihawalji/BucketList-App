package persistence;

import org.json.JSONObject;

//template for interface referenced from JsonSerializationDemo-master
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
