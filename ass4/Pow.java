import java.util.Map;

/**
 * Pow class.
 *
 * @author maor biton
 *  
 */
public class Pow extends BinaryExpression implements Expression {

    /**
     * constructor - string and string.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Pow(String varLeft, String varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - int and int.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */

    public Pow(int varLeft, int varRight) {
        super(varLeft, varRight);

    }

    /**
     * constructor - string and double.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Pow(String varLeft, Double varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - string and Expression.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Pow(String varLeft, Expression varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - double and double.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Pow(Double varLeft, Double varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - double and string.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Pow(Double varLeft, String varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - double and Expression.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Pow(Double varLeft, Expression varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - Expression and double.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Pow(Expression varLeft, Double varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - Expression and string.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Pow(Expression varLeft, String varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - Expression and Expression.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Pow(Expression varLeft, Expression varRight) {
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
        return new Pow(super.getLeftExpression().assign(var, expression),
                super.getRightExpression().assign(var, expression));
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
        double rightExp = super.getLeftExpression().evaluate(assignment);
        double leftExp = super.getRightExpression().evaluate(assignment);
        if (leftExp == 0.0 && rightExp == 0.0) {
            throw new Exception("there is no power of 0^0");
        } else if ((leftExp < 0.0 && (rightExp > 0.0 && rightExp < 1.0))) {
            throw new Exception("there is no power of this expression");
        }
        double result = Math.pow(rightExp, leftExp);
        try {
            if (result >= Integer.MAX_VALUE) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Exception!! Result too high");
            System.out.println("The expression is " + this.toString() + " and the answer is :");
        }
        return result;
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
        return new Mult(new Pow(left, right),
                new Plus(new Mult(left.differentiate(var1), new Div(right, left)),
                        new Mult(right.differentiate(var1),
                                new Log("e", left))));

    }

    /**
     * simplify - Simplify expression.
     *
     * @return the expression Simplify
     */
    @Override
    public Expression simplify() {
        Expression left = super.getLeftExpression().simplify();
        Expression right = super.getRightExpression().simplify();
        if (checkEqual(left, 0.0) || checkEqual(right, 0.0)) {
            return new Num(1.0);
        }
        if (checkEqual(left, 1.0)) {
            return right;
        }
        if (checkEqual(right, 1.0)) {
            return left;
        }
        // Simplifying the parts without variables
        if (left.getVariables().isEmpty() && right.getVariables().isEmpty()) {
            try {
                return new Num(this.evaluate());
            } catch (Exception e) {
                return new Pow(left, right);
            }
        }
        return new Pow(left, right);
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
        return "(" + leftString + "^" + rightString + ")";
    }
}
