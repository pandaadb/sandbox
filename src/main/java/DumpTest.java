import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DumpTest {

    public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
        String file = "/home/artur/tmp/prod3Dump/dump2.txt";
        
        Client client = ClientBuilder.newClient();
        
        ObjectMapper mapper = new ObjectMapper();

        List<Model> readValue = mapper.readValue(Files.readAllBytes(Paths.get(file)), new TypeReference<List<Model>>() {
        });
        
        
        int checked = 0;
        
        for(Model m : readValue) {
            
            try {
                Response head = client.target(m.img).request().head();
                
                if(head.getStatusInfo().getFamily() != Status.Family.SUCCESSFUL) {
                    System.err.println("Url " + m.img +  " id: " + m._id + " status " + head.getStatus());
                }
                checked++;
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Url " + m.img +  " id: " + m._id + " status: ERROR ");
            }
            
            if(checked % 1000 == 0) {
                System.out.println("Checked " + checked);
            }
        }
        
        
    }
    
    public static class Model {
        
        @JsonProperty("_id")
        String _id;
        
        @JsonProperty("ImageURL")
        String img;
    }
}
