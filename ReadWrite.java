/* OBSOLETE
 *
 * Handles I/O to a Java properties file. Replaced by gsonReadWrite.
 */
package hegemony;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Properties;
//import java.io.IOException;

/**
 *
 * @author melan
 */
public class ReadWrite {
    
    static Properties appProps;
    static long T = 0;
    
    public static final int getProp(String key){
        return Integer.parseInt(appProps.getProperty(key));
    }
    public static final void setProp(String key, String value) throws IOException {
        appProps.setProperty(key,value);
        try(BufferedWriter out = 
                new BufferedWriter(new FileWriter("appProperties.properties"))) {
            appProps.store(out, null);
        }
    }
    
    public static final void propInit()throws IOException{
        Instant thisinst = Instant.now();
        
        ///create appProperties
        appProps = new Properties();
        File f = new File("appProperties.properties");

        if(!f.exists() || f.isDirectory()) { 
            System.out.println("No save found, using default");                
            ///Load defaultProperties
            try(BufferedReader in = 
                new BufferedReader(new FileReader("defaultProperties.properties"))){
                appProps.load(in);
            }
        }   
        else{
            System.out.println("Save found");

            ///Load appProperties into appProps
            try(BufferedReader in = 
                    new BufferedReader(new FileReader("appProperties.properties"))){                            
                appProps.load(in);
            }
            ///calculate time difference in seconds
            Instant lastinst = Instant.parse(appProps.getProperty("timestamp"));
            T = Duration.between(lastinst,thisinst).getSeconds();
        }
                
        ///update timestamp
        appProps.setProperty("timestamp",thisinst.toString());
        
        ///Update Resources
        String[] resources = {"minerals","gas"};
        for (String resource : resources) {
            int lvl = Integer.parseInt(appProps.getProperty(resource.concat("lvl")));
            long quantity = Integer.parseInt(appProps.getProperty(resource));
            appProps.setProperty(resource, Long.toString(quantity += lvl*T));
            //System.out.println("");
        }                       
        try(BufferedWriter out = 
                new BufferedWriter(new FileWriter("appProperties.properties"))) {
            appProps.store(out, null);
        }
    }
    
    
}
