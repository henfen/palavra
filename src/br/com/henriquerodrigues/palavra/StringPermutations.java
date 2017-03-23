/**
 * 
 */
package br.com.henriquerodrigues.palavra;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Henrique Rodrigues 2924796
 * 
 *
 */
public class StringPermutations { 
	public static Set<String> permutationFinder(String str) { //Based on code found at: http://www.journaldev.com/526/java-program-to-find-all-permutations-of-a-string
        Set<String> perm = new HashSet<String>();
        //Handling error scenarios
        if (str == null) {
            return null;
        } else if (str.length() == 0) {
            perm.add("");
            return perm;
        }
        char initial = str.charAt(0); // first character
        String rem = str.substring(1); // Full string without first character
        Set<String> words = permutationFinder(rem);
        for (String strNew : words) {
            for (int i = 0;i<=strNew.length();i++){
                perm.add(charInsert(strNew, initial, i));
                //System.out.println(strNew);
            }
        }
        //System.out.println("Calculating: " + count + " combinations");
        return perm;
	}
       


    public static String charInsert(String str, char c, int j) { //Method to insert a Character in a String used in the Permutations to do Anagrams
        String begin = str.substring(0, j); //break the string in 2 first part from 0 to j
        String end = str.substring(j);//Second part from J beyond
        return begin + c + end; //concatenate everything
    }
    
    
    
    public String orderString(String s1){ //Method to order a String in Alphabetical order test become estt
    	s1 = s1.toLowerCase(); //everything to lower case (propably is already, but anyway)
    	char[] charArray = s1.toCharArray(); //Make a string become a proper char array
    	Arrays.sort(charArray); //sort the char array
    	String result = ""; //new empty String to concatenate the result into
    	for (int i =0 ; i < s1.length();i++){ //itarate through char String
    		result += charArray[i]; //adding the char array back to the String
    	}
    	
    	return result; //Spit it back.
    }
    
    
    
    public static String[] stringBreaker(String s){ //Break a String into an array of Strings
    	String[] result = new String[0]; //new empty array
    	if (s == "") { //If its blank
    		result = new String[1]; //size 1
    		result[0] = ""; //index 0 is empty
    		//System.out.println(result[1]);
    		return result; //spit it back
    	}
    	s = s.replaceAll("[^a-zA-Z ]", ""); //here all characters that are not a through z and A - Z, or spaces are deleted from the String
    	result = s.split(" ");//as we keept the spaces into the string use them to break it up
    	return result; // return result
    }
    
    
    
