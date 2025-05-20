
// Unit test written to test the value
import org.junit.Test;

import static org.junit.Assert.*;

public class ClockTest {

    @Test
    public void testMinute()
    {
       //here goes testing invalid minute
       //Clock negativeMinuteClock = new Clock(10, -5, "A.M.");
        assertThrows(new IllegalArgumentException(), new Clock(10, -5, "A.M."));
//       assertTrue("Minute out of range"+negativeMinuteClock.getMinutes(),negativeMinuteClock.getMinutes() >=0 && negativeMinuteClock.getMinutes() <=60);
//        Clock excessMinuteClock = new Clock(7, 80, "P.M.");
//       // assertTrue("minute out of range"+excessMinuteClock.getMinutes(),negativeMinuteClock.getMinutes() >=0 && negativeMinuteClock.getMinutes() <=60);
//        // Test with valid minute
//        Clock validMinuteClock = new Clock(5, 45, "a.m.");
//        //assertEquals("Valid minutes should remain unchanged", 45, validMinuteClock.getMinutes());
    }
}