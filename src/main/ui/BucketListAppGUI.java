package ui;

import model.BucketList;
import model.Goal;
import model.Suggestion;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static javax.swing.JOptionPane.QUESTION_MESSAGE;

public class BucketListAppGUI implements ActionListener {
    protected String jsonStore;
    String name;
    BucketList bucketL = new BucketList();
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/uuuu");
    LocalDate localDate = LocalDate.now();
    String date = dateFormatter.format(localDate);

    DefaultListModel<String> listModel;

    JList<String> stringJList1 = new JList<>();
    JList<String> stringJList2 = new JList<>();

    private JFrame frame;
    private JPanel mainMenu;
    private JPanel displayList;
    private JPanel removeBucketListItem;
    private JPanel addItemToList;
    private JPanel checkOffListItem;
    private JPanel addSuggestion;
    private JPanel welcome;

    Suggestion suggestion = new Suggestion();

    private JButton addItem;
    private JButton removeItem;
    private JButton checkOff;
    private JButton saveList;
    private JButton loadList;
    private JButton getSuggestion;
    private JButton mainMenuButton;

    private JTextField textName;
    private JTextArea textGoal;
    private JTextArea textNotes;
    private JTextField textNotes2;
    private String dateCompleted;
    private String experienceNotes;


    //MODIFIES: this
    //EFFECTS: Constructor initializes the main frame for the GUI,
    // and calls on the methods to initialize all the action panels and the welcome panel
    public BucketListAppGUI() {
        frame = new JFrame("Bucket List App");
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
        initializeAllPanels();
        createWelcomePanel();

    }

    //MODIFIES: this
    //EFFECTS: creates the welcome panel, takes a user's input for name,
    // once user presses ok button triggers action
    public void createWelcomePanel() {
        welcome = new JPanel();
        welcome.setSize(500, 500);
        frame.add(welcome, BorderLayout.CENTER);
        welcome.setBackground(Color.pink);
        welcome.setVisible(true);
        welcome.add(new JLabel(("Welcome to the Bucket List App!")));
        welcome.add(new JLabel("Please enter your name: "));
        textName = new JTextField(15);
        welcome.add(textName);
        JButton ok = new JButton("ok");
        welcome.add(new JLabel(new ImageIcon("./data/helloImage.jpeg")));
        ok.addActionListener(this);
        ok.setActionCommand("ok2");
        welcome.add(ok);
    }

    //MODIFIES: this
    //EFFECTS: initializes and creates all components
    // which are added to the main menu panel
    public void initializeMainMenu() {
        welcome.setVisible(false);
        mainMenu = new JPanel();
        mainMenu.setSize(500, 500);
        frame.add(mainMenu, BorderLayout.CENTER);
        BoxLayout boxLayout = new BoxLayout(mainMenu, BoxLayout.Y_AXIS);
        mainMenu.setLayout(boxLayout);
        mainMenu.setBorder(new EmptyBorder(new Insets(150, 150, 150, 150)));
        mainMenu.setBackground(Color.pink);
        mainMenu.add(new JLabel(("Main Menu Options")));
        mainMenu.setVisible(true);
        createMenuButtons();
        addActionToButton();
        addButtons(addItem, removeItem, checkOff, saveList, loadList, getSuggestion);
    }

    //MODIFIES: this
    //EFFECTS: intializes all panels
    public void initializeAllPanels() {
        addItemToList = new JPanel();
        displayList = new JPanel();
        addSuggestion = new JPanel();
        removeBucketListItem = new JPanel();
        checkOffListItem = new JPanel();
    }

    //MODIFIES: this
    //EFFECTS: creates the main menu button and sets its action command
    public void createMainMenuButton() {
        mainMenuButton = new JButton("Main menu");
        mainMenuButton.addActionListener(this);
        mainMenuButton.setActionCommand("mainMenu");
    }

    //MODIFIES: this
    //EFFECTS: initializes menu buttons
    private void createMenuButtons() {
        addItem = new JButton("Add Item");
        removeItem = new JButton("Remove Item");
        checkOff = new JButton("Check Off Item");
        saveList = new JButton("Save List");
        loadList = new JButton("Load Existing List");
        getSuggestion = new JButton("Get Suggestion");
    }

    //MODIFIES: this
    //EFFECTS: sets the look of the menu buttons and adds them as a pack
    public void addButton(JButton button, JPanel panel) {
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBackground(Color.white);
        panel.add(button);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //frame.setResizable(false);
    }

