package udacityCourse;

import java.util.Random;

public class DiceGame
{
    Random generator;
    
    public DiceGame()
    {   
        generator = new Random();
    }
    /** 
     * Throw a die four times and bet on at least one 6. 
     * @return true if the chevalier wins. 
     */
    public boolean game1()
    {
        // YOUR CODE HERE
        // Use the instance variable generator to simulate 
        // 4 die rolls. 
        // Return true if the chevalier wins (at least one is a 6).
        boolean winner = false;
        for (int i =0; i < 4; i++)
        {
            if ((generator.nextInt(6)+1) == 6)
            {
                winner = true;
            }
        }
        return winner;
    }
    
    /**
     * Throw two dice 24 times and bet on at least one double-six.
     * @return true if the chevalier wins. 
     */
    public boolean game2()
    {   
        // YOUR CODE HERE 
        // Use the instance variable generator to simulate 
        // 24 rolls of a pair of dice.
        // Return true if at least one pair is both 6s. 
        boolean winner2 = false;
        for (int i = 0; i < 24; i++)
        {
            int dice1Roll = (generator.nextInt(6)+1);
            int dice2Roll = (generator.nextInt(6)+1);
            if (dice1Roll == 6 && dice2Roll == 6)
            {
                winner2 = true;
            }
        }
        return winner2;
    }
}