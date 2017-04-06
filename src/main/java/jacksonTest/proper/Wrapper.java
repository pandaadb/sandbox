package jacksonTest.proper;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Wrapper {

	@JsonProperty
	String test = UUID.randomUUID().toString();
	
	@JsonIgnore
	TestInterface other;
	
	@JsonCreator static Wrapper create(@JsonProperty("type") String type) {
		System.out.println("asd");
		Wrapper w = new Wrapper();
		w.other = TestClass1.My_Enum_one;
		return w;
	}
}

