import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class dsa_qu2c {

//Function display(String G) takes as input the string and displays the attributes
public static void display(String G){

    String Details[]= G.split("!");

    //split() allows splitting of a string based "!" and returns an array of strings

    System.out.println("Chassis Number:\t"+Details[0]);
    System.out.println("Make:\t"+Details[1]);
    System.out.println("Model:\t"+Details[2]);
    System.out.println("Engine Capacity:\t"+Details[3]);
    System.out.println("Year of Registration:\t"+Details[4]);
    System.out.println("Buyer NIC:\t"+Details[5]);

}

public static int menu(){
    Scanner sc= new Scanner(System.in);

    int choice;

    System.out.println("1:Add a car.");
    System.out.println("2:Display car based on registration number");
    System.out.println("3:Display all details of car");

    choice=sc.nextInt();
    sc.nextLine();

    return choice;


}

public static void main(String[] args) {
    // TODO Auto-generated method stub

    String S= ""; //Compiled string to be stored as value in the hashmap
    String R= ""; //Registration number to be stored as key in the hashmap

    Scanner sc= new Scanner(System.in);

    int choice;

    HashMap<String, String> HMap= new HashMap<String, String>();

    while(true){
        choice= menu();
              switch(choice)
              {
              case 1 : {

                  System.out.println("Enter registration number");
                  R= sc.nextLine();
                  System.out.println("Enter compiled string");
                  S= sc.nextLine();

                  HMap.put(R,S);
                  break;
              }

              case 2: {

                  System.out.println("Enter registration number");
                  R= sc.nextLine();
                  String value= HMap.get(R);

                  display(value);
                  break;
              }

              case 3: {

                  for(Map.Entry entry: HMap.entrySet()){

                      display((String)entry.getValue());
                      break;

                  }
              }

              case 4: {
                  System.exit(0);
              }

              default: {
                  break;
              }
              }
    }

 }
}