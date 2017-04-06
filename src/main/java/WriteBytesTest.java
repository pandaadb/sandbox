import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

public class WriteBytesTest {

	public static void main(String[] args) {
		
		OutputStream out = new ByteArrayOutputStream();
		
		try(FileInputStream s = new FileInputStream("/home/artur/empty");) {
			byte[] chunks = new byte[1024];
			
			int read;
			
			while( (read = s.read(chunks)) != -1) {
				out.write(chunks);
				System.out.println(new String(chunks)); // see what you have 
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
