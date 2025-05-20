/**
 * The Clock class represents a 12-hour format clock with hours, minutes,
 * and a meridian (AM/PM). It includes validation and encapsulation.
 */
public class Clock {
    private int hours;
    private int minutes;
    private String meridian;

    /**
     * Default constructor initializes the clock to 12:00 AM.
     */
    public Clock() {
        this(12, 0, "a.m.");
    }

    /**
     * Constructs a Clock instance with specified hours, minutes, and meridian.
     * @param hours The hour component (1-12).
     * @param minutes The minute component (0-59).
     * @param meridian The meridian indicator ("a.m." or "p.m.").
     * @throws IllegalArgumentException If values are invalid.
     */
    public Clock(int hours, int minutes, String meridian) {
        if (hours < 1 || hours > 12) {
            throw new IllegalArgumentException("Hours must be between 1 and 12.");
        }
        if (minutes < 0 || minutes > 59) {
            throw new IllegalArgumentException("Minutes must be between 0 and 59.");
        }
        if (!meridian.equalsIgnoreCase("a.m.") && !meridian.equalsIgnoreCase("p.m.")) {
            throw new IllegalArgumentException("Meridian must be 'a.m.' or 'p.m.'");
        }
        this.hours = hours;
        this.minutes = minutes;
        this.meridian = meridian.toLowerCase();
    }

    /**
     * Returns a formatted string representation of the clock time.
     * @return Formatted time in HH:MM AM/PM format.
     */
    @Override
    public String toString() {
        return String.format("%02d:%02d %s", hours, minutes, meridian);
    }

    /**
     * Determines which of the two given Clock objects represents an earlier time.
     * @param c1 The first Clock object.
     * @param c2 The second Clock object.
     * @return The Clock object that represents the earlier time.
     */
    public static Clock getEarlier(Clock c1, Clock c2) {
        if (c1.meridian.equals("a.m.") && c2.meridian.equals("p.m.")) {
            return c1;
        } else if (c1.meridian.equals("p.m.") && c2.meridian.equals("a.m.")) {
            return c2;
        } else {
            if (c1.hours == 12 && c2.hours != 12) {
                return c1;
            } else if (c2.hours == 12 && c1.hours != 12) {
                return c2;
            } else {
                if (c1.hours < c2.hours) {
                    return c1;
                } else if (c2.hours < c1.hours) {
                    return c2;
                } else {
                    return (c1.minutes < c2.minutes) ? c1 : c2;
                }
            }
        }
    }
}
