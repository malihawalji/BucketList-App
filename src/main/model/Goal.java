package model;

//Creates goal objects and has getters and setters for each component of the object
public class Goal {

    private String goalName;
    private String notes;
    private String date;
    private String experience;

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
}

