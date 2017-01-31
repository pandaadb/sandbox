
import java.security.Principal;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.CachingAuthenticator;
import io.dropwizard.auth.UnauthorizedHandler;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;

public class DWAuth {

	public AuthDynamicFeature getAuthFilter(CachingAuthenticator<String, User> cachingAuthenticator) {
	    OAuthCredentialAuthFilter<User> authFilter = new OAuthCredentialAuthFilter.Builder<User>()
	            .setAuthenticator(cachingAuthenticator)
	            .setAuthorizer(null)
	            .setRealm("")
	            .setPrefix("")
	            .setUnauthorizedHandler(new MyUnauthorizedHandler())
	            .buildAuthFilter();
	    return new AuthDynamicFeature(authFilter);
	}
	
	public static class MyUnauthorizedHandler implements UnauthorizedHandler {

		@Override
		public Response buildResponse(String prefix, String realm) {
			return Response.status(Status.UNAUTHORIZED).entity("").build();
		}
		
	}

	public static  class User implements Principal {

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
}
