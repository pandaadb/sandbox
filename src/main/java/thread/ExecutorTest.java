package thread;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import jersey.repackaged.com.google.common.util.concurrent.ThreadFactoryBuilder;

public class ExecutorTest {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
//		ThreadPoolExecutor executor = new ThreadPoolExecutor(10, (int)10, 5 * 60 , TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1), new ThreadFactoryBuilder().setDaemon(true).build());
//		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
//		
//		while(true) {
//			executor.submit(() -> {
//				System.out.println("Executing " + UUID.randomUUID().toString());
//			});
//			Thread.sleep(1000);
//		}
	
	    
	    ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(1);
	    Future<?> submit = newFixedThreadPool.submit(() -> {
	        try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
	        System.err.println("Run");
	    });
	   
	    try {
            Object object = submit.get(100, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            e.printStackTrace();
            submit.cancel(true);
        }
	    
	    Thread.sleep(1000);
	}
	
	

}
