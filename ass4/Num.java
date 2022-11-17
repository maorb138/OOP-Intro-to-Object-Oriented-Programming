import java.util.ArrayList;
import java.util.Map;

/**
 * Num class.
 *
 * @author maor biton
 *  
 */
public class Num implements Expression {
    private double num;

    /**
     * constructor - Instantiates a new Num.
     *
     * @param num the num (Double)
     */
    public Num(double num) {
        this.num = num;
    }

    /**
     * constructor a new Num from int.
     *
     * @param num the num
     */
    public Num(int num) {
        this.num = (double) num;
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
        return this.num;
    }

    /**
     * Evaluate - Evaluate the expression for empty assignment.
     *
     * @return the double
     * @throws Exception the exception
     */
    @Override
    public double evaluate() throws Exception {
        return this.num;
    }

    /**
     * getVariables - Gets variables.
     *
     * @return Returns a list of the variables in the expression.
     */
    @Override
    public ArrayList getVariables() {
        return new ArrayList();
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
        return this;
    }

    /**
     * differentiate - Differentiate expression.
     *
     * @param var1 the var we Differentiate of
     * @return expression
     */
    @Override
    public Expression differentiate(String var1) {
        return new Num(0);
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
     * getNums - Gets Nums.
     *
     * @return Returns a list of the Nums in the expression.
     */
    @Override
    public ArrayList getNums() {
        ArrayList listOfNum = new ArrayList();
        listOfNum.add(this.num);
        return listOfNum;
    }

    @Override
    public ArrayList getVariablesHelp() {
        return getVariables();
    }

    /**
     * toString - create a nice string representation of the expression.
     *
     * @return a nice string representation of the expression
     */
    public String toString() {
        return String.valueOf(this.num);
    }

}