    //EFFECTS: passes the different buttons
    // to be added onto mainMenu JPanel
    public void addButtons(JButton addItem, JButton removeItem, JButton checkOff, JButton saveList,
                           JButton loadList, JButton getSuggestion) {
        addButton(addItem, mainMenu);
        addButton(removeItem, mainMenu);
        addButton(checkOff, mainMenu);
        addButton(saveList, mainMenu);
        addButton(loadList, mainMenu);
        addButton(getSuggestion, mainMenu);

    }

    //MODIFIES: this
    //EFFECTS: adds action commands to different menu buttons
    public void addActionToButton() {
        addItem.addActionListener(this);
        addItem.setActionCommand("Add");
        removeItem.addActionListener(this);
        removeItem.setActionCommand("Remove");
        checkOff.addActionListener(this);
        checkOff.setActionCommand("Check");
        saveList.addActionListener(this);
        saveList.setActionCommand("Save");
        loadList.addActionListener(this);
        loadList.setActionCommand("Load");
        getSuggestion.addActionListener(this);
        getSuggestion.setActionCommand("Suggestion");

    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    @Override
    //EFFECTS: based on action, triggered calls method to handle action
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        switch (action) {
            case "Add":
                addAction();
                break;
            case "Remove":
                removeAction();
                break;
            case "Check":
                checkAction();
                break;
            case "Save":
                saveAction();
                break;
            case "Load":
                loadAction();
                break;
            case "Suggestion":
                suggestionAction();
                break;
            case "ok":
                okActionAddsGoal();
                break;
            case "ok2":
                ok2Action();
                break;
            case "mainMenu":
                mainMenuAction();
                break;
            case "no":
                initializeMainMenu();
                break;
            case "yes":
                addSuggestionToList();
                break;
            case "ok3":
                ok3Action();
                break;
        }
    }

    //MODIFIES: this
    //EFFECTS: is called when add item button is pressed
    // shows instructions for how to add a goak and then calls on addGoals function
    public void addAction() {
        if (bucketL.getNumberOfItemsInList() == 0) {
            JOptionPane.showMessageDialog(mainMenu, "Enter the title of the goal you want to achieve, "
                    + "\nclick suggestion in main menu to get suggestions "
                    + "\nNotes ex: when I want to complete it,"
                    + " \nwhere, why, with etc :)", "Instructions", JOptionPane.INFORMATION_MESSAGE);
        }
        addGoals();
    }

    //EFFECTS: calls on the actions for the remove button
    public void removeAction() {
        removeGoals();
    }

    //EFFECTS: calls on the actions connected to the check off buttons
    public void checkAction() {
        checkGoals();
    }

    //EFFECTS:  calls on the actions connected to the save list button
    public void saveAction() {
        saveGoals();
    }

    //EFFECTS: calls function for when load button is pressed
    public void loadAction() {
        loadGoals();
    }

    //EFFECTS: calls function for when suggestion is pressed
    public void suggestionAction() {
        getGoalSuggestions();
    }

    //MODIFIES: this
    //EFFECTS: adds user input goal to list
    public void okActionAddsGoal() {
        JOptionPane.showMessageDialog(addItemToList, "'"
                + textGoal.getText() + "'" + " Was added to your list");
        String goalName = textGoal.getText();
        String notes = textNotes.getText();
        String name = textName.getText();
        String experience = "";
        Goal goal = new Goal(goalName, notes, date, experience);
        goal.setGoal(goalName);
        goal.setNotes(notes);
        goal.setDate(date);
        goal.setExperience(experience);
        goal.setName(name);
        bucketL.addGoal(goal);
        displayList();
    }

    //MODIFIES: this
    //EFFECTS: sets name field to user input, displays welcome message
    // and calls function to initialize main menu
    public void ok2Action() {
        String name = textName.getText();
        bucketL.setName(name);
        JOptionPane.showMessageDialog(welcome, "Hi " + bucketL.getName() + " let's get Started");
        initializeMainMenu();
    }

    //MODIFIES: this
    //EFFECTS: action to help add suggestion user wanted to, to list and displays it
    public void ok3Action() {
        setAddSuggestion();
        addSuggestion.setVisible(false);
        displayList();
    }

    //MODIFIES: this
    //EFFECTS: Sets visibility to false for all other panels except mainMenu
    public void mainMenuAction() {
        removeBucketListItem.setVisible(false);
        displayList.setVisible(false);
        checkOffListItem.setVisible(false);
        mainMenu.setVisible(true);
        addSuggestion.setVisible(false);
    }

