/**
 * task 1.3 CharCount.
 *
 * @author maor biton.
 * @version 1.8.0_231.
 * @since 20.03.2020.
 */
public class CharCount {
    /**
     * func:makeString
     * check if args are valid.
     * create string.
     * copy args to string.
     *
     * @param args from user.
     */
    public static void makeString(String[] args) {
        if (args.length < 2 || args[args.length - 1].length() != 1) {
            System.out.println("Invalid input");
        } else {

            String[] str = new String[args.length];
            for (int i = 0; i < args.length; i++) {
                str[i] = args[i];
            }
            lastLetter(str);
        }
    }

    /**
     * func:lastLetter.
     * the func print the string that Equal to subtring in str.
     *
     * @param str string of args.
     */
    public static void lastLetter(String[] str) {
        char str2 = str[str.length - 1].charAt(0);
        for (int i = 0; i < str.length - 1; i++) {
            if (str[i].indexOf(str2) != -1) {
                System.out.println("" + str[i]);
            }

        }
        printRestStr(str, str2);
    }

    /**
     * func: printRestStr.
     *
     * @param str  string of args.
     * @param str2 char substring.
     *             Finding a single character in a string.
     *             Compares substring str to char in str2.
     */
    private static void printRestStr(String[] str, char str2) {
        for (int i = 0; i < str.length - 1; i++) {
            if (str[i].indexOf(str2) == -1) {
                System.out.println("" + str[i]);
            }

        }
    }


    /**
     * func:main.
     * getting args from user.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        makeString(args);


    }

}
