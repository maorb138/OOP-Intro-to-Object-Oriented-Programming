import java.util.Map;

/**
 * Neg class.
 *
 * @author maor biton
 *  
 */
public class Neg extends UnaryExpression implements Expression {
    /**
     * constructor - Expression .
     *
     * @param expression the var expression
     */
    public Neg(Expression expression) {
        super(expression);
    }

    /**
     * constructor - double.
     *
     * @param expression the num expression
     */
    public Neg(Double expression) {
        super(expression);
    }

    /**
     * constructor - int.
     *
     * @param expression the num expression
     */
    public Neg(int expression) {
        super(expression);
    }

    /**
     * constructor - String.
     *
     * @param expression the var expression
     */
    public Neg(String expression) {
        super(expression);
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
        return new Neg(super.getExpression().assign(var, expression));
    }

    /**
     * differentiate - Differentiate expression.
     *
     * @param var1 the var we Differentiate of
     * @return expression expression
     */
    @Override
    public Expression differentiate(String var1) {
        if (super.getExpression().toString().equals(var1)) {

            return new Neg(super.getExpression().differentiate(var1));
        }
        return new Num(0);
    }

    /**
     * simplify - Simplify expression.
     *
     * @return the expression Simplify
     */
    @Override
    public Expression simplify() {
        try {
            return new Num(1 * this.evaluate());
        } catch (Exception e) {
            Object flag = null;
        }
        return new Neg(super.getExpression().simplify());
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
        return (-1 * (super.getExpression().evaluate(assignment)));
    }

    /**
     * toString - create a string representation of the expression.
     *
     * @return a nice string representation of the expression
     */
    @Override
    public String toString() {
        try {
            if (super.getExpression().evaluate() <= Double.MAX_VALUE) {
                return "(-" + super.exToString() + ")";
            }
        } catch (Exception e) {
            return "(-" + super.exToString() + ")";
        }
        return "(-" + super.exToString() + ")";
    }

}
