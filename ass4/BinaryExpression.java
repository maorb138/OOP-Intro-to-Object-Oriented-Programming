import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * BinaryExpression abstract class.
 *
 * @author maor biton
 *  
 */
public abstract class BinaryExpression extends BaseExpression {
    private Expression leftExpression;
    private Expression rightExpression;


    /**
     * constructor - string and string.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public BinaryExpression(String varLeft, String varRight) {
        this.leftExpression = new Var(varLeft);
        this.rightExpression = new Var(varRight);
    }
    /**
     * constructor - int and int.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public BinaryExpression(int varLeft, int varRight) {
        this.leftExpression = new Num((double) varLeft);
        this.rightExpression = new Num((double) varRight);
    }

    /**
     * constructor - string and double.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public BinaryExpression(String varLeft, Double varRight) {
        this.leftExpression = new Var(varLeft);
        this.rightExpression = new Num(varRight);
    }

    /**
     * constructor - string and Expression.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public BinaryExpression(String varLeft, Expression varRight) {
        this.leftExpression = new Var(varLeft);
        this.rightExpression = varRight;
    }

    /**
     * constructor - double and double.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public BinaryExpression(Double varLeft, Double varRight) {
        this.leftExpression = new Num(varLeft);
        this.rightExpression = new Num(varRight);
    }

    /**
     * constructor - double and string.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public BinaryExpression(Double varLeft, String varRight) {
        this.leftExpression = new Num(varLeft);
        this.rightExpression = new Var(varRight);
    }

    /**
     * constructor - double and Expression.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public BinaryExpression(Double varLeft, Expression varRight) {
        this.leftExpression = new Num(varLeft);
        this.rightExpression = varRight;
    }


    /**
     * constructor - Expression and double.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public BinaryExpression(Expression varLeft, Double varRight) {
        this.leftExpression = varLeft;
        this.rightExpression = new Num(varRight);
    }

    /**
     * constructor - Expression and string.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public BinaryExpression(Expression varLeft, String varRight) {
        this.leftExpression = varLeft;
        this.rightExpression = new Var(varRight);
    }

    /**
     * constructor - Expression and Expression.
     *
     * @param varLeft  the var left
     * @param varRight the var right
     */
    public BinaryExpression(Expression varLeft, Expression varRight) {
        this.leftExpression = varLeft;
        this.rightExpression = varRight;
    }


    /**
     * getVariablesHelp - all the Gets variables.
     *
     * @return Returns a list of all the variables in the expression.
     */
    public ArrayList getVariablesHelp() {
        List<String> listL = getLeftExpression().getVariablesHelp();
        List<String> listR = getRightExpression().getVariablesHelp();
        listL.addAll(listR);
        return (ArrayList) listL;
    }

    /**
     * getVariables - Gets variables.
     *
     * @return Returns a list of the variables in the expression.
     */
    public ArrayList getVariables() {
        ArrayList listL = new ArrayList<>(new HashSet<>(getVariablesHelp()));
        return (ArrayList) listL;
    }

    /**
     * toStringRightExpression - string of right Expression.
     *
     * @return string right expression.
     */
    public String toStringRightExpression() {
        return this.rightExpression.toString();
    }

    /**
     * toStringLeftExpression - string of  left Expression.
     *
     * @return string left expression.
     */
    public String toStringLeftExpression() {
        return this.leftExpression.toString();
    }

    /**
     * getRightExpression - get the right Expression.
     *
     * @return the right expression.
     */
    public Expression getRightExpression() {
        return this.rightExpression;
    }

    /**
     * getLeftExpression - get the left Expression.
     *
     * @return the left expression.
     */
    public Expression getLeftExpression() {
        return this.leftExpression;
    }


    /**
     * getNums - Gets Nums.
     *
     * @return Returns a list of the Nums in the expression.
     */
    public ArrayList<Double> getNums() {
        ArrayList listL = getLeftExpression().getNums();
        ArrayList listR = getRightExpression().getNums();
        listL.addAll(listR);
        return listL;
    }

    /**
     * getOperationsList - Add operations.
     *
     * @return the operations list
     */
    public ArrayList getOperationsList() {
        List<String> listL = getLeftExpression().getOperationsList();
        List<String> listR = getRightExpression().getOperationsList();
        listL.add(this.getClass().getSimpleName());
        listL.addAll(listR);
        return (ArrayList) listL;
    }

}
