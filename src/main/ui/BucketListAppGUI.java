package ui;

import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import static java.awt.Component.CENTER_ALIGNMENT;
import static java.awt.Font.BOLD;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;

//Graphic interface which Creates new List object which stores multiple goal objects
//the user creates with their input, and is able to modify
//by through removing or checking goals as completed
public class BucketListAppGUI implements ActionListener {
    protected String jsonStore;
    private String name;
    BucketList bucketL = new BucketList();
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/uuuu");
    LocalDate localDate = LocalDate.now();
    String date = dateFormatter.format(localDate);
    JOptionPane kk;

    DefaultListModel<String> listModel;

    JList<String> stringJList1;
    JList<String> stringJList2;

    private final JFrame frame;
    private JPanel mainMenu;
    private JPanel displayList;
    private JPanel removeBucketListItem;
    private JPanel addItemToList;
    private JPanel checkOffListItem;
    private JPanel addSuggestion;
    private JPanel welcome;
    private Color backgroundColor = new Color(239, 177, 224, 255);

    Suggestion suggestion = new Suggestion();

    private JButton addItem;
    private JButton displayListButton;
    private JButton removeItem;
    private JButton checkOff;
    private JButton saveList;
    private JButton loadList;
    private JButton getSuggestion;
    private JButton mainMenuButton;
    private JButton newUser;

    private JLabel welcomeLabel;
    private JLabel getName;
    private JLabel enterPassword;
    private JLabel enterUsername;
    protected JTextField textName;

    private ImageIcon imageIcon;

    private JTextArea textGoal;
    private JTextArea textNotes;
    private JTextField textNotes2;
    private JTextField dateCompleted;
    private JTextField experienceNotes;
    private JTextField textUserName;
    private JTextField textPassword;
    private String dateCompletedString;
    private String experienceNotesString;


