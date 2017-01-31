package subprocess;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder.Redirect;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.dataformat.yaml.snakeyaml.scanner.Scanner;


public class SbTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("Start new python session");
		
		String executable = new CommandLine("python $HOME/tmp/subprocess/sb.py").getExecutable();
		System.out.println(executable);

		
		Process process = new ProcessBuilder(new String[] {"$HOME/tmp/subprocess/sb.py"})
				.redirectInput(Redirect.PIPE).redirectError(Redirect.PIPE).start();
		
		
		inherit(process.getInputStream());
		inherit(process.getErrorStream());

		try {
			process.waitFor(3000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Done");
		process.destroy();
		process.destroyForcibly();
		Thread.sleep(1005);
		System.out.println(process.isAlive());
		
		System.out.println("Destroyed");
		
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
