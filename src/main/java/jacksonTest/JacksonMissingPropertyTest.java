package jacksonTest;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class JacksonMissingPropertyTest {


    public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
        String t1 = " { \"name\" : \"asd\", \"id\" : \"1\" } ";
        String t2 = " { \"name\" : \"asd\" } ";

        ObjectMapper mapper = new ObjectMapper();
        
        Test t = mapper.readValue(t1, Test.class);
        System.out.println(t.getClass());
        t = mapper.readValue(t2, Test.class);
        System.out.println(t.getClass());
    }
    
    @JsonTypeInfo(  
            use = JsonTypeInfo.Id.CUSTOM,  
            include = JsonTypeInfo.As.PROPERTY,  
            property = "id",
            visible = true,
            defaultImpl=T2.class
            )  
    @JsonTypeIdResolver(value=Tresolve.class)
    public static interface Test {
        
        
        
    }
    
    public static class T1 implements Test {
        
        @JsonProperty
        String name = "";
        
        @JsonProperty
        String id = "";
    }
    
    public static class T2 implements Test {
        @JsonProperty
        String name = "";
    }
    
    public static class Tresolve implements TypeIdResolver {

        private JavaType baseType;

        @Override
        public void init(JavaType baseType) {
            this.baseType = baseType;
        }

        @Override
        public String idFromValue(Object value) {
            return null;
        }

        @Override
        public String idFromValueAndType(Object value, Class<?> suggestedType) {
            return null;
        }

        @Override
        public String idFromBaseType() {
            return null;
        }

        @Override
        public JavaType typeFromId(DatabindContext context, String id) throws IOException {
            if(id != null && !id.isEmpty()) {
                return TypeFactory.defaultInstance().constructSpecializedType(baseType, T1.class);
            }
            else {
                return TypeFactory.defaultInstance().constructSpecializedType(baseType, T2.class);
            }
        }

        @Override
        public String getDescForKnownTypeIds() {
            return null;
        }

        @Override
        public Id getMechanism() {
            return null;
        }
        
    }
}

