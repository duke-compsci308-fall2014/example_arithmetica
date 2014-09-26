import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Repeatedly prompts the user for expressions to parse and evaluate.
 *
 * This style of interaction is called the read-eval-print loop and forms the
 * basis of most interactive programming environments.
 *
 * @author former student solution
 * @author Robert C. Duvall (added comments, constants)
 */
public class Main {
    public static final String VERSION = "0.1";
    public static final String TITLE = "Arithmetica";
    public static final String EXIT_COMMAND = ".";
    public static final String PROMPT = "-> ";

    public static void main (String[] args) {
        Scanner input = new Scanner(new InputStreamReader(System.in));
        Parser parser = new Parser();
        int numExpressions = 1;

        System.out.println("Welcome to " + TITLE + " " + VERSION);
        System.out.print(numExpressions + PROMPT);
        while (input.hasNext()) {
            String line = input.nextLine();
            if (line == null || line.equals(EXIT_COMMAND)) {
                break;
            } else {
                try {
                    Expression e = parser.makeExpression(line);
                    System.out.println(line + " -> " + e + " = " + e.evaluate());
                    numExpressions++;
                    System.out.print(numExpressions + PROMPT);
                } catch (ArithmeticaException e) {
                    System.out.println("Invalid expression: " + line + "\n --- " + e.getMessage());
                }
            }
        }
    }
}
