
public class PassByRef {
	public static void main(String[] args) {
		Dog aDog = new Dog("Max");
		foo(aDog);
		System.out.println(aDog.name);
	}

	public static void foo(Dog d) {
		d.getName().equals("Max"); // true
		d = new Dog("Fifi");
		if (d.getName().equals("Fifi")) { // true }
		}
	}

	public static class Dog {
		Dog(String name) {
			this.name = name;
		}

		String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}