    //MODIFIES: this
    //EFFECTS: sets visibility for irrelevant panels to false and sets up addItemToList panel
    public void setAddGoals() {
        addItemToList = new JPanel();
        addItemToList.setSize(500, 500);
        mainMenu.setVisible(false);
        displayList.setVisible(false);
        checkOffListItem.setVisible(false);
        removeBucketListItem.setVisible(false);
        addSuggestion.setVisible(false);
        frame.add(addItemToList, BorderLayout.CENTER);
        addItemToList.setVisible(true);
        addItemToList.setBackground(Color.pink);
    }

    //MODIFIES: this
    //EFFECTS: gets user inout for the goal and notes
    public void addGoals() {
        setAddGoals();
        JLabel goal = new JLabel("Enter a goal:");
        addItemToList.add(goal);
        textGoal = new JTextArea();
        textGoal.setLineWrap(true);
        addItemToList.add(textGoal);
        JLabel notes = new JLabel("Enter any notes:");
        addItemToList.add(notes);
        textNotes = new JTextArea();
        textNotes.setLineWrap(true);
        addItemToList.add(textNotes);
        BoxLayout boxLayout = new BoxLayout(addItemToList, BoxLayout.Y_AXIS);
        addItemToList.setLayout(boxLayout);
        newOkButton();
    }

    //EFFECTS: adds action command to ok button and adds it to addItemToList panel
    public void newOkButton() {
        JButton ok = new JButton("ok");
        ok.addActionListener(this);
        ok.setActionCommand("ok");
        addItemToList.add(ok);
    }

    //MODIFIES: this
    //EFFECTS: sets irrelevant panels visibility to false and sets up the display list panel
    public void displayList() {
        removeBucketListItem.setVisible(false);
        addSuggestion.setVisible(false);
        mainMenu.setVisible(false);
        addItemToList.setVisible(false);
        checkOffListItem.setVisible(false);
        displayList = new JPanel();
        displayList.setSize(500, 500);
        frame.add(displayList, BorderLayout.CENTER);
        displayList.setVisible(true);
        displayList.setBackground(Color.pink);
        BoxLayout boxLayout = new BoxLayout(displayList, BoxLayout.Y_AXIS);
        displayList.setLayout(boxLayout);
        displayListTwo();
    }

