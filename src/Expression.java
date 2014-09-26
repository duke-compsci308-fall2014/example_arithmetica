/**
 * An Expression represents a mathematical expression as a tree.
 *
 * In this format, the internal nodes represent mathematical functions and the
 * leaves represent constant values.
 *
 * @author former student solution
 * @author Robert C. Duvall (added comments, some code)
 */
public class Expression {
    private int myValue;
    private String myOp;
    private Expression myLeft;
    private Expression myRight;

    /**
     * Create an empty expression
     */
    public Expression () {
        // not needed, set by default
        this(0);
    }

    /**
     * Create expression representing the given constant value
     */
    public Expression (int value) {
        myValue = value;
        // not needed, set by default
        myOp = null; 
        myLeft = null;
        myRight = null;
    }

    /**
     * Create expression representing the given operation between the two given
     * sub-expressions.
     */
    public Expression (String op, Expression left, Expression right) {
        myOp = op;
        myLeft = left;
        myRight = right;
        // not needed, set by default
        myValue = 0; 
    }

    /**
     * @return value of expression
     */
    public int evaluate () {
        if (myOp == null) {
            return myValue;
        } else {
            if (myOp.equals("+")) {
                return myLeft.evaluate() + myRight.evaluate();
            } else if (myOp.equals("-")) {
                return myLeft.evaluate() - myRight.evaluate();
            } else if (myOp.equals("*")) {
                return myLeft.evaluate() * myRight.evaluate();
            } else if (myOp.equals("/")) {
                return myLeft.evaluate() / myRight.evaluate();
            } else {
                throw new ArithmeticaException("Unrecognized operator");
            }
        }
    }

    /**
     * @return string representation of expression
     */
    @Override
    public String toString () {
        StringBuilder result = new StringBuilder();
        if (myOp == null) {
            result.append(myValue);
        } else {
            result.append("(" + myLeft.toString() + " " + myOp + " " + myRight.toString() + ")");
        }
        return result.toString();
    }
}
