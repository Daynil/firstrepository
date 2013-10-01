package udacityCourse;

public class Month
{
    //instance variables
    int theMonthNumber;
    /**
     * Contructs a Month
     * @param the month number. Must be 1 to 12. For any other number, the month number is set to 1
     */
    public Month(int theMonthNumber)
    {
        if (theMonthNumber >=1 && theMonthNumber <= 12)
            this.theMonthNumber = theMonthNumber;
        else
        {this.theMonthNumber = 1;}
    }

    /**
     * Gets the name of this month
     * @return the name of this month
     */
    public String getMonthName()
    {
        int mn = theMonthNumber;
        if (mn == 1)
        {return "January";}
        else if (mn == 2)
        {return "February";}
        else if (mn == 3)
        {return "March";}
        else if (mn == 4)
        {return "April";}
        else if (mn == 5)
        {return "May";}
        else if (mn == 6)
        {return "June";}
        else if (mn == 7)
        {return "July";}
        else if (mn == 8)
        {return "August";}
        else if (mn == 9)
        {return "September";}
        else if (mn == 10)
        {return "October";}
        else if (mn == 11)
        {return "November";}
        else
        {return "December";}
    }

    /**
     * Gets the number of days in this month
     * @return the number of days in this month in a non-leap year
     */
    public int getNumberOfDays()
    {
        int mn = theMonthNumber;
        if (mn == 1 || mn == 3 || mn == 5 || mn == 7 || mn == 8 || mn == 10 || mn == 12)
        {return 31;}
        if (mn == 4 || mn == 6 || mn == 9 || mn == 11)
        {return 30;}
        else
        {return 28;}
    }
}