package HK2Test;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.monitoring.ApplicationEvent;
import org.glassfish.jersey.server.monitoring.ApplicationEventListener;
import org.glassfish.jersey.server.monitoring.RequestEvent;
import org.glassfish.jersey.server.monitoring.RequestEventListener;
import org.glassfish.jersey.servlet.ServletContainer;

import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ViewApplication extends io.dropwizard.Application<Configuration> {

	@Override
	public void run(Configuration configuration, Environment environment) throws Exception {

		environment.jersey().register(new ApplicationEventListener() {
			@Override
			public void onEvent(ApplicationEvent event) {
				if (event.getType() == ApplicationEvent.Type.INITIALIZATION_FINISHED) {
					ServiceLocator serviceLocator = ((ServletContainer) environment.getJerseyServletContainer())
							.getApplicationHandler().getServiceLocator();

					ServiceLocatorUtilities.bind(serviceLocator, new AbstractBinder() {

						@Override
						protected void configure() {
							bind(new Repository("test")).to(Repository.class);
							bind(MyCommandInjected.class).to(MyCommandInjected.class);
						}
					});

					MyCommandInjected service = serviceLocator.getService(MyCommandInjected.class);
					MyCommandManual tmp = new MyCommandManual(serviceLocator);
				}
			}
			@Override
			public RequestEventListener onRequest(RequestEvent requestEvent) {
				return null;
			}
		});
		
		
	}

	@Override
	public void initialize(Bootstrap<Configuration> bootstrap) {
		super.initialize(bootstrap);
	}

	public static void main(String[] args) throws Exception {
		new ViewApplication().run("server", "/home/artur/dev/repo/sandbox/src/main/resources/config/test.yaml");
	}

	@Path("test")
	@Produces(MediaType.APPLICATION_JSON)
	public static class HelloResource {

		@GET
		@Path("asd")
		public String test(String x) {
			return "Hello";
		}

	}

	public static class Repository {

		@Inject
		public Repository(String something) {
		}
	}

	public static class MyCommandInjected {

		@Inject
		public MyCommandInjected(final Repository repo) {
			System.out.println("Repo injected " + repo);
		}
	}

	public static class MyCommandManual {

		public MyCommandManual(final ServiceLocator sl) {
			Repository service = sl.getService(Repository.class);
			System.out.println("Repo found: " + service);
		}
	}

}
