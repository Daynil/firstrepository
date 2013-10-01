package udacityCourse;

import java.util.Scanner;

public class MaxMinPrinter
{
   public static void main(String[] args)
   {
       Scanner in = new Scanner(System.in);
       int max = 0;
       int min = 0;
       int i = 1;
       int interm;
       while (true)
       {
	       System.out.print("Enter an integer: ");
	       if (in.hasNextInt())
	       {
	    	   interm = in.nextInt();
	    	   if (i==1)
	    	   {
	    		   min = interm;
	    	   }
	    	   if (i==2)
	    	   {
	    		   if (interm > min)
	    		   {
	    			   max = interm;
	    		   }
	    		   else
	    		   {
	    			   max = min;
	    			   min = interm;
	    		   }
	    	   }
	    	   else
	    	   {
	    		   if (interm > max)
	    		   {
	    			   max = interm;
	    		   }
	    		   else if (interm < min)
	    		   {
	    			   min = interm;
	    		   }
	    	   }
	       }
	       else
	       {
	    	   break;
	       }
	       i++;
       }
       System.out.println(max);
       System.out.println(min);
   }
}
