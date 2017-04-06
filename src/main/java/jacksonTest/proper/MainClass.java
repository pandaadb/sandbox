package jacksonTest.proper;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MainClass {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		String test = "{\"test\":\"arty\",\"my_type\":\"att1\"}";
		
		ObjectMapper m = new ObjectMapper();
		
		Wrapper readValue = m.readValue(test, Wrapper.class);
		
		System.out.println(readValue.other);
		System.out.println(readValue.test);
	}
}
