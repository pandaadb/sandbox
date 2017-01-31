package strings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestStringTab {

	public static void main(String[] args) throws IOException {
		
		List<String> readAllLines = Files.readAllLines(Paths.get("/home/artur/tmp/content_VE_CAP_DMD_SE_00073.json"));

		JsonNode readTree = new ObjectMapper().readTree(Paths.get("/home/artur/tmp/content_VE_CAP_DMD_SE_00073.json").toFile());
		
		JsonNode jsonNode = readTree.get("thumbImg");
		
		System.out.println();
		String asText = jsonNode.asText();
		System.out.println();
		
	}
}
