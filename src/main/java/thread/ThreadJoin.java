package thread;

public class ThreadJoin {

	
	public static void main(String[] args) {
		
		Thread t1 = new Thread() {
			public void run() {
				for(int i = 0; i< 5; i++) {
					System.out.println("Threads t1 running " );
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
		};
		
		Thread t2 = new Thread() {
			public void run() {
				for(int i = 0; i< 5; i++) {
					System.out.println("Threads t2 running " );
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					try {
						System.out.println("T2 waiting on T1");
						t1.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
		};
		
		t1.start();
		t2.start();
		
		System.out.println("Joined and exit");

	}
	
}
