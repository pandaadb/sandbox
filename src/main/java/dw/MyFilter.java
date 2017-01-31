package dw;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;

public class MyFilter implements ContainerRequestFilter {

	@Context
	private ServletContext context;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println("");
	}


}