    //MODIFIES: this
    //EFFECTS: Constructor initializes the main frame for the GUI,
    // and calls on the methods to initialize all the action panels and the welcome panel
    //Window listener before quitting application prints out the collection stored in Event log,
    // which keeps track of all events related to the bucket list, to console
    public BucketListAppGUI() {
        frame = new JFrame("Bucket List App");
        //frame.setLocationRelativeTo(null);
        WindowListener windowListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (bucketL.getList().size() > 0) {
                    int yesNO = JOptionPane.showConfirmDialog(frame, "Would you like to save your list before you go?",
                            "Goodbye!", JOptionPane.YES_NO_OPTION, QUESTION_MESSAGE, imageIcon);

                    if (yesNO == JOptionPane.YES_OPTION) {
                        saveAction();
                    }
                }
                EventLog el = EventLog.getInstance();
                    for (Event next : el) {
                        System.out.println(next.toString() + "\n\n");
                    }
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        };
        frame.addWindowListener(windowListener);
        frame.setSize(600, 600);
        initializeAllPanels();
        frame.setVisible(true);
    }


    //MODIFIES: this
    //EFFECTS: initializes the textFields for the welcomePanel
    public void initializeTextFieldsForWelcome() {
        textName = new JTextField(15);
        textName.setSize(25, 20);
        textName.setPreferredSize(new Dimension(25,15));
        textName.setMaximumSize(textName.getPreferredSize());
        textName.setAlignmentX(CENTER_ALIGNMENT);
        textUserName = new JTextField(15);
        textUserName.setSize(25, 20);
        textUserName.setPreferredSize(new Dimension(25,15));
        textUserName.setMaximumSize(textName.getPreferredSize());
        textUserName.setAlignmentX(CENTER_ALIGNMENT);
        textPassword = new JTextField(15);
        textPassword.setSize(25, 20);
        textPassword.setPreferredSize(new Dimension(25,15));
        textPassword.setMaximumSize(textName.getPreferredSize());
        textPassword.setAlignmentX(CENTER_ALIGNMENT);
        welcome.add(textName);
    }

    //MODIFIES: this
    //EFFECTS: initializes new JPanel and adds components
    public void initializeWelcome() {
        welcome.setVisible(false);
        welcome = new JPanel();
        frame.add(welcome, BorderLayout.CENTER);
        welcome.setSize(600, 600);
        welcome.setBackground(backgroundColor);
        welcomeLabel = new JLabel("Welcome to");
        welcomeLabel.setForeground(new Color(56, 13, 103, 153));
        getName = new JLabel("Please enter your name: ");
        getName.setForeground(new Color(56, 13, 103, 153));
        enterUsername = new JLabel("Please enter your userName: ");
        enterUsername.setForeground(new Color(56, 13, 103, 153));
        enterUsername.setFont(new Font("ComicSans", BOLD, 16));
        enterPassword = new JLabel("Please enter your password: ");
        enterPassword.setForeground(new Color(56, 13, 103, 153));
        enterPassword.setFont(new Font("ComicSans", BOLD, 16));
        getName.setFont(new Font("ComicSans", BOLD, 16));
        welcomeLabel.setFont(new Font("ComicSans", BOLD, 25));
        welcomeLabel.setAlignmentX(CENTER_ALIGNMENT);
        welcome.add(welcomeLabel);
        enterPassword.setAlignmentX(CENTER_ALIGNMENT);
        enterUsername.setAlignmentX(CENTER_ALIGNMENT);

    }

    //MODIFIES: this
    //EFFECTS: creates the welcome panel, takes a user's input for name,
    // once user presses ok button triggers action
    public void createWelcomePanel() {
        initializeWelcome();
        ImageIcon imageIcon = new ImageIcon("./data/bucketListPhoto.jpeg");
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(400, 300, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setAlignmentX(CENTER_ALIGNMENT);
        welcome.add(imageLabel);
        getName.setAlignmentX(CENTER_ALIGNMENT);
        welcome.add(getName);
        initializeTextFieldsForWelcome();
        //welcome.add(enterUsername);
        //welcome.add(textUserName);
        //welcome.add(enterPassword);
        //welcome.add(textPassword);
        JButton ok = new JButton("ok");
        newUser = new JButton("new user");
        newUser.addActionListener(this);
        newUser.setActionCommand("newUser");
        ok.setAlignmentX(CENTER_ALIGNMENT);
        newUser.setAlignmentX(CENTER_ALIGNMENT);
        welcome.add(ok);
        //welcome.add(newUser);
        BoxLayout boxLayout = new BoxLayout(welcome, BoxLayout.Y_AXIS);
        welcome.setLayout(boxLayout);
        welcome.setVisible(true);
        ok.addActionListener(this);
        ok.setActionCommand("ok2");
    }

    //MODIFIES: this
    //EFFECTS: initializes and creates all components
    // which are added to the main menu panel
    public void initializeMainMenu() {
        welcome.setVisible(false);
        mainMenu = new JPanel();
        mainMenu.setSize(600, 600);
        frame.add(mainMenu, BorderLayout.CENTER);
        BoxLayout boxLayout2 = new BoxLayout(mainMenu, BoxLayout.Y_AXIS);
        mainMenu.setLayout(boxLayout2);
        mainMenu.setBorder(new EmptyBorder(new Insets(150, 150, 150, 150)));
        mainMenu.setBackground(backgroundColor);
        JLabel mainMenuLabel = new JLabel("Main Menu Options");
        mainMenuLabel.setForeground(new Color(56, 13, 103, 153));
        mainMenuLabel.setFont(new Font("ComicSans", BOLD, 20));
        mainMenu.add(mainMenuLabel);
        mainMenu.setVisible(true);
        createMenuButtons();
        addActionToButton();
        addButtons(addItem, removeItem, checkOff, displayListButton, saveList, loadList, getSuggestion);
    }

    //MODIFIES: this
    //EFFECTS: intializes all panels
    public void initializeAllPanels() {
        welcome = new JPanel();
        mainMenu = new JPanel();
        kk = new JOptionPane();
        addItemToList = new JPanel();
        displayList = new JPanel();
        addSuggestion = new JPanel();
        removeBucketListItem = new JPanel();
        checkOffListItem = new JPanel();
        createWelcomePanel();
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
        displayListButton = new JButton("Look at list");
        removeItem = new JButton("Remove Item");
        checkOff = new JButton("Check Off Item");
        saveList = new JButton("Save List");
        loadList = new JButton("Load Existing List");
        getSuggestion = new JButton("Get Suggestion");
    }

    //MODIFIES: this
    //EFFECTS: sets the look of the menu buttons and adds them as a pack
    public void addButton(JButton button, JPanel panel) {
        button.setFont(new Font("Arial", BOLD, 12));
        button.setBackground(Color.white);
        panel.add(button);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //frame.setResizable(false);
    }

    //EFFECTS: passes the different buttons
    // to be added onto mainMenu JPanel
    public void addButtons(JButton addItem, JButton removeItem, JButton checkOff, JButton displayListButton,
                           JButton saveList, JButton loadList, JButton getSuggestion) {
        addButton(addItem, mainMenu);
        addButton(removeItem, mainMenu);
        addButton(checkOff, mainMenu);
        addButton(displayListButton, mainMenu);
        addButton(saveList, mainMenu);
        addButton(loadList, mainMenu);
        addButton(getSuggestion, mainMenu);

    }

    //MODIFIES: this
    //EFFECTS: adds action commands to different menu buttons
    public void addActionToButton() {
        addItem.addActionListener(this);
        addItem.setActionCommand("Add");
        displayListButton.addActionListener(this);
        displayListButton.setActionCommand("display");
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

    // TA approved override checkstyle
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
            case "display":
                displayList();
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
            JOptionPane.showConfirmDialog(mainMenu, "Enter the title of the goal you want to achieve, "
                    + "\nclick suggestion in main menu to get suggestions "
                    + "\nNotes ex: when I want to complete it,"
                    + " \nwhere, why, with etc :)", "Instructions", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, imageIcon);
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
        JOptionPane.showConfirmDialog(addItemToList, "'"
                + textGoal.getText() + "'" + " Was added to your list", "Added Goal",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, imageIcon);
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
        bucketL.setName(name);
        bucketL.addGoal(goal);
        displayList();
    }

    //MODIFIES: this
    //EFFECTS: creates an image icon of a flower
    public void createImageIconFlower() {
        imageIcon = new ImageIcon("./data/flowerIcon.jpeg");
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
    }

    //MODIFIES: this
    //EFFECTS: sets name field to user input, displays welcome message
    // and calls function to initialize main menu
    public void ok2Action() {
        String username = textUserName.getText();
        String password = textPassword.getText();
//        getUser(username, password);
        createImageIconFlower();
        UIManager uiManager = new UIManager();
        uiManager.put("OptionPane.background()", new ColorUIResource(255, 212, 240));
        uiManager.put("Panel.background", new ColorUIResource(255, 212, 240));
        JOptionPane.showConfirmDialog(welcome, "Hi " + textName.getText()
                + " let's get Started", "Welcome!", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, imageIcon);
        loadGoals();
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
        initializeMainMenu();
        removeBucketListItem.setVisible(false);
        displayList.setVisible(false);
        checkOffListItem.setVisible(false);
        mainMenu.setVisible(true);
        addSuggestion.setVisible(false);
    }

    //MODIFIES: this
    //EFFECTS: sets visibility for irrelevant panels to false and sets up addItemToList panel
    public void setAddGoals() {
        addItemToList.setVisible(false);
        addItemToList = new JPanel();
        addItemToList.setSize(600, 600);
        mainMenu.setVisible(false);
        displayList.setVisible(false);
        checkOffListItem.setVisible(false);
        removeBucketListItem.setVisible(false);
        addSuggestion.setVisible(false);
        frame.add(addItemToList, BorderLayout.CENTER);
        addItemToList.setVisible(true);
        addItemToList.setBackground(backgroundColor);
    }

    //MODIFIES: this
    //EFFECTS: initializes all textArea fields needed for addGoals function
    public void addGoalsInitializeTextAreas() {
        textGoal = new JTextArea();
        textGoal.setLineWrap(true);
        textGoal.setPreferredSize(new Dimension(300,500));
        textGoal.setMaximumSize(textGoal.getPreferredSize());
        textGoal.setAlignmentX(CENTER_ALIGNMENT);
        textNotes = new JTextArea();
        textNotes.setLineWrap(true);
        textNotes.setPreferredSize(new Dimension(300,500));
        textNotes.setMaximumSize(textGoal.getPreferredSize());
        textNotes.setAlignmentX(CENTER_ALIGNMENT);
    }

    //MODIFIES: this
    //EFFECTS: gets user inout for the goal and notes
    public void addGoals() {
        setAddGoals();
        JLabel goal = new JLabel("Enter your goal:");
        goal.setForeground(new Color(56, 13, 103, 153));
        goal.setFont(new Font("ComicSans", BOLD, 20));
        addItemToList.add(goal);
        addGoalsInitializeTextAreas();
        addItemToList.add(textGoal);
        JLabel notes = new JLabel("Enter any notes:");
        notes.setForeground(new Color(56, 13, 103, 153));
        notes.setFont(new Font("ComicSans", BOLD, 20));
        addItemToList.add(notes);
        addItemToList.add(textNotes);
        BoxLayout boxLayout3 = new BoxLayout(addItemToList, BoxLayout.Y_AXIS);
        addItemToList.setLayout(boxLayout3);
        newOkButton();
    }

    //MODIFIES: this
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
        welcome.setVisible(false);
        kk.setVisible(false);
        displayList.setVisible(false);
        removeBucketListItem.setVisible(false);
        addSuggestion.setVisible(false);
        mainMenu.setVisible(false);
        addItemToList.setVisible(false);
        checkOffListItem.setVisible(false);
        displayList = new JPanel();
        displayList.setSize(600, 600);
        frame.add(displayList, BorderLayout.CENTER);
        displayList.setVisible(true);
        displayList.setBackground(backgroundColor);
        BoxLayout boxLayout4 = new BoxLayout(displayList, BoxLayout.Y_AXIS);
        displayList.setLayout(boxLayout4);
        displayListTwo();
    }

    //MODIFIES: this
    //EFFECTS: adds components to the displayList panel
    public void displayListTwo() {
        JLabel bucketListDisplay = new JLabel("Here is your updated Bucket List!");
        bucketListDisplay.setFont(new Font("ComicSans", BOLD, 25));
        bucketListDisplay.setForeground(new Color(56, 13, 103, 153));
        displayList.add(bucketListDisplay);
        JTextArea listOfGoals = new JTextArea();
        listOfGoals.setBackground(backgroundColor);
        listOfGoals.setText(bucketL.getBucketList());
        listOfGoals.setFont(new Font("ComicSans", BOLD, 15));
        listOfGoals.setForeground(new Color(28, 6, 54, 255));
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
        removeBucketListItem.setVisible(false);
        removeBucketListItem = new JPanel();
        removeBucketListItem.setSize(600, 600);
        mainMenu.setVisible(false);
        addItemToList.setVisible(false);
        displayList.setVisible(false);
        addSuggestion.setVisible(false);
        checkOffListItem.setVisible(false);
        frame.add(removeBucketListItem, BorderLayout.CENTER);
        removeBucketListItem.setVisible(true);
        removeBucketListItem.setBackground(backgroundColor);
        JLabel remove = new JLabel("Double click the goal you would like to remove");
        remove.setFont(new Font("ComicSans", BOLD, 15));
        removeBucketListItem.add(remove);
        BoxLayout boxLayout5 = new BoxLayout(removeBucketListItem, BoxLayout.Y_AXIS);
        removeBucketListItem.setLayout(boxLayout5);
        removeFunction();
    }

    //MODIFIES: this
    //EFFECTS: creates the checkOffListItem JPanel and its components
    public void checkGoals() {
        checkOffListItem.setVisible(false);
        mainMenu.setVisible(false);
        addSuggestion.setVisible(false);
        displayList.setVisible(false);
        addItemToList.setVisible(false);
        removeBucketListItem.setVisible(false);
        checkOffListItem = new JPanel();
        checkOffListItem.setSize(600, 600);
        frame.add(checkOffListItem, BorderLayout.CENTER);
        checkOffListItem.setVisible(true);
        checkOffListItem.setBackground(backgroundColor);
        BoxLayout boxLayout6 = new BoxLayout(checkOffListItem, BoxLayout.Y_AXIS);
        checkOffListItem.setLayout(boxLayout6);
        JLabel check = new JLabel("After entering date completed and experience");
        JLabel check2 = new JLabel("Double click the item you would like to check off");
        check.setFont(new Font("ComicSans", BOLD, 15));
        check2.setFont(new Font("ComicSans", BOLD, 15));
        checkOffListItem.add(check);
        checkOffListItem.add(check2);
        checkOffFunction();
    }

    //MODIFIES: this
    //EFFECTS: saves list to jsonStore
    public void saveGoals() {
        File file = new File("./data/" + textName.getText() + "bucketList.json");
        jsonStore = "./data/" + textName.getText() + "bucketList.json";
        try {
            boolean fileCreated = file.createNewFile();
            if (fileCreated) {
                System.out.println("new file created");
            }
            JsonWriter jsonWriter = new JsonWriter(jsonStore);
            jsonWriter.open();
            jsonWriter.write(bucketL);
            jsonWriter.close();
            JOptionPane.showConfirmDialog(mainMenu, "Saved " + bucketL.getName()
                    + "'s Bucket List to " + jsonStore, "Saved", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, imageIcon);
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
        if (bucketL.getName() == null) {
            bucketL.setName(textName.getText());
        }
        jsonStore = "./data/" + bucketL.getName() + "bucketList.json";
        JsonReader jsonReader = new JsonReader(jsonStore);
        if (jsonStore.contains(bucketL.getName())) {
            try {
                BucketList bucketL2 = jsonReader.read();
                for (int intI = 0; intI < bucketL.getList().size(); intI++) {
                    bucketL2.addGoal(bucketL.getList().get(intI));
                }
                bucketL = bucketL2;
                JOptionPane.showConfirmDialog(mainMenu,"Loaded " + bucketL.getName()
                        + "'s Bucket List from" + jsonStore, "Loaded", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, imageIcon);
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
                "Goal suggestion", JOptionPane.YES_NO_OPTION, QUESTION_MESSAGE, imageIcon);

        if (yesNO == JOptionPane.YES_OPTION) {
            addSuggestionToList();
        }
    }

    //MODIFIES: this
    //EFFECTS: allows user to enter notes about the suggestion
    // goal they decided to add to their list
    public void addSuggestionToList() {
        addSuggestion.setVisible(false);
        addSuggestion = new JPanel();
        addSuggestion.setSize(600, 600);
        welcome.setVisible(false);
        mainMenu.setVisible(false);
        addItemToList.setVisible(false);
        removeBucketListItem.setVisible(false);
        displayList.setVisible(false);
        checkOffListItem.setVisible(false);
        frame.add(addSuggestion, BorderLayout.CENTER);
        addSuggestion.setVisible(true);
        addSuggestion.setBackground(backgroundColor);

        addSuggestion.add(new JLabel("Enter any notes, ex: when "
                + "I want to complete it, where, why, with etc"));
        textNotes2 = new JTextField(15);
        addSuggestion.add(textNotes2);

        JButton ok = new JButton("ok");
        ok.addActionListener(this);
        ok.setActionCommand("ok3");
        addSuggestion.add(ok);
        BoxLayout boxLayout7 = new BoxLayout(addSuggestion, BoxLayout.Y_AXIS);
        addSuggestion.setLayout(boxLayout7);
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
        goal.setName(name);
        bucketL.setName(name);
        bucketL.addGoal(goal);
    }

    //MODIFIES: this
    //EFFECTS: displays all the bucketList items in a Jlist
    public void removeFunction() {
        if (bucketL.getNumberOfItemsInList() == 0) {
            JOptionPane.showConfirmDialog(removeBucketListItem,
                    "No items to remove :( Please add an item first!", "Empty List",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, imageIcon);
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
        stringJList1 = new JList<>();
        stringJList1.setModel(listModel2);
        removeFunctionTwo();
    }

    //MODIFIES: this
    //EFFECTS: adds the list of items to be displayed to a JList where if a user
    // double-clicks an item it is removed from the bucket list
    public void removeFunctionTwo() {
        stringJList1.setFont(new Font("ComicSans", BOLD, 15));
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

                    Object item = target.getModel().getElementAt(index);
                    bucketL.getGoalItemToRemove();

                    JOptionPane.showConfirmDialog(removeBucketListItem, "Removed: \n" + item.toString(),
                            "Removed Goal", JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE, imageIcon);
                    displayList();

                }
            }
        });
    }

    //EFFECTS: checks whether the bucketList has any items to check off,
    // if it doesn't shows message and returns to main menu
    public void ifConditionForCheckOff() {
        if (bucketL.getNumberOfItemsInList() == 0) {

            JOptionPane.showConfirmDialog(checkOffListItem,
                    "No items to check off :( Please add an item first!", "Empty List", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, imageIcon);
            mainMenuAction();
        }
    }

    //MODIFIES: this
    //EFFECTS: creates all labels and their corresponding
    // textFields for checkOff function
    public void labelsForCheckOffFunction() {
        JLabel dateCompletedLabel = new JLabel("Enter the date completed "
                + "(format - MM/dd/uuuu):");
        dateCompletedLabel.setFont(new Font("ComicSans", BOLD, 15));
        dateCompleted = new JTextField(15);
        checkOffListItem.add(dateCompletedLabel);
        dateCompleted.setPreferredSize(new Dimension(25,20));
        dateCompleted.setMaximumSize(textName.getPreferredSize());
        checkOffListItem.add(dateCompleted);
        JLabel experienceLabel = new JLabel("Enter what your experience was like:");
        experienceLabel.setFont(new Font("ComicSans", BOLD, 15));
        experienceNotes = new JTextField(15);
        experienceNotes.setPreferredSize(new Dimension(25,20));
        experienceNotes.setMaximumSize(textName.getPreferredSize());
        checkOffListItem.add(experienceLabel);
        checkOffListItem.add(experienceNotes);
        checkOffListItem.add(mainMenuButton);
    }

    //MODIFIES: this
    //EFFECTS: helper method to set up JList for checkOff function
    public void setUpJListForCheckOff() {
        stringJList2 = new JList<>();
        stringJList2.setModel(listModel);
        stringJList2.setFont(new Font("ComicSans", BOLD, 15));
        stringJList2.setSelectedIndex(0);
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
        setUpJListForCheckOff();
        checkOffListItem.add(stringJList2);
        JScrollPane scroll = new JScrollPane(stringJList2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        checkOffListItem.add(scroll);
        labelsForCheckOffFunction();
        checkOffListAction();
    }

    //MODIFIES: this, goal
    //EFFECTS: Modifies the goal clicked on to reflect that it has been
    // "checked off" and takes in user input for date completed as well as experience
    public void checkOffListAction() {
        stringJList2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JList target = (JList) e.getSource();
                    int index = target.locationToIndex(e.getPoint());
                    for (int intI = 0; intI < bucketL.getList().size(); intI++) {
                        if (listModel.get(index).contains(bucketL.getList().get(intI).getGoal())) {
                            bucketL.setAnInt(intI + 1);
                            dateCompletedString = dateCompleted.getText();
                            bucketL.setDateCompleted(dateCompletedString);
                            experienceNotesString = experienceNotes.getText();
                            bucketL.setExperienceNotes(experienceNotesString);
                            bucketL.getGoalItemToCheck();
                            Object item = target.getModel().getElementAt(index);
                            JOptionPane.showMessageDialog(checkOffListItem, "Checked off: \n" + item.toString());
                        }
                    }
                        displayList();
                }
            }
        });
    }
}

