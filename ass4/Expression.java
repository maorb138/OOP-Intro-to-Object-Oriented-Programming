import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Expression class.
 *
 * @author maor biton
 *  
 */

public interface Expression {
    /**
     * Evaluate - Evaluate the expression using the variable values provided in the assignment.
     *
     * @param assignment the map of the assignment
     * @return double a value of the assignment
     * @throws Exception If the expression contains a variable which is not in the assignment, exception is thrown.
     */
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * Evaluate - Evaluate the expression for empty assignment.
     *
     * @return the double
     * @throws Exception the exception
     */
    double evaluate() throws Exception;

    /**
     * getVariables - Gets variables.
     *
     * @return Returns a list of the variables in the expression.
     */
    ArrayList getVariables();

    /**
     * toString - create a nice string representation of the expression.
     *
     * @return a nice string representation of the expression
     */
    String toString();

    /**
     * assign - Returns a new expression in which all occurrences of the variable.
     *
     * @param var        the variable replaced with the provided expression.
     * @param expression the provided expression
     * @return the new expression
     */
    Expression assign(String var, Expression expression);
    /**
     * differentiate - Differentiate expression.
     *
     * @param var the var we Differentiate of
     * @return expression expression
     */
    Expression differentiate(String var);

    /**
     * simplify - Simplify expression.
     *
     * @return the expression Simplify
     */
    Expression simplify();

    /**
     * getOperationsList - Gets Operations.
     *
     * @return Returns a list of the Operations in the expression.
     */
    ArrayList getOperationsList();
    /**
     * getNums - Gets Nums.
     *
     * @return Returns a list of the Nums in the expression.
     */
    ArrayList getNums();

    /**
     * getVariablesHelp - all the Gets variables.
     *
     * @return Returns a list of all the variables in the expression.
     */
    List<String> getVariablesHelp();

}