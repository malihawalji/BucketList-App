package ui;

import model.*;

import java.util.Objects;
import java.util.Scanner;

//Creates new List object which stores multiple goal objects
//the user creates with the scanner object, and is able to modify
//by through removing or checking goals as completed
public class BucketListApp {

    Scanner in = new Scanner(System.in);
    BucketList list = new BucketList();
    String name;

    //EFFECTS: constructor calls first method to execute
    public BucketListApp() {
        welcomeLine();
    }

    //EFFECTS: takes in name from user
    public void welcomeLine() {
        System.out.println("Hello! Welcome to The Bucket List! Please enter your name");
        name = in.nextLine();
        menu();
    }

    //REQUIRES: input = a | b | c | d | x
    //EFFECTS: calls class user chooses
    public void menu() {
        System.out.println("So, " + name + " what would you like to do?");
        System.out.println("a. add item \n" + "b. remove item \n"
                + "c. view list \n" + "d. check off item \n" + "x. exit");

        switch (in.nextLine().toLowerCase()) {
            case "a":
                caseA();
                break;
            case "b":
                caseB();
                break;
            case "c":
                caseC();
                break;
            case "d":
                caseD();
                break;
            case "x":
                exit();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + in.nextLine());
        }
    }

    //MODIFIES: Goal, List
    //EFFECTS: creates goal object and adds it to list class
    public void caseA() {
        System.out.println("Please type in what goal you would like to add to your bucket list!");
        String goalName = in.nextLine();
        System.out.println("Please enter the current date");
        String date = in.nextLine();
        System.out.println("Enter any notes, ex: when I want to complete it, where, why, with etc :)");
        String notes = in.nextLine();
        String experience = "";
        Goal goal = new Goal(goalName, notes, date, experience);
        goal.setNotes(notes);
        goal.setGoal(goalName);
        goal.setDate(date);
        goal.setExperience(experience);
        list.addGoal(goal);
        System.out.println("Would you like to add another? (yes or no)");
        String yesNo = in.nextLine();

        if (Objects.equals(yesNo, "yes")) {
            caseA();
        } else {
            caseC();
        }
    }

    //REQUIRES: a non-empty list
    //MODIFIES: Goal, List
    //EFFECTS: removes chosen goal object from list class
    public void caseB() {
        System.out.println(list.getBucketList());

        if (list.getNumberOfItemsInList() == 0) {
            System.out.println("No items to remove :( Please add an item first!");
            yesNo();
        }

        System.out.println("Select the item you would like to remove from your list "
                + "(enter the number next to the goal)");
        int i = in.nextInt();
        list.setAnInt(i);
        list.getGoalItemToRemove();
        scannerIssueFix();
        System.out.println("Would you like to remove another? (yes or no)");
        String yesNo = in.nextLine();

        if (Objects.equals(yesNo, "yes")) {
            caseB();
        } else {
            caseC();
        }
    }

    //EFFECTS: prints updated list
    public void caseC() {
        System.out.println("Here is your updated list:\n" + name + "'s BucketList! "
                + list.getNumberOfItemsInList() + " items in list");
        System.out.println(list.getBucketList());
        yesNo();
    }

    //REQUIRES: index exists && list.getNumberOfItemsInList() > 0 && list item isn't already checked off
    //MODIFIES: Goal, List
    //EFFECTS: modifies goal object's name to reflect new user input that "checks off" item from list
    // also modifies experience parameter of the goal object
    public void caseD() {
        if (list.getNumberOfItemsInList() == 0) {
            System.out.println("No items to check off :( Please add an item first!");
            yesNo();
        }

        System.out.println(list.getBucketList());
        System.out.println("Select the item you would like to check off on your list");
        int i = in.nextInt();
        list.setAnInt(i);
        scannerIssueFix();

        if (list.getNumberOfItemsInList() <= i - 1) {
            System.out.println("list item doesn't exist");
            yesNo();
        } else if (!(list.checkExperience().equals(""))) {
            System.out.println("item already checked off");
            yesNo();
        }
        caseDQuestions();
        String yesNo = in.nextLine();

        if (Objects.equals(yesNo, "yes")) {
            caseD();
        } else {
            caseC();
        }
    }

    //MODIFIES: Goal
    //EFFECTS: Sets date completed and experience notes, modifies goal object being "checked off"
    public void caseDQuestions() {
        System.out.println("Enter the date completed :)");
        String dateCompleted = in.nextLine();
        list.setDateCompleted(dateCompleted);
        System.out.println("What was your experience like?");
        String experienceNotes = in.nextLine();
        list.setExperienceNotes(experienceNotes);
        list.getGoalItemToCheck();
        System.out.println("Would you like to check off another? (yes or no)");
    }


    //EFFECTS: Either ends program or goes back to menu based on user input
    public void yesNo() {
        System.out.println("Would you like to go back to the main menu?");
        String yesOrNo = in.nextLine();

        if (Objects.equals(yesOrNo, "yes")) {
            menu();
        } else {
            exit();
        }
    }

    //EFFECTS: Stops scanner from reading previous input if called in a method
    public void scannerIssueFix() {
        in.nextLine();
    }

    //EFFECTS: terminates program
    public void exit() {
        System.out.println("Goodbye!");
        System.exit(1);
    }
}


