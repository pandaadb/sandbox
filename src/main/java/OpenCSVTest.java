import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class OpenCSVTest {

	public static void main(String[] args) {
		
		StringWriter target = new StringWriter();

		try(CSVReader reader = new CSVReader(new StringReader("my,test,\"addi\"\"tion\"")); 
			CSVWriter writer = new CSVWriter(target);) {
			for(String[] line : reader) {
				String[] added = Arrays.copyOf(line, line.length + 1);
				added[added.length-1] = "addi\"tion";
				writer.writeNext(added, true);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		System.out.println(target.toString());
	}
}
