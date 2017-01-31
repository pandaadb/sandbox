package snippet;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTestSubtype {

	static String json = "{\"serviceItemType\": \"typeA\", \"name\": \"obj name\",  \"description\": \"object description\"}";

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		AbstractClass readValue = mapper.readValue(json, AbstractClass.class);
		
		ClassA a = (ClassA) readValue;
		System.out.println(a.description);
		System.out.println(a.name);
	}

}
