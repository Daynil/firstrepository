package udacityCourse;

import java.util.Scanner;
public class MonthPrinter
{
   public static void main(String[] args)
   {
       Scanner in = new Scanner(System.in);

       System.out.print("Enter a month number (1 through 12) " );    
       //your code here
       if (in.hasNextInt())
       {
    	   int lemonthy = in.nextInt();
           if (lemonthy <1 || lemonthy >12)
           {
        	   System.out.println("Number must be 1 through 12");
           }
           else
           {
        	   Month tester = new Month(lemonthy);
        	   System.out.printf("%s %d", tester.getMonthName(), tester.getNumberOfDays());
           }
       }
       else
       {
    	   System.out.println("Not an integer. Terminating");
       }
   }
}