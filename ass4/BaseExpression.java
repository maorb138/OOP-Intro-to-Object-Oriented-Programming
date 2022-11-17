import java.util.Collections;
import java.util.Map;

/**
 * BaseExpression abstract class.
 *
 * @author maor biton
 *  
 */
public abstract class BaseExpression {
    private Expression exp;

    /**
     * Evaluate - Evaluate the expression for empty assignment.
     *
     * @return the double
     * @throws Exception the exception
     */
    public double evaluate() throws Exception {
        return this.evaluate(Collections.emptyMap());
    }

    /**
     * Evaluate - Evaluate the expression using the variable values provided in the assignment.
     *
     * @param assignment the map of the assignment
     * @return double a value of the assignment
     * @throws Exception If the expression contains a variable which is not in the assignment, exception is thrown.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return this.exp.evaluate(assignment);
    }

    /**
     * checkEqual Check equal the Expression and the num.
     *
     * @param exp1 the exp1
     * @param num  the num
     * @return the boolean
     */
    public boolean checkEqual(Expression exp1, Double num) {
        return exp1.toString().equals(Double.toString(num));
    }

    /**
     * checkEqual Check equal the Expression and the string.
     *
     * @param exp1 the exp1
     * @param var1 the string
     * @return the boolean
     */
    public boolean checkEqual(Expression exp1, String var1) {
        return exp1.toString().equals(var1);
    }

    /**
     * checkEqual Check equal the Expression and the Expression.
     *
     * @param exp1 the Expression 1
     * @param exp2 the Expression 2
     * @return the boolean
     */
    public boolean checkEqual(Expression exp1, Expression exp2) {
        return exp1.toString().equals(exp2.toString());
    }

}
