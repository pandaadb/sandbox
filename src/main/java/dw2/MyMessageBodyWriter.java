package dw2;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyMessageBodyWriter implements MessageBodyWriter<MyModel> {

	private ObjectMapper aMapper;

	MyMessageBodyWriter(ObjectMapper aMapper) {
		this.aMapper = aMapper;
	}
	
	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return MyModel.class.isAssignableFrom(type);
	}

	@Override
	public long getSize(MyModel t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return 0;
	}

	@Override
	public void writeTo(MyModel t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {
		
		System.out.println();
		
		for(Annotation a : annotations) {
			if(a instanceof JsonView) {
				JsonView v = (JsonView) a;
				aMapper.writerWithView(v.value()[0]).writeValue(entityStream, t);
			}
		}
		
//		aMapper.writeValue(entityStream, t);
	}
}
