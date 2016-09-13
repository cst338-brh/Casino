/*Heather McCabe
 *Brett Hansen
 *Randall Rood
 *CST 338
 *9/9/16
 *Module 2: Casino
 */

import java.util.*;
import java.lang.Math;

public class Assig2 
{
   public static Scanner input = new Scanner(System.in);

   public static void main(String[] args) 
   {
      // TODO: "a loop that is controlled by a value returned from getBet().  As long as that value is non-zero, we keep playing."
      /* Each time through the loop, we have to call pull() to get the pullString as a return value. 
       * Then we need to pass that to getPayMultiplier() to find the multiplier.  We then compute the winnings based on the 
       * previous information, and finally we display it all using display().  */ 
      /* TODO: "Submit one run that lasts about 20 to 40 pulls -- enough to see a few wins.  At least once enter an illegal amount 
       * to make sure that your program handles it correctly." */
      // TODO: Close scanner at end of program
      
      int betMade = getBet();
      int pullCounter = 0;
      TripleString casinoSpin = new TripleString();
      
      while (betMade != 0 && pullCounter < 40) 
      {
        TripleString currentSpin = pull();
        int payoutMultiplier = getPayMultiplier(currentSpin);
        int payout = payoutMultiplier * betMade;
        display(currentSpin, payout);
        casinoSpin.saveWinnings(payout);
        
        
        pullCounter ++; 
        betMade = getBet();
      }
      
      // Testing for getBet
      /*
      Integer foo = getBet();
      String bar = foo.toString();
      System.out.print(bar);*/
      
      // Testing randString()
      /*
      for (int i = 0; i<100; i++)
      {
         String testString = randString();
         System.out.print(testString + "\n");
      } */
      
      // Testing for pull()
      /*
      TripleString foobar = pull();
      System.out.print(foobar.getString1() + "\n");
      System.out.print(foobar.getString2() + "\n");
      System.out.print(foobar.getString3());*/
      
      // Testing for getPayMultiplier
      /*
      for (int i = 0; i < 100; i++)
      {
         TripleString testTriple = pull();
         Integer payout = getPayMultiplier(testTriple);
         System.out.print(testTriple.getString1() + " " + testTriple.getString2() + " " 
         + testTriple.getString3() + " || " + payout.toString() + "\n");
      } */
        
      // Testing for TripleString class method
      //TripleString ts = new TripleString();         
      //ts.test(); // TODO: Remove this!
      
      input.close();
   }
   
   public static int getBet() 
   {
      // TODO: Get the bet from the user and return it to main
      /* "This prompts the user for input and returns the bet amount as a functional return.  
       * It should validate the amount before it returns and insist on a legal bet (0 < bet < 100) 
       * until it gets one from the user.  It must return the legal value to the client and not 
       * take any other action besides getting the legal amount." */
      /* "The only place the user can make an input error is in getBet(), so that's the method that 
       * deals with such errors.  Don't worry about non-numbers.  Assume that a number was entered.  
       * But do test for range and only return to main after you have a valid range. getBet() may 
       * not decide about ending the program.  That's up to main()." */
      
      int betValue = -1;
      
      do
      {
        System.out.print("Please enter your bet (1-100, 0 to exit): ");
        betValue = input.nextInt();
      } while (betValue < 0 || betValue > 100);
      
      return betValue;
   }
   
   public static TripleString pull() 
   {
      // TODO: Simulate a random pull of the slot machine
      // Generate three random strings and return them as a TripleString object to main
      // Use randString() three times to get strings
      TripleString returnTuple = new TripleString();
      returnTuple.setString1(randString());
      returnTuple.setString2(randString());      
      returnTuple.setString3(randString());   
      
      return returnTuple;
   }
   
   public static String randString() 
   {
      // TODO: Return a randomized string
      // Use Math.Random() to decide which of 4 strings to return ("BAR", "CHERRIES", "SPACE", "7")
      /* "Math.random() returns a double between 0 and 1.  One idea (but not the only one) is to 
       * turn that double into an int  between 1 and 1000.  Then, decide which of those numbers 
       * should trigger a "7", which should trigger a "cherries", etc. based on the desired probabilities.  
       * Since a "Bar" should happen half the time, which numbers would you want to trigger a "Bar"?  
       * Since a "cherries" should happen 25% of the time, which numbers would trigger a "cherries"?  
       * So you see, this is a very simple -- and even short -- function, even though it has to be 
       * designed carefully.  Common sense will go a long way here." */
      /* Probabilities: 
       * BAR      1/2  (50%)
       * cherries 1/4  (25%)
       * space    1/8  (12.5%)
       * 7        1/8  (12.5%) */
      String returnString = "ERROR";
      double internalRandom = Math.random();
      
      if (internalRandom >= 0 && internalRandom < .5)
      {
         returnString = "BAR";
      }
      else if (internalRandom >= .5 && internalRandom < .75)
      {
         returnString = "cherries";
      }
      else if (internalRandom >= 0.75 && internalRandom < .875)
      {
         returnString = "space";
      }
      else if (internalRandom >= .875 && internalRandom < 1)
      {
         returnString = "7";
      }
      
      return returnString;
   }
   
