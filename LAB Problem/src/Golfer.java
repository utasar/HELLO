
public class Golfer implements Comparable<Golfer> {

    private String firstName;
    private String lastName;
    private int score;
    private int holesCompleted;

    /**
     * Constructs a new Golfer with the specified details.
     *
     * @param firstName the first name of the golfer
     * @param lastName the last name of the golfer
     * @param score the golfer's score
     * @param holesCompleted the number of holes completed by the golfer
     */
    public Golfer(String firstName, String lastName, int score, int holesCompleted) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.score = score;
        this.holesCompleted = holesCompleted;
    }

    /**
     * Returns a string representation of the Golfer object in the format:
     * <code>lastName, firstName: score with holesCompleted holes completed</code>.
     *
     * @return a string representation of the Golfer object
     */
    @Override
    public String toString() {
        return lastName + ", " + firstName + ":" + score + " with " + holesCompleted + " holes completed";
    }

    /**
     * Compares this Golfer object to another Golfer object for order.
     * The comparison is based on the following criteria:
     * <ol>
     *     <li>Score (lower score is better),</li>
     *     <li>If scores are the same, compare the number of holes completed (higher is better),</li>
     *     <li>If both score and holes completed are the same, compare lexicographically by last name (ignoring case),</li>
     *     <li>If all other criteria are the same, compare lexicographically by first name (ignoring case).</li>
     * </ol>
     *
     * @param other the Golfer object to be compared
     * @return a negative integer, zero, or a positive integer as this Golfer is less than, equal to, or greater than the specified Golfer
     */
    @Override
    public int compareTo(Golfer other) {
        // 1. Compare by score first (more negative score comes first)
        if (this.score != other.score) {
            return Integer.compare(this.score, other.score);  // More negative score comes first
        }

        // 2. If scores are the same, compare by holes completed (higher is better)
        if (this.holesCompleted != other.holesCompleted) {
            return Integer.compare(other.holesCompleted, this.holesCompleted);  // More holes completed comes first
        }

        // 3. If both score and holes completed are the same, compare lexicographically by last name (ignoring case)
        int lastNameComparison = this.lastName.compareToIgnoreCase(other.lastName);
        if (lastNameComparison != 0) {
            return lastNameComparison;  // Lexicographical comparison by last name
        }

        // 4. If both score, holes completed, and last name are the same, compare lexicographically by first name (ignoring case)
        return this.firstName.compareToIgnoreCase(other.firstName);  // Lexicographical comparison by first name
    }
}
