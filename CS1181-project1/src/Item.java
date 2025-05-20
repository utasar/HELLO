/**
 * Represents an item with a name, weight, value, and inclusion status.
 * Used in scenarios such as the Knapsack problem to determine which items are included.
 */
public class Item {

    private final String name;  // The name of the item (e.g., Jewelry, Kindle)
    private final double weight;  // The weight of the item in pounds
    private final int value;  // The monetary value of the item in dollars
    private boolean included;  // Indicates if the item is included in the solution

    /**
     * Constructs an Item with the specified name, weight, and value.
     * The 'included' status is initialized to false by default.
     *
     * @param name  The name of the item
     * @param weight  The weight of the item in pounds
     * @param value  The value of the item in dollars
     */
    public Item(String name, double weight, int value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
        this.included = false;  // Default inclusion is false
    }

    /**
     * Creates a new Item as a copy of the given item.
     *
     * @param other  The Item to copy from
     */
    public Item(Item other) {
        this.name = other.name;
        this.weight = other.weight;
        this.value = other.value;
        this.included = other.included;
    }

    /**
     * Gets the name of the item.
     *
     * @return The name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the weight of the item in pounds.
     *
     * @return The weight of the item
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Gets the value of the item in dollars.
     *
     * @return The value of the item
     */
    public int getValue() {
        return value;
    }

    /**
     * Checks if the item is included in the solution.
     *
     * @return true if the item is included, false otherwise
     */
    public boolean isIncluded() {
        return included;
    }

    /**
     * Sets whether the item is included in the solution.
     *
     * @param included true to include the item, false otherwise
     */
    public void setIncluded(boolean included) {
        this.included = included;
    }

    /**
     * Returns a string representation of the item, including its name, weight, and value.
     * Example format: "Jewelry (2.5 lbs, $300)"
     *
     * @return A string representing the item
     */
    @Override
    public String toString() {
        return name + " (" + weight + " lbs, $" + value + ")";
    }
}
