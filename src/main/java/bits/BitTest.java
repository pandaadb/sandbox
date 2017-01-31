package bits;

import java.time.Instant;

public class BitTest {

	public static long mask = Long.MAX_VALUE << 40;
	
	public static void main(String[] args) {
		
		
		long epochSecond = Instant.now().getEpochSecond();
		
		System.out.println(Long.toBinaryString(mask));
		
		System.out.println(Long.toBinaryString(epochSecond));
		System.out.println(Long.toBinaryString(epochSecond |= (1 << 4) ));
		
		System.out.println(mask);
		
		System.out.println("Orginal:" + epochSecond);
		System.out.println("Masked:" + (epochSecond & mask ));
		
	}
	
}
