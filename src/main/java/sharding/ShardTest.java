package sharding;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.common.hash.Hashing;

import dw.Person;

public class ShardTest {
	
	public static void main(String[] args) {
		
//		Map<Integer, Integer> shards = new HashMap<>();
//		
//
//		for(int i=0; i<100; i++) {
//			String string = UUID.randomUUID().toString();
//			
//			int bucket = Hashing.consistentHash(Hashing.md5().hashString(string, Charset.forName("UTF-8")), 75);
//			if(shards.get(bucket) == null) {
//				shards.put(bucket, 1);
//			} else {
//				Integer val = shards.get(bucket);
//				shards.put(bucket, val+1);
//			}
//		}
//		
//		
//		
//		
//		
//		shards.forEach( (k,v) -> {
//			System.out.println("Bucket: " + k + " Values: " + v);
//		});

		A[] aa ;
		B[] ba = new B[3];
		aa = ba;
		
		aa[1] = new A();
		aa[0] = new B();
	}
	
	public static class A {
		
	}
	
	public static class B extends A {
		
	}
	
	public static void x(List<? extends Person> list){
	}

}

