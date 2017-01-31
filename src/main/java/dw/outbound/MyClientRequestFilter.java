package dw.outbound;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

@Priority(1)
	public  class MyClientRequestFilter implements ClientRequestFilter {

		@Override
		public void filter(ClientRequestContext requestContext) throws IOException {
			System.out.println("Added header");
			requestContext.getHeaders().add("X-Powered-By", "foo");
		}
		
	}