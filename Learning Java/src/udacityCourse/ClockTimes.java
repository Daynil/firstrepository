package udacityCourse;

public class ClockTimes
{
    public static void main(String[] args)
    {
        for (int minute = 0; minute < 60; minute++)
        {
            for (int hour = 1; hour <= 12; hour++)
            {
                // YOUR CODE HERE
                // Use nested loops to count through all the hours 
                // and all the minutes, and print formatted strings. 
                // Hint: use %02d to print single digit numbers with 
                // a leading zero. The 0 means "zero-padded" check out
                // the fact sheet from lesson 4 to read about more 
                // formatting options. 
                System.out.printf("%d:%02d ", hour, minute);
                if (hour == 12)
                {
                    System.out.println();
                }
                
            }
        }
    }
}