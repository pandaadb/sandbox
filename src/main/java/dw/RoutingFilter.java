package dw;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;

@PreMatching
public class RoutingFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String headerString = requestContext.getHeaderString("IS_V2");
		boolean v2 = headerString != null && headerString.equals("yes");
		URI absolutePath = requestContext.getUriInfo().getAbsolutePath();
		
		if(v2) {
			URI v2Redirect = URI.create(absolutePath.toString().replace("v1", "v2"));
			requestContext.setRequestUri(v2Redirect);
		}
	}

}
