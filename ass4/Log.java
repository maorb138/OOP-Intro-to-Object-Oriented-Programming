import java.util.Map;

/**
 * Log class.
 *
 * @author maor biton
 *  
 */
public class Log extends BinaryExpression implements Expression {

    /**
     * constructor - string and string.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Log(String varLeft, String varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - int and int.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */

    public Log(int varLeft, int varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - string and double.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Log(String varLeft, Double varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - string and Expression.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Log(String varLeft, Expression varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - double and double.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Log(Double varLeft, Double varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - double and string.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Log(Double varLeft, String varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - double and Expression.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Log(Double varLeft, Expression varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - Expression and double.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Log(Expression varLeft, Double varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - Expression and string.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Log(Expression varLeft, String varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - Expression and Expression.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Log(Expression varLeft, Expression varRight) {
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
        double rightExp = super.getLeftExpression().evaluate(assignment);
        double base = super.getRightExpression().evaluate(assignment);
        if (base == 1 || base <= 0) {
            throw new Exception("The log base not legal");
        }
        if (rightExp <= 0) {
            throw new Exception("The right expression of the log smaller or equal to zero");
        }
        try {
            if (Math.log(rightExp) == 0.0) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Exception!! divided by zero!!!");
            System.out.println("The expression is " + this.toString() + " and the answer is :");
        }
        double result = Math.log(base) / Math.log(rightExp);
        return result;
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
        String together = "log(" + leftString + ", " + rightString + ")";
        return together;
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
        return new Log(super.getLeftExpression().assign(var, expression),
                super.getRightExpression().assign(var, expression));
    }

    /**
     * differentiate - Differentiate expression.
     * A division into two cases, one at the base having the variable and the other when there is no.
     *
     * @param var1 the var we Differentiate of
     * @return expression
     */
    @Override
    public Expression differentiate(String var1) {
        Expression left = super.getLeftExpression();
        Expression right = super.getRightExpression();
        //if the base having the variable
        if (left.getVariables().contains(var1)) {
            return new Div(new Neg(new Log("e", right)),
                    new Mult(left, new Pow(new Log("e", left), 2.0)));
        }
        return new Div(right.differentiate(var1),
                new Mult(right, new Log(new Var("e"), left)));
    }

    /**
     * simplify - Simplify expression.
     * Simplifies expression of tow side equals.
     * Simplifying the parts without variables
     *
     * @return the expression Simplify
     */
    public Expression simplify() {
        Expression left = super.getLeftExpression().simplify();
        Expression right = super.getRightExpression().simplify();
        // checking if  2 side are  num  and equal
        try {
            if (right.evaluate() == left.evaluate()) {
                return new Num(1.0);
            }
        } catch (Exception e) {
            //do nothing
            Object flag = null;
        }
        // checking if  2 side are val/exp and equal
        try {
            if (left.toString().equals(right.toString())) {
                return new Num(1.0);
            }
        } catch (Exception e) {
            //do nothing
            Object flag = null;
        }
        // Simplifying the parts without variables
        try {
            return new Num(this.evaluate());
        } catch (Exception e) {
            //Can not be simplified
            return new Log(left.simplify(), right.simplify());
        }

    }
}
