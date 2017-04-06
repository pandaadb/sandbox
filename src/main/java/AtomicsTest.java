import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class AtomicsTest {

    public static void main(String... args) throws InterruptedException{

        AtomicsTest test = new AtomicsTest();        
        for(int i =0; i< 20; i++)
        test.test1();        
//        test.test2();                             
    }

    static int test = 0;
    
    public void test1() throws InterruptedException {
        AtomicInteger atomicInt = new AtomicInteger(0);
        
        Runnable r = () -> test++;
        
        ExecutorService executor = Executors.newSingleThreadExecutor();                
        IntStream.range(0,1000).forEach(i->executor.submit(r));
        executor.shutdown();
        executor.awaitTermination(100, TimeUnit.SECONDS);
        System.out.println("test1 result = "+ test);
        executor.shutdown();
    }

    public void test2() {

        AtomicInteger atomicInt = new AtomicInteger(0);

        ExecutorService executor = Executors.newFixedThreadPool(2);            
        IntStream.range(0,1000).forEach(i->executor.submit( atomicInt::incrementAndGet ));
        System.out.println("test2 result = " + atomicInt.get());        
        executor.shutdown();           
    }    
}