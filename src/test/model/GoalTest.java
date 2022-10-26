package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GoalTest {
    String goalName;
    String notes;
    String date;
    String experience;
    Goal goal;

    @BeforeEach
    void runBeforeEach() {
        goal = new Goal(goalName, notes, date, experience);
    }

    @Test
    void testGetGoal(){
        goal.setGoal("camping");
        assertEquals("camping", goal.getGoal());
    }

    @Test
    void testGetDate(){
        goal.setDate("09/24");
        assertEquals("09/24", goal.getDate());
    }

    @Test
    void testGetNotes(){
        goal.setNotes("with friends");
        assertEquals("with friends", goal.getNotes());
    }

    @Test
    void testGetExperience(){
        goal.setExperience("awesome!");
        assertEquals("awesome!", goal.getExperience());
    }
}