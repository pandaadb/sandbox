package dw.context;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("context")
public class ContextResource {

	@Inject
	MySecondObject o;
	
	@GET
	public String get() {
		return o.getO().get();
	}
	
}
