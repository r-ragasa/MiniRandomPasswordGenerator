import static org.junit.Assert.*;

import org.junit.Test;

public class Question1Test {
	
	/**
	 * singleCharNewLineCheck() to see if the a same word is returned (test returns True) when it has NO \n char and is longer than 1 char
	 */
	
	@Test
	public void testSingleCharNewLineCheck_ReturnsSameWord() {
		String input = "word";
		String output = Question1.singleCharNewLineCheck(input);
		assertEquals(input,output);
	}
	
	/**
	 * New word is generated when there is a \n char. Test returns True if a new word is generated.
	 */
	
	@Test
	public void testSingleCharNewLineCheck_NewLineGetsNewWord() {
		String input = "bad\n";
		String output = Question1.singleCharNewLineCheck(input);
		assertTrue(!input.equals(output));
	}
	
	/**
	 * New word generated when there is only a single char as input. Test returns True if a new word is generated.
	 */
	
	@Test
	public void testSingleCharNewLineCheckGets_SingleCharGetsNewWord() {
		String input = "b";
		String output = Question1.singleCharNewLineCheck(input);
		assertTrue(!input.equals(output));
	}

	/**
	 * If a word has at least 1 Uppercase character, this test and function return True
	 */

	@Test
	public void testScanUppercase_has1Uppercase() {
		String input = "wORd";
		boolean output = Question1.scanUppercase(input);
		assertTrue(output);
	}
	
	@Test
	public void testScanUppercase_has2Uppercase() {
		String input = "aPple";
		boolean output = Question1.scanUppercase(input);
		assertTrue(output);
	}

	/**
	 * Tests: If a word has NO lower case letters, test and function return False. Returns True otherwise.
	 */
	@Test
	public void testScanLowercase_hasNOLowercase() {
		String input = "WORD";
		boolean output = Question1.scanLowercase(input);
		assertTrue(output);
	}
	

	@Test
	public void testScanLowercase_has1Lowercase() {
		String input = "WORDs";
		boolean output = Question1.scanLowercase(input);
		assertTrue(output);
	}

	@Test
	public void testScanLowercase_has2Lowercase() {
		String input = "WoRds";
		boolean output = Question1.scanLowercase(input);
		assertTrue(output);
	}
	/**
	 * Test should be False when there is a lowercase criteria not met (pasword has NO lowercase letters)
	 */
	
	@Test
	public void testGeneratePassword_noLowercase(){
		//the 3 words must be between 8 and 16 chars
		String one = "WORDS";
		String two = "HAS";
		String three = ".G";
		String output = Question1.generatePassword(one,two,three);
		assertTrue((one+two+three).equals(output));
	}
	
	@Test
	public void testGeneratePassword_hasLowercase(){
		//the 3 words must be between 8 and 16 chars
		String one = "WOsDS";
		String two = "HAS";
		String three = ".G";
		String output = Question1.generatePassword(one,two,three);
		assertTrue((one+two+three).equals(output));
	}

}
