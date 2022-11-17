import java.util.Map;

/**
 * Div class.
 *
 * @author maor biton
 * 
 */
public class Div extends BinaryExpression implements Expression {
    /**
     * constructor - string and string.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Div(String varLeft, String varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - string and double.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Div(String varLeft, Double varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - string and Expression.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Div(String varLeft, Expression varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - int and int.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */

    public Div(int varLeft, int varRight) {
        super(varLeft, varRight);

    }

    /**
     * constructor - double and double.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Div(Double varLeft, Double varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - double and string.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Div(Double varLeft, String varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - double and Expression.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Div(Double varLeft, Expression varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - Expression and double.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Div(Expression varLeft, Double varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - Expression and string.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Div(Expression varLeft, String varRight) {
        super(varLeft, varRight);
    }

    /**
     * constructor - Expression and Expression.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public Div(Expression varLeft, Expression varRight) {
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
        return new Div(super.getLeftExpression().assign(var, expression),
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
        Expression left = new Mult(super.getLeftExpression().differentiate(var1), super.getRightExpression());
        Expression right = new Mult(super.getLeftExpression(), super.getRightExpression().differentiate(var1));
        Expression counter = new Minus(left, right);
        Expression denominator = new Pow(getRightExpression(), 2.0);
        return new Div(counter, denominator);
    }

    /**
     * simplify - Simplify expression.
     * Simplifies expression of right side equal to 1
     * Simplifies expression of both are equal
     * Simplifying the parts without variables
     *
     * @return the expression Simplify
     */
    @Override
    public Expression simplify() {
        Expression left = super.getLeftExpression().simplify();
        Expression right = super.getRightExpression().simplify();
        // Simplifies expression of right side equal to 1
        try {
            if (checkEqual(right, 1.0)) {
                return left;
            }
        } catch (Exception e) {
            //do nothing
            Object flag = null;
        }
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

        if (left.getVariables().isEmpty() && right.getVariables().isEmpty()) {
            try {
                return new Num(left.evaluate() / right.evaluate()).simplify();
            } catch (Exception e) {
                //do nothing
                Object flag = null;
            }
        }
        if (left.getVariables().isEmpty() && (!right.getVariables().isEmpty())) {
            try {
                return new Div(new Num(left.evaluate()), right);
            } catch (Exception e) {
                //do nothing
                Object flag = null;
            }
        }
        if ((!right.getVariables().isEmpty()) && left.getVariables().isEmpty()) {
            try {
                return new Div(left, new Num(right.evaluate()));
            } catch (Exception e) {
                //do nothing
                Object flag = null;
            }
        }
        //Can not be simplified
        return new Div(left, right);
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
        double leftExp = super.getLeftExpression().evaluate(assignment);
        double rightExp = super.getRightExpression().evaluate(assignment);
        try {
            if (rightExp == 0.0) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Exception!! divided by zero!!!");
            System.out.println("The expression is " + this.toString() + " and the answer is :");
        }
        return (leftExp / rightExp);
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
        String addTogether = "(" + leftString + " / " + rightString + ")";
        return addTogether;
    }
}
