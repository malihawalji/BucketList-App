package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BucketListTest {

    String notes = "by summer";
    String date = "Dec 20th";
    String goalName = "Go skydiving";
    String dateCompleted = "06/16";
    String experience = "";
    BucketList list;
    Goal goal;


    @BeforeEach
    void runBefore() {
        list = new BucketList();
        goal = new Goal(goalName, notes, date, experience);
        goal.setNotes(notes);
        goal.setGoal(goalName);
        goal.setDate(date);
        list.addGoal(goal);
    }

    @Test
    void testAddGoal() {
        list.addGoal(goal);
        list.addGoal(goal);
        assertEquals(3, list.getNumberOfItemsInList());
    }

    @Test
    void testGetGoalItemToRemove() {
        list.setAnInt(1);
        list.getGoalItemToRemove();
        assertEquals(0, list.getNumberOfItemsInList());

    }

    @Test
    void testGetGoalItemToCheck() {
        list.setDateCompleted(dateCompleted);
        experience = "Incredible! Felt like I was flying!";
        list.setExperienceNotes(experience);
        list.getGoalItemToCheck();
        assertEquals("Go skydiving X Completed: 06/16", goal.getGoal());
        assertEquals("Experience: Incredible! Felt like I was flying!", goal.getExperience());
        assertNotNull(list.checkExperience());
    }


    @Test
    void testGetBucketList() {
        assertEquals(list.getBucketList(), "\n#1 " + "Date: " + "Dec 20th" + "\n" + "Go skydiving" + "\n"
                + "Notes: " + "by summer" + "\n" + "" + "\n");
        assertFalse(list.getBucketList().isEmpty());
    }

    @Test
    void testGetNumberOfItemsInList() {
        list.addGoal(goal);
        assertEquals(2, list.getNumberOfItemsInList());
    }
}