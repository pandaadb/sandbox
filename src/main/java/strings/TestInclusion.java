package strings;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TestInclusion {

	public static final List<String> exclusions = new ArrayList<>();
	{
		for(int i =0; i < 1000; i ++) {
			String substring = UUID.randomUUID().toString().substring(0, 10);
			exclusions.add(substring);
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		int exlcuded = 0;
		int included = 1;
		
		for(int i =0; i< 100; i++) {
			Thread.sleep(1000);
			long start = System.currentTimeMillis();
			String testString = getTestString();
			Optional<String> findFirst = exclusions.stream().filter( e -> testString.indexOf(e) != -1).findFirst();
			
			if(findFirst.isPresent()) {
				exlcuded++;
			} else {
				included ++;
			}
			
			
			long time = System.currentTimeMillis() - start;
			System.out.println(time);
		}
		
		System.out.println("Excluded: " + exlcuded);
		System.out.println("Included: " + included);
	}
	
	
	public static String getTestString() {
		StringBuffer b = new StringBuffer();
		
		while(b.length() < 200) {
			b.append(UUID.randomUUID().toString());
		}
		
		return b.toString();
	}
}
