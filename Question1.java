
// -------------------------------------------------------
// Assignment A3
// Written by: Rodger S. Ragasa
// For COMP 248 Section (FF) – Fall 2019
// --------------------------------------------------------

/**
 * 
 * Description: Question 1. Generate a password using data from the 3d array below. The password is composed of three words. The page number, paragraph number, and line number are chosen randomly. The password generator program will continue generating passwords until its generates
 *  a total of 10,000 passwords, OR if it generates a password where the number of password generation attempts caused by having a password generated that contains NO lowercase strings be higher than zero.
 * The methods used in this program are organized as such:
 * 	Methods used to extract a random word and check whether it is a single char and if it has a new line character: \n. These 2 methods return a word.
 *	Step 1 -> getRandomWord()
 *	Step 2 -> singleCharNewLineCheck()
 *	Methods using the 3 strings as inputs to return a validPassword string.
 *	Step 3 -> generatePassword()
 *	Step 4 -> scanUppercase()
 *	Step 5 -> scanLowercase()
 * 
 * 
 */

import java.util.Random;

public class Question1 {
	// Quoted text from http://www.gutenberg.org/cache/epub/19033/pg19033.txt
	final public static String[][][] book = { {
			{ "ALICE was beginning to get very tired of sitting by her sister on the\n",
					"bank, and of having nothing to do. Once or twice she had peeped into the\n",
					"book her sister was reading, but it had no pictures or conversations in\n",
					"it, \"and what is the use of a book,\" thought Alice, \"without pictures or\n",
					"conversations?\"\n" },
			{ "So she was considering in her OWN mind (as well as she could, for the\n",
					"day made her feel very sleepy and stupid), whether the pleasure of\n",
					"making a daisy-chain would be worth the trouble of getting up and\n",
					"picking the daisies, when suddenly a White Rabbit with pink eyes ran\n", "close by her.\n" },
			{ "There was nothing so very remarkable in that, nor did Alice think it so\n",
					"very much out of the way to hear the Rabbit say to itself, \"Oh dear! Oh\n",
					"dear! I shall be too late!\" But when the Rabbit actually took a watch\n",
					"out of its waistcoat-pocket and looked at it and then hurried on, Alice\n",
					"started to her feet, for it flashed across her mind that she had never\n",
					"before seen a rabbit with either a waistcoat-pocket, or a watch to take\n",
					"out of it, and, burning with curiosity, she ran across the field after\n",
					"it and was just in time to see it pop down a large rabbit-hole, under\n",
					"the hedge. In another moment, down went Alice after it!" } },
			{ { "\"WHAT a curious feeling!\" said Alice. \"I must be shutting up like a\n", "telescope!\"\n" },
					{ "And so it was indeed! She was now only ten inches high, and her face\n",
							"brightened up at the thought that she was now the right size for going\n",
							"through the little door into that lovely garden.\n" },
					{ "After awhile, finding that nothing more happened, she decided on going\n",
							"into the garden at once; but, alas for poor Alice! When she got to the\n",
							"door, she found she had forgotten the little golden key, and when she\n",
							"went back to the table for it, she found she could not possibly reach\n",
							"it: she could see it quite plainly through the glass and she tried her\n",
							"best to climb up one of the legs of the table, but it was too slippery,\n",
							"and when she had tired herself out with trying, the poor little thing\n",
							"sat down and cried.\n" },
					{ "\"Come, there's no use in crying like that!\" said Alice to herself rather\n",
							"sharply. \"I advise you to leave off this minute!\" She generally gave\n",
							"herself very good advice (though she very seldom followed it), and\n",
							"sometimes she scolded herself so severely as to bring tears into her\n", "eyes.\n" },
					{ "Soon her eye fell on a little glass box that was lying under the table:\n",
							"she opened it and found in it a very small cake, on which the words \"EAT\n",
							"ME\" were beautifully marked in currants. \"Well, I'll eat it,\" said\n",
							"Alice, \"and if it makes me grow larger, I CAN reach the key; and if it\n",
							"makes me grow smaller, I can creep under the door: so either way I'll\n",
							"get into the garden, and I don't care which happens!\"\n" },
					{ "She ate a little bit and said anxiously to herself, \"Which way? Which\n",
							"way?\" holding her hand on the top of her head to feel which way she was\n",
							"growing; and she was quite surprised to find that she remained the same\n",
							"size. So she set to work and very soon finished off the cake." } },
			{ { "The King and Queen of Hearts were seated on their throne when they\n",
					"arrived, with a great crowd assembled about them--all sorts of little\n",
					"birds and beasts, as well as the whole pack of cards: the Knave was\n",
					"standing before them, in chains, with a soldier on each side to guard\n",
					"him; and near the King was the White Rabbit, with a trumpet in one hand\n",
					"and a scroll of parchment in the other. In the very middle of the court\n",
					"was a table, with a large DISH of tarts upon it. \"I wish they'd get the\n",
					"trial done,\" Alice thought, \"and hand 'round the refreshments!\"\n" },
					{ "The judge, by the way, was the King and he wore his crown over his great\n",
							"wig. \"That's the jury-box,\" thought Alice; \"and those twelve creatures\n",
							"(some were animals and some were birds) I suppose they are the jurors.\"\n" },
					{ "Just then the White Rabbit cried out \"Silence in the court!\"\n" },
					{ "\"HERALD! read the accusation!\" said the King." } } };

