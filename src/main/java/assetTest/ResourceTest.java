package assetTest;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.dropwizard.setup.Environment;

public class ResourceTest extends io.dropwizard.Application<DBConfiguration> {
	@Override
	public void run(DBConfiguration configuration, Environment environment) throws Exception {
		environment.jersey().register(new MyHelloResource());
	}

	public static void main(String[] args) throws Exception {
		new ResourceTest().run("server", "/home/artur/dev/repo/sandbox/src/main/resources/config/test.yaml");
	}

	@Path("test")
	@Produces(MediaType.APPLICATION_JSON)
	public static class MyHelloResource {
		
		@GET
		@Path("/dl")
		@Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
		public Response downloadFile() throws Exception {
		    File target = new File("/home/artur/tmp/dw_download/test.xlsx");
		    return Response.ok((Object)target, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
		                   .header("content-disposition","attachment; filename =filename.xlsx")
		                   .build();
		}
		
	}

}
