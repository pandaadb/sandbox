package mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class MongoConnectTest {

    public static void main(String[] args) {
        MongoClientURI uri = new MongoClientURI(
                "mongodb://sa:n6U4LXbEQlSkLYwY@eu-staging-shard-00-00-svjpi.mongodb.net:27017,eu-staging-shard-00-01-svjpi.mongodb.net:27017,eu-staging-shard-00-02-svjpi.mongodb.net:27017/FindSimilar?ssl=true&replicaSet=eu-staging-shard-0&authSource=admin");

        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("FindSimilar");
        
        MongoIterable<String> listCollectionNames = database.listCollectionNames();
        for(String x : listCollectionNames) {
            System.out.println(x);
        }
    }
}
