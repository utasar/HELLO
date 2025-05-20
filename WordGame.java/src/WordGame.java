import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class WordGame {
    private Set<String> validWords;
    private String gameWord;
    private List<Character> letters;
    private Set<String> guessedWords;
    private int score;

    public WordGame() {
        validWords = loadWords("src/word.txt");
        gameWord = selectWord();
        letters = new ArrayList<>();
        for (char c : gameWord.toCharArray()) {
            letters.add(c);
        }
        guessedWords = new HashSet<>();
        score = 0;
    }

    private Set<String> loadWords(String filename) {
        Set<String> words = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String word;
            while ((word = br.readLine()) != null) {
                if (word.length() == 7 && hasUniqueChars(word)) {
                    words.add(word);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return words;
    }

    private boolean hasUniqueChars(String word) {
        Set<Character> charSet = new HashSet<>();
        for (char c : word.toCharArray()) {
            charSet.add(c);
        }
        return charSet.size() == word.length();
    }

    private String selectWord() {
        List<String> wordList = new ArrayList<>(validWords);
        Collections.shuffle(wordList);
        return wordList.get(0);
    }

    private String scrambleLetters() {
        Collections.shuffle(letters);
        StringBuilder scrambled = new StringBuilder();
        for (char c : letters) {
            scrambled.append(c).append(" ");
        }
        return scrambled.toString().trim();
    }

    private int checkWord(String word) {
        if (guessedWords.contains(word) || word.length() < 4 || !validWords.contains(word)) {
            return 0; // Invalid word or already guessed
        }
        guessedWords.add(word);
        int points = word.length() == 4 ? 1 : word.length();
        if (word.length() > 5) {
            points *= 2; // Double points for words longer than 5
        }
        score += points;
        return points;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Word Game!");
        System.out.println("Scrambled letters: " + scrambleLetters());

        while (true) {
            System.out.print("Enter a word, 'mix', 'ls', or 'bye': ");
            String action = scanner.nextLine().trim();

            if (action.equals("bye")) {
                System.out.println("Thanks for playing!");
                break;
            } else if (action.equals("mix")) {
                System.out.println("Scrambled letters: " + scrambleLetters());
            } else if (action.equals("ls")) {
                System.out.println("Valid words guessed: " + guessedWords);
                System.out.println("Score: " + score);
            } else {
                int points = checkWord(action);
                if (points > 0) {
                    System.out.println(action + " scored " + points + " points.");
                } else {
                    System.out.println(action + " is not a valid guess.");
                }
                System.out.println("Score: " + score);
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        WordGame game = new WordGame();
        game.play();
    }
}
