////import java.util.Scanner;
////
////public class Lab3aStartingCode {
////
////    static Scanner input = new Scanner(System.in);
////
////    public static void main(String[] args) {
////
////        Clock appointmentTime = new Clock(12, 30, "p.m.");
////        Clock userTime = new Clock(getUserHours(), getUserMinutes(),
////                getUserMeridian());
////
////        if (Clock.getEarlier(userTime, appointmentTime) == userTime) {
////            System.out.println("You're not late! "+ userTime);
////        } else {
////            System.out.println("You're late! "+ userTime);
////        }
////
////    }
////
////    public static int getUserHours() {
////        System.out.print("What hour should the clock be set to? ");
////        int hours = input.nextInt();
////        input.nextLine(); // consumes the trailing newline
////        return hours;
////    }
////
////    public static int getUserMinutes() {
////        System.out.print("What minute should the clock be set to? ");
////        int hours = input.nextInt();
////        input.nextLine(); // consumes the trailing newline
////        return hours;
////    }
////
////    public static String getUserMeridian() {
////        System.out.print("Is it a.m. (a) or p.m. (p)? ");
////        String answer = input.nextLine();
////        if (answer.toLowerCase().startsWith("a")) {
////            return "a.m.";
////        } else {
////            return "p.m.";
////        }
////    }
////
////}
//public int getMinutes() {
//    return minutes;
//}
//
//public int getHours() {
//    return hours;
//}
//
//public String getMeridian() {
//    return meridian;
//}
//
import java.util.Scanner;

public class Lab3aStartingCode {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        Clock appointmentTime = new Clock(12, 30, "p.m.");
        Clock userTime = new Clock(getUserHours(), getUserMinutes(), getUserMeridian());

        if (Clock.getEarlier(userTime, appointmentTime) == userTime) {
            System.out.println("You're not late! " + userTime);
        } else {
            System.out.println("You're late! " + userTime);
        }

    }

    public static int getUserHours() {
        System.out.print("What hour should the clock be set to? ");
        int hours = input.nextInt();
        input.nextLine(); // consumes the trailing newline
        return hours;
    }

    public static int getUserMinutes() {
        System.out.print("What minute should the clock be set to? ");
        int minutes = input.nextInt();
        input.nextLine(); // consumes the trailing newline
        return minutes;
    }

    public static String getUserMeridian() {
        System.out.print("Is it a.m. (a) or p.m. (p)? ");
        String answer = input.nextLine();
        if (answer.toLowerCase().startsWith("a")) {
            return "a.m.";
        } else {
            return "p.m.";
        }
    }
}
