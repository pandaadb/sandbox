package cortexica;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FingerPrintUnpacker {

    static final List<Integer> mask = Arrays.asList(0, 1, 3, 7, 15, 31, 63, 127);
    static final int bitsPerItem = 4;
    
    public static void main(String[] args) throws IOException {
        byte[] fp = Files.readAllBytes(Paths.get("/home/artur/tmp/fp_real/fp_manualy_cropped_2"));
        List<Integer> unpack = unpack(fp, numSignificantBits());
        
        unpack.forEach(System.out::println);
    }
    
    
    static int numSignificantBits() {
        long val =(long) Math.pow(2, 32) -1;
        int bits = 0;
        while(true) {
            if(val == 0) {
                return bits;
            }
            val >>= 1;
            bits++;
        }
    }
    
    public static List<Integer> unpack(byte[] in, int bitsPerItems) {
        int bitsWrittenDataItem = 0;
        
        int data = 0;
        
        List<Integer> words = new ArrayList<>();
        
        for(byte current : in) {
            int currentlyReadBytes = 0;
            
            while(currentlyReadBytes < 8) {
                int bitsRemainingCurrentByte = 8 - currentlyReadBytes;
                int bitsRemainingDataItem = bitsPerItems - bitsWrittenDataItem;
                int bitsToRead = Math.min(bitsRemainingCurrentByte, bitsRemainingDataItem);
                
                if(bitsToRead == 8) {
                    data += current << bitsWrittenDataItem;
                } else {
                    int tmpMask = mask.get(bitsToRead);
                    data += (current & tmpMask) << bitsWrittenDataItem;
                    current >>= bitsToRead;
                }
                
                currentlyReadBytes += bitsToRead;
                bitsWrittenDataItem += bitsToRead;
                
                if(bitsWrittenDataItem == bitsPerItems) {
                    words.add(data);
                    bitsWrittenDataItem = 0;
                    data = 0;
                }
            }
        }
              
        return words;
    }
}
