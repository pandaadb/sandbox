package sparky;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.spark.launcher.SparkLauncher;

public class SparkJsonTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		Process handle = new SparkLauncher()
				.setAppName("myAppTest")
				.setAppResource("/home/artur/tmp/spark-es-test/target/scala-2.10/scalaestest_2.10-1.0.jar")
//				.setMainClass("json_test_perf")
				.setMaster("local")
				.setSparkHome("/home/artur/dev/spark/spark-2.0.2-bin-hadoop2.7")
				.launch();
		
		int status = handle.waitFor();
		
		System.out.println(status);
		
		System.out.println("Inputstream: ");
		IOUtils.copy(handle.getInputStream(), System.out);
		
//		System.out.println("OutputStream: ");
//		IOUtils.copy(handle.getOutputStream(), System.out);
		
		System.out.println("Errorstream: ");
		IOUtils.copy(handle.getErrorStream(), System.out);
		
	}
	
}
