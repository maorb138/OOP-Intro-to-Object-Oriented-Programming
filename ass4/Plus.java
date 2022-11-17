import java.util.ArrayList;
import java.util.Map;

/**
 * Plus class.
 *
 * @author maor biton
 *  
 */
public class Plus extends BinaryExpression implements Expression {
    /**
     * constructor - string and string.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Plus(String varLeft, String varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - string and double.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Plus(String varLeft, Double varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - int and int.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */

    public Plus(int varLeft, int varRight) {
        super(varLeft, varRight);

    }

    /**
     * constructor - string and Expression.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Plus(String varLeft, Expression varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - double and double.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Plus(Double varLeft, Double varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - double and string.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Plus(Double varLeft, String varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - double and Expression.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Plus(Double varLeft, Expression varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - Expression and double.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Plus(Expression varLeft, Double varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - Expression and string.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Plus(Expression varLeft, String varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - Expression and Expression.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Plus(Expression varLeft, Expression varRight) {
        super(varLeft, varRight);
    }

    /**
     * Evaluate - Evaluate the expression using the variable values provided in the assignment.
     *
     * @param assignment the map of the assignment
     * @return double a value of the assignment
     * @throws Exception If the expression contains a variable which is not in the assignment, exception is thrown.
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return (super.getLeftExpression().evaluate(assignment) + super.getRightExpression().evaluate(assignment));

    }

    /**
     * toString - create a string representation of the expression.
     *
     * @return a nice string representation of the expression
     */
    public String toString() {
        String leftString = super.toStringLeftExpression();
        String rightString = super.toStringRightExpression();
        return "(" + leftString + " + " + rightString + ")";
    }

    /**
     * assign - Returns a new expression in which all occurrences of the variable.
     *
     * @param var        the variable replaced with the provided expression.
     * @param expression the provided expression
     * @return the new expression
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Plus(super.getLeftExpression().assign(var, expression),
                super.getRightExpression().assign(var, expression));
    }

    /**
     * differentiate - Differentiate expression.
     *
     * @param var1 the var we Differentiate of
     * @return expression
     */
    @Override
    public Expression differentiate(String var1) {
        return new Plus(super.getLeftExpression().differentiate(var1),
                super.getRightExpression().differentiate(var1));
    }

    /**
     * simplify - Simplify expression.
     * Simplifies expression of one side equal to 0.
     * Simplifying the parts without variables
     *
     * @return the expression Simplify
     */
    @Override
    public Expression simplify() {
        Expression left = super.getLeftExpression().simplify();
        Expression right = super.getRightExpression().simplify();
        if (checkEqual(right, 0.0)) {
            return left;
        }
        if (checkEqual(left, 0.0)) {
            return right;
        }
        if (left.getVariables().isEmpty() && right.getVariables().isEmpty()) {
            try {
                return new Num(left.evaluate() + right.evaluate()).simplify();
            } catch (Exception e) {
                //do nothing
                Object flag = null;
            }
        }
        if (left.getVariables().isEmpty() && (!right.getVariables().isEmpty())) {
            try {
                return new Plus(new Num(left.evaluate()).simplify(), right);
            } catch (Exception e) {
                //do nothing
                Object flag = null;
            }
        }
        if ((!left.getVariables().isEmpty()) && right.getVariables().isEmpty()) {
            try {
                return new Plus(left, new Num(right.evaluate()));
            } catch (Exception e) {
                //do nothing
                Object flag = null;
            }
        }
        return new Plus(left, right);
    }

    /**
     * sumGetNum - get the sum of the arr.
     *
     * @param arr the arr
     * @return the double
     */
    public double sumGetNum(ArrayList arr) {
        double sum = 0;
        for (int i = 0; i < arr.size(); i++) {
            sum += (double) arr.get(i);
        }
        return sum;
    }

}

