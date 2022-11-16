package persistence;

import model.Goal;
import org.junit.jupiter.api.Assertions;

public class JsonTest {
    protected void checkBucketList(Goal goal, String goalName, String notes, String date, String experience) {
        Assertions.assertEquals(goalName, goal.getGoal());
        Assertions.assertEquals(date, goal.getDate());
        Assertions.assertEquals(notes, goal.getNotes());
        Assertions.assertEquals(experience, goal.getExperience());
    }
}
