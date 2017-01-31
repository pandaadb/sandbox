package dw;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MyMessageBodyWriter implements MessageBodyWriter<TestModel> {

	private ObjectMapper aMapper;
	private ObjectMapper bMapper;

	MyMessageBodyWriter(ObjectMapper aMapper, ObjectMapper bMapper) {
		this.aMapper = aMapper;
		this.bMapper = bMapper;
	}
	
	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return TestModel.class.isAssignableFrom(type);
	}

	@Override
	public long getSize(TestModel t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return 0;
	}

	@Override
	public void writeTo(TestModel t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {
		
		Person t2 = (Person) t;
		
		switch(t2.x) {
		case "A": aMapper.writeValue(entityStream, t);
		break;
		case "B" : bMapper.writeValue(entityStream, t);
		break;
		}
	}
}
