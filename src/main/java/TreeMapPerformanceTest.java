import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;

public class TreeMapPerformanceTest {

    private static final int dataSize = 1000 * 1000;

    static class Pair {

        private final Integer key;
        private final String value;

        public Pair(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

    }

    /**
     * time cost (3 times) = 196, 178, 186
     */
    public void testPutPerformance_string_intern() {
        testPutPerformance((loc) -> new String("abc").intern());
    }

    /**
     * time cost (3 times) = 275, 317, 331
     */
    public void testPutPerformance_string_new() {
        testPutPerformance((loc) -> new String("abc"));
    }

    /**
     * this case got bad performance time cost (3 times) = 591, 565, 570
     */
    public void testPutPerformance_string_hardcoded() {
        testPutPerformance((loc) -> "abc");
    }

    private void testPutPerformance(Function<Integer, String> stringCreateMethod) {
        // prepare data
        List<Pair> data = new ArrayList(dataSize);
        for (int i = 0; i < dataSize; i++) {
            Pair pair = new Pair(i, stringCreateMethod.apply(i));
            data.add(pair);
        }
        int size = data.size();

        // map operation
        Map<Integer, String> map = new TreeMap<>();

        long startTimeMillis = System.currentTimeMillis();

        for (int i = 0; i < size; i++) {
            Pair pair = data.get(i);
            map.put(pair.getKey(), pair.getValue());
        }

        long endTimeMillis = System.currentTimeMillis();
        System.out.println("time cost = " + (endTimeMillis - startTimeMillis));
    }

    public static void main(String[] args) {
        TreeMapPerformanceTest t = new TreeMapPerformanceTest();
        for(int i=0 ;i< 10; i ++) {
            System.out.print("new: ");
            t.testPutPerformance_string_new();
            System.out.println();
            System.out.print("intern: ");
            t.testPutPerformance_string_intern();
            System.out.println();
            System.out.print("hardcoded: ");
            t.testPutPerformance_string_hardcoded();
            System.out.println();
            System.out.println();
        }
        
    }
}