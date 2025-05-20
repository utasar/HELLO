/**
 * Subclass for handling Negative Side Length Exception
 */
public class NegativeLengthException extends Exception {
    /**
     * Constructor with a NegativeLengthException with custom error message
     * @param errorMsg The error message for displaying once the exception is thrown
     */
    public NegativeLengthException(String errorMsg) {
        super(errorMsg);
    }
}
