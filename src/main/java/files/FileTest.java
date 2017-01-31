package files;

import java.io.File;

public class FileTest {

	public static void main(String[] args) {
		File[] listFiles = new File("/doesnotexist").listFiles(File::isDirectory);
		System.out.println(listFiles);
	}
}
