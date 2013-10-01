package udacityCourse;

import java.util.Random;
import java.util.Scanner;

/**
   This program computes an estimate of pi by simulating dart throws onto a square.
*/
public class MonteCarlo
{
    public static void main(String[] args)
    {
        System.out.println("Number of tries");
        Random generator = new Random(42);
        Scanner in = new Scanner(System.in);
        int tries = in.nextInt();

        int hits = 0;
        for (int i = 1; i <= tries; i++)
        {
            // Generate two random numbers between -1 and 1

            double x = 2*generator.nextDouble() - 1;

            double y = 2*generator.nextDouble() - 1;

            // Check whether the point lies in the unit circle
            double distance = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
            if (distance <= 1)  //****DON'T PUT A SEMICOLON AFTER A CONDTION OR IT ALWAYS RUNS
            {
                hits++;
            }
        }

        /*
           The ratio hits / tries is approximately the same as the ratio
           circle area / square area = pi / 4
        */

        double piEstimate = 4.0 * hits / tries;
        System.out.println("Estimate for pi: " + piEstimate);
    }
}