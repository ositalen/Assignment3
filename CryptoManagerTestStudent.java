package BobsCircus;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CryptoManagerTestStudent {

    @Test
    public void testIsStringInBoundsTrue() {
        assertTrue(CryptoManager.isStringInBounds("SITALENKO"));
    }

    @Test
    public void testIsStringInBoundsFalse() {
        assertFalse(CryptoManager.isStringInBounds("\n"));
    }

    @Test
    public void testCaesarWithName() {
        String text = "SITALENKO";
        int key = 3;

        String encrypted = CryptoManager.caesarEncryption(text, key);
        String decrypted = CryptoManager.caesarDecryption(encrypted, key);

        assertEquals(text, decrypted);
    }

    @Test
    public void testCaesarWithWordAndSymbol() {
        String text = "APPLES!";
        int key = 5;

        String encrypted = CryptoManager.caesarEncryption(text, key);
        String decrypted = CryptoManager.caesarDecryption(encrypted, key);

        assertEquals(text, decrypted);
    }

    @Test
    public void testVigenereWithWordAndSymbol() {
        String text = "APPLES!";
        String key = "KEY";

        String encrypted = CryptoManager.vigenereEncryption(text, key);
        String decrypted = CryptoManager.vigenereDecryption(encrypted, key);

        assertEquals(text, decrypted);
    }

    @Test
    public void testVigenereWithSentence() {
        String text = "I LIKE APPLES";
        String key = "SECRET";

        String encrypted = CryptoManager.vigenereEncryption(text, key);
        String decrypted = CryptoManager.vigenereDecryption(encrypted, key);

        assertEquals(text, decrypted);
    }

    @Test
    public void testPlayfairEvenLengthText() {
        String text = "SITALENK";
        String key = "SECRET";

        String encrypted = CryptoManager.playfairEncryption(text, key);
        String decrypted = CryptoManager.playfairDecryption(encrypted, key);

        assertEquals(text, decrypted);
    }

    @Test
    public void testPlayfairOddLengthText() {
        String text = "APPLES!";
        String key = "KEY";

        String encrypted = CryptoManager.playfairEncryption(text, key);
        String decrypted = CryptoManager.playfairDecryption(encrypted, key);

        assertEquals(text, decrypted);
    }
}