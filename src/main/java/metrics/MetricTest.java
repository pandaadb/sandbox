package metrics;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

public class MetricTest {
	public static void main(String[] args) {
		MetricRegistry r = new MetricRegistry();
		Counter counter = r.counter("counter");
		Meter meter = r.meter("meter");
		
		counter.inc();
		meter.mark();
		
		System.out.println(counter.getCount());
		System.out.println(meter.getCount());
		
		counter.inc(10);
		meter.mark(10);
		
		System.out.println(counter.getCount());
		System.out.println(meter.getCount());
	}
}
