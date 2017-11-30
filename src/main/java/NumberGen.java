
public class NumberGen {

    public static void main(String[] args) throws InterruptedException {
        for(int a =0; a< 5000; a++) {
            for(int b = 0; b < 5000; b++) {
                int val = a * 5000 + b;
                System.out.println(val);
                Thread.sleep(20);
            }
        }
    }
}