    //MODIFIES: this
    //EFFECTS: adds components to the displayList panel
    public void displayListTwo() {
        JLabel bucketListDisplay = new JLabel("Here is your updated Bucket List!");
        bucketListDisplay.setFont(new Font("ComicSans", Font.BOLD, 25));
        displayList.add(bucketListDisplay);
        JTextArea listOfGoals = new JTextArea();
        listOfGoals.setBackground(Color.pink);
        listOfGoals.setText(bucketL.getBucketList());
        listOfGoals.setFont(new Font("ComicSans", Font.BOLD, 15));
        displayList.add(listOfGoals);
        JScrollPane scroll = new JScrollPane(listOfGoals, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        displayList.add(scroll);
        createMainMenuButton();
        displayList.add(mainMenuButton);
    }

    //MODIFIES: this
    //EFFECTS: creates the removeBucketListItem JPanel and its components
    public void removeGoals() {
        removeBucketListItem = new JPanel();
        removeBucketListItem.setSize(500, 500);
        mainMenu.setVisible(false);
        addItemToList.setVisible(false);
        displayList.setVisible(false);
        addSuggestion.setVisible(false);
        checkOffListItem.setVisible(false);
        frame.add(removeBucketListItem, BorderLayout.CENTER);
        removeBucketListItem.setVisible(true);
        removeBucketListItem.setBackground(Color.pink);
        JLabel remove = new JLabel("Select the goal you would like to remove");
        remove.setFont(new Font("ComicSans", Font.BOLD, 15));
        removeBucketListItem.add(remove);
        BoxLayout boxLayout = new BoxLayout(removeBucketListItem, BoxLayout.Y_AXIS);
        removeBucketListItem.setLayout(boxLayout);
        removeFunction();
    }

    //MODIFIES: this
    //EFFECTS: creates the checkOffListItem JPanel and its components
    public void checkGoals() {
        mainMenu.setVisible(false);
        addSuggestion.setVisible(false);
        displayList.setVisible(false);
        addItemToList.setVisible(false);
        removeBucketListItem.setVisible(false);
        checkOffListItem = new JPanel();
        checkOffListItem.setSize(500, 500);
        frame.add(checkOffListItem, BorderLayout.CENTER);
        checkOffListItem.setVisible(true);
        checkOffListItem.setBackground(Color.pink);
        JLabel check = new JLabel("double click the item you would like to check off");
        check.setFont(new Font("ComicSans", Font.BOLD, 15));
        checkOffListItem.add(check);
        checkOffFunction();
    }

    //MODIFIES: this
    //EFFECTS: saves list to jsonStore
    public void saveGoals() {
        File file = new File("./data/" + bucketL.getName() + "bucketList.json");
        jsonStore = "./data/" + bucketL.getName() + "bucketList.json";
        try {
            boolean fileCreated = file.createNewFile();
            if (fileCreated) {
                System.out.println("new file created");
            }
            JsonWriter jsonWriter = new JsonWriter(jsonStore);
            jsonWriter.open();
            jsonWriter.write(bucketL);
            jsonWriter.close();
            JOptionPane.showMessageDialog(mainMenu, "Saved " + bucketL.getName() + "'s Bucket List to " + jsonStore);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(mainMenu, "Unable to write to file: " + jsonStore);
        } catch (IOException e) {
            e.printStackTrace();
        }
        displayList();
    }

    //REQUIRES: file exists to read from
    //MODIFIES: this
    //EFFECTS: loads list from jsonStore
    public void loadGoals() {
        jsonStore = "./data/" + bucketL.getName() + "bucketList.json";
        JsonReader jsonReader = new JsonReader(jsonStore);
        if (jsonStore.contains(bucketL.getName())) {
            try {
                bucketL = jsonReader.read();
                JOptionPane.showMessageDialog(mainMenu,"Loaded " + bucketL.getName()
                        + "'s Bucket List from" + jsonStore);
            } catch (IOException e) {
                name = JOptionPane.showInputDialog("Unable to read from file: " + jsonStore
                        + "\nfile may not exist, try typing in your name again: ");
                bucketL.setName(name);
                loadGoals();
            }
            displayList();
        }
    }

    //MODIFIES: this
    //EFFECTS: displays a random suggestion to user, asks whether
    // they want to add to their list (yes/no)
    public void getGoalSuggestions() {
        suggestion.chooseRandomSuggestion();
        int yesNO = JOptionPane.showConfirmDialog(mainMenu, "Add "
                        + suggestion.getRandomSuggestion()
                        + " to your bucket list?",
                null, JOptionPane.YES_NO_OPTION, QUESTION_MESSAGE);

        if (yesNO == JOptionPane.YES_OPTION) {
            addSuggestionToList();
        }
    }

    //MODIFIES: this
    //EFFECTS: allows user to enter notes about the suggestion
    // goal they decided to add to their list
    public void addSuggestionToList() {
        addSuggestion = new JPanel();
        addSuggestion.setSize(500, 500);
        mainMenu.setVisible(false);
        addItemToList.setVisible(false);
        removeBucketListItem.setVisible(false);
        displayList.setVisible(false);
        checkOffListItem.setVisible(false);
        frame.add(addSuggestion, BorderLayout.CENTER);
        addSuggestion.setVisible(true);
        addSuggestion.setBackground(Color.pink);

        addSuggestion.add(new JLabel("Enter any notes, ex: when I want to complete it, where, why, with etc"));
        textNotes2 = new JTextField(15);
        addSuggestion.add(textNotes2);

        JButton ok = new JButton("ok");
        ok.addActionListener(this);
        ok.setActionCommand("ok3");
        addSuggestion.add(ok);
        BoxLayout boxLayout = new BoxLayout(addSuggestion, BoxLayout.Y_AXIS);
        addItemToList.setLayout(boxLayout);
    }

    //MODIFIES: this
    //EFFECTS: sets the suggestion as a new goal and adds it to the bucket list
    public void setAddSuggestion() {
        String goalName = suggestion.getRandomSuggestion();
        String notes = textNotes2.getText();
        String experience = "";
        Goal goal = new Goal(goalName, notes, date, experience);
        goal.setGoal(goalName);
        goal.setNotes(notes);
        goal.setDate(date);
        goal.setExperience(experience);
        bucketL.addGoal(goal);
    }

    //MODIFIES: this
    //EFFECTS: displays all the bucketList items in a Jlist
    public void removeFunction() {
        if (bucketL.getNumberOfItemsInList() == 0) {
            JOptionPane.showMessageDialog(removeBucketListItem, "No items to remove :( Please add an item first!");
            mainMenuAction();
        }
        DefaultListModel<String> listModel2 = new DefaultListModel<>();
        for (int i = 0; i < bucketL.getNumberOfItemsInList(); i++) {
            int listItem = i + 1;
            String listValue = "\n#" + listItem + " Date: "
                    + bucketL.getList().get(i).getDate() + ("\n")
                    + (bucketL.getList().get(i).getGoal()) + ("\n")
                    + ("Notes: ") + (bucketL.getList().get(i)
                    .getNotes()) + ("\n")
                    + (bucketL.getList().get(i).getExperience()) + ("\n");
            listModel2.addElement(listValue);
        }
        stringJList1.setModel(listModel2);
        removeFunctionTwo();
    }

    //MODIFIES: this
    //EFFECTS: adds the list of items to be displayed to a JList where if a user
    // double-clicks an item it is removed from the bucket list
    public void removeFunctionTwo() {
        stringJList1.setFont(new Font("ComicSans", Font.BOLD, 15));
        stringJList1.setSelectedIndex(0);
        removeBucketListItem.add(stringJList1);
        JScrollPane scroll = new JScrollPane(stringJList1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        removeBucketListItem.add(scroll);
        stringJList1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JList target = (JList) e.getSource();
                    int index = target.locationToIndex(e.getPoint());
                    bucketL.setAnInt(index + 1);
                    if (index != -1) {
                        Object item = target.getModel().getElementAt(index);
                        JOptionPane.showMessageDialog(removeBucketListItem, "removed: " + item.toString());
                        bucketL.getGoalItemToRemove();
                        displayList();
                    }
                }
            }
        });
    }

    //EFFECTS: checks whether the bucketList has any items to check off,
    // if it doesn't shows message and returns to main menu
    public void ifConditionForCheckOff() {
        if (bucketL.getNumberOfItemsInList() == 0) {
            JOptionPane.showMessageDialog(checkOffListItem, "No items to check off :( Please add an item first!");
            mainMenuAction();
        }
    }

    //MODIFIES: this
    //EFFECTS: if the items in the bucket list are not checked off already they are displayed
    //so user can check them off
    public void checkOffFunction() {
        listModel = new DefaultListModel<>();
        ifConditionForCheckOff();
        for (int i = 0; i < bucketL.getNumberOfItemsInList(); i++) {
            int listItem = i + 1;
            if (Objects.equals(bucketL.getList().get(i).getExperience(), "")) {
                String listValue = "\n#" + listItem + " Date: "
                        + bucketL.getList().get(i).getDate() + ("\n")
                        + (bucketL.getList().get(i).getGoal()) + ("\n")
                        + ("Notes: ") + (bucketL.getList().get(i)
                        .getNotes()) + ("\n")
                        + (bucketL.getList().get(i).getExperience()) + ("\n");
                listModel.addElement(listValue);
            }
        }
        stringJList2.setModel(listModel);
        stringJList2.setFont(new Font("ComicSans", Font.BOLD, 15));
        stringJList2.setSelectedIndex(0);
        checkOffListItem.add(stringJList2);
        JScrollPane scroll = new JScrollPane(stringJList2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        checkOffListItem.add(scroll);
        checkOffListItem.add(mainMenuButton);
        checkOffListAction();
    }

    //MODIFIES: this
    //EFFECTS: Modifies the goal clicked on to reflect that it has been
    // "checked off" and takes in user input for date completed as well as experience
    public void checkOffListAction() {
        stringJList2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JList target = (JList) e.getSource();
                    int index = target.locationToIndex(e.getPoint());
                    bucketL.setAnInt(index + 1);
                    if (index != -1) {
                        Object item = target.getModel().getElementAt(index);
                        dateCompleted = JOptionPane.showInputDialog(checkOffListItem,"Enter the date completed");
                        bucketL.setDateCompleted(dateCompleted);
                        experienceNotes = JOptionPane.showInputDialog(checkOffListItem,"How was the experience?");
                        bucketL.setExperienceNotes(experienceNotes);
                        JOptionPane.showMessageDialog(checkOffListItem, "Marked completed: " + item.toString());
                        bucketL.getGoalItemToCheck();
                        checkOffListItem.add(mainMenuButton);
                        displayList();
                    }
                }
            }
        });
    }
}

