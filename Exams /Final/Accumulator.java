package Final;

public class Accumulator {
    private double temp;
    private double total;

    public Accumulator(double initialValue) {
        temp = initialValue;
        total =+ temp;
    }

    public void accumulate(double value) {
        total += value;
    }

    public double accumulate(Accumulator other) {
        temp = other.total;
        total += temp;
        return total;
    }

    @Override
    public String toString() {
        return "" + this.total;
    }
}
