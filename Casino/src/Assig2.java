/*Heather McCabe
 *Brett Hansen
 *Randall Rood
 *CST 338
 *9/9/16
 *Module 2: Casino
 */

import java.util.*;
import java.lang.Math;

public class Assig2 {
   private static Scanner input = new Scanner(System.in);

   public static void main(String[] args) {
      boolean playAgain = true;
      
      while (playAgain) {
        // Get user's bet
        int bet = getBet();
        
        // End game if user entered 0
        if (bet == 0) {
           playAgain = false;
           continue;
        }
        
        // Simulate a slot machine pull
        TripleString currentSpin = pull();
        
        // Calculate payout
        int payoutMultiplier = getPayMultiplier(currentSpin);
        int payout = payoutMultiplier * bet;
        
        // Display results
        display(currentSpin, payout);
        
        // Update winnings array
        playAgain = currentSpin.saveWinnings(payout); // Returns false if max number of pulls reached
      }
      
      // User has quit or reached max number of pulls
      // Display all winnings
      System.out.println("Thanks for playing at the Casino!");
      System.out.println("Your individual winnings were:");
      System.out.println(TripleString.displayWinnings());
      
      // Close scanner object
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
   public static TripleString pull() {
      TripleString returnTuple = new TripleString();
      returnTuple.setString1(randString());
      returnTuple.setString2(randString());      
      returnTuple.setString3(randString());   
      
      return returnTuple;
   }
   
   // Return a string randomly selected from four possible slot machine results
   public static String randString() {
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
   public static int getPayMultiplier(TripleString thePull) {
      int payoutMult = 0;
      
      if (thePull.getString1() == "cherries") {
         if (thePull.getString2() == "cherries") {
            if (thePull.getString3() == "cherries") {
               payoutMult = 30;
            } else {
               payoutMult = 15;
            }
         } else {
            payoutMult = 5;
         }
      } else if (thePull.getString1() == "BAR" && thePull.getString2() == "BAR" && thePull.getString3() == "BAR") {
         payoutMult = 50;
      } else if (thePull.getString1() == "7" && thePull.getString2() == "7" && thePull.getString3() == "7") {
         payoutMult = 100;
      }
      
      return payoutMult;     
   }
   
   // Display the results of a pull and associated winnings
   public static void display(TripleString thePull, int winnings) {
      
      // Pull results
      System.out.println("Whirrrrrr.... Your pull is...");
      System.out.println(thePull.getString1() + " " + thePull.getString2() + " " + thePull.getString3());
      
      // Winnings
      if (winnings == 0) {
         System.out.println("Sorry - you lost.\n");
      } else {
         System.out.println("Congrats, you won $" + winnings + "!\n");
      }
   }
}

class TripleString {
   public static final int MAX_LEN = 20;     // Max allowable length for Strings
   public static final int MAX_PULLS = 40;   // Max number of pulls
   private static int numPulls = 0;          // Pull count
   private static int[] pullWinnings = new int[MAX_PULLS];
   private String string1,string2,string3;
   
   // Default constructor
   public TripleString() {
      string1 = "";
      string2 = "";
      string3 = "";
   }
   
   // Check that a given string is not null and is less than or equal to the maximum allowable length
   // Return true if string is valid; otherwise return false
   private boolean validString(String str) {
      if (str != null && str.length() <= MAX_LEN) {
         return true;
      } else {
         return false;
      }
   }

   // Return the value of string1
   public String getString1() {
      return string1;
   }
   
   // Return the value of string2
   public String getString2() {
      return string2;
   }
   
   // Return the value of string3
   public String getString3() {
      return string3;
   }
   
   // Update the value of string1 to the given string, if valid
   // Return true if update is successful; otherwise return false
   public boolean setString1(String str) {
      if (validString(str)) {
         string1 = str;
         return true;
      } else {
         return false;
      }
   }
   
   // Update the value of string2 to the given string, if valid
   // Return true if update is successful; otherwise return false
   public boolean setString2(String str) {
      if (validString(str)) {
         string2 = str;
         return true;
      } else {
         return false;
      }
   }
   
   // Update the value of string3 to the given string, if valid
   // Return true if update is successful; otherwise return false
   public boolean setString3(String str) {
      if (validString(str)) {
         string3 = str;
         return true;
      } else {
         return false;
      }
   }
   
   // Return a string containing the values of string1, string2, and string3
   public String toString() {
      return string1 + " " + string2 + " " + string3;
   }
   
   // Add winnings value to array and update pull count
   // Return true if pull count is less than MAX_PULLS; otherwise return false
   public boolean saveWinnings(int winnings) {
      pullWinnings[numPulls] = winnings;
      numPulls++;
      
      if (numPulls < MAX_PULLS) {
         return true;
      } else {
         return false;
      }
   }
   
   // Return string containing all winnings values
   public static String displayWinnings() {
      String winnings = "";
      int winningTotal = 0;
      for (int i = 0; i < numPulls; i++) {
         winnings += " $" + pullWinnings[i];
         winningTotal += pullWinnings[i];
      }
                              
      winnings += "\n Your total winnings were: $";
      winnings += winningTotal;
      
      return winnings;
   }
}