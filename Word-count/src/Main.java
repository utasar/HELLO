import java.io.*;
import java.nio.file.*;
import java.util.*;

public class WordGame {
    private List<String> words;
    private String currentWord;
    private int score;
    private Set<String> guessedWords;

    public WordGame() {
        words = loadWords();
        guessedWords = new HashSet<>();
        score = 0;
    }

    // Load words from the file and filter for 7 unique letters
    private List<String> loadWords() {
        List<String> validWords = new ArrayList<>();
        try {
            List<String> allWords = Files.readAllLines(Paths.get("words.txt"));
            for (String word : allWords) {
                if (word.length() == 7 && hasUniqueLetters(word)) {
                    validWords.add(word);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading words file: " + e.getMessage());
        }
        return validWords;
    }

    // Check if a word has all unique letters
    private boolean hasUniqueLetters(String word) {
        Set<Character> letters = new HashSet<>();
        for (char c : word.toCharArray()) {
            letters.add(c);
        }
        return letters.size() == word.length();
    }

    // Choose a random word from the valid words
    private void chooseWord() {
        Random rand = new Random();
        currentWord = validWords.get(rand.nextInt(validWords.size()));
        displayLetters();
    }

    // Display scrambled letters
    private void displayLetters() {
        char[] letters = currentWord.toCharArray();
        List<Character> letterList = new ArrayList<>();
        for (char letter : letters) {
            letterList.add(letter);
        }
        Collections.shuffle(letterList);
        System.out.println("Letters: " + letterList);
    }

    // Validate and score the guessed word
    private int validateWord(String word) {
        if (guessedWords.contains(word) || word.length() < 4 || !words.contains(word)) {
            return 0;
        }
        guessedWords.add(word);
        int points = (word.length() == 4) ? 1 : word.length();
        if (word.length() > 5) {
            points *= 2;
        }
        return points;
    }

    // Main game loop
    public void play() {
        chooseWord();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter a command (mix, ls, bye or a word): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Goodbye!");
                break;
            } else if (input.equalsIgnoreCase("mix")) {
                displayLetters();
            } else if (input.equalsIgnoreCase("ls")) {
                System.out.println("Guessed words: " + guessedWords);
            } else {
                int points = validateWord(input);
                score += points;
                if (points > 0) {
                    System.out.println("Score: " + score);
                } else {
                    System.out.println("Invalid word or already guessed.");
                }
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        WordGame game = new WordGame();
        game.play();
    }
}
