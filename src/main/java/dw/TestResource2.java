package dw;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

@Path("v2")
@Produces(MediaType.APPLICATION_JSON)
public class TestResource2 {
	@GET
	@Path("test")
	public MyResultObj get() {
		
		MyResultObj o = new MyResultObj();
		o.name = "pandaa";
		o.message = "Hello V2";
		
		return o;
	}
	
	public static class MyResultObj {
		@JsonProperty
		String name;
		@JsonProperty
		String message;
	}
}