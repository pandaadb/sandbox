import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class DateDeserializerTest {

	public static void main(String[] args) {

		String json = "{\"d\" : \"10-10-2010 10:10:10\"}";
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			Test t = mapper.readValue(json, Test.class);
			System.out.println(t);
			System.out.println(t.d);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static class Test {
		
		public Test(){}
		
		
		Date d;
		
		@JsonDeserialize(using=DSTest.class, as=Date.class)
		public void setD(Date d) {
			this.d = d;
		}
	}

	public static class DSTest extends StdDeserializer<Date> {
		private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

		public DSTest()
	    {
			this(null);
	    }
		
		public DSTest(Class<?> c) {
			super(c);
		}

		@Override
		public Date deserialize(JsonParser arg0, DeserializationContext arg1)
				throws IOException, JsonProcessingException {

			String date = arg0.getText();

			try {
				return formatter.parse(date);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
