package strings;

import java.util.BitSet;
import java.util.Optional;
import java.util.stream.Collector;

import org.apache.commons.lang3.tuple.Pair;

public class PairCollector {

    public static 
    Collector<Character, ?, Pair<Optional<Character>,Optional<Character>>> get() {
        return Collector.of(PairCollectorImpl::new, PairCollectorImpl::accumulate,
                            PairCollectorImpl::merge, PairCollectorImpl::finish);
    }

    private static final class PairCollectorImpl {

        private BitSet seen=new BitSet(); 
        private BitSet repeated=new BitSet();
        private StringBuilder builder=new StringBuilder();

        public void accumulate(Character val) {
            builder.append(val);
            (seen.get(val)? repeated: seen).set(val);
        }
        PairCollectorImpl merge(PairCollectorImpl other) {
            builder.append(other.builder);
            repeated.or(other.repeated);
            other.seen.stream().forEach(c -> (seen.get(c)? repeated: seen).set(c));
            return this;
        }
        public Pair<Optional<Character>, Optional<Character>> finish() {
            return Pair.of(
                builder.chars().filter(repeated::get).mapToObj(c -> (char)c).findFirst(),
                builder.chars().filter(c -> !repeated.get(c))
                               .mapToObj(c -> (char)c).findFirst());
        }
    }
}
