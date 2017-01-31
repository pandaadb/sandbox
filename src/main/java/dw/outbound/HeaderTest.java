package dw.outbound;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

public class HeaderTest {

	public static void main(String[] args) {
		// create the client
		Client newClient = ClientBuilder.newClient().register(MyClientRequestFilter.class).register(MyClientRequestPrintingFilter.class);
		
		// make a request
		newClient.target("http://google.com").request().get();
	}
	
	@Priority(1)
	public static class MyClientRequestFilter implements ClientRequestFilter {

		@Override
		public void filter(ClientRequestContext requestContext) throws IOException {
			System.out.println("Added header");
			requestContext.getHeaders().add("X-Powered-By", "foo");
		}
		
	}
	
	@Priority(2)
	public static class MyClientRequestPrintingFilter implements ClientRequestFilter {

		@Override
		public void filter(ClientRequestContext requestContext) throws IOException {
			requestContext.getHeaders().forEach((x,y) -> System.out.println("headerKey:" + x + " HEadervalue:" + y));
		}
		
	}
}
