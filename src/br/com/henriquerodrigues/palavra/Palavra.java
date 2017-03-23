//Palavra(Portuguese for Word), a software that Parlava (past tense of speak in Italian) a Palavar(A series of words that don't make much sense) of Palrava(words without much meaning).
package br.com.henriquerodrigues.palavra;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.swing.JOptionPane;

public class Palavra {
	//this is the class that call the other classes and the more higher level operations happen here
	
	
	/**
	 * 
	 * 
	 * START OF THE MAIN ASSIGMENT AREA THE OTHER FUNCTIONS ARE A BONUS
	 * 
	 * 
	 */
	
	public static Dictionary dict = new Dictionary();
	
	public static void main(String[] args) {
		try {//Try to start the interface or die
			FullInterface window = new FullInterface();
			window.frame.setVisible(true); //call method to set base frame of interface visible
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace(); //If any error happens
		}	
		
		
		}
	
	public static void assigmentTest (boolean[] userSettings, int sliderValue, String uInput){ //I made this a boolean array to dodge a Guiness Book of record nomination for biggest Parameters in a function
		/** Unpack the array to human readable for debugging it came as 0-7: verbose, cisNames, cisSurnames, engNames, engSurnames, generalPortuguese, generalEnglish, chckbxCopyResultTo.isSelected() */
		boolean verbose = userSettings[0]; 
		boolean cisNames = userSettings[1];
		boolean cisSurnames = userSettings[2];
		boolean engNames = userSettings[3];
		boolean engSurnames = userSettings[4];
		boolean generalPortuguese = userSettings[5];
		boolean generalEnglish = userSettings[6];
		boolean copyToClipboard = userSettings[7];
		//Build the Start of the verboseMessage that will show if the user selected the little box in the top of the software
		String dictMessage = "";//A message to store what the dictionary will tell us, useful for the verbose way to use the software
		try { //Try to make the dictionary sending the parameters
			dictMessage = dict.createDic(verbose, cisNames, cisSurnames, engNames, engSurnames, generalPortuguese, generalEnglish, sliderValue);
			}
			catch (Exception e){ //Probably the dictionaries couldn't be found, or accessed They need to be with the original names and the software need to be able to access them
				JOptionPane.showMessageDialog(null, "Something went awfully wrong, You didn't delete the Dictionary Files did you? If you don't know what is happening download the files again, or contact 24 hours Apple Care Support Center, they probably can't help you, but neither do I.");
			}
		int numberInputed = StringPermutations.getN(uInput); //Call the method that will tell what N the user Inputed
		
		String[] brokenDownUInput = StringPermutations.stringBreaker(uInput); //break the String into little pieces after clean it						
		String[] answerInvertedUserInput = StringPermutations.invertNth(brokenDownUInput, numberInputed); //do the main thing the Assignment asked, inverting the Nth Strings
		String result = ""; 
		if (StringPermutations.isPangram(brokenDownUInput)){ //Send the broken down user input to check for Pangrams this will return true or false
			result += "The Input is a "+ StringPermutations.countSArrayChars(brokenDownUInput) +" letters Pangram\n"; //Tell that its a pangram
		}
		for (int i = 0; i < answerInvertedUserInput.length; i++){ //Lets iterate to Check for anagrams,

			if (dict.hasAnagrams(brokenDownUInput[i])){ //Check for the roots of the words to save time in really big words without anagrams
				result += answerInvertedUserInput[i] + dict.giveAnagrams(brokenDownUInput[i]); //Concatenate what we have of inverted Strings with the Anagrams given by the Dictionary and concatenate on the result
				if (StringPermutations.isPalindrome(answerInvertedUserInput[i])){ //We check for Palindrome
					result += " (Palindrome)\n"; //And tell if it is
				}
				else { //If is not a Palindrome
					result += "\n"; //Just go a new line
				}
			}
			else {//If there is no Anagrams for such a word
				if (StringPermutations.isPalindrome(answerInvertedUserInput[i])){//Check for Palindrome
					result += answerInvertedUserInput[i] + " (Palindrome)\n";//Tell if it is after the input
				}
				else {//If its not a palindrome
					result += answerInvertedUserInput[i] + "\n";//Just add what we have to the result and go to a new line
				}
				
			}
		}
		if (verbose){ //If the user want to see what is happening behind the curtains
			String verboseMessage = "Your input is \" " + uInput + "\"\n"; //Start making a message to the user
			verboseMessage += dictMessage; //Adding what we got from the dictionary
			verboseMessage += "\nThe Selected N is: " + numberInputed; //Tell the N that we found
			verboseMessage += "\nThe Broken Down User Input is: "; //A arbitrary line
			for (int i = 0; i < brokenDownUInput.length; i++){ //show the user input line by line
				verboseMessage += "\n" + brokenDownUInput[i];
			}
			FullInterface.showMessage(verboseMessage); //Print everything is that awful looking java pop up		

		}
		if (StringPermutations.isPalindrome(uInput)){ //Lets check if everything is a Palindrome now
			result += "\nThe full Input is a Palindrome that has " + StringPermutations.cleanString(uInput).length() + " character counting only letters.\n"; //Tell the user if it is
		}

		if (numberInputed > brokenDownUInput.length){ //The user input an N that is > than the sum of the words in S
			result += "\nSorry there is no " + numberInputed + "th word in your input. Call again."; //If the user inputed a number that is greater than the String, tell him, but play along and do what you can.
		}
		FullInterface.showMessage(result); //Show the message
		dict.murderDict(); //Erase the Dictionary so the user can change the input
		if (copyToClipboard){ //If the user had selected to have his result copied to the clipboard do so.
		    StringSelection selection = new StringSelection(result); //make a StringSelectio (don't ask me why)
		    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard(); //get the System clipboard (I know why)
		    clipboard.setContents(selection, selection); //put the selection inside the clipboard
		}
		
		
	}
	/**
	 * 
	 * 
	 * 
	 * -----------------END OF MAIN ASSIGMENT AREA FROM NOW ITS A BONUS------------------------
	 * -----------------SORRY FOR THIS AWFUL AMOUNT OF CODE, BUT I LIKED THIS -----------------
	 * -----------------MAYBE WAY TOO MUCH. I OWE YOU A COOKIE. -------------------------------
	 * 
	 * 
	 * 
	 */
	public static void generateRandomNames(boolean[] userSettings, int sliderValue, String uInput){ //This class will generate random names from the selected dictionary
		//look above function to see what is happening from here
		/** Unpack the array to human readable for debugging it came as 0-7: verbose, cisNames, cisSurnames, engNames, engSurnames, generalPortuguese, generalEnglish, chckbxCopyResultTo.isSelected() */
		boolean verbose = userSettings[0]; 
		boolean cisNames = userSettings[1];
		boolean cisSurnames = userSettings[2];
		boolean engNames = userSettings[3];
		boolean engSurnames = userSettings[4];
		boolean generalPortuguese = userSettings[5];
		boolean generalEnglish = userSettings[6];
		boolean copyToClipboard = userSettings[7];
		String result = "";
		String verboseMessage = "";
		try {
			verboseMessage += dict.createDic(verbose, cisNames, cisSurnames, engNames, engSurnames, generalPortuguese, generalEnglish, sliderValue);
			}
			catch (Exception e){
				JOptionPane.showMessageDialog(null, "Something went awfully wrong, You didn't delete the Dictionary Files did you? If you don't know what is happening download the files again, or contact 24 hours Apple Care Support Center, they probably can't help you, but neither do I.");
			}
		//To here
		String capitalize = ""; //Lets capitalize the names
		String randomWord = ""; //lets store the random word here
		int n = 3; //if the user didn't gave us a number lets use 3
		if (uInput.hashCode() != 0){ //If the user inputer something
			char uInputFirstChar = uInput.charAt(0); //Get just the first character, because I'm lazy right now
			if (Character.isDigit(uInputFirstChar)){ //see it is a digit
				n = Character.getNumericValue(uInputFirstChar); //Make it become a number
			}
		}

		for (int i = 0; i < n; i++){ //Generate n names n being first digit of user input or 3
			randomWord = dict.getRandomObject(); //call our dictionary and make him bring a random String from there
			capitalize += randomWord.charAt(0); //Start capitalize process of what the dicitonary gave us
			capitalize = capitalize.toUpperCase(); 
			capitalize += randomWord.substring(1, randomWord.length()) + " ";
			result += capitalize;//end of the process
			capitalize = ""; //get ready for a new round
			randomWord = "";
		}
		if (verbose){
			FullInterface.showMessage(verboseMessage); //If verbose show what the dictionary told us

		}
		FullInterface.showMessage(result); //Show the result
		dict.murderDict(); //Erase the Dictionary so the user can change the input
		if (copyToClipboard){ //And copy to the clipboard if required
		    StringSelection selection = new StringSelection(result);
		    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		    clipboard.setContents(selection, selection);
		}
	}
	public static void generateAnagram(boolean[] userSettings, int sliderValue, String uInput){ //We use this to generate Anagrams using the user input
		/** Unpack the array to human readable for debugging it came as 0-7: verbose, cisNames, cisSurnames, engNames, engSurnames, generalPortuguese, generalEnglish, chckbxCopyResultTo.isSelected() */
		boolean verbose = userSettings[0]; 
		boolean cisNames = userSettings[1];
		boolean cisSurnames = userSettings[2];
		boolean engNames = userSettings[3];
		boolean engSurnames = userSettings[4];
		boolean generalPortuguese = userSettings[5];
		boolean generalEnglish = userSettings[6];
		boolean copyToClipboard = userSettings[7];
		String result = "";
		String verboseMessage = "";
		try {
			verboseMessage += dict.createDic(verbose, cisNames, cisSurnames, engNames, engSurnames, generalPortuguese, generalEnglish, sliderValue);
			}
			catch (Exception e){
				JOptionPane.showMessageDialog(null, "Something went awfully wrong, You didn't delete the Dictionary Files did you? If you don't know what is happening download the files again, or contact 24 hours Apple Care Support Center, they probably can't help you, but neither do I.");
			}
		
		for (int i = 0; i < 3; i++){ //Generate 3 names
			result += (dict.getRandomObject()).toUpperCase() + " ";
		}
		
		FullInterface.showMessage(result);
		dict.murderDict(); //Erase the Dictionary so the user can change the input
		if (copyToClipboard){
		    StringSelection selection = new StringSelection(result);
		    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		    clipboard.setContents(selection, selection);
		}
	}
	public static void generatePangram(boolean[] userSettings, int sliderValue, String uInput){
		/** Unpack the array to human readable for debugging it came as 0-7: verbose, cisNames, cisSurnames, engNames, engSurnames, generalPortuguese, generalEnglish, chckbxCopyResultTo.isSelected() */
		boolean copyToClipboard = userSettings[7];
		String result = "";
		try {
			dict.createPangramDictionary();
			}
		catch (Exception e){
				FullInterface.showMessage("Something went awfully wrong, You didn't delete the Dictionary Files did you? If you don't know what is happening download the files again, or contact 24 hours Apple Care Support Center, they probably can't help you, but neither do I.");
			}
		result = dict.getRandomObject();
		FullInterface.showMessage(result);
		dict.murderDict();
		if (copyToClipboard){
		    StringSelection selection = new StringSelection(result);
		    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		    clipboard.setContents(selection, selection);
		}
		
		
		
	}
}



	


