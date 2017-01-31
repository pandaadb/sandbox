package subprocess;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ProcessBuilder.Redirect;
import java.util.concurrent.TimeUnit;

public class EchoTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		
		Process b = new ProcessBuilder("bash", "-c","echo","asd", ">>", "/tmp/test.txt").start();
		
		inherit(b.getInputStream());
		inherit(b.getErrorStream());
		
		
		Thread.sleep(1000);
		b.waitFor(3000, TimeUnit.MILLISECONDS);
	}

	public static void inherit(final InputStream is) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				java.util.Scanner s = new java.util.Scanner(is);
				
				System.out.println("Start");
				
				while(s.hasNext()) {
					System.out.println(s.nextLine());
				}
				
				System.out.println("end");
			}
		});
		thread.setDaemon(true);
		thread.start();
	}
}
