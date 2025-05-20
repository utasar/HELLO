//Utsav Acharya
//CLarissa
// Project 1
//Dhruv brother help me in cross over and campare to in Chromosome
//Google was used i just forgot what i was for (stack over flow , oracle,jetbrain and gpt was for java doc )
//https://www.geeksforgeeks.org/genetic-algorithms/
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.io.File;


/**
 * GeneticAlgorithm class implements a solution for the bin packing problem using genetic algorithms.
 * It aims to maximize the value of items selected by an individual, subject to a weight limit of 10 pounds.
 * The algorithm performs over multiple generations, applying initialization, crossover, mutation, and selection.
 */
public class GeneticAlgorithm {
    private static Random rng = new Random(); // Random number generator for crossover and mutation

    /**
     * Reads the data from a file. Each line contains the item name, weight, and value, separated by a comma and space.
     *
     * @param filename the name of the file containing the item data
     * @return an ArrayList of Item objects representing the items loaded from the file
     * @throws FileNotFoundException if the specified file is not found
     */
    public static ArrayList<Item> readData(String filename) throws FileNotFoundException {
        ArrayList<Item> items = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filename));

        // Read each line from the file and parse the data into Item objects
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(", ");
            String name = parts[0];
            double weight = Double.parseDouble(parts[1]);
            int value = Integer.parseInt(parts[2]);
            items.add(new Item(name, weight, value));
        }
        scanner.close();
        return items;
    }

    /**
     * Initializes the population of chromosomes. Each chromosome is randomly created based on the items.
     *
     * @param items the list of items to be considered while creating chromosomes
     * @param populationSize the number of chromosomes to generate in the population
     * @return an ArrayList of Chromosome objects representing the initial population
     */
    public static ArrayList<Chromosome> initializePopulation(ArrayList<Item> items, int populationSize) {
        ArrayList<Chromosome> population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            population.add(new Chromosome(items)); // Create a new chromosome for each individual in the population
        }
        return population;
    }

    /**
     * The main method that runs the genetic algorithm for 20 generations.
     * It reads the item data, initializes the population, and performs genetic operations.
     *
     * @param args command-line arguments (not used in this implementation)
     * @throws FileNotFoundException if the file 'items.txt' cannot be found
     */
    public static void main(String[] args) throws FileNotFoundException {
        // Step 1: Read item data from a file
        ArrayList<Item> items = readData("src\\Items.txt");

        // Step 2: Initialize the population with a size of 10
        ArrayList<Chromosome> population = initializePopulation(items, 10);

        // Step 3: Run the genetic algorithm for 20 generations
        for (int generation = 0; generation < 20; generation++) { // Iterate for 20 generations
            ArrayList<Chromosome> nextGeneration = new ArrayList<>(population);
            Collections.sort(nextGeneration); // Sort the population based on fitness

            // Step 4: Select the fittest individuals for the next generation
            population.clear();
            population.addAll(nextGeneration.subList(0, 10)); // Add the top 10 fittest individuals to the next generation

            // Step 5: Crossover to create new offspring (children)
            while (population.size() < 10) {
                Chromosome parent1 = nextGeneration.get(rng.nextInt(nextGeneration.size())); // Select first parent
                Chromosome parent2 = nextGeneration.get(rng.nextInt(nextGeneration.size())); // Select second parent
                Chromosome child = parent1.crossover(parent2); // Perform crossover to create a child
                population.add(child); // Add child to the population
            }

            // Step 6: Mutate 10% of the population to introduce variation
            for (int i = 0; i < population.size() * 0.1; i++) {
                population.get(rng.nextInt(population.size())).mutate(); // Mutate a random individual
            }

            // Step 7: Sort the population by fitness after crossover and mutation
            Collections.sort(population);
        }

        // Step 8: Output the fittest individual (the solution) after 20 generations
        System.out.println("Fittest Individual:");
        System.out.println(population.get(0).toString()); // Print the fittest individual from the population
    }
}
