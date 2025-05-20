//Utsav Acharya
// CS 1180
// Clarissa and Male TA Thank You for helping me out
//Project -3
// Chatgpt is use for comments

import java.io.*;
import java.util.*;


public class WordGame {

    private Set<String> validWords = new HashSet<>();
    private String gameWord;
    private List<Character> letters = new ArrayList<>();
    private Set<String> guessedWords = new HashSet<>();
    private int score = 0;

    /**
     * Constructor: Loads words from the dictionary file and selects a random 7-letter word.
     */
    public WordGame() {
        loadWords("src/word.txt"); // Load words from the file
        selectGameWord(); // Select a random word from the dictionary
        scrambleLetters(); // Scramble the letters of the selected word
    }

    /**
     * Loads valid words from the words.txt file that have exactly 7 unique letters.
     */
    private void loadWords(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String word;
            while ((word = br.readLine()) != null) {
                // Add all lowercase words to the set, trimming any extra spaces
                validWords.add(word.trim().toLowerCase());
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Selects a random word from the valid words list.
     */
    private void selectGameWord() {
        letters.add('a');
        while(letters.size() != 7) {
            letters.clear();
            List<String> wordList = new ArrayList<>(validWords);
            Collections.shuffle(wordList); // Shuffle the list to get a random word
            gameWord = wordList.get(0); // Select the first word after shuffling
            for (char c : gameWord.toCharArray()) {
                if(!letters.contains(c)) {
                    letters.add(c); // Store the characters of the selected word
                }
            }
        }
    }

    /**
     * Scrambles the letters and displays them.
     */
    private void scrambleLetters() {
        Collections.shuffle(letters); // Shuffle the list of letters
        System.out.print("Scrambled letters: ");
        for (char c : letters) {
            System.out.print(c + " "); // Print the scrambled letters
        }
        System.out.println();
    }

    /**
     * Validates a word, checks if it is made from scrambled letters, and calculates its score.
     */
    private int checkWord(String word) {
        word = word.trim().toLowerCase(); // Trim and convert the word to lowercase

        // Check if the word has already been guessed
        if (guessedWords.contains(word)) {
            System.out.println(word + " has already been guessed.");
            return 0;
        }

        // Check if the word length is valid
        if (word.length() < 4) {
            System.out.println(word + " is not valid. It must be at least 4 characters long.");
            return 0;
        }

        // Check if the word is valid and can be formed from the scrambled letters
        if (!validWords.contains(word)) {
            System.out.println(word + " is not a valid word.");
            return 0;
        }

        // Check if the word can be formed from the scrambled letters
        if (!canFormWord(word)) {
            System.out.println(word + " cannot be formed from the scrambled letters.");
            return 0;
        }

        // Add the word to guessed words and calculate points
        guessedWords.add(word);
        int points = calculatePoints(word);
        score += points;
        return points;
    }

    /**
     * Checks if the word can be formed from the scrambled letters.
     */
    private boolean canFormWord(String word) {
        List<Character> availableLetters = new ArrayList<>(letters);
        for (char c : word.toCharArray()) {
            if (!availableLetters.contains(c)) {
                return false;
            }
            //availableLetters.remove((Character) c); // Remove the used letter
        }
        return true;
    }

    /**
     * Calculates the score for a given word based on its length.
     */
    private int calculatePoints(String word) {
        int points = 0;
        if (word.length() == 4) {
            points = 1; // 4-letter words get 1 point
        } else if (word.length() > 4 && word.length() <= 5) {
            points = word.length(); // Words longer than 4 and up to 5 characters get points equal to their length
        } else if (word.length() > 5) {
            points = word.length() * 2; // Words longer than 5 characters get double points
        }
        return points;
    }

    /**
     * Starts the game loop, where the user can input commands and guesses.
     */
    public void play() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Word Game!");
        System.out.println("You can guess words using the scrambled letters.");

        while (true) {
            System.out.print("Enter a word, 'mix', 'ls', or 'bye': ");
            String action = scanner.nextLine().trim().toLowerCase();

            if (action.equals("bye")) {
                System.out.println("Thanks for playing! Final Score: " + score);
                break;
            }

            if (action.equals("mix")) {
                scrambleLetters(); // Shuffle and display the scrambled letters
            } else if (action.equals("ls")) {
                // List all valid words guessed and their scores
                System.out.println("Valid words guessed:");
                for (String word : guessedWords) {
                    int points = calculatePoints(word);
                    System.out.println(word + " (Score: " + points + ")");
                }
                System.out.println("Total Score: " + score);
            } else {
                // Check the word and update score
                int points = checkWord(action);
                if (points > 0) {
                    System.out.println(action + " scored " + points + " points.");
                }
                System.out.println("Total Score: " + score);
            }
        }

        scanner.close();
    }

    /**
     * Main method to start the game.
     */
    public static void main(String[] args) {
        WordGame game = new WordGame();
        game.play();
    }
}
