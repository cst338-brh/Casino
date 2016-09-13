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
   
   // Prompt the user for an integer between 0 and 100 and return result
   public static int getBet() {      
      int betValue = -1;
      
      do {
         System.out.print("Please enter your bet (1-100, 0 to exit): ");
         betValue = input.nextInt();
      } while (betValue < 0 || betValue > 100);
      
      return betValue;
   }
   
   // Return a TripleString object which contains 3 randomized slot machine result strings
   public static TripleString pull() 
   {
      TripleString returnTuple = new TripleString();
      returnTuple.setString1(randString());
      returnTuple.setString2(randString());      
      returnTuple.setString3(randString());   
      
      return returnTuple;
   }
   
   // Return a string randomly selected from four possible slot machine results
   public static String randString() 
   {
      String returnString = "ERROR";
      double internalRandom = Math.random();
      
      // Select a string based on different probabilities for each
      if (internalRandom >= 0 && internalRandom < .5) {
         returnString = "BAR";         // 50% chance
      } else if (internalRandom >= .5 && internalRandom < .75) {
         returnString = "cherries";    // 25% chance
      } else if (internalRandom >= 0.75 && internalRandom < .875) {
         returnString = "space";       // 12.5% chance
      } else if (internalRandom >= .875 && internalRandom < 1) {
         returnString = "7";           // 12.5% chance
      }
      
      return returnString;
   }
   
   // Return the appropriate winnings multiplier based on the slot machine result strings
   public static int getPayMultiplier(TripleString thePull) 
   {
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
   
   // Display the results of a pull and associated winnings
   public static void display(TripleString thePull, int winnings) {
      
      // Pull results
      System.out.print(thePull.getString1() + " " + thePull.getString2() + " " + thePull.getString3());
      
      // Winnings
      if (winnings == 0) {
         System.out.print("Congrats, you won $" + winnings + "!");
      } else {
         System.out.print("Sorry - you lost.");
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