package classpath;

import java.net.URL;
import java.net.URLClassLoader;

import assetTest.AssetApplication;

public class ClassPathTest {
	
	public static void main(String[] args) {
		

		ClassLoader cl = ClassLoader.getSystemClassLoader();

		URL[] urls = ((URLClassLoader) cl).getURLs();

		URL r = null;
		
		for (URL url : urls) {
			System.out.println(url.getFile());
			if(url.getFile().contains("assets")) {
				r = url;
			}
		}
		
		System.out.println(r);
		
		URL resource = AssetApplication.class.getResource("/home/artur/tmp/assets/test.txt");
		URL t = AssetApplication.class.getResource("/test.txt");
		System.out.println(resource);
		System.out.println(t);
		
	}
}
