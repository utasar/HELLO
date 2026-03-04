import java.util.Scanner;

public class Midterm {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = 0;

        System.out.print("Do you want fruit, cookies, ice cream, or nothing? ");
        String whatYouWant = input.nextLine();

        while (!(whatYouWant.equals("nothing"))) {
            if (whatYouWant.equals("fruit")) {
                System.out.println("Good for you!");
            }
            n++;
            System.out.print("Do you want fruit, cookies, ice cream, or nothing? ");
            whatYouWant = input.nextLine();
        }
        input.close();
        System.out.println("You had " + n + " things");
    }
}

/*
Write a program that repeatedly asks the user this question: Do you want fruit, cookies, ice
cream, or nothing? Until they answer “nothing”. If they say fruit, display the message “Good for
you!”. After they say nothing, display the message “You had N things”. Note that “nothing”
does not count as a thing. Your code can assume that the user will always enter one of the
requested strings, that they will spell everything correctly, and that everything they enter will
be in lower case.
 */
