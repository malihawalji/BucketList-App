package ui;

import model.*;
import java.util.Objects;
import java.util.Scanner;

//Creates new List object which stores multiple goal objects
//the user creates with the scanner object, and is able to modify
//by through removing or checking goals as completed
public class BucketListApp {

    Scanner in = new Scanner(System.in);
    List list = new List();
    String name;

    //EFFECTS: takes in name from user
    public void welcomeLine() {}

    //REQUIRES: input = a | b | c | d | x
    //EFFECTS: calls class user chooses
    public void menu() {}

    //REQUIRES:
    //MODIFIES: Goal, List
    //EFFECTS: creates goal object and adds it to list class
    public void caseA() {}

    //REQUIRES: a non-empty list
    //MODIFIES: Goal, List
    //EFFECTS: removes chosen goal object from list class
    public void caseB() {}

    //EFFECTS: prints updated list
    public void caseC() {}

    //REQUIRES: index exists && list.getNumberOfItemsInList() > 0 && list item isn't already checked off
    //MODIFIES: Goal, List
    //EFFECTS: modifies goal object's name to reflect new user
    // input that "checks off" item from list
    //also modifies experience parameter of the goal object
    public void caseD() {}


    //EFFECTS: Either ends program or goes back to menu based on user input
    public void yesNo() {}

    //Stops scanner from reading previous input if called in a method
    public void scannerIssueFix() {
        in.nextLine();
    }

    public void exit() {
        System.exit(1);
    }
}


