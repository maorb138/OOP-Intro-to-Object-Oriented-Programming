import java.util.Map;
import java.util.TreeMap;

/**
 * ExpressionsTest class.
 *
 * @author maor biton
 *  
 */

public class ExpressionsTest {

    /**
     * main - main function for checking the assignment.
     *
     * @param args - arguments from user.
     * @throws Exception - in case there is no way to calculate the expression.
     */
    public static void main(String[] args) throws Exception {
        // Create the expression (2*x) + (sin(4*y)) + (e^x)
        Expression expression1 = new Plus(new Plus(new Mult(2.0, "x"),
                new Sin(new Mult(4.0, "y"))), new Pow("e", "x"));
        // Create a map of x,y,e
        Map<String, Double> assignment = new TreeMap<String, Double>();
        assignment.put("x", 2.0);
        assignment.put("y", 0.25);
        assignment.put("e", 2.71);
        // Create the differentiated expression according to x
        Expression difExp1 = expression1.differentiate("x");
        // Print the expression.
        System.out.println(expression1);
        // Print the value of the expression with (x=2,y=0.25,e=2.71)
        System.out.println(expression1.evaluate(assignment));
        //Print the differentiated expression according to x
        System.out.println(difExp1);
        //Print the value of the differentiated expression according to x with the assignment above.
        System.out.println(difExp1.evaluate(assignment));
        //Print the simplified differentiated expression
        System.out.println(difExp1.simplify());
    }
}