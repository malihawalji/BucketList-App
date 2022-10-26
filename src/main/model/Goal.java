package model;

import org.json.JSONObject;

//Creates goal objects and has getters and setters for each component of the object
public class Goal {

    private String goalName;
    private String notes;
    private String date;
    private String experience;

    //MODIFIES: Goal
    //EFFECTS: Constructor creates new Goal object with goalName, notes, date, and experience
    public Goal(String goalName, String notes, String date, String experience) {
        this.goalName = goalName;
        this.date = date;
        this.notes = notes;
        this.experience = experience;

    }

    public String getGoal() {
        return goalName;
    }

    public void setGoal(String goalName) {
        this.goalName = goalName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getExperience() {
        return experience;
    }

    //EFFECTS: Makes each component of goal part of JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("goalName", this.goalName);
        json.put("notes", this.notes);
        json.put("date", this.date);
        json.put("experience", this.experience);
        return json;
    }
}

