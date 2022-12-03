package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//Method templates for toJson and listToJson referenced from JsonSerializationDemo-master
//This class creates a list of goal objects, handles adding/removing goal objects,
// checking them off and printing out the final list.
public class BucketList implements Writable {

    private int anInt;
    private String newGoalName;
    public final ArrayList<Goal> listOfGoals;
    private String dateCompleted;
    private String experienceNotes = null;
    private String name;

    //MODIFIES: this
    //EFFECTS: creates new ArrayList, listOfGoals which stores multiple
    // goal objects as part of List class constructor
    public BucketList() {
        listOfGoals = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    //MODIFIES: this
    //EFFECTS: adds Goal item to listOfGoals
    // creates new event to log in event log every time a goal is added
    public void addGoal(Goal goal) {
        listOfGoals.add(goal);
        EventLog.getInstance().logEvent(new Event("goal added to bucket list"));
    }

    public int getNumberOfItemsInList() {
        return listOfGoals.size();
    }

    public void setAnInt(Integer anInt) {
        this.anInt = anInt - 1;
    }

    public List<Goal> getList() {
        return listOfGoals;
    }

    //MODIFIES: Goal
    //EFFECTS: modifies the name of the goal and adds the date completed as part of it
    public void setNewGoalName(String newGoalName) {
        this.newGoalName = newGoalName;
    }

    //REQUIRES: index exists, listOfGoals isn't empty
    //MODIFIES: this, Goal
    //EFFECTS: removes item from listOfGoals
    // creates new event to log in event log every time a goal is removed
    public void getGoalItemToRemove() {
        listOfGoals.remove(anInt);
        EventLog.getInstance().logEvent(new Event("goal removed from bucket list"));
    }

    //REQUIRES: index exists, listOfGoals.size() > 0, item isn't already checked off
    //MODIFIES: this, Goal
    //EFFECTS: sets goal object's name to "name" + " X Completed: " + getDateCompleted())
    // also changes goal object's experience parameter to reflect user input
    // Creates new event in event log everytime goal is checked off
    public void getGoalItemToCheck() {
        setNewGoalName(listOfGoals.get(anInt).getGoal() + " X Completed: " + getDateCompleted());
        setExperienceNotes(listOfGoals.get(anInt).getExperience() + "Experience: " + getExperienceNotes());
        listOfGoals.get(anInt).setGoal(newGoalName);
        listOfGoals.get(anInt).setExperience(experienceNotes);
        EventLog.getInstance().logEvent(new Event("goal checked off!"));
    }

    public void setDateCompleted(String dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public String getDateCompleted() {
        return dateCompleted;
    }

    public void setExperienceNotes(String experienceNotes) {
        this.experienceNotes = experienceNotes;
    }

    public String getExperienceNotes() {
        return experienceNotes;
    }

    //EFFECTS: based on user response gets the index and
    // checks whether experience notes have been added or not
    public String checkExperience() {
        return listOfGoals.get(anInt).getExperience();
    }

    //REQUIRES: listOfGoals isn't empty
    //EFFECTS: prints listOfGoals information in a numbered and labelled format
    public String getBucketList() {
        StringBuilder bucketList = new StringBuilder();
        if (!listOfGoals.isEmpty()) {
            for (int i = 0; i < getNumberOfItemsInList(); i++) {
                int listItem = i + 1;
                bucketList.append("\n#").append(listItem).append(" Date: ")
                        .append(listOfGoals.get(i).getDate()).append("\n")
                        .append(listOfGoals.get(i).getGoal()).append("\n")
                        .append("Notes: ").append(listOfGoals.get(i)
                                .getNotes()).append("\n")
                        .append(listOfGoals.get(i).getExperience()).append("\n");
            }
        }
        return bucketList.toString();
    }

    //MODIFIES: this
    //EFFECTS: makes the list a JSON object
    // creates new event to log in event log every time bucket list is
    // converted to json file as saved
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("list of goals", this.listToJson());
        EventLog.getInstance().logEvent(new Event("saved bucket list"));
        return json;
    }

    //MODIFIES: this
    //EFFECTS: returns all the list of goals and each component of each goal as a JSON array
    protected JSONArray listToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Goal g : this.listOfGoals) {
            jsonArray.put(g.toJson());
        }
        return jsonArray;
    }
}
