public class Square {
    private double side;
    //constructor
    public Square(double side)
    {
        this.side=side;
    }
    //getter to get the peremeter
    public double getPeremeter()
    {
        return 4*side;
    }
    // getter to get area
    public double getArea()
    {
        return side*side;
    }

    @Override
    public String toString()
    {
        return("Square with side ="+side);
    }

}
