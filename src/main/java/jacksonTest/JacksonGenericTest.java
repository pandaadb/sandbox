package jacksonTest;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.base.GeneratorBase;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.Impl;

public class JacksonGenericTest {

	public static void main(String[] args) throws JsonProcessingException, IOException {
		MyGen gen = new MyGen(0, new ObjectMapper());
		
		
		String test = "{ \"a\" : \"test\", \"b\" : 1, \"c\" : true, \"d\" : 2.5 }";
				
		JsonNode tree = new ObjectMapper().readTree(test);
		
		Impl instance = new DefaultSerializerProvider.Impl();
		
		ObjectMapper m = new ObjectMapper();
		
		for(JsonNode node : tree) {
			node.serialize(gen, instance);
			Object currentObject = gen.currentObject;
			System.out.println(currentObject);
		}
		
	}
	
	public static class MyGen extends GeneratorBase {

		protected MyGen(int features, ObjectCodec codec) {
			super(features, codec);
		}

		private Object currentObject = null;
		
		@Override
		public void flush() throws IOException {
			// do nothing
		}

		@Override
		protected void _releaseBuffers() {
			// do nothing			
		}

		@Override
		protected void _verifyValueWrite(String typeMsg) throws IOException {
			// do nothing
		}

		@Override
		public void writeStartArray() throws IOException {
			// do nothing
		}

		@Override
		public void writeEndArray() throws IOException {
		}			// do nothing

		@Override
		public void writeStartObject() throws IOException {
			// do nothing
		}

		@Override
		public void writeEndObject() throws IOException {
			// do nothing
		}

		@Override
		public void writeFieldName(String name) throws IOException {
			// do nothing
		}

		@Override
		public void writeString(String text) throws IOException {
			currentObject = text;
		}

		@Override
		public void writeString(char[] text, int offset, int len) throws IOException {
			currentObject = new String(text);
		}

		@Override
		public void writeRawUTF8String(byte[] text, int offset, int length) throws IOException {
			currentObject = new String(text);
		}

		@Override
		public void writeUTF8String(byte[] text, int offset, int length) throws IOException {
			currentObject = new String(text);
		}

		@Override
		public void writeRaw(String text) throws IOException {
			currentObject = new String(text);
		}

		@Override
		public void writeRaw(String text, int offset, int len) throws IOException {
			currentObject = new String(text);			
		}

		@Override
		public void writeRaw(char[] text, int offset, int len) throws IOException {
			currentObject = new String(text);			
		}

		@Override
		public void writeRaw(char c) throws IOException {
			currentObject = new Character(c);
		}

		@Override
		public void writeBinary(Base64Variant bv, byte[] data, int offset, int len) throws IOException {
			currentObject = bv;
		}

		@Override
		public void writeNumber(int v) throws IOException {
			currentObject = new Integer(v);
		}

		@Override
		public void writeNumber(long v) throws IOException {
			currentObject = new Long(v);
		}

		@Override
		public void writeNumber(BigInteger v) throws IOException {
			currentObject = v;
		}

		@Override
		public void writeNumber(double v) throws IOException {
			currentObject = new Double(v);
		}

		@Override
		public void writeNumber(float v) throws IOException {
			currentObject = new Float(v);
		}

		@Override
		public void writeNumber(BigDecimal v) throws IOException {
			currentObject = v;
		}

		@Override
		public void writeNumber(String encodedValue) throws IOException {
			currentObject = encodedValue;
		}

		@Override
		public void writeBoolean(boolean state) throws IOException {
			currentObject = new Boolean(state);
		}

		@Override
		public void writeNull() throws IOException {
			currentObject = null;
		}
		
	}
	
}
