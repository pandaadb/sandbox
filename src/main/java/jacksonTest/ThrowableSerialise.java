package jacksonTest;

import javax.ws.rs.BadRequestException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

public class ThrowableSerialise {

	public static void main(String[] args) throws JsonProcessingException {
		ObjectMapper m = new ObjectMapper();
		m.setAnnotationIntrospector(new Ignore());
		String writeValueAsString = m.writeValueAsString(new MyExcep());
		
		System.out.println(writeValueAsString);
	}
	
	public static class MyExcep extends BadRequestException {
		@JsonProperty
		String test = "asd";
	}
	
	public static class Ignore extends JacksonAnnotationIntrospector {
		
		@Override
		public boolean hasIgnoreMarker(AnnotatedMember m) {
			if(m.getDeclaringClass() != MyExcep.class) {
				return true;
			}
			return  super.hasIgnoreMarker(m);
		}
		
	}
	
}
