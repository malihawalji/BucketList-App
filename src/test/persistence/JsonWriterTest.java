package persistence;

import model.BucketList;
import model.Goal;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonTest;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            BucketList bucketList = new BucketList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyList() {
        try {
            BucketList bucketList = new BucketList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyList.json");
            writer.open();
            writer.write(bucketList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyList.json");
            bucketList = reader.read();
            assertEquals(0, bucketList.getNumberOfItemsInList());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterNotEmptyList() {
        try {
            BucketList bucketList = new BucketList();
            Goal goal1 = new Goal("go to banff via roadtrip", "in may", "10/18/2022", "");
            bucketList.addGoal(goal1);
            Goal goal2 = new Goal("go skydiving"
                    , "with friends next summer", "10/18/2022", "");
            bucketList.addGoal(goal2);
            goal1.setName("maliha");
            goal2.setName("maliha");
            JsonWriter writer = new JsonWriter("./data/testWriterBucketListNotEmpty.json");
            writer.open();
            writer.write(bucketList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterBucketListNotEmpty.json");
            bucketList = reader.read();
            List<Goal> listOfGoals = bucketList.getList();
            assertEquals(2, listOfGoals.size());
            this.checkBucketList((Goal)listOfGoals.get(0), "go to banff via roadtrip", "in may"
                    , "10/18/2022", "");
            this.checkBucketList((Goal)listOfGoals.get(1), "go skydiving"
                    , "with friends next summer", "10/18/2022", "");

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
