package mongo;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;


public class MongoCounterTest {
    
    static Map<Integer, Integer> tracker = new ConcurrentHashMap<>();

    public static void main(String[] args) throws UnknownHostException {
        ExecutorService e = Executors.newFixedThreadPool(15);

        List<Future<?>> futures = new ArrayList<>();
        for(int j=0; j < 5; j++) {
            futures.add( e.submit(() -> {

                List<ServerAddress> addresses = new ArrayList<>();
                addresses.add(new ServerAddress("mongo0.eu-staging1.cortexica-cloud.com", 27001));
                addresses.add(new ServerAddress("mongo1.eu-staging1.cortexica-cloud.com", 27001));
                
                
                MongoClientOptions options = MongoClientOptions.builder()
                        .readPreference(ReadPreference.secondaryPreferred())
                        .build();
                
                MongoClient client = new MongoClient(addresses,options);
                MongoDatabase db = client.getDatabase("artur_test");
                
                final MongoCollection<Document> collection = db.getCollection("counters_tmp");
                for(int i = 0; i< 1000; i ++) {
                    final Document document = collection.findOneAndUpdate(Filters.eq("_id", "artur_coutner"),
                            new Document("$inc", new Document("seq", 1)), new FindOneAndUpdateOptions().upsert(true));
                    Integer val = document.getInteger("seq");
                    tracker.compute(val, (k,v) -> {
                        if(v != null) {
                            System.err.println("Duplicate");
                            return v.intValue() + 1;
                        }
                        
                        System.out.println("ID " + k);
                        
                        return 1;
                    });
                }
            }));
        }
        
        futures.forEach(f -> {
            try {
                f.get();
            } catch (InterruptedException | ExecutionException e1) {
                e1.printStackTrace();
            }
        });
        
        System.out.println("Done");
        
        tracker.forEach( (k,v) -> {
            if(v > 1) {
                System.err.println("Duplicated key: " + k);
            }
        });
        
        System.out.println("Checked: " + tracker.size());
        e.shutdown();
    }
    
}
