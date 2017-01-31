package LogTest;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import io.dropwizard.logging.filter.FilterFactory;

public class LogFilter implements FilterFactory<ILoggingEvent> {

	@Override
	public Filter<ILoggingEvent> build() {
		return new Filter<ILoggingEvent>() {

			@Override
			public FilterReply decide(ILoggingEvent event) {
				return FilterReply.ACCEPT;
			}
		};
	}

}
