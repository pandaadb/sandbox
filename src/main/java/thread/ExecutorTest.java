package thread;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import jersey.repackaged.com.google.common.util.concurrent.ThreadFactoryBuilder;

public class ExecutorTest {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(10, (int)10, 5 * 60 , TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1), new ThreadFactoryBuilder().setDaemon(true).build());
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
		
		while(true) {
			executor.submit(() -> {
				System.out.println("Executing " + UUID.randomUUID().toString());
			});
			Thread.sleep(1000);
		}
		
	}

}
