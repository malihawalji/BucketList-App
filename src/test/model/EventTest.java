package model;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {


    private Event add;
    private Event remove;
    private Event check;
    private Event load;
    private Date d;

    @Test
    public void testEventAdd() {
        add = new Event("goal added to bucket list");
        assertEquals("goal added to bucket list" , add.getDescription());

    }

    @Test
    public void testEventRemove() {
        remove = new Event("goal removed from bucket list");
        assertEquals("goal removed from bucket list" , remove.getDescription());
    }

    @Test
    public void testEventCheck() {
        check = new Event("goal checked off!");
        assertEquals("goal checked off!" , check.getDescription());
    }

    @Test
    public void testEventLoad() {
        load = new Event("goals loaded from saved bucket list");
        assertEquals("goals loaded from saved bucket list" , load.getDescription());
    }

    @Test
    public void testToString() {
        d = Calendar.getInstance().getTime();
        add = new Event("goal added to bucket list");
        assertEquals(d.toString() + "\n" + "goal added to bucket list", add.toString());
    }
    //iloveyousomuchmaliha
}
