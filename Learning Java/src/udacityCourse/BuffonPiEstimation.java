package udacityCourse;

import java.util.Random;

public class BuffonPiEstimation
{
    public static void main(String[] args) 
    {
        //System.out.println("Buffon Pi Estimation");
        //System.out.println("Enter the number of tries: ");
        //Scanner in = new Scanner(System.in);
        //int tries = in.nextInt();
        int tries = 30000;

        Random generator = new Random();

        // YOUR CODE HERE
        // Calculate the number of hits. 
        // generate a yLow, the lowest point, or the farthest point on 
        // the needle from the line (a number between 0 and 2).
        // generate a random angle a between 0 and 180 degrees. 
        // yHigh is yLow plus the sine of a. 
        // if yHigh is above the line (the line is at a height of 2), 
        // the needle is touching the line, and counts as a hit.
        // Hint: Math.sin takes an angle in radians. 
        double hits = 0;
        for (int i = 0; i < tries; i++)
        {
            double yLow = generator.nextDouble()*2;
            double angle = generator.nextDouble()*180;
            double yHigh = yLow + Math.sin(Math.toRadians(angle));
            if (yHigh > 2)
            {
                hits++;
            }
            //System.out.printf("yLow: %.2f, angle: %.2f, yHigh: %.2f\n", yLow, angle, yHigh);
        }
        double piEstimate = tries / hits;
        
        
        //System.out.println();
        System.out.printf("hits: %.2f, piEstimate: %.2f", hits, piEstimate);
    }   
}

