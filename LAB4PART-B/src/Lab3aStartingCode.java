import java.util.Scanner;

public class Lab3aStartingCode {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Clock appointmentTime = new Clock(12, 30, "p.m.");
        Clock userTime = new Clock(getUserHours(), getUserMinutes(), getUserMeridian());

        if (Clock.getEarlier(userTime, appointmentTime) == userTime) {
            System.out.println("You're not late!");
        } else {
            System.out.println("You're late!");
        }
    }

    public static int getUserHours() {
        System.out.print("What hour should the clock be set to? ");
        return input.nextInt();
    }

    public static int getUserMinutes() {
        System.out.print("What minute should the clock be set to? ");
        return input.nextInt();
    }

    public static String getUserMeridian() {
        System.out.print("Is it a.m. (a) or p.m. (p)? ");
        return input.next().toLowerCase().startsWith("a") ? "a.m." : "p.m.";
    }
}
