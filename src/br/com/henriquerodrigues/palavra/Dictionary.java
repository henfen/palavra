/**
 * 
 */
package br.com.henriquerodrigues.palavra;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Henrique Rodrigues 2924796
 *
 */


public class Dictionary {
	//Public objects for the objects of this class.
	public Set<String> dictionary  = new HashSet<String>();//Hashset of Strings to hold the dictionaries
	public Set<String> orderedDictionary  = new HashSet<String>(); //Hashset to hold a alphab banana aaabnn
	//Create Dictionary receiving 8 boolean parameters for each of the possible dictionaries selected and for Verbose mode. And an int between 1 and 100 for how many words of English we should use.
	public String createDic(boolean verbose, boolean cisNames, boolean cisSurnames, boolean engNames, boolean engSurnames, boolean generalPortuguese,boolean generalEnglish, int howMuchEnglish) throws IOException{
		long time = System.currentTimeMillis(); //Get the time to see how long a dict takes to be made
		int count = 0; //Count the words inputed into the dictionary
		String message = ""; //Start the message for verbose mode
		String currentLine = ""; //Start a blank line to read from the Dict
		if (cisNames){//If receive true as cisNames boolean argument do this dictionary
			Scanner filescan = new Scanner(new File("./filteredDics/cis-name")); //Open the file inside the filteredDics folder called cis-name
			while (filescan.hasNext()) { //While there is next lines in the file
				count++; //increase count
				currentLine = filescan.nextLine().toLowerCase(); //get all that is in the next line making it lowerCase
				dictionary.add(currentLine); //add to the main dictionary (hashset)
				orderedDictionary.add(orderString(currentLine)); //calling the function orderString that return a String with all letters arranged in alphabetical order ie: banana becomes aaabnn and add it to the Ordered dictionary, this will make checking for anagrams much faster
			}
			filescan.close(); //Close the fileScan to prevent leakage.
		}
		if (cisSurnames){ //Look cisNames for comments as it is really similar to this one
			Scanner filescan = new Scanner (new File("./filteredDics/cis-surname"));
			while (filescan.hasNext()) {
				count++;
				currentLine = filescan.nextLine().toLowerCase();
				dictionary.add(currentLine);
				orderedDictionary.add(orderString(currentLine));
			}
			filescan.close();
		}
		if (engNames){ //Look cisNames for comments as it is really similar to this one
			Scanner filescan = new Scanner (new File("./filteredDics/eng-name"));
			while (filescan.hasNext()) {
				count++;
				currentLine = filescan.nextLine().toLowerCase();
				dictionary.add(currentLine);
				orderedDictionary.add(orderString(currentLine));
			}
			filescan.close();
		}
		if (engSurnames){ //Look cisNames for comments as it is really similar to this one
			Scanner filescan = new Scanner (new File("./filteredDics/eng-surname"));
			while (filescan.hasNext()) {
				count++;
				currentLine = filescan.nextLine().toLowerCase();
				dictionary.add(currentLine);
				orderedDictionary.add(orderString(currentLine));
			}
			filescan.close();
		}
		if (generalPortuguese){ //Look cisNames for comments as it is really similar to this one
			Scanner filescan = new Scanner (new File("./filteredDics/ptbr"));
			while (filescan.hasNext()) {
				count++;
				currentLine = filescan.nextLine().toLowerCase();
				dictionary.add(currentLine);
				orderedDictionary.add(orderString(currentLine));
			}
			filescan.close();
		}
		if (generalEnglish){ 
			/*This is a bit different, using 10 different dictionaries 
			 * each of one representing the words found in 10 big websites
			 * match10 mean that the word appears in 10 of those sites
			 * match9 means that the word appears in 9 and so on.
			 * The match1 has a lot of typos, and made up words, but makes checking 
			 * for anagrams almost always a hit.
			 *  
			 * 
			 * */
			Scanner filescan = new Scanner (new File("./filteredDics/blank"));
			if (howMuchEnglish < 11) {
				filescan = new Scanner (new File("./filteredDics/match10"));
				message = ("English dictionary selected: Match 10\n");
			}
			if (howMuchEnglish > 10 && howMuchEnglish < 21) {
				filescan = new Scanner (new File("./filteredDics/match9"));
				message = ("English dictionary selected: Match 9\n");
			}
			if (howMuchEnglish > 20 && howMuchEnglish < 31) {
				filescan = new Scanner (new File("./filteredDics/match8"));
				message = ("English dictionary selected: Match 8\n");
			}
			if (howMuchEnglish > 30 && howMuchEnglish < 41) {
				filescan = new Scanner (new File("./filteredDics/match7"));
				message = ("English dictionary selected: Match 7\n");
			}
			if (howMuchEnglish > 40 && howMuchEnglish < 51) {
				filescan = new Scanner (new File("./filteredDics/match6"));
				message = ("English dictionary selected: Match 6\n");
			}
			if (howMuchEnglish > 50 && howMuchEnglish < 61) {
				filescan = new Scanner (new File("./filteredDics/match5"));
				message = ("English dictionary selected: Match 5\n");
			}
			if (howMuchEnglish > 60 && howMuchEnglish < 71) {
				filescan = new Scanner (new File("./filteredDics/match4"));
				message = ("English dictionary selected: Match 4\n");
			}
			if (howMuchEnglish > 70 && howMuchEnglish < 81) {
				filescan = new Scanner (new File("./filteredDics/match3"));
				message = ("English dictionary selected: Match 3\n");
			}
			if (howMuchEnglish > 80 && howMuchEnglish < 91) {
				filescan = new Scanner (new File("./filteredDics/match2"));
				message = ("English dictionary selected: Match 2\n");
			}
			if (howMuchEnglish > 90) {
				filescan = new Scanner (new File("./filteredDics/match1"));
				message = ("English dictionary selected: Match 1\n");
			}
			
			while (filescan.hasNext()) {
				count++;
				currentLine = filescan.nextLine().toLowerCase();
				//System.out.println(currentLine);
				dictionary.add(currentLine);
				orderedDictionary.add(orderString(currentLine));
			}

			filescan.close();
		}
		if (verbose){ //if the Verbose is selected tell all kind of informations for the user
			message += (count + " Words processed in " + (System.currentTimeMillis() - time) + " miliseconds");
			message += ("\nGenerating " + orderedDictionary.size() + " alphabeticaly ordered roots to check for anagrams");
			
		}
		return message;
	}


