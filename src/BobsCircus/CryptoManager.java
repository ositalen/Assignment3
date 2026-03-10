package BobsCircus;
/**
 * This is a utility class that encrypts and decrypts a phrase using three
 * different approaches. 
 * 
 * The first approach is called the Vigenere Cipher.Vigenere encryption 
 * is a method of encrypting alphabetic text based on the letters of a keyword.
 * 
 * The second approach is Playfair Cipher. It encrypts two letters (a digraph) 
 * at a time instead of just one.
 * 
 * The third approach is Caesar Cipher. It is a simple replacement cypher. 
 * 
 * @author Huseyin Aygun
 * @version 8/3/2025
 */

public class CryptoManager { 

    private static final char LOWER_RANGE = ' ';
    private static final char UPPER_RANGE = '_';
    private static final int RANGE = UPPER_RANGE - LOWER_RANGE + 1;
    // Use 64-character matrix (8X8) for Playfair cipher  
    private static final String ALPHABET64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 !\"#$%&'()*+,-./:;<=>?@[\\]^_";

    public static boolean isStringInBounds(String plainText) {
        for (int i = 0; i < plainText.length(); i++) {
            if (!(plainText.charAt(i) >= LOWER_RANGE && plainText.charAt(i) <= UPPER_RANGE)) {
                return false;
            }
        }
        return true;
    }

	/**
	 * Vigenere Cipher is a method of encrypting alphabetic text 
	 * based on the letters of a keyword. It works as below:
	 * 		Choose a keyword (e.g., KEY).
	 * 		Repeat the keyword to match the length of the plaintext.
	 * 		Each letter in the plaintext is shifted by the position of the 
	 * 		corresponding letter in the keyword (A = 0, B = 1, ..., Z = 25).
	 */   

    public static String vigenereEncryption(String plainText, String key) {
    	
         //to be implemented by students

    	    if (!isStringInBounds(plainText) || !isStringInBounds(key)) {
    	        return "The selected string is not in bounds, Try again.";
    	    }

    	    String result = "";

    	    for (int i = 0; i < plainText.length(); i++) {

    	        char p = plainText.charAt(i);
    	        char k = key.charAt(i % key.length());

    	        int shift = k - LOWER_RANGE;
    	        int encrypted = p + shift;

    	        while (encrypted > UPPER_RANGE) {
    	            encrypted = LOWER_RANGE + (encrypted - UPPER_RANGE - 1);
    	        }

    	        while (encrypted < LOWER_RANGE) {
    	            encrypted = UPPER_RANGE - (LOWER_RANGE - encrypted - 1);
    	        }

    	        result += (char) encrypted;
    	    }

    	    return result;
    	}
    

    // Vigenere Decryption
    public static String vigenereDecryption(String encryptedText, String key) {
         //to be implemented by students


    	    if (!isStringInBounds(encryptedText) || !isStringInBounds(key)) {
    	        return "The selected string is not in bounds, Try again.";
    	    }

    	    String result = "";

    	    for (int i = 0; i < encryptedText.length(); i++) {

    	        char e = encryptedText.charAt(i);
    	        char k = key.charAt(i % key.length());

    	        int shift = k - LOWER_RANGE;
    	        int decrypted = e - shift;

    	        while (decrypted < LOWER_RANGE) {
    	            decrypted = UPPER_RANGE - (LOWER_RANGE - decrypted - 1);
    	        }

    	        while (decrypted > UPPER_RANGE) {
    	            decrypted = LOWER_RANGE + (decrypted - UPPER_RANGE - 1);
    	        }

    	        result += (char) decrypted;
    	    }

    	    return result;
    	}
    	


	/**
	 * Playfair Cipher encrypts two letters at a time instead of just one.
	 * It works as follows:
	 * A matrix (8X8 in our case) is built using a keyword
	 * Plaintext is split into letter pairs (e.g., ME ET YO UR).
	 * Encryption rules depend on the positions of the letters in the matrix:
	 *     Same row: replace each letter with the one to its right.
	 *     Same column: replace each with the one below.
	 *     Rectangle: replace each letter with the one in its own row but in the column of the other letter in the pair.
	 */    

    public static String playfairEncryption(String plainText, String key) {
    	
         //to be implemented by students
    	

    	    if (!isStringInBounds(plainText) || !isStringInBounds(key)) 
    	    {
    	        return "The selected string is not in bounds, Try again.";
    	    }

    	    char[][] matrix = buildMatrix(key);
    	    String newText = prepareText(plainText);
    	    String result = "";

    	    for (int i = 0; i < newText.length(); i = i +2) 
    	    {

    	        char first = newText.charAt(i);
    	        char second = newText.charAt(i + 1);

    	        int[] pos1 = findChar(matrix, first);
    	        int[] pos2 = findChar(matrix, second);

    	        int row1 = pos1[0];
    	        int col1 = pos1[1];
    	        int row2 = pos2[0];
    	        int col2 = pos2[1];

    	        if (row1 == row2) 
    	        {
    	            result += matrix[row1][(col1 + 1) % 8];
    	            result += matrix[row2][(col2 + 1) % 8];
    	        }
    	        else if (col1 == col2) 
    	        {
    	            result += matrix[(row1 + 1) % 8][col1];
    	            result += matrix[(row2 + 1) % 8][col2];
    	        }
    	        else 
    	        {
    	            result += matrix[row1][col2];
    	            result += matrix[row2][col1];
    	        }
    	    }

    	    return result;
    	
    }

