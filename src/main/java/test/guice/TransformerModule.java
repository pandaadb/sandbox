package test.guice;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

public class TransformerModule {

	public static void main(String[] args) {
		
		Injector i = Guice.createInjector(new AbstractModule() {
			@Override
			protected void configure() {
				bind(Transformer.class).toProvider(TransformerProvider.class).in(Singleton.class); // bind factory
			}
		});
		
		
		
		// Use factory

		Provider<Transformer> provider = i.getProvider(Transformer.class); // Or inject the factory
		Transformer transformer = provider.get();
		
		// do some work
		
	}
	
	public static class TransformerProvider implements Provider<Transformer> {

		@Inject
		@Named("output.uri.resolver")
		String outputUriResolver;
		
		@Inject
		@Named("xslt.location")
		String xsltLocation;
		
		Transformer instance = null;
		
		@Override
		public Transformer get() {
			if(instance == null) {
				try {
					instance = create();
				} catch (TransformerConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return instance;
		}

		private Transformer create() throws TransformerConfigurationException {
			System.setProperty("javax.xml.transform.TransformerFactory",
			                "net.sf.saxon.TransformerFactoryImpl");
			TransformerFactory xmlTransformerFactory = TransformerFactory.newInstance();
//			CustomUriResolver out = new CustomUriResolver(new HashMap<String, StringWriter>());
//			xmlTransformerFactory.setAttribute(outputUriResolver, out );
			return xmlTransformerFactory.newTransformer(new StreamSource(xsltLocation));
		}
	}
	
}
