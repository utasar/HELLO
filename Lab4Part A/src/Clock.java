//////import java.util.InputMismatchException;
//////import java.util.Scanner;
//////
//////public class Clock {
//////
//////    private int hours;
//////    private int minutes;
//////    private String meridian;
//////    // defining scanner multiple place is not good practice but
//////    //I dont want to modify the starting code given so I did it. So I put static for living in this lifecycle
//////    static Scanner scnr = new Scanner(System.in);
//////    public Clock() {
//////        hours = 12;
//////        minutes = 0;
//////        meridian = "a.m.";
//////    }
//////
//////    public Clock(int hours, int minutes, String meridian) {
//////        this.hours = hours;
//////        //fix: set minutes to 0 if out of range
//////        // code commented because I wanted to implement try catch exception handling here
//////        // if (minutes<0||minutes>60)
//////        // {
//////        // this.minutes=0;
//////        // }
//////        // else
//////        // {
//////        //     this.minutes = minutes;
//////        // }
//////        //     this.meridian = meridian;
//////        this.minutes=validateMinutes(minutes);
//////        this.meridian=meridian;
//////    }
//////    // Method to validate and get correct minute input using try-catch
//////    private int validateMinutes(int minutes) {
//////        boolean valid = false;
//////        while (!valid) {
//////            try {
//////                if (minutes < 0 || minutes > 60) {
//////                    throw new IllegalArgumentException("Invalid minute value: " + minutes);
//////                }
//////                valid = true; // If the input is valid loop breaks
//////            } catch (IllegalArgumentException e) {
//////                System.out.println(e.getMessage());
//////                System.out.print("Please enter a valid minute value (0-60): ");
//////                try {
//////                    minutes = scnr.nextInt();
//////                } catch (InputMismatchException ime) {
//////                    System.out.println("Invalid input. Please enter a numeric value.");
//////                    scnr.next(); // Clear the invalid input
//////                }
//////            }
//////        }
//////        return minutes;
//////    }
//////
//////    @Override
//////    public String toString() {
//////        String time = hours + ":";
//////        if (minutes < 10) {
//////            time += "0";
//////        }
//////        time += minutes + " " + meridian;
//////        return time;
//////    }
//////    // public int getMinutes(){ // getter implimented to get the minute
//////    //     return minutes;
//////    // }
//////
//////    public static Clock getEarlier(Clock c1, Clock c2) {
//////        if (c1.meridian.equals("a.m.") && c2.meridian.equals("p.m.")) {
//////            return c1;
//////        } else if (c1.meridian.equals("p.m.") && c2.meridian.equals("a.m.")) {
//////            return c2;
//////        } else {
//////            // there is a special case if it is 12-something a.m. or p.m. on one
//////            // but not both of the clocks (i.e. 12:00 a.m. is before 1:00 a.m.)
//////            if (c1.hours == 12 && c2.hours != 12) {
//////                return c1;
//////            } else if (c2.hours == 12 && c1.hours != 12) {
//////                return c2;
//////            } else {
//////                if (c1.hours < c2.hours) {
//////                    return c1;
//////                } else if (c2.hours < c1.hours) {
//////                    return c2;
//////                } else {
//////                    if (c1.minutes < c2.minutes) {
//////                        return c1;
//////                    } else if (c2.minutes < c1.minutes) {
//////                        return c2;
//////                    } else {
//////                        // the clocks have the same time
//////                        assert c1.toString().equals(c2.toString()) : c1.toString() + " " + c2.toString();
//////                        // we will arbitrarily return the first one
//////                        return c1;
//////                    }
//////                }
//////            }
//////        }
//////    }
//////}
////public Clock(int hours, int minutes, String meridian) {
////    this.hours = hours;
////    if (minutes < 0 || minutes > 60) {
////        this.minutes = 0; // Invalid minutes default to 0 or throw an exception
////    } else {
////        this.minutes = minutes;
////    }
////    this.meridian = meridian;
////}
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
//}
import java.util.Scanner;

public class Clock {

    private int hours;
    private int minutes;
    private String meridian;

    public Clock() {
        hours = 12;
        minutes = 0;
        meridian = "a.m.";
    }

    public Clock(int hours, int minutes, String meridian) {
        this.hours = hours;

        // Ensure minutes are within the valid range
        if (minutes < 0 || minutes > 60) {
            this.minutes = 0; // Default to 0 if invalid
        } else {
            this.minutes = minutes;
        }
        this.meridian = meridian;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getHours() {
        return hours;
    }

    public String getMeridian() {
        return meridian;
    }

    @Override
    public String toString() {
        String time = hours + ":";
        if (minutes < 10) {
            time += "0";
        }
        time += minutes + " " + meridian;
        return time;
    }

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
                    if (c1.minutes < c2.minutes) {
                        return c1;
                    } else {
                        return c2;
                    }
                }
            }
        }
    }
}

