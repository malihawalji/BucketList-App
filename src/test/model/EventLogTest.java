package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonWriter;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class EventLogTest {

    BucketList bucketList;
    Goal goal;

    @BeforeEach
    void beforeEach() {
        bucketList = new BucketList();
        goal = new Goal("go to Cancun", "Spring break", "09/24", "");
    }


    @Test
    void testAddGoalLogEvent() {
        bucketList.addGoal(goal);

        List<Event> l = new ArrayList<Event>();

        EventLog el = EventLog.getInstance();
        for (Event next : el) {
            l.add(next);
        }

        assertEquals("goal added to bucket list", l.get(0).getDescription());
        EventLog.getInstance().clear();
    }

    @Test
    void testCheckOffGoalLogEvent() {
        bucketList.addGoal(goal);
        bucketList.setAnInt(1);
        bucketList.getGoalItemToCheck();
        List<Event> l = new ArrayList<Event>();

        EventLog el = EventLog.getInstance();
        for (Event next : el) {
            l.add(next);
        }
        assertEquals("goal added to bucket list", l.get(1).getDescription());
        assertEquals("goal checked off!", l.get(2).getDescription());
        EventLog.getInstance().clear();
    }

    @Test
    void testRemoveGoalLogEvent() {
        bucketList.addGoal(goal);
        bucketList.setAnInt(1);
        bucketList.getGoalItemToRemove();

        List<Event> l = new ArrayList<Event>();

        EventLog el = EventLog.getInstance();
        for (Event next : el) {
            l.add(next);
        }
        assertEquals("goal added to bucket list", l.get(1).getDescription());
        assertEquals("goal removed from bucket list", l.get(2).getDescription());
        EventLog.getInstance().clear();
    }

    @Test
    void testSaveListLogEvent() {
        try {
            bucketList.addGoal(goal);
            Goal goal2 = new Goal("go skydiving"
                    , "with friends next summer", "10/18/2022", "");
            bucketList.addGoal(goal2);
            goal.setName("maliha");
            goal2.setName("maliha");
            JsonWriter writer = new JsonWriter("./data/testWriterBucketListNotEmpty.json");
            writer.open();
            writer.write(bucketList);
            writer.close();
        }
        catch (Exception e) {
            //
        }
        List<Event> l = new ArrayList<Event>();
        EventLog el = EventLog.getInstance();
        for (Event next : el) {
            l.add(next);
        }
        assertEquals("goal added to bucket list", l.get(1).getDescription());
        assertEquals("goal added to bucket list", l.get(2).getDescription());
        assertEquals("saved bucket list", l.get(3).getDescription());
        EventLog.getInstance().clear();
    }

}
