package viewTest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("viewTest")
@Produces(MediaType.TEXT_HTML)
public class ViewResource {

	@GET
	public MyView view() {
		throw new IllegalStateException("I am throwing");
//		return new MyView();
	}
}
