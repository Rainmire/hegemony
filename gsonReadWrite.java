/*
 * Gson I/O. Converts a TechTree to a json string and writes it to
 * "Savefile.json". Reads save file and converts back to TechTree object.
 */
package hegemony;
//import java.time.Duration;
//import java.time.Instant;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import com.google.gson.*;
/**
 *
 * @author melan
 */
public class gsonReadWrite {
    public static void write(TechTree data) {
        //Instant thisinst = Instant.now();
        //data.setTimeStamp(thisinst.toString());
        Gson gson = new Gson();
        try(BufferedWriter out = 
                new BufferedWriter(new FileWriter("savefile.json"))) {
            gson.toJson(data, out);
        } catch (IOException ex) {
                System.out.println("Save failed: "+ex);
        }
        
    }
    public static TechTree read() {
        Gson gson = new Gson();
        try(BufferedReader in = 
                new BufferedReader(new FileReader("savefile.json"))){                            
            return gson.fromJson(in, TechTree.class);
            }
        catch (IOException ex) {
            System.out.println("Load failed: "+ex);
            return null;
        }
    }
}
