/*
 * Use 'q' to quit
 */
package hegemony;

//import java.io.IOException;
//import java.util.Scanner;

/**
 *
 * @author melan
 */
public class Hegemony {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    
    public static void main(String[] args) {
        Game game = new Game();
    
    /*
    public static void main(String[] args) throws IOException {
        //Load save file
        ReadWrite.propInit();
        //User 
        System.out.println("UI Start");
        System.out.println("-Resources-");
        String[] resources = {"minerals","gas"};
        for (String resource : resources) {
            System.out.println(resource+": "+ReadWrite.getProp(resource));
        }
        System.out.println("What would you like to upgrade?\n1-Mines\n2-Extractors");
        Scanner reader = new Scanner(System.in);
        String strInput = reader.next();
        switch (strInput) {
            case "1":
                upgrade("mineralslvl");
                System.out.println("Upgraded 1");
                break;
            case "2":
                upgrade("gaslvl");
                System.out.println("Upgraded 2");                
                break;
            case "quit":
                break;
        }
        System.out.println("End");
    }
    
    private static void upgrade(String key) throws IOException{
        //System.out.println(ReadWrite.getProp(key)+1);
        ReadWrite.setProp(key,String.valueOf(ReadWrite.getProp(key)+1));
    }
    */
    }
}
