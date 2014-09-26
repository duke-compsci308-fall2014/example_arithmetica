import java.util.Scanner;
import java.util.Stack;

/**
 * Parses a string into an expression tree based on rules for arithmetic.
 *
 * @author former student solution
 * @author Robert C. Duvall (added comments, exceptions)
 */
public class Parser {
    /**
     * Converts the given string into expression tree for easier manipulation.
     *
     * This is an implementation of Dijkstra's algorithm for converting infix to
     * postfix notation. It iterates through the infix version, adding operands
     * to end of one stack and keeping operators on another. When an operator is
     * encountered, all operators that have higher precedence (i.e., should be
     * evaluated first) are popped off operator stack with their corresponding
     * operands from the operand stack and combined into new operand. After the
     * input is exhausted, any remaining operators are popped similarly.
     *
     * By giving grouping operators lower precedence than anything operator and
     * creating special case that prevents group opening operators from popping
     * previous operators off the stack, this algorithm can be adapted to give
     * such operators the effect of boosting the precedence of other operators.
     */
    public Expression makeExpression (String infix) {
        Stack<String> operators = new Stack<>();
        Stack<Expression> operands = new Stack<>();

        Scanner in = new Scanner(infix);
        while (in.hasNext()) {
            String token = in.next();
            int order = orderOfOperation(token);
            // constant value -- create expression directly
            if (order == 0) {
                try {
                    operands.add(new Expression(Integer.parseInt(token)));
                } catch (NumberFormatException e) {
                    throw new ArithmeticaException("unrecognized input");
                }
            }
            // operator -- convert previous higher order operators before push
            else {
                if (!isOpenGroup(token)) {
                    while (operators.size() > 0 && orderOfOperation(operators.peek()) >= order) {
                        String op = operators.pop();
                        if (!isOpenGroup(op)) {
                            // convert operator into expression
                            if (operands.size() > 1) {
                                Expression right = operands.pop();
                                Expression left = operands.pop();
                                operands.add(new Expression(op, left, right));
                            } else {
                                throw new ArithmeticaException("not enough arguments to operator");
                            }
                        }
                    }
                }
                if (!isCloseGroup(token)) {
                    operators.push(token);
                }
            }
        }
        // resolve any remaining operators
        while (operators.size() > 0) {
            String op = operators.pop();
            if (!isOpenGroup(op)) {
                // convert operator into expression
                if (operands.size() > 1) {
                    Expression right = operands.pop();
                    Expression left = operands.pop();
                    operands.add(new Expression(op, left, right));
                } else {
                    throw new ArithmeticaException("not enough arguments to operator");
                }
            }
        }
        // all operators handled, should be only one operand left, the result
        if (operands.size() == 1) {
            return operands.pop();
        } else {
            throw new ArithmeticaException("ill-formatted expression");
        }
    }

    /**
     * @return true iff operator signifies beginning of group expression
     */
    private boolean isOpenGroup (String operator) {
        return operator.equals("(");
    }

    /**
     * @return true iff operator signifies end of group expression
     */
    private boolean isCloseGroup (String operator) {
        return operator.equals(")");
    }

    /**
     * @return true if operator signifies beginning or end of group expression
     */
    private boolean isGrouping (String operator) {
        return isOpenGroup(operator) || isCloseGroup(operator);
    }

    /**
     * @return relative importance of operators (highest precedence indicates
     *         that operation should be performed first)
     */
    private int orderOfOperation (String operator) {
        if (isGrouping(operator)) {
            return 1;
        } else if (operator.equals("+") || operator.equals("-")) {
            return 2;
        } else if (operator.equals("*") || operator.equals("/")) {
            return 3;
        } else {
            return 0;
        }
    }
}
