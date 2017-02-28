import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisitorAnalyse {

	public static void main(String[] args) throws IOException {
		
		List<String> readAllLines = Files.readAllLines(Paths.get("/home/artur/tmp/weird_numbers/visitors"));
		
		Map<String,Integer> counts = new HashMap<>();
		
		for(String line : readAllLines) {
			String[] split = line.split("-");
			
			for(String s : split) {
				Integer computeIfAbsent = counts.computeIfAbsent(s, k -> 0);
				counts.put(s, computeIfAbsent.intValue() + 1); 
			}
		}
		
		counts.entrySet().forEach(e -> {
//			System.out.println(e.getKey() + " : " + e.getValue());
			
			if(e.getValue() > 1) {
				System.err.println("no");
			}
		});
		
		System.out.println(counts.size());
	}
}
