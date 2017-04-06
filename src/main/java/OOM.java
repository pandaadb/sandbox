import java.util.ArrayList;
import java.util.List;

public class OOM {

	public static void main(String[] args) {
        int num = 0;
        System.out.println("Start...");
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 5400000; i++) {
            try {
//                users.add(new User("Bob", 25));
            	new User("Bob", 25);
                num++;
            } catch(OutOfMemoryError e) {
                break;
            }
        }
        System.out.println("Objects created before error: " + num);
        showInfo();
        System.out.println("Finish.");
    }
	
	public static class User {
		
		private int age;
		private String name;

		public User(String name, int age) {
			this.name = name;
			this.age = age;
		}
		
		@Override
	    protected void finalize() throws Throwable {
	        super.finalize();
	        System.out.println("Finalized.");
	    }
	}
	
	 public static void showInfo() {
	        Runtime runtime = Runtime.getRuntime();
	        System.out.println("##### Heap utilization statistics [bytes] #####");
	        System.out.println("Used memory: " + (runtime.totalMemory() - runtime.freeMemory()) + " bytes");
	        System.out.println("Free memory: " + runtime.freeMemory() + " bytes");
	        System.out.println("Total memory: " + (runtime.totalMemory()) + " bytes");
	        System.out.println("Max memory: " + (runtime.maxMemory()) + " bytes");
	    }

	
}
