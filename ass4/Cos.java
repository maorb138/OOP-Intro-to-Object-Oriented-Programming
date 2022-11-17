import java.util.Map;

/**
 * Cos class.
 *
 * @author maor biton
 * 
 */
public class Cos extends UnaryExpression implements Expression {
    /**
     * constructor - Expression.
     *
     * @param expression the var expression
     */
    public Cos(Expression expression) {
        super(expression);
    }

    /**
     * constructor - double.
     *
     * @param expression the num expression
     */
    public Cos(Double expression) {
        super(expression);
    }

    /**
     * constructor - int.
     *
     * @param expression the num expression
     */
    public Cos(int expression) {
        super(expression);
    }

    /**
     * constructor - String.
     *
     * @param expression the var expression
     */
    public Cos(String expression) {
        super(expression);
    }

    /**
     * Evaluate - Evaluate the expression using the variable values provided in the assignment.
     *
     * @param assignment the map of the assignment
     * @return double a value of the assignment
     * @throws Exception If the expression contains a variable which is not in the assignment, exception is thrown.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return (Math.cos(Math.toRadians(super.getExpression().evaluate(assignment))));
    }

    @Override
    /**
     * toString - create a string representation of the expression.
     *
     * @return a nice string representation of the expression
     */
    public String toString() {
        return "cos(" + super.exToString() + ")";
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
        return new Cos(super.getExpression().assign(var, expression));
    }

    /**
     * differentiate - Differentiate expression.
     *
     * @param var1 the var we Differentiate of
     * @return expression
     */
    @Override
    public Expression differentiate(String var1) {
        return new Mult(new Neg(new Sin(super.getExpression())),
                super.getExpression().differentiate(var1));
    }

    /**
     * simplify - Simplify expression.
     *
     * @return the expression Simplify
     */
    @Override
    public Expression simplify() {
        try {
            return new Num(this.evaluate());
        } catch (Exception e) {
            //catch do nothing!!
            Object flag = null;
        }
        return new Cos(super.getExpression().simplify());
    }
}
