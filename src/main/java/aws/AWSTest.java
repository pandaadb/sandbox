package aws;

import java.util.List;
import java.util.stream.Collectors;

import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;

public class AWSTest {
    
    public static void main(String[] args) {
        AmazonS3 build = AmazonS3ClientBuilder.standard().withCredentials(new InstanceProfileCredentialsProvider(false)).build();
        
        List<Bucket> listBuckets = build.listBuckets();
        
        System.out.println(listBuckets.stream().map(b -> b.getName()).collect(Collectors.joining(",")));
    }

}
