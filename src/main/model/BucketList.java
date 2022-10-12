package model;

import java.util.ArrayList;

//This class creates a list of goal objects, handles adding/removing goal objects,
// checking them off and printing out the final list.
public class BucketList {

    private int anInt;
    private String newGoalName;
    public final ArrayList<Goal> listOfGoals;
    private String dateCompleted;
    private String experienceNotes = null;

    //MODIFIES: this
    //EFFECTS: creates new ArrayList, listOfGoals which stores multiple
    // goal objects as part of List class constructor
    public BucketList() {
        listOfGoals = new ArrayList<>();
    }

    //
    public void addGoal(Goal goal) {
        listOfGoals.add(goal);
    }

    public int getNumberOfItemsInList() {
        return listOfGoals.size();
    }

    public void setAnInt(int anInt) {
        this.anInt = anInt - 1;
    }

    public void setNewGoalName(String newGoalName) {
        this.newGoalName = newGoalName;
    }

    //REQUIRES: index exists, listOfGoals isn't empty
    //MODIFIES: this, Goal
    //EFFECTS: removes item from listOfGoals
    public void getGoalItemToRemove() {
        listOfGoals.remove(anInt);
    }

    //REQUIRES: index exists, listOfGoals.size() > 0, item isn't already checked off
    //MODIFIES: this, Goal
    //EFFECTS: sets goal object's name to "name" + " X Completed: " + getDateCompleted())
    // also changes goal object's experience parameter to reflect user input
    public void getGoalItemToCheck() {
        setNewGoalName(listOfGoals.get(anInt).getGoal() + " X Completed: " + getDateCompleted());
        setExperienceNotes(listOfGoals.get(anInt).getExperience() + "Experience: " + getExperienceNotes());
        listOfGoals.get(anInt).setGoal(newGoalName);
        listOfGoals.get(anInt).setExperience(experienceNotes);
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
}
