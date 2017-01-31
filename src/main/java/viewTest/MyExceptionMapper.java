package viewTest;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class MyExceptionMapper  implements ExceptionMapper<Exception>  {
	@Override
	public Response toResponse(Exception exception) {
		return Response.status(400).entity("This makes no sense").build();
	}

}
