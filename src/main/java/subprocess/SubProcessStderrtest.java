package subprocess;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SubProcessStderrtest implements Runnable {

	private final Map<Process, InputStream> streams = new HashMap<>();
	
	public SubProcessStderrtest() {
		Thread streamPrinterThread = new Thread(this);
		streamPrinterThread.setName("stream-printer-thread");
		streamPrinterThread.setDaemon(true);
		streamPrinterThread.start();
	}
	
	public Process test() throws IOException {
		Process p = new ProcessBuilder("python","/home/artur/tmp/py_stdout_test/test.py")
				.redirectErrorStream(true).start();
		streams.put(p, p.getInputStream());
		return p;
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		SubProcessStderrtest t = new SubProcessStderrtest();
		Process start = null;
		for(int i =0; i< 20; i++) {
			start = t.test();
		}
		
		while(start.isAlive()) {
			System.out.println("Alive");
			Thread.sleep(1000);
		}
		
		Thread.sleep(20 * 1000);
		
		System.out.println(t.streams.size());
	}

	@Override
	public void run() {
		byte[] buffer = new byte[1024];
		try {
			while(true) {
				streams.entrySet().removeIf( p -> !p.getKey().isAlive());
				for(InputStream in : streams.values()) {
					while(in.available() > 0) {
						in.read(buffer, 0, Math.min(in.available(), 1024));
						System.out.println(new String(buffer));
					}
				}
				
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			System.err.println("Error occured while trying to read process");
			e.printStackTrace();
		}
	}
}
