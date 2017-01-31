package google;

import java.util.concurrent.ExecutionException;

import com.google.common.base.Ticker;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public class CacheExpiryTest {

	public static void main(String[] args) throws ExecutionException {
		
		MyTicker t = new MyTicker();
		
		Cache<String,String> tmp = CacheBuilder.newBuilder().ticker(t)
				.removalListener(new RemovalListener<String, String>() {

					@Override
					public void onRemoval(RemovalNotification<String, String> notification) {
						System.err.println("remove:" + notification.getKey());
					}
				}).build();
		
		tmp.get("artur", () -> "asd" );
		
		t.next += 1000 * 60 * 60 * 2;
		
		tmp.cleanUp();
	
		t.next += 1000 * 60 * 60 * 2;
		t.next += 1000 * 60 * 60 * 2;
		t.next += 1000 * 60 * 60 * 2;
		t.next += 1000 * 60 * 60 * 2;
		
		System.err.println("asd map");
		
		tmp.asMap();
		
	}
	
	public static class MyTicker extends Ticker {

		long next = System.currentTimeMillis();
		
		@Override
		public long read() {
			System.out.println(next);
			return next;
		} 
		
	}
}
