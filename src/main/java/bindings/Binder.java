package bindings;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import com.google.inject.AbstractModule;

public class Binder {

	public static void main(String[] args) {
		
	}
	
	public static class ProdModule extends AbstractModule {

		@Override
		protected void configure() {
			bind(Client.class).toInstance(ClientBuilder.newBuilder().build());
		}
		
	}
}
