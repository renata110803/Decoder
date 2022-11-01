
/* Renata Gabdrakhmanova
 * pd 2
 * 3/14/21
 * 
 * Pig Latin- Takes in a sentence and converts it to Pig Latin based on the
 * rules of this language.
 */
import java.util.*;

public class PigLatin implements Cryptable {

	// encrypts the sentence
	public String encrypt(String text) {
		String translation = phraseToPig(text);
		return translation;
	}

	// decrypts the sentence
	public String decrypt(String text) {
		String decrypt = "";
		String[] result = text.split(" ");

		for (int i = 0; i < result.length; i++) {
			decrypt = decrypt+ decryptWord(result[i])+" ";
		}

		return decrypt;
	}

	// decrypts each word sent in by removing -ay in the end or transforming the
	// word in its initial phase
	private String decryptWord(String word) {
		int index = word.indexOf("-ay");
		int loc = word.indexOf("-");

		// takes -ay away
		if (index > -1) {
			word = word.substring(0, index);
		}

		// if the word was switched around than it needs to be switched back to its
		// initial phase deleting -ay on the way
		else if (index == -1 && loc != -1) {
			String copy = word;
			word = word.substring(0, word.length() - 2);
			String first = word.substring(loc + 1, word.length());
			String second = word.substring(0, loc);
			word = first + second;
			if (isCapitalized(copy) == true) {
				capitalize(word);
			}
		}
		return word;
	}

	// Checks to see if the word contains a vowel, if it does it returns the
	// location of that letter
	// If not then returns -1
	private int hasAVowel(String word) {
		String[] vowels = { "a", "o", "i", "e", "u" };

		// first it will check from the start, letter by letter adding a new letter each
		// time to the copy
		for (int i = 0; i < word.length(); i++) {
			String copyWord = word.substring(0, i + 1);

			// it will check if the new chunk has a vowel by running through an array
			// if the vowel was found it will return it location
			for (int b = 0; b < vowels.length; b++) {
				int VowelPosition = copyWord.indexOf(vowels[b]);

				if (VowelPosition != -1) {
					return VowelPosition;
				}
			}

		}
		// if not it will return a negative number
		return -1;
	}

	/*
	 * Checks if the word is capitalized or not by saving the copy of the word and
	 * having its first letter capitalized then comparing the original and the copy,
	 * if they match return true, if not false.
	 */

	private boolean isCapitalized(String word) {
		String copy = word.substring(0, 1);
		copy = copy.toUpperCase() + word.substring(1);

		if (word.equals(copy)) {
			return true;
		}

		return false;
	}

	/*
	 * Capitalizes the translated word, if the original was capitalized First it
	 * will convert everything to lowercase, then converts the first letter of the
	 * word to upper case and adds everything back together to make a full word and
	 * returns it.
	 */
	private String capitalize(String word) {

		word = word.toLowerCase();
		String copy = word.substring(0, 1);
		copy = copy.toUpperCase() + word.substring(1);
		return copy;
	}

	/*
	 * Takes in one word from the sentence and checks to see where the word should
	 * be translated based on the rules. returns new word- translated
	 */
	private String wordToPig(String english) {
		int VowLocation = hasAVowel(english);

		// searches for vowel if -1 it was not found, if 0 the first letter is a vowel
		if (VowLocation == -1 || VowLocation == 0) {
			english = english + "-ay";
			return english;
		}

		// if the word is capitalized it will make a translated word start with a
		// capital letter too
		else if (isCapitalized(english)) {
			String firsthalf = english.substring(0, VowLocation);
			String secondhalf = english.substring(VowLocation);
			english = secondhalf + "-" + firsthalf + "ay";

			return capitalize(english);
		}
		String firsthalf = english.substring(0, VowLocation);
		String secondhalf = english.substring(VowLocation);
		english = secondhalf + "-" + firsthalf + "ay";

		return english;
	}

	/*
	 * takes in a sentence and breaks down by words, each word is sent to another
	 * function to convert it to Pig Latin returns the full translated sentence.
	 */
	private String phraseToPig(String phrase) {
		String translation = "";
		String[] result = phrase.split(" ");

		for (int i = 0; i < result.length; i++) {
			translation = translation+ wordToPig(result[i])+" ";
		}

		return translation;
	}
}