	// initialize variables for password strings
	static String selectedWord1, selectedWord2, selectedWord3;
	static String password = "";
	// counter variables for the number of failed attempts due to:
	// (i) having a newline character
	// (ii) having a single-character word
	// (iii) having the same words
	// (iv) not meeting the uppercase requirement
	// (v) not meeting the lowercase requirement
	// (vi) not meeting the special character requirement
	static int newLine, single, equal, length, upper, lower, special;

	public static void main(String[] args) {
		System.out.println("Welcome to the password generator game!\n");

		// generate 10,000 passwords
		int passwordGenerationAttempts = 0;
		while (passwordGenerationAttempts < 10000 & lower == 0) {
			// start the statistics counter from 0 for EVERY password generation
			// attempt
			newLine = 0;
			single = 0;
			equal = 0;
			length = 0;
			upper = 0;
			lower = 0;
			special = 0;
			// get 3 initial random words from the quoted text
			String selectedWord1 = getRandomWord();
			String selectedWord2 = getRandomWord();
			String selectedWord3 = getRandomWord();

			String validPassword = generatePassword(selectedWord1, selectedWord2, selectedWord3);

			// print results
			String formattedString = "Password = %-16s \tNewline = %1d \tSingle = %1d \tEqual = %1d \tLength = %1d \tUpper = %1d \tLower = %1d \tSpecial = %1d%n";
			System.out.printf(formattedString, validPassword, newLine, single, equal, length, upper, lower, special);

			++passwordGenerationAttempts;
		} // end of passwordGeneration while loop

		System.out.print("\nTotal passwords generated: " + passwordGenerationAttempts
				+ "\n\nThanks for using the password generator game. Good bye.");

	}// end of main() method

	// -----------------------------------------------------------------------------------------------------------------
	//
	// My methods used in the program
	//
	// -----------------------------------------------------------------------------------------------------------------

	// --------------------------------------------
	//
	// Methods for single words
	// -> getRandomWord()
	// -> singleCharNewLineCheck()
	//
	// --------------------------------------------
	public static String getRandomWord() {
		// determine array and inner array lengths of the quoted text
		int numberOfPages = book.length;
		int numberOfParagraphs = 0;
		int numberOfLines = 0;

		// book length is the number of pages. Cycle through each page
		for (int i = 0; i < book.length; i++) {
			// book[i].length is the number of paragraphs. Cycle through each
			// paragraph
			for (int j = 0; j < book[i].length; j++) {
				// book[i][j].length is the number of lines.
				for (int k = 0; k < book[i][j].length; k++) {
					numberOfLines += 1;
				}
				numberOfParagraphs += 1;
			}
		}

		Random randNumberGenerator = new Random();
		int chosenPageNumber = randNumberGenerator.nextInt(numberOfPages);
		int chosenParagraphNumber = randNumberGenerator.nextInt(book[chosenPageNumber].length);
		int chosenLineNumber = randNumberGenerator.nextInt(book[chosenPageNumber][chosenParagraphNumber].length);

		// let's choose a random line from the quoted text and convert it to an
		// array of words
		int chosenLineLength = book[chosenPageNumber][chosenParagraphNumber][chosenLineNumber].split(" ").length;

		String[] storedWords = new String[chosenLineLength];

		for (int w = 0; w < chosenLineLength; w++) {
			storedWords = book[chosenPageNumber][chosenParagraphNumber][chosenLineNumber].split(" ");
		}

		// choose a random word from the chosen line
		String randomWord = storedWords[randNumberGenerator.nextInt(chosenLineLength)];

		// remove single character words and "\n" words
		randomWord = singleCharNewLineCheck(randomWord);

		return randomWord;

	}// end of method getRandomWord()

	public static String singleCharNewLineCheck(String randomWord) {
		// if the chosen word only has 1 character or contains a \n character,
		// choose another word
		if (randomWord.length() == 1) {
			while (randomWord.length() == 1) {
				randomWord = getRandomWord();
				// increase password generation attempt counter due to the
				// extracted word being only 1 character
				single++;
			}
		}
		if (randomWord.indexOf("\n") >= 0) {
			while (randomWord.indexOf("\n") >= 0) {
				randomWord = getRandomWord();
				// increase password generation attempt counter due there being
				// a \n character in the extracted word
				newLine++;
			}
		}

		return randomWord;
	}// end of singleCharNewLineCheck()

