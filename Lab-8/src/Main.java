
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    /**
     * Creates a file, writes provided string to file, returns File object.
     *
     * @param filename the name of the file to be created
     * @param s the string content to be written to the file
     * @return the File object representing the created file
     * @throws IOException if an error occurs while writing to the file
     */
    public static File writeText(String filename, String s) throws IOException {
        File file = new File(filename);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(s);
        }
        return file;
    }

    /**
     * Counts number of words in file.
     * A word is any text separated by whitespace.
     *
     * @param f the file from which to count words
     * @return the number of words in the file
     * @throws IOException if an error occurs while reading from the file
     */
    public static int countWords(File f) throws IOException {
        try (Scanner fileScanner = new Scanner(f)) {
            int wordCount = 0;
            while (fileScanner.hasNext()) {
                fileScanner.next(); // Read the next word
                wordCount++;
            }
            return wordCount;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for filename
        System.out.print("What is the name of the file? ");
        String filename = scanner.nextLine();

        // Prompt user for message
        System.out.print("What would you like to print to the file? ");
        String message = scanner.nextLine();

        try {
            // Call the writeText method
            File createdFile = writeText(filename, message);
            System.out.println("File created: " + createdFile.getAbsolutePath());

            // Call the countWords method and display the result
            int wordCount = countWords(createdFile);
            System.out.println("The file \"" + filename + "\" contains " + wordCount + " words.");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close(); // Close the scanner
        }
    }
}
