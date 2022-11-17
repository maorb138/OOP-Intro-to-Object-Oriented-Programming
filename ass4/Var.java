import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 * Var class.
 *
 * @author maor biton
 * 
 */

public class Var implements Expression {
    private String var;

    /**
     * constructor - Instantiates a new Var.
     *
     * @param var the variables (Double)
     */
    public Var(String var) {
        this.var = var;
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
        assignment.put("e", Math.E);
        assignment.put("Pi", Math.PI);
        try {
            if (!assignment.containsKey(this.var)) {
                throw new Exception();
            }
        } catch (Exception e) {
            //System.out.println("Exception!! The val not existing in the map key - show the old expression");
            Object flag = null;
        }
        return assignment.get(this.var);
    }

    /**
     * Evaluate - Evaluate the expression for empty assignment.
     *
     * @return the double
     * @throws Exception the exception
     */
    @Override
    public double evaluate() throws Exception {
        return this.evaluate(Collections.EMPTY_MAP);
    }

    /**
     * getVariables - Gets variables.
     *
     * @return Returns a list of the variables in the expression.
     */
    @Override
    public ArrayList getVariables() {
        return getVariablesHelp();
    }

    /**
     * assign - Returns a new expression in which all occurrences of the variable.
     *
     * @param var1       the variable replaced with the provided expression.
     * @param expression the provided expression
     * @return the new expression
     */
    @Override
    public Expression assign(String var1, Expression expression) {
        if (this.var.equals(var1)) {
            return expression;
        } else {
            return this;
        }
    }

    /**
     * differentiate - Differentiate expression.
     *
     * @param var1 the var we Differentiate of
     * @return expression
     */
    @Override
    public Expression differentiate(String var1) {
        if (this.var.equals(var1)) {
            return new Num(1.0);
        } else {
            return new Num(0.0);
        }
    }

    /**
     * simplify - Simplify expression.
     *
     * @return the expression Simplify
     */
    @Override
    public Expression simplify() {

        return this;
    }

    /**
     * getOperationsList - Gets Operation.
     *
     * @return Returns a list of the Operation in the expression.
     */
    @Override
    public ArrayList getOperationsList() {
        return new ArrayList();
    }

    /**
     * getVariables - Gets getNums.
     *
     * @return Returns a list of the Nums in the expression.
     */
    @Override
    public ArrayList getNums() {
        return new ArrayList();
    }

    /**
     * getVariablesHelp - Gets variables.
     *
     * @return Returns a list of the variables in the expression.
     */
    @Override
    public ArrayList getVariablesHelp() {
        ArrayList listOfVars = new ArrayList();
        listOfVars.add(this.var);
        return listOfVars;
    }

    /**
     * toString - create a string representation of the expression.
     *
     * @return a nice string representation of the expression
     */
    public String toString() {
        return this.var;

    }
}