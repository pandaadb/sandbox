package hash;

import java.math.BigInteger;
import java.util.Random;

import com.github.rholder.nilsimsa.Nilsimsa;

import freemarker.template.utility.StringUtil;

public class SimilarityHash {

    public static void main(String[] args) {
        Random r = new Random();

        Nilsimsa tester = new Nilsimsa();
        for(int i =0 ; i < 25_000_000; i++) {
            tester.update(String.valueOf(i).getBytes());
        }
        
        // random word
        Nilsimsa n = new Nilsimsa();
        int[] baseline = tester.digest();
        
        
        System.out.println("Baseline " + baseline.length);
        
        for(int i = 0; i < 2000 ;i ++) {
            n.update(String.valueOf(i).getBytes());
        }
        
        String hexDigest = n.toHexDigest();
        
        System.out.println("Word compares to baseline as: " + Nilsimsa.compare(baseline, n.digest()));
        
        Nilsimsa n2 = new Nilsimsa();
        for(int i = 200; i < 2200 ;i ++) {
            n2.update(String.valueOf(i).getBytes());
        }
        
        System.out.println("Word2 compares to baseline as: " + Nilsimsa.compare(baseline, n2.digest()));
        
        
//        System.out.println("N1: " + Integer.parseInt(hexDigest, 16));
//        Nilsimsa n2 = new Nilsimsa();
//
//        System.out.println(hexDigest);
//        System.out.println(new BigInteger(hexDigest, 16));
//        
//        for(int i = 0; i < 2000 ;i ++) {
//            n2.update(String.valueOf(i).getBytes());
//        }
//        
//        
//        for(int i = 0 ; i < 20 ; i++) {
//            String hexDigest2 = n2.toHexDigest();
//            for(int i2 =0; i2< 100; i2++) {
//                n2.update(String.valueOf(r.nextInt(250000000)).getBytes());
//            }
//            
//            
//            print(Nilsimsa.unsafeToHex(hexDigest), Nilsimsa.unsafeToHex(hexDigest2));
//            
//        }
        
    }
    
    public static void print(int[] first, int[] second) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("--- --- ---\n");
        buffer.append("Length:" + first.length + " - " + second.length + "\n");
        
        for(int i = 0; i < first.length; i++) {
            buffer.append(first[i] + " - " + second[i]);
            if(first[i] != second[i]) {
                buffer.append(" **");
            }
            buffer.append("\n");
        }
    
        int compare = Nilsimsa.compare(first, second);
        
        buffer.append("Compare: " + compare);
        
        buffer.append("\n\n\n\n\n");
        
        System.out.println(buffer.toString());
    }
    
    
    
}
