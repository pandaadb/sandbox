package dw;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("v1")
@Produces(MediaType.APPLICATION_JSON)
public class TestResource1 {
	@GET
	@Path("test")
	public String get() {
		return "Hello v1";
	}
}


