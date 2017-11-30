package collections;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CollectionPartitionTest {

    
    public static void main(String[] args) {
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);
        Future<Object> submit = newFixedThreadPool.submit(() ->  null);
    }
}
