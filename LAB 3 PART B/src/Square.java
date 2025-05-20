/**
 * Square with a given size
 * Methods to calculate the Perimeter & Area
 */
public class Square {
    private double side;

    /**
     * Constructor to make a square of a given size
     * @param side length of the side of the square
     * @throws NegativeLengthException if the user entered side length is negative
     */
    public Square(double side) throws NegativeLengthException {
        if (side < 0) {
            throw new NegativeLengthException("Negative length: " + side);
        }
        this.side = side;
    }

    /**
     * Getter for getting perimeter
     * @return perimeter of the square
     */
    public double getPerimeter() {  // Fixed method name
        return 4 * side;
    }

    /**
     * Getter for getting area
     * @return area of the square
     */
    public double getArea() {
        return side * side;
    }

    // Override toString for required format
    @Override
    public String toString() {
        return "Square with side = " + side;
    }
}
