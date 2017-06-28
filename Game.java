/*
 * Handles all of the gameplay
 */
package hegemony;

import java.io.File;
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.Properties;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Scanner;

//import java.util.List;
/**
 *
 * @author melan
 */
public class Game {
    
    List<TechNode> availableTech;
    Instant thisinst;

    TechTree gameData;
    public Game() {
        System.out.println("Loading Save");
        File f = new File("savefile.json");
        
        if(!f.exists() || f.isDirectory()) {    //MAKE NEW SAVE
            System.out.println("No save found, using default");
            gameData = new TechTree();            
            //save gameData to json
            save();
        }
        
        else {
            System.out.println("Save found");            
            gameData = gsonReadWrite.read();            
        }
        refreshUI();
        
        while(true){
            System.out.println("\nMake a selection");
            Scanner reader = new Scanner(System.in);
            String strInput = reader.next();
            if ("q".equals(strInput)||"Q".equals(strInput))
                break;
            int input = Integer.parseInt(strInput);
            if (input<1 || input>availableTech.size()) {
                System.out.println("Invalid selection");
                continue;
            }
            availableTech.get(input).incLvl();
            save();
            refreshUI();    //TODO:make resource refresh before incLvl
        }
    }
    
    final void save() {
        thisinst = Instant.now();
        gameData.setTimeStamp(thisinst.toString());
        gsonReadWrite.write(gameData);
    }
    
    final void refreshUI() {
        thisinst = Instant.now();
        Instant lastinst = Instant.parse(gameData.getTimeStamp());
        long T = Duration.between(lastinst,thisinst).getSeconds();
        gameData.updateResource(T);
        save();
        int[] res = gameData.getResources();

        System.out.println("-Resources-\nMetals: "+res[0]+"\nGas: "+res[1]);        
        System.out.println("-Available Upgrades-");
        availableTech = gameData.getAvailableTech();
        for (int i=1;i<availableTech.size();i++) {
            TechNode node = availableTech.get(i);
            System.out.println(i+" "+node.getName()+": "+node.getLvl());
        }
    }
    /*
    void upgrade()
    void updateResource()*/
    
}