   public static int getPayMultiplier(TripleString thePull) 
   {
      // TODO: Figure out the payout from the pullString; return one of the values;  0, 5, 15, 30, 50 or 100
      // Look at the three strings inside the passed-in TripleString object and use if-statements to determine the right value
      /* The following combinations should pay the bet as shown (note ORDER MATTERS):
       * cherries  [not cherries]  [any] pays 5 × bet (5 times the bet)
       * cherries  cherries  [not cherries] pays 15 × bet
       * cherries  cherries  cherries pays 30 × bet
       * BAR  BAR  BARpays 50 × bet
       * 7  7  7 pays 100 × bet */
      /* "Position counts! If you read the above bullet that contains the warning "ORDER MATTERS", you will see that 
       * cherries bar cherries pays 5× while cherries cherries bar pays 15× and bar cherries cherries pays nothing." */
      
      int payoutMult = 0;
      
      if (thePull.getString1() == "cherries")
      {
         if (thePull.getString2() == "cherries")
         {
            if (thePull.getString3() == "cherries")
            {
               payoutMult = 30;
            }
            else
            {
               payoutMult = 15;
            }
         }
         else
         {
            payoutMult = 5;
         }
      }
      else if (thePull.getString1() == "BAR" && thePull.getString2() == "BAR" && thePull.getString3() == "BAR")
      {
         payoutMult = 50;
      }
      else if (thePull.getString1() == "7" && thePull.getString2() == "7" && thePull.getString3() == "7")
      {
         payoutMult = 100;
      }
      
      return payoutMult;
      
   }
   
   public static void display(TripleString thePull, int winnings) 
   {
      // TODO: Show the user the results of the pull and how much they won (or not)
      /* takes the winnings (a dollar amount) and thePull as parameters and displays the three strings 
       * inside thePull along with "  sorry - you lost " or "congrats, you won $X". */
      Integer integerWinnings = winnings;
      System.out.print(thePull.getString1() + " " + thePull.getString2() + " " 
      + thePull.getString3() + " || " + integerWinnings.toString() + "\n");
      
   }

}

class TripleString 
{
   public static final int MAX_LEN = 20;     // Max allowable length for Strings
   public static final int MAX_PULLS = 40;   // Max number of pulls
   private static int numPulls = 0;          // Pull count
   private static int[] pullWinnings = new int[MAX_PULLS];
   private String string1,string2,string3;
   
   // Default constructor
   public TripleString() 
   {
      string1 = "";
      string2 = "";
      string3 = "";
   }
   
   // TODO: Temporary method to test this class. Remove this before turning in!
   public void test() 
   {
      // Check that accessors doesn't break when nothing has been set
      System.out.println("String 1: " + getString1());
      System.out.println("String 2: " + getString2());
      System.out.println("String 3: " + getString3());
      System.out.println("To String: " + toString());
      System.out.println("Winnings: " + displayWinnings());
      
      // Check setting various strings with mutator methods
      boolean testCheck;
      String[] testString = {null,"","This is 20 character","This is 21 characters","BAR","7","CHERRIES","(SPACE)"};
      for (int i = 0; i < testString.length; i++) 
      {
         testCheck = setString1(testString[i]);
         System.out.println("Testing '" + testString[i] + "' string. Successful: " + testCheck);
      }
      
      // Check that string accessors work after things have been set
      setString2("BAR");
      setString3("CHERRIES");
      System.out.println("String 1: " + getString1());
      System.out.println("String 2: " + getString2());
      System.out.println("String 3: " + getString3());
      System.out.println("To String: " + toString());
      
      // Check winnings array
      saveWinnings(5);
      System.out.println("Winnings: " + displayWinnings());
      // Fill array and go one past max pull count
      for (int i = 2; i < 42; i ++) 
      {
         System.out.println(i + " " + saveWinnings(i));
      }
      System.out.println("Winnings: " + displayWinnings());
   }
   
   // Check that a given string is not null and is less than or equal to the maximum allowable length
   // Return true if string is valid; otherwise return false
   private boolean validString(String str) 
   {
      if (str != null && str.length() <= MAX_LEN) 
      {
         return true;
      } 
      else 
      {
         return false;
      }
   }

   // Return the value of string1
   public String getString1() 
   {
      return string1;
   }
   
   // Return the value of string2
   public String getString2() 
   {
      return string2;
   }
   
   // Return the value of string3
   public String getString3() 
   {
      return string3;
   }
   
   // Update the value of string1 to the given string, if valid
   // Return true if update is successful; otherwise return false
   public boolean setString1(String str) 
   {
      if (validString(str)) 
      {
         string1 = str;
         return true;
      } 
      else 
      {
         return false;
      }
   }
   
   // Update the value of string2 to the given string, if valid
   // Return true if update is successful; otherwise return false
   public boolean setString2(String str) 
   {
      if (validString(str)) 
      {
         string2 = str;
         return true;
      } 
      else 
      {
         return false;
      }
   }
   
   // Update the value of string3 to the given string, if valid
   // Return true if update is successful; otherwise return false
   public boolean setString3(String str) 
   {
      if (validString(str)) 
      {
         string3 = str;
         return true;
      } 
      else 
      {
         return false;
      }
   }
   
   // Return a string containing the values of string1, string2, and string3
   public String toString() 
   {
      return string1 + " " + string2 + " " + string3;
   }
   
   // If MAX_PULLS count has not been reached, add winnings value to array and update pull count
   // Return true if insertion is successful; otherwise return false
   public boolean saveWinnings(int winnings) 
   {
      if (numPulls < MAX_PULLS) 
      {
         pullWinnings[numPulls] = winnings;
         numPulls++;
         return true;
      } 
      else 
      {
         return false;
      }
   }
   
   // Return string containing all winnings values
   public String displayWinnings() 
   {
      String winnings = "";
      for (int i = 0; i < numPulls; i++) 
      {
         winnings += " " + pullWinnings[i];
      }
      return winnings;
   }
}