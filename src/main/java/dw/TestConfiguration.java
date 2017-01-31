package dw;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

public class TestConfiguration extends Configuration {

	@JsonProperty
	private String path;
	
	public String getPath() {
		return path;
	}
}
