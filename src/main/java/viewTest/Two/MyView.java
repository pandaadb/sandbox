package viewTest.Two;

import io.dropwizard.views.View;

public class MyView extends View {

	private TestPerson person;

	protected MyView(TestPerson person) {
		super("TestView.ftl");
		this.person = person;
	}
	
	public TestPerson getPerson() {
		return person;
	}

	public static class TestPerson {
		
		public String getName() {
			return "PandaaDb";
		}
		
	}
}
