/*
 * @author UA (Utsav Acharya)
 * 13 November 2024
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class WordGame {
    /**
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<String> words = loadWordsfromFile("words.txt");
        String selectedWord = chooseWord(words);
        char[] letters = scrambleWord(selectedWord);
        ArrayList<String> wordsEntered = new ArrayList();
        int score = 0; // initilizes the score to 0
        Scanner scnr = new Scanner(System.in);
        System.out.println("Score is:"+score);
        System.out.println("================");
        // System.out.println("Make your word(s) with those letters: " + new String(letters));
        // above code is formated and written below:
        System.out.println("Guess the word(s) made with those letters");
        for (char c: letters)
        {
            System.out.print(c+"  ");
        }
        System.out.println();
        System.out.println("=================");
        System.out.println("Enter your words or commands (mix, ls, bye):");
        // I have used if else statement within while loop but it can also be used with switch statement
        // with in while loop
        while (true) {
            String userInput = scnr.nextLine().trim().toLowerCase();// convert user input to lower case
            if (userInput.equals("bye")) // check if user entered bye->> so exit the loop
            {
                break;
            }
            else if (userInput.equals("mix")) // check if user entered mix to scramble
            {
                letters = scrambleWord(selectedWord);
                System.out.print("scrambled letters: ");
                for (char c : letters)
                {
                    System.out.print(c+"  ");
                }
                System.out.println();
                //=>> Following code commented and replaced by above just for the purpose of formating
                // System.out.println("Scrambled letters: " + new String(letters));
            }
            else if (userInput.equals("ls"))
            {
                //System.out.println("Guessed words: " + String.join(", ", wordsEntered));
                // replacement of the code written below for the formated output as per question
                System.out.println("Guessed words: ");
                for (String s: wordsEntered)
                {
                    System.out.println("  "+s);
                }
            }
            else {
                if (wordsEntered.contains(userInput))
                {
                    System.out.println("'" + userInput + "' has already been guessed.");
                    System.out.println("---------------------------------------------");
                }
                else if (checkValidity(userInput, letters, words))
                {
                    wordsEntered.add(userInput);
                    int points = calculatePoints(userInput);
                    score += points;
                    System.out.println("Score: " + points);

                }
                else
                {
                    System.out.println("'" + userInput + "' is not valid or not in words.txt)");
                    System.out.println("-----------------");
                }
            }
            System.out.println("===============");
            System.out.println("Total Score: " + score);
            System.out.println("===============");
        }
        scnr.close();
    }
    /**
     * loads the words from the file words.txt into the arrayList of string
     * @param filePath sends the full path of the file as parameter
     * @return returns the arraylist of type string
     */
    public static ArrayList<String> loadWordsfromFile(String filePath) {
        ArrayList<String> words = new ArrayList();
        try {
            Scanner scanner = new Scanner(new FileInputStream(filePath));
            while (scanner.hasNextLine()) {
                words.add(scanner.nextLine().trim());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error reading words file: " + e.getMessage());
        }
        return words;
    }
    /**
     * check 7 letters words, creates the Array list of 7 letters words and returns one random single 7 letters word with distinct
     * @param wordList as ArrayList passed
     * @param wordsList the list of words to choose from
     * @return a randomly chosen seven-letter word with seven unique characters from the list
     */
    public static String chooseWord(ArrayList<String> wordsList) {
        Random rand = new Random();
        ArrayList<String> sevenLetterWords = new ArrayList();
        for (String word : wordsList) {
            if (word.length() == 7 && word.chars().distinct().count() == 7) { // 7 unique letter word
                sevenLetterWords.add(word);
            }
        }
        return sevenLetterWords.get(rand.nextInt(sevenLetterWords.size())); // returns random 7 letter word
    }
    /**
     * scrambleWord randomly scramble the characters within the word
     * shuffles the list randomly, and then converts it back into a character array
     * The resulting character array contains the characters of the original word in a random order
     * @param string - word the word to be scrambled
     * @return a character array with the characters of the input word in random order
     */
    public static char[] scrambleWord(String word) {
        ArrayList<Character> characters = new ArrayList();// since we can easily use suffle we treat it as character string
        for (char c : word.toCharArray()) {
            characters.add(c);
        }
        Collections.shuffle(characters);
        char[] scrambled = new char[characters.size()];
        for (int i = 0; i < scrambled.length; i++) {
            scrambled[i] = characters.get(i);
        }
        return scrambled;
    }
    /**
     * Checks the validity of a given word against a set of scrambled letters and a list of valid words.
     * This method verifies that the entered word meets the following conditions:
     * The word length is at least 4 characters.
     * The word is present in the list of valid words.
     * All characters of the word are found within the provided scrambled letters.
     * @param wordEntered the word to be checked for validity
     * @param letters an array of scrambled letters available for forming words
     * @param validWords a list of valid words against which the entered word is checked
     * @return {@code true} if the word meets all validity conditions; {@code false} otherwise
     */
    public static boolean checkValidity(String wordEntered, char[] letters, ArrayList<String> validWords)
    {
        if (wordEntered.length() < 4 || !validWords.contains(wordEntered)) { // if word length is less than 4 or not in the wordlist
            return false;
        }
        ArrayList<Character> letterList = new ArrayList();
        for (char c : letters)
        {
            letterList.add(c);// transfered the scrambled letters to letterlist
        }
        for (char c : wordEntered.toCharArray())
        {
            // since characters can be repeated as mentioned by question we just need to check the check the character exist on it or not
            if (!letterList.contains(c))// if the character not found
            {
                //  System.out.println("Character not in the choice: "+c);
                return false;
            }
        }
        return true;
    }

    public static int calculatePoints(String word) {
        return word.length() == 4 ? 1 : word.length(); // use of short hand if else
    }
}
