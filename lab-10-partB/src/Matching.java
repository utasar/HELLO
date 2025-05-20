// Utsav Acharya
// Lab-10-PartB

/**
 * The Matching class contains a recursive method to check if a given string
 * is a properly nested sequence of parentheses.
 */
public class Matching {

    /**
     * Recursively checks whether the given string is a valid nesting of zero or more pairs
     * of parentheses. A valid nesting must consist only of matching open and close
     * parentheses in proper order and cannot contain any non-parenthesis characters.
     *
     * @param n the input string to check
     * @return true if the string is properly nested, false otherwise
     */
    public static boolean nestParen(String n) {
        // Base case: An empty string is valid
        if (n.isEmpty()) {
            return true;
        }

        // If the first and last characters are parentheses, recurse on the inside
        if (n.charAt(0) == '(' && n.charAt(n.length() - 1) == ')') {
            return nestParen(n.substring(1, n.length() - 1));
        }

        // If the string has other characters or mismatched parentheses, it's invalid
        return false;
    }

    /**
     * Tests the nestParen method with various example cases.
     */
    public static void main(String[] args) {
        System.out.println("nestParen(\"(())\") → " + nestParen("(())"));              // true
        System.out.println("nestParen(\"((()))\") → " + nestParen("((()))"));          // true
        System.out.println("nestParen(\"(((x))\") → " + nestParen("(((x))"));          // false
        System.out.println("nestParen(\"((())\") → " + nestParen("((())"));            // false
        System.out.println("nestParen(\"((()()\") → " + nestParen("((()()"));          // false
        System.out.println("nestParen(\"\") → " + nestParen(""));                      // true
        System.out.println("nestParen(\"(yy)\") → " + nestParen("(yy)"));              // false
        System.out.println("nestParen(\"((yy())))\") → " + nestParen("((yy())))"));    // false
    }
}
