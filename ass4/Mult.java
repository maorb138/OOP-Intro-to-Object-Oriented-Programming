import java.util.Map;

/**
 * Mult class.
 *
 * @author maor biton
 *  
 */
public class Mult extends BinaryExpression implements Expression {
    /**
     * constructor - int and int.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */

    public Mult(int varLeft, int varRight) {
        super(varLeft, varRight);

    }

    /**
     * constructor - string and string.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Mult(String varLeft, String varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - string and double.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Mult(String varLeft, Double varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - string and Expression.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Mult(String varLeft, Expression varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - double and double.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Mult(Double varLeft, Double varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - double and string.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Mult(Double varLeft, String varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - double and Expression.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Mult(Double varLeft, Expression varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - Expression and double.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Mult(Expression varLeft, Double varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - Expression and string.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Mult(Expression varLeft, String varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - Expression and Expression.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Mult(Expression varLeft, Expression varRight) {
        super(varLeft, varRight);
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
        return new Mult(super.getLeftExpression().assign(var, expression),
                super.getRightExpression().assign(var, expression));
    }

    /**
     * differentiate - Differentiate expression.
     *
     * @param var1 the var we Differentiate of
     * @return expression expression
     */
    @Override
    public Expression differentiate(String var1) {
        Expression left = super.getLeftExpression();
        Expression right = super.getRightExpression();
        return new Plus(new Mult(left.differentiate(var1), right), new Mult(left, right.differentiate(var1)));

    }

    /**
     * simplify - Simplify expression.
     * Simplifies expression of one side equal to 0.
     * Simplifies expression of one side equal to 1.
     * Simplifying the parts without variables
     *
     * @return the expression Simplify
     */
    @Override
    public Expression simplify() {
        Expression left = super.getLeftExpression().simplify();
        Expression right = super.getRightExpression().simplify();
        // At list one of the side equal to 0
        if (checkEqual(right, 0.0) || checkEqual(left, 0.0)) {
            return new Num(0.0);
        }
        // Simplifies expression of one side equal to 1
        if (checkEqual(left, 1.0)) {
            return right;
        }
        if (checkEqual(right, 1.0)) {
            return left;
        }
        // Simplifying the parts without variables
        if (left.getVariables().isEmpty() && right.getVariables().isEmpty()) {
            try {
                return new Num(left.evaluate() * right.evaluate()).simplify();
            } catch (Exception e) {
                //do nothing
                Object flag = null;
            }
        }
        if (left.getVariables().isEmpty() && (!right.getVariables().isEmpty())) {
            try {
                return new Mult(new Num(left.evaluate()), right);
            } catch (Exception e) {
                //do nothing
                Object flag = null;
            }
        }
        if ((!right.getVariables().isEmpty()) && left.getVariables().isEmpty()) {
            try {
                return new Mult(left, new Num(right.evaluate()));
            } catch (Exception e) {
                //do nothing
                Object flag = null;
            }
        }
        //Can not be simplified
        return new Mult(left, right);
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
        return (super.getLeftExpression().evaluate(assignment) * super.getRightExpression().evaluate(assignment));
    }

    /**
     * toString - create a string representation of the expression.
     *
     * @return a nice string representation of the expression
     */
    @Override
    public String toString() {
        String leftString = super.toStringLeftExpression();
        String rightString = super.toStringRightExpression();
        return "(" + leftString + " * " + rightString + ")";
    }
}
