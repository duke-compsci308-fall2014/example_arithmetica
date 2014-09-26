/**
 * Represents an exceptional situation specific to this project.
 *
 * @author Robert C. Duvall
 */
public class ArithmeticaException extends RuntimeException {
    // for serialization
    private static final long serialVersionUID = 1L;

    /**
     * Create an exception based on an issue in our code.
     */
    public ArithmeticaException (String message) {
        super(message);
    }

    /**
     * Create an exception based on a caught exception.
     */
    public ArithmeticaException (Throwable exception) {
        super(exception);
    }
}