    public static String[] invertNth (String[] s, int n){ //Taking an Array of Strings s, and an integer n, flip each String that has the Index a multiple of N around.
    	String actual = ""; 
    	String inverted = "";
    	String[] result = new String[s.length]; //make a new String to give back the result
    	if (n > 0) { //Check if the user is not kidding with us, even we getting rid of the - that makes numbers negative in the code that get N
	    	for (int i = 0; i < s.length;i++){ //Iterate through the array
	    		if ((i+1) % n == 0) { //using the trick of i+1 to convert from 0 start computer count to 1 start human count (modulus n etc etc)
	    			actual = s[i]; //the string that we will deal now is in s in the index of i
	    			for (int j = actual.length()-1; j >= 0; j--){ //Start from the end of the string, iterate though and
	    				inverted += actual.charAt(j); //Concatenate back
	
	    			}
	    			result[i] = inverted; //put inverted into the String array that we will trow back
	    			actual = ""; //reset actual
	    			inverted =""; //reset inverted
	    		}
	    		else {
	    			result[i] = s[i]; //If its not a multiple of the n number inputed by the user, make it become what it was
	    		}
	    	}
    	}
    	else { //If there was no input, or it was 0
			result = s; //just give back what we received
		}
    	return result; //trow it back
    }
    
    
    
    
    public static int getN (String s){ //A robust method to get the first integer we find beginning from the end of the code to the start    	
    	int result = 0; // new result to store
    	String invertedResult = ""; //lets take the result from end to start and store here in the future
    	String resultAsString = "0"; //We will get a String of numbers and put it here, if one is found as We will do a Integet.parseInt in the future, and don't want to create blackholes, we make sure the result is a number
    	boolean wasANumber = false; //make a boolean to make our life easier to break the loop
    	if (s.length() > 0){ //If String s represents something
	    	for (int i = s.length()-1; i>0;i--){ //starting from the end and going all way to the start of the String
	    		if(Character.isDigit(s.charAt(i))){ //check if the actual char is a digit
	    			if (!wasANumber){ //So if this char is a number, and the last was a number too (wasANumber start as false, this is double negation)
	    				resultAsString = ""; //Get rid of the 0 in the String
	    			}
	    			wasANumber = true; //Set was a number to true because it is a number now
	    			invertedResult += s.charAt(i); //add it to a String (if its a more than 1 digit number it will be inverted in the end, but we will deal with that  			
	    		}
	    		else { //So its not a number 
	    			if (wasANumber){ //If it was (and is not anymore), we got as many digits we need
	    				break; //So break
	    			}
	    		}
	    	}

	    	for (int i = invertedResult.length()-1; i > -1; i--){
	    		resultAsString += invertedResult.charAt(i); //now flip the numbers back, 
	    	}
    	}
    	//System.out.println(resultAsString); //A test to see if this mess works
    	result = Integer.parseInt(resultAsString); //and Thou String Shall became and Integer
    	return result; //If we did all right, we should be able to pick the last number that the user inputed, it being 1 or 2 billion
    }
    
    
  /*  public static String[] invertAll (String[] s){ 
    	String actual = "";
    	String inverted = "";
    	String[] result = new String[s.length];
    	for (int i = 0; i < s.length;i++){
	    	actual = s[i];
	    	for (int j = actual.length()-1; j >= 0; j--){          An Old ugly Skeleton
	    		inverted += actual.charAt(j);
	    		}
	   		result[i] = inverted;
	   		actual = "";
	   		inverted ="";
    	}
 
    	return result;
    }
    */
    
    
    public static boolean isPalindrome(String s){ //Check for palindromes
    	s = cleanString(s); //Make sure s has only letters
    	s = s.toLowerCase(); //And is lower case
    	int lastChar = s.length()-1; //get the position for the last Char
    	for (int i = 0; i < lastChar; i++){ //Iterate trough (It will compare first to last, second to second to last and so on (0+n to last-n) In an odd String they will not compare the last one, but it will be itself
			//System.out.println("i = " + i + " lastChar = " + lastChar);
    		if (s.charAt(lastChar) != s.charAt(i)){//If something is not equal trough our iterations Return false
    			//System.out.println("i = " + i + " lastChar = " + lastChar); //A check
    			//System.out.println(s.charAt(lastChar) + " is not equal " + s.charAt(i)); //Another Check
    			return false;
    		}
    		lastChar--; //take one for the last so the loop works properly
    	}
    	return true; //If we didn't return false, probably we should return true
    }
    
    
    public static String cleanString(String s){ //A simple regex method to clean everything
    	s = s.replaceAll("[^A-Za-z]",""); //Thank for wikipedia for the tutorial  https://en.wikipedia.org/wiki/Regular_expression#Examples
    	return s;
    }
    
    
    
    public static boolean isPangram(String[] s){ //lets see if this has every letter of the alphabet

    	String allStrings= ""; //make a place to put the String
    	for (int i = 0; i < s.length; i++){ //Iterate through  
    		allStrings+=s[i]; //Concatenating every letter
    	}
    	if (allStrings.length() < 26){//If the whole thing is shorter than 26 character, its impossible to be a Pangram
    		return false; //So return false and save time
    	}
    	allStrings = allStrings.toUpperCase(); //Make everything uppercase, to be easier to check
    	for (char c = 'A'; c <= 'Z'; c++){ //Make this beatiful loop that I took from StackOverflow TODO:FIND THE LINK
    		if(allStrings.indexOf(c) < 0){ // We have the letter that we are iterating in the String? If we don't this will return -1
    			return false; //So we don't, return false as there is a letter missing
    		}
    	}
    	return true; //We tried everything to disprove and couldn't so let it be a Pangram
    }
    
    
    public static int countSArrayChars(String[] s){ //More bells and Whistles to check how long the Pangram was
    	int result = 0; 
    	for (int i=0; i < s.length; i++){ //going thought the strings
    		result += s[i].length(); //Sum it size here
    	}
    	return result; //Give the result back
    }
    
    /** 
     * 
     * FROM THIS POINT ON ALL THE FUNCTIONS ARE ONLY USED IN THE BONUS EXERCISES
     * 
     */
    
}


