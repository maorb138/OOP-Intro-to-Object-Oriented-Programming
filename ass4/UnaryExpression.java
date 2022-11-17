import java.util.ArrayList;
/**
 * UnaryExpression abstract class.
 *
 * @author maor biton
 * 
 */
public abstract class UnaryExpression extends BaseExpression {
    private Expression expression;

    /**
     * constructor - Expression .
     *
     * @param expression the var expression
     */
    public UnaryExpression(Expression expression) {
        this.expression = expression;
    }

    /**
     * constructor - double.
     *
     * @param expression the num expression
     */
    public UnaryExpression(Double expression) {
        this.expression = new Num(expression);
    }

    /**
     * constructor - int.
     *
     * @param expression the num expression
     */
    public UnaryExpression(int expression) {
        this.expression = new Num(expression);
    }

    /**
     * constructor - String.
     *
     * @param expression the var expression
     */
    public UnaryExpression(String expression) {
        this.expression = new Var(expression);
    }

    /**
     * getVariables - Gets variables.
     *
     * @return Returns a list of the variables in the expression.
     */
    public ArrayList getVariables() {
        return getExpression().getVariables();
    }

    /**
     * exToString - string of expression.
     *
     * @return string of expression.
     */
    public String exToString() {
        return getExpression().toString();
    }

    /**
     * getExpression - get the Expression.
     *
     * @return the expression.
     */
    public Expression getExpression() {
        return this.expression;
    }

    /**
     * getOperationsList - Add operations.
     *
     * @return the operations list
     */
    public ArrayList getOperationsList() {
        ArrayList listL = getExpression().getOperationsList();
        listL.add(this.getClass().getSimpleName());
        return listL;
    }

    /**
     * getNums - Gets Nums.
     *
     * @return Returns a list of the Nums in the expression.
     */
    public ArrayList getNums() {
        return getExpression().getNums();
    }

    /**
     * getVariablesHelp - Gets variables.
     *
     * @return Returns a list of the variables in the expression.
     */
    public ArrayList getVariablesHelp() {
        return getExpression().getVariables();
    }
}
