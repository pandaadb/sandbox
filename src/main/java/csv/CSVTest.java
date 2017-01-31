package csv;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;

public class CSVTest {

	
	public static void main(String[] args) throws IOException {
		
		
		CSVReader reader = new CSVReader(new FileReader(new File("/home/artur/tmp/surge_csv/test2.csv")));
		
		List<String[]> readAll = reader.readAll();
		
		System.out.println(readAll.size());
		
	}
}
