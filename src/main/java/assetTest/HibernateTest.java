package assetTest;

import java.security.Principal;
import java.util.Optional;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.SessionFactory;

import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.AbstractDAO;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HibernateTest extends io.dropwizard.Application<DBConfiguration> {
	
	private final HibernateBundle<DBConfiguration> hibernate = new HibernateBundle<DBConfiguration>(Object.class) {
	    @Override
	    public DataSourceFactory getDataSourceFactory(DBConfiguration configuration) {
	        return configuration.getDataSourceFactory();
	    }
	};
	
	@Override
	public void initialize(Bootstrap<DBConfiguration> bootstrap) {
		super.initialize(bootstrap);
		bootstrap.addBundle(hibernate);
	}
	
	@Override
	public void run(DBConfiguration configuration, Environment environment) throws Exception {
		MyDao dao = new MyDao(hibernate.getSessionFactory());
		environment.jersey().register(new MyHelloResource(dao));
		
		// THIS IS ABSOLUTELY CRITICAL
		MyAuthenticator proxyAuth = new UnitOfWorkAwareProxyFactory(hibernate).create(MyAuthenticator.class, MyDao.class, dao);
		
		AuthDynamicFeature authDynamicFeature = new AuthDynamicFeature(
	            new BasicCredentialAuthFilter.Builder<Principal>()
	                .setAuthenticator(proxyAuth)
	                .setRealm("SUPER SECRET STUFF")
	                .buildAuthFilter());
		
		environment.jersey().register(authDynamicFeature);
	}

	public static void main(String[] args) throws Exception {
		new HibernateTest().run("server", "/home/artur/dev/repo/sandbox/src/main/resources/config/db.yaml");
	}

	@Path("test")
	@Produces(MediaType.APPLICATION_JSON)
	public static class MyHelloResource {
		
		private MyDao dao;

		public MyHelloResource(MyDao dao) {
			this.dao = dao;
		}
		
		@GET
		@Path("/test")
		@UnitOfWork
		@PermitAll
		public Response downloadFile() throws Exception {
			dao.get();
			return Response.ok().build();
		}
		
	}
	
	public static class MyAuthenticator implements Authenticator<BasicCredentials, Principal> {
		private MyDao dao;

		MyAuthenticator(MyDao dao) {
			this.dao = dao;
		}
		
		@Override
		@UnitOfWork
		public Optional<Principal> authenticate(BasicCredentials credentials) throws AuthenticationException {
			dao.get(); 
			return Optional.empty();
		}
	}
	
	public static class MyDao extends AbstractDAO<Object> {

		public MyDao(SessionFactory sessionFactory) {
			super(sessionFactory);
		}
		
		public Object get() {
			// if not bridged to Jersey this will throw an exception due to session
			currentSession().createSQLQuery("SELECT 1;").uniqueResult();
			return new Object();
		}
	}
	
}
