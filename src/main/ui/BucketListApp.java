package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;

//Creates new List object which stores multiple goal objects
//the user creates with the scanner object, and is able to modify
//by through removing or checking goals as completed
public class BucketListApp {

    private String jsonStore;
    //= "./data/bucketList.json";
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/uuuu");
    private final LocalDate localDate = LocalDate.now();
    private final Suggestion suggestion = new Suggestion();
    private final Scanner in;
    private BucketList list;
    private String name;
    private final String date = dateFormatter.format(localDate);

    //EFFECTS: constructor calls first method to execute
    public BucketListApp() {
        in = new Scanner((System.in));
        list = new BucketList();
        welcomeLine();
    }

    //EFFECTS: takes in name from user
    public void welcomeLine() {
        System.out.println("Hello! Welcome to The Bucket List! Please enter your name"
                + " \nif you are a returning user please enter your name exactly as you did before");
        name = in.nextLine();
        list.setName(name);
        menu();
    }

    //EFFECTS: displays the menu options to user
    public void displayMenu() {
        System.out.println("So, " + list.getName() + " what would you like to do?");
        System.out.println("a. add item \n" + "b. remove item \n"
                + "c. view list \n" + "d. check off item \n"
                + "e. save list\n" + "f. load existing list\n"
                + "g. get a suggestion\n" + "x. exit");
    }

    //REQUIRES: input = a | b | c | d | x
    //EFFECTS: calls method user chooses, throws exception if invalid input
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void menu() {
        displayMenu();
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
            case "e"  :
                caseE();
                break;
            case "f"  :
                caseF();
                break;
            case "g"  :
                caseG();
                break;
            case "x":
                exit();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + in.nextLine());
        }
    }

    //MODIFIES: this
    //EFFECTS: creates goal object and adds it to list class
    public void caseA() {
        System.out.println("Please type in what goal you would like to add to your bucket list!");
        String goalName = in.nextLine();
        System.out.println("Enter any notes, ex: when I want to complete it, where, why, with etc :)");
        String notes = in.nextLine();
        String experience = "";
        Goal goal = new Goal(goalName, notes, date, experience);
        goal.setNotes(notes);
        goal.setGoal(goalName);
        goal.setDate(date);
        goal.setExperience(experience);
        goal.setName(list.getName());
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
    //MODIFIES: this
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
        System.out.println("Here is your updated list:\n" + list.getName() + "'s BucketList! "
                + list.getNumberOfItemsInList() + " items in list");
        System.out.println(list.getBucketList());
        yesNo();
    }

    //REQUIRES: index exists && list.getNumberOfItemsInList() > 0 && list item isn't already checked off
    //MODIFIES: this
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

    //MODIFIES: this
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

    //MODIFIES: this
    //EFFECTS: saves list to JSON_STORE
    public void caseE() {
        File file = new File("./data/" + list.getName() + "bucketList.json");
        jsonStore = "./data/" + list.getName() + "bucketList.json";
        try {
            boolean fileCreated = file.createNewFile();
            if (fileCreated) {
                System.out.println("new file created");
            }
            JsonWriter jsonWriter = new JsonWriter(jsonStore);
            jsonWriter.open();
            jsonWriter.write(list);
            jsonWriter.close();
            System.out.println("Saved " + list.getName() + "'s Bucket List to " + jsonStore);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + jsonStore);
        } catch (IOException e) {
            e.printStackTrace();
        }
        caseC();
    }

    //REQUIRES: JSON_STORE not empty
    //MODIFIES: this
    //EFFECTS: loads existing list data if saved previously
    public void caseF() {
        jsonStore = "./data/" + list.getName() + "bucketList.json";
        JsonReader jsonReader = new JsonReader(jsonStore);
        if (jsonStore.contains(list.getName())) {
            try {
                list = jsonReader.read();
                System.out.println("Loaded " + list.getName() + "'s Bucket List from" + jsonStore);
            } catch (IOException e) {
                System.out.println("Unable to read from file: " + jsonStore
                        + "\nfile may not exist, try typing in your name again: ");
                name = in.nextLine();
                list.setName(name);
                caseF();
            }
            caseC();
        }
    }

    //MODIFIES: this
    //EFFECTS: presents a random suggestion to potentially add to bucket list,
    // if user decides to add the suggestion, it gets added to the bucket list
    // if not goes back to main menu
    public void caseG() {
        System.out.println("Here is a suggestion to add to your bucket list: ");
        suggestion.chooseRandomSuggestion();
        System.out.println(suggestion.getRandomSuggestion());
        System.out.println("Would you like to add this to your bucket list?");
        String yesNo = in.nextLine();
        if (Objects.equals(yesNo, "yes")) {
            String goalName = suggestion.getRandomSuggestion();
            System.out.println("Enter any notes, ex: when I want to complete it, where, why, with etc :)");
            String notes = in.nextLine();
            String experience = "";
            Goal goal = new Goal(goalName, notes, date, experience);
            goal.setNotes(notes);
            goal.setGoal(goalName);
            goal.setDate(date);
            goal.setExperience(experience);
            list.addGoal(goal);
            caseC();
        } else {
            menu();
        }

    }

    //EFFECTS: Either ends program or goes back to menu based on user input
    public void yesNo() {
        System.out.println("Would you like to go back to the main menu? (yes or no)");
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


