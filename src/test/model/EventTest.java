package model;

import org.junit.jupiter.api.Test;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventTest {


    private Event add;
    private Event remove;
    private Event check;
    private Event load;
    private Event toad;
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
        d = Calendar.getInstance().getTime();
        assertEquals(d.toInstant().truncatedTo(ChronoUnit.SECONDS),
                load.getDate().toInstant().truncatedTo(ChronoUnit.SECONDS));
        assertEquals("goals loaded from saved bucket list" , load.getDescription());
    }

    @Test
    public void testToString() {
        d = Calendar.getInstance().getTime();
        add = new Event("goal added to bucket list");
        assertEquals(d.toString() + "\n" + "goal added to bucket list", add.toString());
    }

    @Test
    public void testEqualsAndHashcode() {
        d = Calendar.getInstance().getTime();
        load = new Event("goals loaded from saved bucket list");
        load.getDate().setTime(d.getTime());
        toad = new Event("goals loaded from saved bucket list");
        toad.getDate().setTime(d.getTime());
        assertTrue(load.equals(toad));
        assertTrue(load.hashCode() == toad.hashCode());
    }
}
