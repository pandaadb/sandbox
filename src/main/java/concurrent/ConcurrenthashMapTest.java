package concurrent;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrenthashMapTest {

	public static void main(String[] args) throws InterruptedException {
		
		
		final Map<Integer, Integer> m = new ConcurrentHashMap<>();
		m.put(1, 1);
		m.put(2, 2);
		m.put(3, 3);
		m.put(4, 4);
		
		Iterator<Integer> iterator = m.values().iterator();
		
		new Thread(() -> {
			
			for(int i = 5; i <= 10; i++) {
				m.put(i - 100, i - 100);
				System.out.println("added");
				try {
					Thread.sleep(400);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				m.remove(3);
			}
			
		}).start();
		
		while(iterator.hasNext()) {
			Integer next = iterator.next();
			System.out.println(next);
			Thread.sleep(600);
		}
		
		System.out.println("Exit");
		
	}
}
