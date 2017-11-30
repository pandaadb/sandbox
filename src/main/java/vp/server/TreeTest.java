package vp.server;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.netty.util.internal.ThreadLocalRandom;


public class TreeTest {

    public static void main(String[] args) throws IOException {
        
        String f1 = "/home/artur/tmp/prod3/comp/27_sorted";
        String f2 = "/home/artur/tmp/prod3/comp/meta_sorted";
        
        
        List<String> mod27 = Files.readAllLines(Paths.get(f1));
        List<String> meta = Files.readAllLines(Paths.get(f2));
        
        
        
        Set<String> modSet = new HashSet<>(mod27);
        Set<String> metaSet = new HashSet<>(meta);
        
        
        
        modSet.removeAll(metaSet);
        
        metaSet.removeAll( new HashSet<>(mod27));
        
        
        System.out.println("Not in metadata: ");
        
        modSet.forEach( System.out::println);
        
        System.out.println(modSet.size());
        
        System.out.println("Not in 27");
        
        metaSet.forEach( System.out::println);
        
        
        
    }
    
}
