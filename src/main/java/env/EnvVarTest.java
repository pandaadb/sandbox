package env;

public class EnvVarTest {

    
    public static void main(String[] args) {
        
        String getenv = System.getProperty("TEST_VARIABLE");
        System.out.println(getenv); // null because we haven't set it 
        
        System.setProperty("TEST_VARIABLE", "bla");
        getenv = System.getProperty("TEST_VARIABLE");
        System.out.println(getenv); // prints "bla"
        
        String property = System.getProperty("TEST_FROM_ENV");
        System.out.println(property); // prints whatevber you put in evn
        
        Object asd = new Integer(23);
        String bla = asd.toString();
        System.out.println(bla);
        
        String helllo = "adasdasd";
        System.out.println(helllo.toString());
        
    }
}
