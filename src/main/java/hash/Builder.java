package hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.UUID;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;

public class Builder {

    public static void main(String[] args) throws InterruptedException, NoSuchAlgorithmException {
//        
//        System.out.println(UUID.nameUUIDFromBytes("Hello".getBytes()).toString());
//        Thread.sleep(1000);
//        System.out.println(UUID.nameUUIDFromBytes("Hello".getBytes()).toString());
        
        
        Hasher newHasher = Hashing.md5().newHasher();
        Hasher putBytes = newHasher.putBytes("String".getBytes());
        HashCode hash2 = putBytes
            .putBytes("ObjectIdOfClient".getBytes()).hash();
        
        byte[] b = new byte[12];
        hash2.writeBytesTo(b, 0 ,12);
        System.out.println(b.length);
        System.out.println(Hex.encodeHexString(b));
        
//        for (int i = 0; i < 10; i++) {
//            MessageDigest instance = MessageDigest.getInstance("MD5");
//            instance.update("Hell".getBytes());
//            byte[] digest = instance.digest();
//            digest = Arrays.copyOf(digest, 12);
//            char[] encodeHex = Hex.encodeHex(digest);
//            System.out.println(new String(encodeHex));
//        }
    }
    
}
