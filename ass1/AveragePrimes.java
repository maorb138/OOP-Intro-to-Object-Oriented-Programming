/**
 * task 1.1 AveragePrimes.
 *
 * @author maor biton.
 * @version 1.8.0_231.
 * @since 20.03.2020.
 */
public class AveragePrimes {
    /**
     * func:averagePri.
     * Calculation average of prime numbers.
     * recive num.
     * count all prime numbers between[2,n].
     *
     * @param num from user.
     */
    public static void averagePri(int num) {
        int sum = 0, count = 0;
        int flag, j;
        if (num <= 1) {
            System.out.println("Invalid value");
        } else {
            for (int i = 2; i <= num; i++) {
                flag = 0;
                for (j = i - 1; j > 1; j--) {
                    if (i % j == 0) {
                        flag = 1;
                        break;
                    }
                }
                if (flag == 0) {
                    sum += i;
                    count++;
                }
            }
            double average = (double) sum / count;
            System.out.println(average);
        }
    }

    /**
     * func:main.
     * getting args from user.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        int num = Integer.parseInt(args[0]);
        averagePri(num);
    }
}