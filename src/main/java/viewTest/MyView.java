package viewTest;

import io.dropwizard.views.View;

public class MyView extends View {

	private Person p;

	protected MyView(Person p) {
		super("MyView.mustache");
		this.p = p;
	}
	
	Person getPerson() {
		return p;
	}

	public static class Person {
		
		public String getName() {
			return "PandaaDb";
		}
		
	}
}
