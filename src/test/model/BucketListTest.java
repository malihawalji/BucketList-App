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
    }

    @Test
    void noItemsAdded(){
        assertEquals(0, list.getNumberOfItemsInList());
    }

    @Test
    void testAddGoal() {
        list.addGoal(goal);
        list.addGoal(goal);
        list.addGoal(goal);
        assertEquals(3, list.getNumberOfItemsInList());
    }

    @Test
    void testGetGoalItemToRemove() {
        list.addGoal(goal);
        list.setAnInt(1);
        list.getGoalItemToRemove();
        assertEquals(0, list.getNumberOfItemsInList());

    }

    @Test
    void testGetGoalItemToCheck() {
        list.addGoal(goal);
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
        list.addGoal(goal);
        assertEquals(list.getBucketList(), "\n#1 " + "Date: " + "Dec 20th" + "\n" + "Go skydiving" + "\n"
                + "Notes: " + "by summer" + "\n" + "" + "\n");
        assertFalse(list.getBucketList().isEmpty());
    }

    @Test
    void testGetBucketListTwoGoals(){
        list.addGoal(goal);
        list.addGoal(goal);
        assertEquals(list.getBucketList(),"\n#1 " + "Date: " + "Dec 20th" + "\n" + "Go skydiving" + "\n"
                + "Notes: " + "by summer" + "\n" + "" + "\n" + "\n#2 " + "Date: "
                + "Dec 20th" + "\n" + "Go skydiving" + "\n"
                + "Notes: " + "by summer" + "\n" + "" + "\n" );
    }

    @Test
    void itemAlreadyCheckedOff(){
        list.addGoal(goal);
        list.setDateCompleted(dateCompleted);
        experience = "Incredible! Felt like I was flying!";
        list.setExperienceNotes(experience);
        list.getGoalItemToCheck();
        assertEquals("Go skydiving X Completed: 06/16", goal.getGoal());
        assertEquals("Experience: Incredible! Felt like I was flying!", goal.getExperience());

        assertNotEquals("", list.checkExperience());
    }

    @Test
    void testGetList() {
        assertEquals("[]", list.getList().toString());
        list.addGoal(goal);
        assertEquals("Go skydiving", list.getList().get(0).getGoal());
        assertEquals("", list.getList().get(0).getExperience());
        assertEquals("by summer", list.getList().get(0).getNotes());
        assertEquals("Dec 20th", list.getList().get(0).getDate());
    }
    @Test
    void testGetBucketListWhenListIsEmpty(){
        boolean empty = list.getBucketList().isEmpty();
        assertTrue(empty);
    }

    @Test
    void testGetNumberOfItemsInList() {
        list.addGoal(goal);
        list.addGoal(goal);
        assertEquals(2, list.getNumberOfItemsInList());
    }

    @Test
    void testBucketListToJson(){
        list.addGoal(goal);
       assertEquals("Go skydiving", list.toJson().getJSONArray("list of goals").getJSONObject(0).getString("goalName"));
        assertEquals("Dec 20th", list.toJson().getJSONArray("list of goals").getJSONObject(0).getString("date"));
        assertEquals("by summer", list.toJson().getJSONArray("list of goals").getJSONObject(0).getString("notes"));
        assertEquals("", list.toJson().getJSONArray("list of goals").getJSONObject(0).getString("experience"));
    }

    @Test
    void testBucketListListToJson() {
        list.addGoal(goal);
        assertEquals("Go skydiving",list.listToJson().getJSONObject(0).getString("goalName"));
        assertEquals("Dec 20th", list.listToJson().getJSONObject(0).getString("date"));
        assertEquals("by summer", list.listToJson().getJSONObject(0).getString("notes"));
        assertEquals("", list.listToJson().getJSONObject(0).getString("experience"));

    }
}