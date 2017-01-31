import java.io.*;
import java.util.*;

public class Stand {
    HashMap<String, String> mapa = new HashMap();
   
    public void stand() throws IOException {
        //reads filename
        Scanner Scan = new Scanner(System.in);
        System.out.print("Input filename:");
        String filename = Scan.nextLine();
        System.out.println("Debugging: " + filename);
       
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String line = "";
        while ((line = in.readLine()) != null){
            String parts[] = line.split(" ");
            mapa.put(parts[0], parts[1]);            
        }
       
        in.close();
        System.out.println(mapa.toString());  
               
    }  
   
    public static void main(String[] args) throws IOException {
        Stand stand1 = new Stand();
        stand1.stand();
   
    }
   
}