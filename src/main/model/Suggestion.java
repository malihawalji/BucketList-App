package model;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

//Creates a list of type String that holds a bunch of
// suggestions for what to add to the bucket list
public class Suggestion {

    String randomSuggestion;

    private final List<String> listOfSuggestions;

    //EFFECTS: Constructor creates array listOfSuggestions
    // which holds a set number of strings
    public Suggestion() {
        String s1 = "Go skydiving";
        String s2 = "Go on a roadtrip to somewhere I haven't been";
        String s3 = "Go to an amusement park near me";
        String s4 = "Go tubing when it snows";
        String s5 = "Go on a seaplane";
        String s6 = "Go whale watching";
        String s7 = "ride a zipline";
        String s8 = "Get a new tattoo";
        String s9 = "indoor skydive with a friend";
        String s10 = "Go scuba-diving";
        String s11 = "Write a thank you letter to a teacher who meant a lot to me";
        String s12 = "take a glass blowing class";
        String s13 = "Attend a sport game I haven't been to before";
        String s14 = "See a broadway play";
        String s15 = "Have a Harry Potter movie marathon";


        listOfSuggestions = Arrays.asList(s1, s2, s3, s4, s5, s6, s7, s8, s9,
                s10, s11, s12, s13, s14, s15);

    }


    //EFFECTS: chooses a random string from the listOfSuggestions
    public void chooseRandomSuggestion() {
        Random random = new Random();
        int randomIndex = random.nextInt(listOfSuggestions.size());
        randomSuggestion = listOfSuggestions.get(randomIndex);
    }

    public String getRandomSuggestion() {
        return randomSuggestion;
    }
}
