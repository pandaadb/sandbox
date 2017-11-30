package jacksonTest;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Inheritancetest {

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        
        B b = new B();
        b.test2 = "ads";
        b.test = "this is the subclass;";

        System.out.println(mapper.writeValueAsString(b));
    }
    
    public static class A {
        String test;
    }
    
    @JsonAutoDetect(creatorVisibility = Visibility.ANY, fieldVisibility = Visibility.ANY)
    public static class B extends A {
        String test2;
    }
}
