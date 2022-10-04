package model;

import java.util.ArrayList;

//This class creates a list of goal objects, handles adding/removing goal objects,
// checking them off and printing out the final list.
public class List {
    private int i;
    private String newgoalName;
    public final ArrayList<Goal> listOfGoals;
    private String dateCompleted;
    private String experienceNotes = null;

    //MODIFIES: this
    //EFFECTS: creates new ArrayList, listOfGoals which stores multiple
    // goal objects as part of List class constructor
    public List() {
        listOfGoals = new ArrayList<>();
    }
}
