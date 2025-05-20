////////Utsav Acharya
//////// LAb 4 part A
////////02/11/2025
////////// Unit test written to test the value
//////////import static org.junit.Assert.assertEquals;
//////// //import static org.junit.Assert.assertTrue;
////////import org.junit.Test;
////////
////////public class ClockTest {
////////
////////    @Test
////////    public void testMinute()
////////    {
////////        //here goes testing invalid minute
////////        Clock negativeMinuteClock = new Clock(10, -5, "A.M.");
////////        // assertTrue("Minute out of range"+negativeMinuteClock.getMinutes(),negativeMinuteClock.getMinutes() >=0 && negativeMinuteClock.getMinutes() <=60);
////////        Clock excessMinuteClock = new Clock(7, 80, "P.M.");
////////        // assertTrue("minute out of range"+excessMinuteClock.getMinutes(),negativeMinuteClock.getMinutes() >=0 && negativeMinuteClock.getMinutes() <=60);
////////        // Test with valid minute
////////        Clock validMinuteClock = new Clock(5, 45, "a.m.");
////////        //assertEquals("Valid minutes should remain unchanged", 45, validMinuteClock.getMinutes());
////////    }
////////}
////////
////// import static org.junit.Assert.assertEquals;
//////import org.junit.Test;
//////
//////public class ClockTest {
//////
//////    @Test
//////    public void testInvalidMinuteNegative() {
//////        Clock negativeMinuteClock = new Clock(10, -5, "A.M.");
//////        assertEquals("Minutes should default to 0 for negative input", 0, negativeMinuteClock.getMinutes());
//////    }
//////
//////    @Test
//////    public void testInvalidMinuteExcess() {
//////        Clock excessMinuteClock = new Clock(7, 80, "P.M.");
//////        assertEquals("Minutes should default to 0 for values above 60", 0, excessMinuteClock.getMinutes());
//////    }
//////
//////    @Test
//////    public void testValidMinute() {
//////        Clock validMinuteClock = new Clock(5, 45, "a.m.");
//////        assertEquals("Valid minutes should remain unchanged", 45, validMinuteClock.getMinutes());
//////    }
//////}
//import static org.junit.Assert.assertEquals;
//import org.junit.Test;
//
//public class ClockTest {
//
//    @Test
//    public void testInvalidMinuteNegative() {
//        Clock negativeMinuteClock = new Clock(10, -5, "A.M.");
//        assertEquals("Minutes should default to 0 for negative input", 0, negativeMinuteClock.getMinutes());
//    }
//
//    @Test
//    public void testInvalidMinuteExcess() {
//        Clock excessMinuteClock = new Clock(7, 80, "P.M.");
//        assertEquals("Minutes should default to 0 for values above 60", 0, excessMinuteClock.getMinutes());
//    }
//
//    @Test
//    public void testValidMinute() {
//        Clock validMinuteClock = new Clock(5, 45, "a.m.");
//        assertEquals("Valid minutes should remain unchanged", 45, validMinuteClock.getMinutes());
//    }
//
//    @Test
//    public void testGetEarlierDifferentPeriods() {
//        Clock clock1 = new Clock(3, 45, "p.m.");
//        Clock clock2 = new Clock(8, 15, "a.m.");
//        assertEquals("A.M. time should be considered earlier than P.M.", clock2, Clock.getEarlier(clock1, clock2));
//    }
//
////    @Test
////    public void testGetEarlierIdenticalTimes() {
////        Clock clock1 = new Clock(3, 45, "a.m.");
////        Clock clock2 = new Clock(3, 45, "a.m.");
////        assertEquals("Identical times should return either clock", clock1, Clock.getEarlier(clock1, clock2));
////    }
//
//    @Test
//    public void testGetEarlierSamePeriodDifferentTimes() {
//        Clock clock1 = new Clock(2, 30, "a.m.");
//        Clock clock2 = new Clock(3, 15, "a.m.");
//        assertEquals("Earlier hour should be returned", clock1, Clock.getEarlier(clock1, clock2));
//    }
//
//    @Test
//    public void testGetEarlierSameHourDifferentMinutes() {
//        Clock clock1 = new Clock(3, 30, "a.m.");
//        Clock clock2 = new Clock(3, 15, "a.m.");
//        assertEquals("Earlier minutes should be returned", clock2, Clock.getEarlier(clock1, clock2));
//    }
//}
/**
 * ClockTest.java
 * This class contains JUnit tests for the Clock class, focusing on validating time
 * and the getEarlier method to ensure it correctly determines the earlier of two times.
 */
//import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ClockTest {

    /**
     * Test case: AM should always be earlier than PM.
     */
    @Test
    public void testEarlier_AMvsPM() {
        Clock morning = new Clock(10, 30, "a.m.");
        Clock evening = new Clock(10, 30, "p.m.");
        assertEquals(morning, Clock.getEarlier(morning, evening));
    }

    /**
     * Test case: Identical times should return the first clock.
     */
    @Test
 public   void testIdenticalTimes() {
        Clock clock1 = new Clock(8, 15, "a.m.");
        Clock clock2 = new Clock(8, 15, "a.m.");
        assertEquals(clock1, Clock.getEarlier(clock1, clock2));
    }

    /**
     * Test case: When both are AM, the earlier hour should be selected.
     */
    @Test
  public  void testEarlier_AMDifferentHours() {
        Clock early = new Clock(5, 45, "a.m.");
        Clock late = new Clock(7, 15, "a.m.");
        assertEquals(early, Clock.getEarlier(early, late));
    }

    /**
     * Test case: When both are PM, the earlier hour should be selected.
     */
    @Test
   public void testEarlier_PMDifferentHours() {
        Clock early = new Clock(3, 20, "p.m.");
        Clock late = new Clock(6, 45, "p.m.");
        assertEquals(early, Clock.getEarlier(early, late));
    }

    /**
     * Test case: Same hour but different minutes.
     */
    @Test
   public void testEarlier_SameHourDifferentMinutes() {
        Clock early = new Clock(9, 10, "a.m.");
        Clock late = new Clock(9, 50, "a.m.");
        assertEquals(early, Clock.getEarlier(early, late));
    }

    /**
     * Test case: Midnight (12:00 AM) should be earlier than any morning time.
     */
    @Test
    public void testEarlier_MidnightVsMorning() {
        Clock midnight = new Clock(12, 0, "a.m.");
        Clock morning = new Clock(1, 0, "a.m.");
        assertEquals(midnight, Clock.getEarlier(midnight, morning));
    }

    /**
     * Test case: Noon (12:00 PM) should be earlier than any afternoon time.
     */
    @Test
    public void testEarlier_NoonVsAfternoon() {
        Clock noon = new Clock(12, 0, "p.m.");
        Clock afternoon = new Clock(1, 0, "p.m.");
        assertEquals(noon, Clock.getEarlier(noon, afternoon));
    }
}
