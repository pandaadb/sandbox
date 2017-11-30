package crypto;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class KeyPairExample {

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        
        KeyPair generateKeyPair = keyGen.generateKeyPair();

    
        Cipher c = Cipher.getInstance("RSA");
        
        c.init(Cipher.ENCRYPT_MODE, generateKeyPair.getPublic());
        
        byte[] bytes = c.doFinal("test".getBytes("UTF-8"));
        
        Cipher decrypt = Cipher.getInstance("RSA");
        decrypt.init(Cipher.DECRYPT_MODE, generateKeyPair.getPrivate());
        
        byte[] doFinal = decrypt.doFinal(bytes);
        System.out.println(new String(doFinal, "UTF-8"));
                
        
    }
}
