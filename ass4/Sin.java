import java.util.Map;

/**
 * Sin class.
 *
 * @author maor biton
 *  
 */
public class Sin extends UnaryExpression implements Expression {
    /**
     * constructor - Expression .
     *
     * @param expression the var expression
     */
    public Sin(Expression expression) {
        super(expression);
    }

    /**
     * constructor - double.
     *
     * @param expression the num expression
     */
    public Sin(Double expression) {
        super(expression);
    }

    /**
     * constructor - int.
     *
     * @param expression the num expression
     */
    public Sin(int expression) {
        super(expression);
    }

    /**
     * constructor - String.
     *
     * @param expression the var expression
     */
    public Sin(String expression) {
        super(expression);
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
        return (Math.sin(Math.toRadians(super.getExpression().evaluate(assignment))));
    }
    /**
     * toString - create a string representation of the expression.
     *
     * @return a nice string representation of the expression
     */
    @Override
    public String toString() {
        return "sin(" + super.exToString() + ")";
    }

    /**
     * assign - Returns a new expression in which all occurrences of the variable.
     *
     * @param var the variable replaced with the provided expression.
     * @param expression the provided expression
     * @return the new expression
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Sin(super.getExpression().assign(var, expression));
    }
    /**
     * differentiate - Differentiate expression.
     *
     * @param var1 the var we Differentiate of
     * @return  expression
     */
    @Override
    public Expression differentiate(String var1) {
        return new Mult(new Cos(super.getExpression()),
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
            //do nothing
            Object flag = null;
        }
        return new Sin(super.getExpression().simplify());
    }
}
