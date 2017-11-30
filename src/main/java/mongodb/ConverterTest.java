//package mongodb;
//
//import java.net.UnknownHostException;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
//import org.springframework.data.mongodb.core.convert.DbRefResolver;
//import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
//import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
//import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
//
//import com.mongodb.BasicDBObject;
//import com.mongodb.MongoException;
//import com.mongodb.MongoURI;
//
//public class ConverterTest {
//
//    static Test create() {
//        Test t = new Test();
//        Map<String, List<String>> temp = new HashMap<>();
//        temp.put("1", Arrays.asList("test", "second"));
//        temp.put("2", Arrays.asList("something"));
//        
//        t.values = temp;
//        
//        t.properties = Arrays.asList("a", "b", "c");
//        return t;
//    }
//    
//    
//    public static void main(String[] args) throws MongoException, UnknownHostException {
//        MongoMappingContext c = new MongoMappingContext();
//        c.afterPropertiesSet();
//        DbRefResolver res = new DefaultDbRefResolver(new SimpleMongoDbFactory(new MongoURI("mongodb://localhost:27001")));
//        
//        MappingMongoConverter conv = new MappingMongoConverter(res, c);
//        
////        org.bson.Document doc = new org.bson.Document();
//        BasicDBObject o = new BasicDBObject();
//        conv.write(create(), o);
//        
//        System.out.println(0);
//        
//        
//    }
//    
//    public static class Test{ 
//        
//        String id;
//        Map<String, List<String>> values;
//        List<String> properties;
//    }
//}
