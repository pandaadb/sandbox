package viewTest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("viewTest")
@Produces(MediaType.TEXT_HTML)
public class ViewResource {

	@GET
	public viewTest.Two.MyView view() {
		return new viewTest.Two.MyView(new viewTest.Two.MyView.TestPerson());
	}
}
