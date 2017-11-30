package crypto;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.concurrent.ThreadLocalRandom;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.codec.binary.Hex;

public class PasswordHash {

    private static int iterations = 1000;
    private static int keylength = 192;
    
    public static String hashPassword(final String password, final String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        char[] pwdChars = password.toCharArray();
        byte[] saltBytes = salt.getBytes();
        
        PBEKeySpec spec = new PBEKeySpec(pwdChars, saltBytes, iterations, keylength);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hashed = keyFactory.generateSecret(spec).getEncoded();
        return Hex.encodeHexString(hashed);
    }
    
    public static String generateSalt() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Hex.encodeHexString(salt);
    }
    
}
