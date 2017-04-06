package dw2;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

public class ResourceTest extends io.dropwizard.Application<Configuration>{
	
	@Override
	public void run(Configuration configuration, Environment environment) throws Exception {
		environment.jersey().register(SubResourceTest.class);
	}
	
	public static void main(String[] args) throws Exception {
		new ResourceTest().run("server", "/home/artur/dev/repo/sandbox/src/main/resources/config/test.yaml");
	}
	
	@Path("/x/{store-id}/x")
	@Produces(MediaType.APPLICATION_JSON)
	public static class SubResourceTest {
		
		@GET
		@Path("/{other}")
		public String get(@PathParam("other") String other, @PathParam("store-id") String sId) {
			return "get " + other + " " + sId;
		}
		
		@POST
		public String post(@PathParam("store-id") String sId, @PathParam("other") String other) {
			return "Post"+ " " + sId;
		}
		
		@DELETE
		@Path("/{other}")
		public String delete(@PathParam("store-id") String sId, @PathParam("other") String other) {
			return "delete"+ " " + sId;
		}
		
		@PUT
		@Path("/{other}")
		public String put(@PathParam("store-id") String sId, @PathParam("other") String other) {
			return "put"+ " " + sId;
		}
		
	}
	
}