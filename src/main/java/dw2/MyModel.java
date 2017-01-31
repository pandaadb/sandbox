package dw2;

import com.fasterxml.jackson.annotation.JsonView;

public class MyModel {

	@JsonView(Views.Public.class)
	public String name;
	
	@JsonView(Views.Public.class)
	public String password;
	
}
