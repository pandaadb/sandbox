package dw;

import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dw.introspec.VersioningProperties;
import dw.introspec.VersioningPropertiesIntrospector;

public class Person extends TestModel {

	@VersioningProperties ( {
		@VersioningProperties.Property(version="A", value="test1")
		,@VersioningProperties.Property(version="B", value="test2")
	})
	public String name = UUID.randomUUID().toString();
	
	public String x = "A"; // or B
	
	
	public static void main(String[] args) throws JsonProcessingException {
		
		
		Person p = new Person();
		p.x = "B";
		
		ObjectMapper m = new ObjectMapper();
		m.setAnnotationIntrospector(new VersioningPropertiesIntrospector("B"));
		
		String writeValueAsString = m.writeValueAsString(p);
		System.out.println(writeValueAsString);
		
	}
}
