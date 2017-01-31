package dw;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTest {

	public static void main(String[] args) throws JsonProcessingException {
		
		Event e = new Event();
		e.type="end";
		e.service="<service>";
		e.hostname = "<hostname>";
		e.timestamp = LocalDateTime.now().toString();
		Map<String,String> payload = new HashMap<>();
		payload.put("param1", "xyz");
		e.payload = payload;
		
		String writeValueAsString = new ObjectMapper().writeValueAsString(e);
		System.out.println(writeValueAsString);
	}
	
	
	
	public static class Event {
		@JsonProperty
		String type;
		@JsonProperty("time")
		String timestamp;
		@JsonIgnore
		String hostname;
		@JsonIgnore
		String service;
		@JsonProperty("data")
		Map<String, String> payload;
		
		@JsonProperty("origin")
		Map<String,String> getOrigin() {
			Map<String,String> tmp = new HashMap<>();
			tmp.put("hostname", hostname);
			tmp.put("service", service);
			return tmp;
		}

		@JsonProperty("version")
		private String getVersion() {
			return "1.0";
		}
	}
	
	
}
