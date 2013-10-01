package udacityCourse;

//Bluej project: plurals
public class Word
{
 private String letters;

 public Word(String letters)
	 {
	     this.letters = letters.toLowerCase();
	 }
	
	 /**
	     Forms the plural of this word.
	     @return the plural, using the rules for regular nouns
	 */
	 public String getPluralForm()
	 {
	     // TODO: Complete this method
	     // If the word ends in y preceded by a consonant you take away the y and add ies.
	     // If the word ends in y preceded by a vowel, you just add an s.
	     // You add an es when a word ends in o, or s, or sh, or ch.
	     // In all the other case just add an s.
	     // you can use the
	     //  isVowel
	     //  isConsonant
	     //  is
	     // methods from below.
	     int letterLen = letters.length();
	     String lastLet = letters.substring(letterLen-1);
	     if (lastLet.equals("y") && isConsonant(letterLen-2))
	     {
	         String stringYC = letters.substring(0, letterLen-1) + "ies";
	         return stringYC;
	     }
	     else if (lastLet.equals("y") && isVowel(letterLen-2))
	     {
	         String stringYV = letters + "s";
	         return stringYV;
	     }
	     else if (lastLet.equals("o") || lastLet.equals("s")
	              || letters.substring(letterLen-2, letterLen).equals("sh")
	              || letters.substring(letterLen-2, letterLen).equals("ch"))
	     {
	         String stringOS = letters + "es";
	         return stringOS;
	     }
	     else
	     {
	         String stringOT = letters + "s";
	         return stringOT;
	     }
	 }
	
	 /**
	    Tests whether the ith letter is a vowel.
	    @param i the index of the letter to test
	    @return true if the ith letter is a vowel
	 */
	 public boolean isVowel(int i)
	 {
	     return is(i, "a")
	            || is(i, "e")
	            || is(i, "i")
	            || is(i, "o")
	            || is(i, "u");
	 }
	
	 /**
	    Tests whether the ith letter is a consonant.
	    @param i the index of the letter to test
	    @return true if the ith letter is a consonant
	 */
	 public boolean isConsonant(int i)
	 {
	     return !isVowel(i);
	 }
	
	 /**
	  * Checks what letter is in position i
	  * @return true when the the letter of letters is the given letter.
	  *         false otherwise.
	  * @param i index in letters
	  * @param letter the letter to match with. must be one character long.
	  */
	 public boolean is(int i, String letter)
	 {
	     return letters.substring(i, i + 1).equals(letter);
	 }
}
