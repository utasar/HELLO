package Final;

public class Driver {
    public static void main(String[] args) {
        Accumulator acc1 = new Accumulator(1);
        Accumulator acc2 = new Accumulator(100);
        System.out.println(acc1);
        acc1.accumulate(50);
        acc1.accumulate(acc2);
        System.out.println(acc1);
    }
}

/*
Write a class called Accumulator with the following features:
• A field to keep track of the accumulated value. This field should be capable of storing
numbers with decimal values.
• A constructor that initializes the accumulated value to whatever value is passed in by
the calling method
• toString is overridden to return the accumulated value
• A method called accumulate that takes as a parameter a number that can have a
decimal point. This method should increase the accumulated value field by this amount.
• A second method, also called accumulate that takes as a parameter another
Accumulator object and increases the accumulated value field of this Accumulator by
the amount of the other one’s accumulated value.
• Any other methods needed to meet the other requirements

Your class must enforce strong encapsulation to receive full credit. In other words, code outside
of Accumulator.java should not be able to directly access the accumulated value field of an
Accumulator object. Your Accumulator class should also not print anything out from within the
class – only the main method, described below, should do this.

In a separate class called Driver, write a main method that:
• Instantiates an Accumulator object with an initial value of 1.
• Instantiates a second Accumulator object with an initial value of 100.
• Prints out the first Accumulator object
• Calls the accumulate method on the first Accumulator object with a value of 50.
• Calls the accumulate method on the first Accumulator object and passes it the second
accumulator object.
• Prints out the first Accumulator object again


Example:
If you follow the directions above, your code should display the following:
1
151
 */
