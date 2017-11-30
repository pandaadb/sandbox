package sorting;

import java.util.Arrays;
import java.util.Comparator;

public class DoubleArraySort {

    public static void main(String[] args) {
        int[][] test = new int[][] {
            {1, 2, 3, 4},
            {1,8,2,8},
            {8,6,10,11},
            {5,6,7,8},
            {1,6,2,6}
        };
        
        print(test);
        
        // sort in 2 steps
        
        Arrays.sort(test, Comparator.comparing((int[] arr) -> arr[1]).thenComparing((int[] arr) -> arr[2]));
        
        System.out.println();
        print(test);
        
    }

    static void print(int[][] test) {
        for(int i = 0; i < test.length; i ++) {
            int[] sub = test[i];
            Arrays.stream(sub).forEach( x -> System.out.print(x + " "));
            System.out.println();
        }
    }
}
