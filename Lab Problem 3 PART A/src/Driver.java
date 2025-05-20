import java.util.Scanner;

public class Driver {
    public static void main(String[] args)
    {
        Scanner scnr =new Scanner(System.in);
        System.out.print("Enter the length of the square's sides:");
        double side = scnr.nextDouble();
        Square square = new Square(side);
        System.out.println(square);
        System.out.println("Perimeter of the square is "+square.getPeremeter());
        System.out.println("The area of the square is "+square.getArea());

        scnr.close();

    }
}
