import java.util.ArrayList;
import java.util.Random;

/**
 * The Chromosome class represents a collection of Item objects and provides methods
 * for performing genetic operations such as crossover, mutation, and fitness calculation.
 */
public class Chromosome extends ArrayList<Item> implements Comparable<Chromosome> {

    private static Random rng = new Random();  // Random number generator for crossover and mutation

    /**
     * Default constructor for a Chromosome object.
     * This constructor can be empty, as we will populate it later.
     */
    public Chromosome() {
        // No implementation needed for now.
    }

    /**
     * Constructor that creates a Chromosome from a list of items.
     * For each item, randomly determines whether it should be included in the Chromosome.
     *
     * @param items A list of Item objects to create the Chromosome from.
     */
    public Chromosome(ArrayList<Item> items) {
        for (int i = 0; i < items.size(); i++) {
            Item changingIncluding = items.get(i);
            changingIncluding.setIncluded(rng.nextBoolean());  // Randomly set the 'included' field
            this.add(new Item(changingIncluding));  // Add a copy of the item to the chromosome
        }
    }

    /**
     * Performs the crossover operation between this Chromosome and another Chromosome
     * to create and return a new child Chromosome.
     * A random selection is made for each item, choosing from either this Chromosome
     * or the other Chromosome.
     *
     * @param other The other Chromosome to perform crossover with.
     * @return A new Chromosome representing the child produced by the crossover.
     */
    public Chromosome crossover(Chromosome other) {
        Chromosome child = new Chromosome();  // Create a new Chromosome (child)

        for (int i = 0; i < this.size(); i++) {
            int choosingParent = rng.nextInt(10) + 1;  // Generate a random number between 1 and 10
            if (choosingParent <= 5) {
                child.add(new Item(this.get(i)));  // Take item from this Chromosome
            } else {
                child.add(new Item(other.get(i)));  // Take item from the other Chromosome
            }
        }

        return child;  // Return the newly created child Chromosome
    }

    /**
     * Performs the mutation operation on this Chromosome.
     * A random mutation is applied to each item in the Chromosome.
     * If an item is included, it will be excluded, and vice versa.
     */
    public void mutate() {
        for (Item item : this) {
            int mutation = rng.nextInt(10) + 1;  // Generate a random number between 1 and 10
            if (mutation == 1) {  // With a 1/10 chance, mutate the item
                item.setIncluded(!item.isIncluded());  // Flip the 'included' status
            }
        }
    }

    /**
     * Calculates the fitness of this Chromosome.
     * The fitness is determined by the sum of the values of the items that are included.
     * If the total weight exceeds 10 pounds, the fitness is set to 0.
     *
     * @return The fitness of this Chromosome.
     */
    public int getFitness() {
        int totalValue = 0;
        double totalWeight = 0;

        for (Item item : this) {
            if (item.isIncluded()) {
                totalWeight += item.getWeight();  // Add item weight
                totalValue += item.getValue();  // Add item value
            }
        }

        if (totalWeight > 10) {
            return 0;  // Fitness is 0 if the total weight exceeds 10 pounds
        } else {
            return totalValue;  // Return the total value of included items
        }
    }

    /**
     * Compares the fitness of this Chromosome to another Chromosome.
     * Returns -1 if this Chromosome's fitness is greater, +1 if it's less, and 0 if they are the same.
     *
     * @param other The other Chromosome to compare to.
     * @return -1, 1, or 0 based on the comparison of fitness.
     */
    @Override
    public int compareTo(Chromosome other) {
        if (this.getFitness() < other.getFitness()) {
            return +1;  // If this Chromosome's fitness is less, return +1
        }
        if (this.getFitness() == other.getFitness()) {
            return 0;  // If fitness is the same, return 0
        } else {
            return -1;  // If this Chromosome's fitness is greater, return -1
        }
    }

    /**
     * Returns a string representation of this Chromosome.
     * It includes the name, weight, and value of all items that are included,
     * followed by the Chromosome's fitness.
     *
     * @return A string representing the Chromosome.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (Item item : this) {
            if (item.isIncluded()) {
                result.append("\n" + item.toString());  // Append included item to result
            }
        }

        result.append("\n" + "This Chromosome has a fitness of " + this.getFitness());  // Append fitness
        return result.toString();  // Return the final string representation
    }
}
