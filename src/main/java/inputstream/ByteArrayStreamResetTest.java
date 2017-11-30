package inputstream;

import java.io.ByteArrayInputStream;

public class ByteArrayStreamResetTest {

    
    
    
    public static void main(String[] args) {
        
        byte[] test = new byte[] {1,2,3};
        
        ByteArrayInputStream s = new ByteArrayInputStream(test);
        
        int read;
        while( (read = s.read()) != -1) {
            System.out.println(read);
        }
        
        s.reset();
        System.out.println(s.read());
    }
    
}
