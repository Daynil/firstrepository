package udacityCourse;

import java.util.Random;

public class WordScramble
{
   private Random generator = new Random();

   public WordScramble()  //leave the constructor alone
   {
        generator = new Random();
        //final long SEED = 42;
        //generator.setSeed(SEED);
   }
   /**
    * Gets a version of this word with the leters scrambled
    * @param word the string to scramble
    * @return the scrabled string
    */
   public String scramble(String word)
   {
        //TODO scramble the letters following the pseudocode
       int len = word.length();
       String swapped = "";
       int i = generator.nextInt(len-2);
       String first = word.substring(0, i);
       int lenFirst = first.length();
       int lenPostI = word.substring(i+1, len).length();
       int j = generator.nextInt(lenPostI);
       String middle = word.substring(i, j);
       String last = word.substring(j+1, len);
       String iChar = word.substring(i, i+1);
       String jChar = word.substring(i+1+j, (i+1)+j+1);

       //return i + " " + last;
       return first + " " + iChar + " " + middle + " " + jChar + " " + last;
   }


}
