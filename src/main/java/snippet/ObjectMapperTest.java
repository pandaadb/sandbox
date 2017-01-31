package snippet;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class ObjectMapperTest {

	public static void main(String[] args) throws JsonProcessingException {

		IntegerSchema schema = new IntegerSchema();
		schema.type = "Int";
		schema.max = 10200000000d;
		schema.min = 100d;

		ObjectMapper m = new ObjectMapper();
		
		System.out.println(m.writeValueAsString(schema));

	}

	public static class IntegerSchema {

		@JsonProperty
		String type;
		@JsonProperty
		double min;
		@JsonProperty
		@JsonSerialize(using=MyDoubleDesirializer.class)
		double max;
	}
	
	public static class MyDoubleDesirializer extends JsonSerializer<Double> {


		@Override
		public void serialize(Double value, JsonGenerator gen, SerializerProvider serializers)
				throws IOException, JsonProcessingException {
			BigDecimal d = new BigDecimal(value);
			gen.writeNumber(d.toPlainString());
		}
		
	}

}
