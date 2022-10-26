package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class SuggestionTest {

    Suggestion suggestion;

    @BeforeEach
    void beforeEach(){
        suggestion = new Suggestion();
    }

    @Test
    void testChooseRandomSuggestion(){
        suggestion.chooseRandomSuggestion();
        assertEquals(suggestion.randomSuggestion, suggestion.getRandomSuggestion());
    }
}
