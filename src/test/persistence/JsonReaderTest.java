package persistence;

import java.io.IOException;
import java.util.List;

import model.BucketList;
import model.Goal;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            BucketList bucketList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyBucketList.json");
        try {
            BucketList bucketList = reader.read();
            assertEquals(0, bucketList.getNumberOfItemsInList());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderBucketListNotEmpty() {
        JsonReader reader = new JsonReader("./data/testReaderBucketListNotEmpty.json");
        try {
            BucketList bucketList = reader.read();
            List<Goal> listOfGoals = bucketList.getList();
            assertEquals(2, bucketList.getNumberOfItemsInList());
            this.checkBucketList((Goal)listOfGoals.get(0), "go to banff via roadtrip", "in may"
                    , "10/18/2022", "");
            this.checkBucketList((Goal)listOfGoals.get(1), "go skydiving"
                    , "with friends next summer", "10/18/2022", "");
           assertEquals("{\"list of goals\": [  {    \"date\": \"10/18/2022\",   " +
                   " \"notes\": \"in may\",    \"goalName\": \"go to banff via roadtrip\",   " +
                   " \"experience\": \"\",    \"User's name\": \"\"  },  {    \"date\": \"10/18/2022\",   " +
                   " \"notes\": \"with friends next summer\",    \"goalName\": \"go skydiving\",  " +
                   "  \"experience\": \"\",    \"User's name\": \"\"  }]}", reader.readFile("./data/testReaderBucketListNotEmpty.json"));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
   }

//   @Test
//   void testAddList() throws IOException {
//       JsonReader reader = new JsonReader("./data/testReaderBucketListNotEmpty.json");
//       BucketList bucketList = reader.read();
//       List<Goal> listOfGoals = bucketList.getList();
//       //reader.addList(bucketList, listOfGoals.get(0).toJson());
//       //list.addGoal(goal);
//       //reader.addList(list, goal.toJson());
////       assertEquals("A", goal.toJson().getString("goalName"));
////       assertEquals("B", goal.toJson().getString("notes"));
////       assertEquals("C", goal.toJson().getString("date"));
////       assertEquals("D", goal.toJson().getString("experience"));
////       reader.addLists(list, goal.toJson());
////       assertEquals("", goal.toJson().toString());
//   }

}