    // Vigenere Decryption
    
    public static String playfairDecryption(String encryptedText, String key) {
    	
         //to be implemented by students
    	

    	    if (!isStringInBounds(encryptedText) || !isStringInBounds(key)) {
    	        return "The selected string is not in bounds, Try again.";
    	    }

    	    char[][] matrix = buildMatrix(key);
    	    String result = "";

    	    for (int i = 0; i < encryptedText.length(); i = i + 2) {

    	        char first = encryptedText.charAt(i);
    	        char second = encryptedText.charAt(i + 1);

    	        int[] pos1 = findChar(matrix, first);
    	        int[] pos2 = findChar(matrix, second);

    	        int row1 = pos1[0];
    	        int col1 = pos1[1];
    	        int row2 = pos2[0];
    	        int col2 = pos2[1];

    	        if (row1 == row2) {
    	            result += matrix[row1][(col1 - 1 + 8) % 8];
    	            result += matrix[row2][(col2 - 1 + 8) % 8];
    	        }
    	        else if (col1 == col2) {
    	            result += matrix[(row1 - 1 + 8) % 8][col1];
    	            result += matrix[(row2 - 1 + 8) % 8][col2];
    	        }
    	        else {
    	            result += matrix[row1][col2];
    	            result += matrix[row2][col1];
    	        }
    	    }

    	    if (result.endsWith("X")) {
    	        result = result.substring(0, result.length() - 1);
    	    }

    	    return result;
    	}
    	
    

    /**
     * Caesar Cipher is a simple substitution cipher that replaces each letter in a message 
     * with a letter some fixed number of positions down the alphabet. 
     * For example, with a shift of 3, 'A' would become 'D', 'B' would become 'E', and so on.
     */    
 
    public static String caesarEncryption(String plainText, int key) {
    	
	//to be implemented by students


    	    if (!isStringInBounds(plainText)) {
    	        return "The selected string is not in bounds, Try again.";
    	    }

    	    String result = "";

    	    for (int i = 0; i < plainText.length(); i++) {

    	        char c = plainText.charAt(i);
    	        int encrypted = c + key;

    	        while (encrypted > UPPER_RANGE) {
    	            encrypted = LOWER_RANGE + (encrypted - UPPER_RANGE - 1);
    	        }

    	        while (encrypted < LOWER_RANGE) {
    	            encrypted = UPPER_RANGE - (LOWER_RANGE - encrypted - 1);
    	        }

    	        result += (char) encrypted;
    	    }

    	    return result;
    	}

    // Caesar Decryption
    
    public static String caesarDecryption(String encryptedText, int key) {
    	
	//to be implemented by students
    		
    	    if (!isStringInBounds(encryptedText)) {
    	        return "The selected string is not in bounds, Try again.";
    	    }

    	    String result = "";

    	    for (int i = 0; i < encryptedText.length(); i++) {

    	        char c = encryptedText.charAt(i);
    	        int decrypted = c - key;

    	        while (decrypted < LOWER_RANGE) {
    	            decrypted = UPPER_RANGE - (LOWER_RANGE - decrypted - 1);
    	        }

    	        while (decrypted > UPPER_RANGE) {
    	            decrypted = LOWER_RANGE + (decrypted - UPPER_RANGE - 1);
    	        }

    	        result += (char) decrypted;
    	    }

    	    return result;
    	}
    //helper1 to build the playFair matrix
    
    private static char[][] buildMatrix(String key) 
    {

        String newKey = key.toUpperCase();
        String allChars = "";

        for (int i = 0; i < newKey.length(); i++) 
        {

            char ch = newKey.charAt(i);

            if (ALPHABET64.indexOf(ch) != -1 && allChars.indexOf(ch) == -1) 
            {
                allChars += ch;
            }
        }

        for (int i = 0; i < ALPHABET64.length(); i++) 
        {

            char ch = ALPHABET64.charAt(i);

            if (allChars.indexOf(ch) == -1) 
            {
                allChars += ch;
            }
        }

        char[][] matrix = new char[8][8];

        int position = 0;

        for (int r = 0; r < 8; r++) 
        {

            for (int c = 0; c < 8; c++) 
            {

                matrix[r][c] = allChars.charAt(position);
                position++;
            }
        }

        return matrix;
    }
    
  //helper2 to work on the plain text part
    
    private static String prepareText(String plainText) {

        if (plainText.length() % 2 == 0) 
        {
            return plainText;
        }
        else 
        {
            return plainText + "X";
        }
    }


    //helper3 to find the character position in matrix
    
    private static int[] findChar(char[][] matrix, char target) {

        int[] pos = new int[2];

        for (int r = 0; r < 8; r++) 
        {
            for (int c = 0; c < 8; c++) 
            {
                if (matrix[r][c] == target) 
                {
                    pos[0] = r;
                    pos[1] = c;
                    return pos;
                }
            }
        }

        return pos;
    }
    }    