	public String getRandomObject() {
		//Get a random entry from the dictionary, good for some of the extra features from the software
		Random rnd = new Random();
		int x = rnd.nextInt(dictionary.size());
		int y = 0;
		for (String s : dictionary){
			if (y == x){
				return s;
			}
			else
				y++;
		}
		return "";
	}
	
	public String orderString(String s1){//Generate a String with all letters alphabetically sorted
		s1 = s1.toLowerCase(); //make everything lowerCase (it is already, but better safe than sorry)
		char[] charArray = s1.toCharArray(); //make an char array
		Arrays.sort(charArray); //Call the sort method of the object array, this is where the magic happen
		String result = ""; //make a String to store the result
		for (int i =0 ; i < s1.length();i++){
			result += charArray[i]; //make the char array become a String in a brute force approach
		}
		
		return result;
	}
	public String giveAnagrams(String s){ //given a String, find the anagrams for that String
		s = s.toLowerCase(); //all to lower case
		String knownAnagrams = ""; 
		Set<String> perm = StringPermutations.permutationFinder(s); //make a Set and store the permutations that come from this method
		for (String s2 : perm) {
		    //System.out.println(s);
		    if (dictionary.contains(s2) && s2 != s){//Check the set of Strings against the dictionary to see if the word is there
		    	knownAnagrams += " " + s2; //Add to a String of the know anagrams

		    }	
		}
		if (knownAnagrams.equals(" "+s)){
			knownAnagrams = ""; //If the only know anagram is the word itself, clear the String
		}
		else {
			//System.out.println(knownAnagrams);
			knownAnagrams = " (Known anagrams:" + knownAnagrams + ")"; //else format the result
		}
		return knownAnagrams; //return it
	}
	public boolean hasAnagrams(String s){ //method to check a word against the dictionary to see if the alphabetically ordered version of the word is into the dictionary meaning we know that word
		s = orderString(s); //Order the String to check against the dict
		if (orderedDictionary.contains(s)){ //return what we discovered
			return true;
		}
		else{
			return false;
		}
		
	}
	public void murderDict(){
		dictionary.clear();
		orderedDictionary.clear();
	}
	public void createPangramDictionary(){
		String currentLine="";
		try {
			Scanner filescan = new Scanner(new File("./filteredDics/pangram")); //Open the file inside the filteredDics folder called cis-name
			while (filescan.hasNext()) { //While there is next lines in the file
				currentLine = filescan.nextLine();//get all that is in the next line making it lowerCase
				dictionary.add(currentLine); //add to the main dictionary (hashset)
			}
			filescan.close();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}

