package jacksonTest;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ArrayTest {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		
		String array = " {\"a\" : [ {\"c\" : \" asd \"} ]}";
//		String array = " {\"a\" : []}";
		JsonNode readValue = new ObjectMapper().readValue(array, JsonNode.class);
		
		JsonNode jsonNode2 = readValue.get("a");
		JsonNode jsonNode = jsonNode2.get("c");
		System.out.println(jsonNode);

}}