	// --------------------------------------------
	//
	// Methods to generate password with string concatenation
	// -> generatePassword()
	// -> scanUppercase()
	// -> scanLowercase()
	//
	// --------------------------------------------
	public static String generatePassword(String selectedWord1, String selectedWord2, String selectedWord3) {

		// check that two words are not exactly the same, if not find another
		// word
		boolean sameWordsExist = selectedWord1.compareToIgnoreCase(selectedWord2) == 0
				|| selectedWord2.compareToIgnoreCase(selectedWord3) == 0
				|| selectedWord1.compareToIgnoreCase(selectedWord3) == 0;

		if (sameWordsExist) {
			while (sameWordsExist) {
				selectedWord2 = getRandomWord();
				selectedWord1 = getRandomWord();
				sameWordsExist = selectedWord1.compareToIgnoreCase(selectedWord2) == 0
						|| selectedWord2.compareToIgnoreCase(selectedWord3) == 0
						|| selectedWord1.compareToIgnoreCase(selectedWord3) == 0;
				// increase password generation attempt counter because 2
				// similar words were extracted
				equal++;
				generatePassword(selectedWord1, selectedWord2, selectedWord3);
			}
		}
		password = selectedWord1 + selectedWord2 + selectedWord3;

		// password must contain only ONE special (non-alphabetic) character
		boolean hasOneSpecialChar = false;

		// detect number of special characters/punctuation from password
		String specialCharacters = "~`!@#$%^&*()_+=-\\\'\"[] {}:;?/.,<>|0123456789";
		int specialCharsCount = 0;
		/// count the number of special characters inside the password
		// check each letter of the password
		for (int j = 0; j < password.length(); j++) {
			for (int i = 0; i < specialCharacters.length(); i++) {
				hasOneSpecialChar = password.substring(j, j + 1).indexOf(specialCharacters.substring(i, i + 1)) >= 0;
				if (hasOneSpecialChar) {
					specialCharsCount++;
				}
			}
		}
		// if it has more than 1 special non-alphabetic character, make a new
		// password
		if (specialCharsCount > 1) {
			// increase password generation attempt counter due to special
			// character criteria not being met
			special++;
			selectedWord1 = getRandomWord();
			selectedWord2 = getRandomWord();
			selectedWord3 = getRandomWord();
			generatePassword(selectedWord1, selectedWord2, selectedWord3);
		}

		// check that the password criteria is met: password length, password
		// contains an uppercase and a lowercase character
		boolean passwordLengthAcceptable = password.length() >= 8 & password.length() <= 16;
		boolean passwordHasUppercase = scanUppercase(password);
		boolean passwordHasLowercase = scanLowercase(password);

		if (!passwordLengthAcceptable) {
			// increase password generation attempt counter due to password
			// length criteria not being met
			length++;
			selectedWord1 = getRandomWord();
			selectedWord2 = getRandomWord();
			selectedWord3 = getRandomWord();
			generatePassword(selectedWord1, selectedWord2, selectedWord3);
		}
		if (!passwordHasUppercase) {
			// increase password generation attempt counter due to password
			// containing an uppercase character
			upper++;
			selectedWord1 = getRandomWord();
			selectedWord2 = getRandomWord();
			selectedWord3 = getRandomWord();
			generatePassword(selectedWord1, selectedWord2, selectedWord3);
		}
		if (!passwordHasLowercase) {
			// increase password generation attempt counter due to password
			// having NO lowercase characters
			lower++;
			selectedWord1 = getRandomWord();
			selectedWord2 = getRandomWord();
			selectedWord3 = getRandomWord();
			generatePassword(selectedWord1, selectedWord2, selectedWord3);
		}
		return password;
	}// end of password generation method generatePassword()

	public static boolean scanUppercase(String password) {
		boolean passwordHasUppercase = false;
		// scan the password for uppercase letters
		// only compare characters from 'a' to 'z' and 'A' to 'Z'
		for (int w = 0; w < password.length(); w++) {
			char charScanned = password.charAt(w);
			String passwordStringChar = password.substring(w, w + 1);
			if ((charScanned >= 'a' & charScanned <= 'z') || (charScanned >= 'A' & charScanned <= 'Z')) {
				passwordHasUppercase = passwordStringChar.equals(passwordStringChar.toUpperCase());
				if (passwordHasUppercase)
					return passwordHasUppercase = true;
			}
		}
		return passwordHasUppercase = false;
	}// end of scanUppercase()

	public static boolean scanLowercase(String password) {
		boolean passwordHasLowercase = false;
		// scan the password for lowercase letters
		// only compare characters from 'a' to 'z' and 'A' to 'Z'
		for (int w = 0; w < password.length(); w++) {
			char charScanned = password.charAt(w);
			if (charScanned >= 'a' & charScanned <= 'z') {
				return passwordHasLowercase = true;
			}
		}
		return passwordHasLowercase = false;
	}// end of scanLowercase()

}// end of Question1 class
