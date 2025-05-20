/**
        Utsav Acharya
        Clarissa Milligan
        02/06/2025
        */
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        try {
            System.out.print("Enter the length of the square's side: ");
            double side = scnr.nextDouble();

            // Try to create a Square object
            Square square = new Square(side);

            // Display square information
            System.out.println(square);
            System.out.println("Perimeter of the square is " + square.getPerimeter()); // Fixed method name
            System.out.println("The area of the square is " + square.getArea());
        }
        // Catch invalid input (non-numeric value)
        catch (InputMismatchException e) {
            System.err.println("Error: you must enter a number");
            scnr.nextLine(); // Clear scanner buffer after invalid input
        }
        // Catch custom NegativeLengthException
        catch (NegativeLengthException e) {
            System.err.println(e.getMessage());
        }
        finally {
            scnr.close();
        }
    }
}
