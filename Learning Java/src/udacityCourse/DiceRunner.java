package udacityCourse;

public class DiceRunner 
{
	public static void main(String[] args)
	{
		DiceGame dice = new DiceGame();
		double counter1 = 0;
		double counter2 = 0;
		int runs1 = 10000000;
		int runs2 = 10000000;
		for (int i = 0; i < runs1; i++)
		{
			if (dice.game1())
			{
				counter1+=1;
			}
		}
		for (int i = 0; i < runs2; i++)
		{
			if (dice.game2())
			{
				counter2+=1;
			}
		}
		double game1Odds = (counter1/runs1) * 100;
		double game2Odds = (counter2/runs2) * 100;
		//System.out.printf("%f, %d, %f\n %f, %d, %f", counter1, runs1, game1Odds, counter2, runs2, game2Odds);
		System.out.printf("Game 1 win probability = %.2f percent\n", game1Odds);
		System.out.printf("Game 2 win probability = %.2f percent", game2Odds);
	}

}
