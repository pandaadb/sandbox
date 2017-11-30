
public class PassByCopyTest {

    
    public static void main(String[] args) {
        
        Holder h = new Holder();
        dos(h);
        System.out.println(h.i);
        
        int i = 5;
        doI(i);
        System.out.println(i);
    }
    
    public static void doI(int i) {
        i = 10;
    }
    
    public static void dos(Holder h) {
        h = new Holder();
        h.i = 22;
    }
    
    public static class Holder {
        int i = 3;
    }
}
