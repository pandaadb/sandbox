package strings;

import java.util.Arrays;
import java.util.List;

public class SplitTest {

    public static void main(String[] args) {
        
        List<String> asList = Arrays.asList("".split(","));
        System.out.println(asList.size());
        
    }
}
