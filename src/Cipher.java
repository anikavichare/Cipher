import java.util.Dictionary;
import java.util.Arrays;
public class Cipher {

	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,.() '\"![]/%-_;?-=:"
			+ '\n' + '\r';
	private static final String SIMPLE_ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final String DEFAULT_ALPHABET = ALPHABET;

	//worked with Neha on rotation ciphers
	
	public static String rotationCipherEncrypt(String plaintext, int shiftAmount, String alphabet) {
		
		String cipher = "";
		for (int i = 0; i < plaintext.length(); i++) {
			
			 String letter = plaintext.substring(i, i + 1);
			int index = alphabet.indexOf(letter) ;
			
			int shiftIndex = (index + shiftAmount) % alphabet.length();
			
			
			cipher = cipher + alphabet.substring(index, index + 1);
		}
		return cipher;
	}

	public static String rotationCipherDecrypt(String cipher, int shiftAmount, String alphabet) {
		String letter = "";
		
		for (int i = 0; i < cipher.length(); i++) {
			letter = cipher.substring(i, i + 1);
			int index = alphabet.indexOf(letter);
			int shiftIndex = index - shiftAmount;
			
			while(shiftIndex < 0){
				
				shiftIndex = shiftIndex + alphabet.length();
			}
			
			cipher = cipher + alphabet.substring(shiftIndex, shiftIndex + 1);
		}
		return cipher;
	}

	public static String rotationCipherCrack(String cipherText, String alphabet) {
		// open Letterbag
		String output = "";
		
		for (int i = 0; i < alphabet.length(); i++) {
			output = Cipher.rotationCipherEncrypt(cipherText, i, alphabet);
			if (CipherTwo.isEnglish(cipherText) == true)
				return output;
		}

		return null;
	}


	

	public static String vigenereCipherEncrypt(String plaintext, String password, String alphabet) {

		String output = "";
		for(int i = 0; i < plaintext.length(); i++) {
			String letter = plaintext.substring(i, i+1);
			int index = alphabet.indexOf(letter);
			
			String string = password.substring(i % password.length(), i % password.length()+1);
			int indexOfCode = alphabet.indexOf(string);
			
			int oldIndex = index + indexOfCode;
			
			int newIndex = oldIndex % alphabet.length();
			String newLetter = alphabet.substring(newIndex, newIndex+1);
			
			
			
			output = output + alphabet.substring(newIndex, newIndex+1);
		}
		return output;
	}

	public static String vigenereCipherDecrypt(String cipherText, String password, String alphabet) {
		String output = "";
		
		for(int i = 0; i < cipherText.length(); i++) {
			
			String letter = cipherText.substring(i, i+1);
			int index = alphabet.indexOf(letter);
			String string = password.substring(i % password.length(), i % password.length());
			int indexOfCode = alphabet.indexOf(string);
			int oldIndex = index - indexOfCode;
			
			int newIndex = oldIndex % alphabet.length();
			
			
			if(newIndex < 0) {
				
				newIndex = newIndex + alphabet.length();
			}
			
			output = output + alphabet.substring(newIndex, newIndex+1);
		}
		return output;
	}
	
	public static String vigenereCipherCrack(String plaintext, int passwordlength, String alphabet) {
 		String password = "";
 		for (int i =0 ; i<passwordlength; i++){
 			String letterGroup = getLettersOut(plaintext ,i, passwordlength);
 			password = password + findCodeLetter(letterGroup, alphabet);
 			
 		}
 		return password;
	
	}
 		
	public static String vigenereCipherCrackThreeLetter(String plaintext, String alphabet) {
			return vigenereCipherCrack(plaintext,3,alphabet);
		}
		
	
 		private static String findCodeLetter(String letterGroup,String alphabet) {
 			for(int i = 0; i < alphabet.length(); i++) {
 				
 				String decryption = rotationCipherDecrypt(letterGroup,i, alphabet);
 				LetterBag bag = new LetterBag();
 				for(int index = 0; index <  decryption.length(); index++) {
 					bag.add(decryption.substring(index,index+1));
 				}
 				
 				if(bag.getMostFrequent().equals(" ")) {
 					System.out.println(i);
 					return alphabet.substring(i, i+1);
 				}
 			}
 				
 				return "";
 			}
	
 		
 		
 		public static String getLettersOut(String plaintext, int index, int length) {
 			String group = "";
 			for(int i = index; i < plaintext.length(); i = i + length) {
 				group = group + plaintext.substring(i, i+1);
 			}
 			return group;
 		}
 		
 		
 		
	
}

