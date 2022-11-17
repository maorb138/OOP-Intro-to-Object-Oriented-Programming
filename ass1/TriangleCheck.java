/**
 * task 1.2 TriangleCheck.
 *
 * @author maor biton.
 * @version 1.8.0_231.
 * @since 20.03.2020.
 */
public class TriangleCheck {
    /**
     * func:checkIfTriangle.
     * check if args are valid.
     *
     * @param args from main.
     */
    public static void checkIfTriangle(String[] args) {
        int flag;
        if (args.length < 3 || args.length > 3) {
            System.out.println("Invalid input");
        } else {
            flag = 1;
            double[] arry = new double[3];
            for (int i = 0; i < args.length; i++) {
                arry[i] = Double.parseDouble(args[i]);
                if (arry[i] <= 0) {
                    System.out.println("Invalid input");
                    --flag;
                    break;
                }
            }
            if (flag == 1) {
                //call func-arrySort
                arrySort(arry);
            }

        }
    }

    /**
     * func:legalTriangle.
     * checking sum of two edges,if sum larger then other edge it's a triangle.
     * o.w not.
     *
     * @param arry of double.
     */
    public static void legalTriangle(double[] arry) {
        double sum = 0;
        double x = arry[2];
        for (int i = 0; i < 2; i++) {
            sum += arry[i];
        }
        if (sum <= x) {
            System.out.println("not triangle");
        } else {
            System.out.println("triangle!");
            rightAngle(arry);
        }
    }

    /**
     * func:rightAngle.
     * Calculates in pytgoras if two edges are equal to other edge.
     *
     * @param arry of double.
     */
    public static void rightAngle(double[] arry) {
       if( Math.sqrt(Math.pow(arry[0], 2) + Math.pow(arry[1], 2)) == Math.sqrt(Math.pow(arry[2],2))){
            System.out.println("right angled!");
        }
    }

    /**
     * func:arrySort.
     * getting args and sort them min -> max.
     *
     * @param arry of double.
     */
    public static void arrySort(double[] arry) {
        double temp = 0;
        int len = arry.length;
        // sort the arry (bubble sort)
        for (int i = 0; i < len; i++) {
            for (int j = 1; j < (len - i); j++) {
                if (arry[j - 1] > arry[j]) {
                    // swap elements
                    temp = arry[j - 1];
                    arry[j - 1] = arry[j];
                    arry[j] = temp;
                }
            }
        }
        // call legalTriangle func
        legalTriangle(arry);
    }

    /**
     * func:main.
     * getting args from user.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {

        checkIfTriangle(args);
    }
}
