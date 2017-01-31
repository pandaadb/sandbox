package checksum;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

public class ChecksumTest {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		MessageDigest instance = MessageDigest.getInstance("SHA-256");
		byte[] digest = instance.digest("test".getBytes());
		
		StringBuffer sb = new StringBuffer();
		for(byte b : digest) {
		    if (b > 0 && b < 16) {
		        sb.append("0");
		    }
		    sb.append(Integer.toHexString(b & 0xff));
		}
		
		String sha256Hex = DigestUtils.sha256Hex(digest);
		
		String encodeHexString = Hex.encodeHexString(digest);
		
		System.out.println(sb);
		System.out.println(encodeHexString);
		
		
	}
	
}
