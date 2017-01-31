package assetTest;

import java.security.Principal;
import java.util.Optional;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.dropwizard.Configuration;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.setup.Environment;

public class AuthenticatorTest extends io.dropwizard.Application<Configuration> {
	@Override
	public void run(Configuration configuration, Environment environment) throws Exception {
		environment.jersey().register(new MyHelloResource());
		UserAuth a = new UserAuth();
		environment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<Principal>()
				.setAuthenticator(a).setRealm("SUPER SECRET STUFF").buildAuthFilter()));
	}

	public static void main(String[] args) throws Exception {
		new AuthenticatorTest().run("server", "/home/artur/dev/repo/sandbox/src/main/resources/config/test.yaml");
	}

	@Path("test")
	@Produces(MediaType.APPLICATION_JSON)
	public static class MyHelloResource {

		@GET
		@Path("asd")
		@PermitAll
		public String test(String x) {
			return "Hello";
		}
	}
	
	public static class UserAuth implements Authenticator<BasicCredentials, Principal> {
		@Override
		public Optional<Principal> authenticate(BasicCredentials credentials) throws AuthenticationException {
			throw new WebApplicationException(Response.status(403).entity("User is blocked").build());
		}
	}

}
