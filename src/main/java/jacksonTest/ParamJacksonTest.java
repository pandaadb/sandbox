package jacksonTest;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonParseException;

import jacksonTest.ParamJacksonTest.Stats;
import jacksonTest.ParamJacksonTest.Params.Param1;
import jacksonTest.ParamJacksonTest.Params.Param2;

public class ParamJacksonTest {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		
		ObjectMapper m = new ObjectMapper();
		
		String test = "{\"eventType\": \"1\", \"params\": { \"field2\" : \"10\"  }}";
		
		Stats readValue = m.readValue(test, Stats.class);
		
		System.out.println(readValue.params);
	}
	
	
	public static class Stats {

		public Stats(){}
		
	    @JsonProperty
	    String eventType;

	    public Params params;

	    @JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.EXTERNAL_PROPERTY, property="eventType")
	    @JsonSubTypes({ @Type(value = Param1.class, name = "1"), @Type(value = Param2.class, name = "2") })
	    public void setParams(Params params) {
	        this.params = params;
	    }
	}
	
	public static interface Params {

	    public static class Param1 implements Params {
	        
	    	@JsonCreator static Param1 c() {
	    		System.out.println("asd");
	    		return new Param1();
	    	}
	    }

	    public static class Param2 implements Params {

	        @JsonProperty
	        String field2;
	        
	        @JsonCreator
	        public static Params create(String test) {
	        	System.out.println(test);
	        	return new Param2();
	        }
	    }

	}
}
