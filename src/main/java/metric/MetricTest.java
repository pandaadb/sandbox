package metric;

import java.util.concurrent.TimeUnit;

import com.codahale.metrics.Histogram;
import com.codahale.metrics.SlidingTimeWindowReservoir;

public class MetricTest {
	public static final long SLEEP_TIME = 0;

	public static void main(String[] args) throws InterruptedException {

		SlidingTimeWindowReservoir reservoir = new SlidingTimeWindowReservoir(50, TimeUnit.MILLISECONDS);
		Histogram h = new Histogram(reservoir);

		h.update(1);
		System.out.println(h.getSnapshot().size());
		Thread.sleep(SLEEP_TIME);

		h.update(1);
		System.out.println(h.getSnapshot().size());
		Thread.sleep(SLEEP_TIME);

		h.update(1);
		System.out.println(h.getSnapshot().size());
		Thread.sleep(SLEEP_TIME);

		h.update(1);
		System.out.println(h.getSnapshot().size());
		Thread.sleep(SLEEP_TIME);

		h.update(1);
		System.out.println(h.getSnapshot().size());
		Thread.sleep(SLEEP_TIME);

		h.update(1);
		System.out.println(h.getSnapshot().size());

	}
}
