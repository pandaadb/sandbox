package viewTest.Two;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ServiceLoader;

import io.dropwizard.views.ViewRenderer;
import viewTest.Two.MyView.TestPerson;

public class ViewTestMain {

	public static void main(String[] args) throws UnsupportedEncodingException {
		ServiceLoader<ViewRenderer> load = ServiceLoader.load(ViewRenderer.class);
		TestPerson p = new TestPerson();
		MyView v = new MyView(p);
		
		ByteArrayOutputStream st = new ByteArrayOutputStream();

		ViewRenderer r = null;
		for(ViewRenderer vr : load) {
			if(vr.isRenderable(v)) {
				r = vr;
			}
		}
		
		try {
			r.render(v, Locale.getDefault(), st);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		String string = st.toString("UTF-8");
		System.out.println(string);
	}
}
