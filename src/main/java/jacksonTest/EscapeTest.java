package jacksonTest;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EscapeTest {

	
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		
		JsonNode readValue = new ObjectMapper().readValue(EscapeTest.class.getResourceAsStream("/escape.json"), JsonNode.class);
		System.out.println(readValue);
		
		String textValue = readValue.get("a").textValue();
		System.out.println(textValue);
	}
}
