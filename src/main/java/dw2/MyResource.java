package dw2;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.annotation.JsonView;

@Path("test")
@Produces(MediaType.APPLICATION_JSON)
public class MyResource {

	@Path("viewtest")
	@GET
	@JsonView(Views.Public.class)
	public String t1(@HeaderParam("test") String test) {
		System.out.println(test);
		return test;
	}
}
