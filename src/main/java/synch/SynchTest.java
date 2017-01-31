package synch;

public class SynchTest {

	public static void main(String[] args) {

		final Counter c = new Counter();

		for (int i = 0; i < 20; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						c.incrementAndPrint();
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}

	public static class Counter {

		int c = 1;

		public synchronized void incrementAndPrint() {
			c++;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
			System.out.println(c);
		}
	}

}
