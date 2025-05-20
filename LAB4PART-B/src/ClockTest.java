/**
 * ClockTest.java
 * This class contains JUnit tests for the Clock class, focusing on validating time
 * and the getEarlier method to ensure it correctly determines the earlier of two times.
 */
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ClockTest {

    @Test
    public void testEarlier_AMvsPM() {
        Clock morning = new Clock(10, 30, "a.m.");
        Clock evening = new Clock(10, 30, "p.m.");
        assertEquals(morning, Clock.getEarlier(morning, evening));
    }

    @Test
    public void testIdenticalTimes() {
        Clock clock1 = new Clock(8, 15, "a.m.");
        Clock clock2 = new Clock(8, 15, "a.m.");
        assertEquals(clock1, Clock.getEarlier(clock2, clock1));
    }

    @Test
    public void testEarlier_AMDifferentHours() {
        Clock early = new Clock(5, 45, "a.m.");
        Clock late = new Clock(7, 15, "a.m.");
        assertEquals(early, Clock.getEarlier(early, late));
    }

    @Test
    public void testEarlier_PMDifferentHours() {
        Clock early = new Clock(3, 20, "p.m.");
        Clock late = new Clock(6, 45, "p.m.");
        assertEquals(early, Clock.getEarlier(early, late));
    }

    @Test
    public void testEarlier_SameHourDifferentMinutes() {
        Clock early = new Clock(9, 10, "a.m.");
        Clock late = new Clock(9, 50, "a.m.");
        assertEquals(early, Clock.getEarlier(early, late));
    }

    @Test
    public void testEarlier_MidnightVsMorning() {
        Clock midnight = new Clock(12, 0, "a.m.");
        Clock morning = new Clock(1, 0, "a.m.");
        assertEquals(midnight, Clock.getEarlier(midnight, morning));
    }

    @Test
    public void testEarlier_NoonVsAfternoon() {
        Clock noon = new Clock(12, 0, "p.m.");
        Clock afternoon = new Clock(1, 0, "p.m.");
        assertEquals(noon, Clock.getEarlier(noon, afternoon));
    }
}
