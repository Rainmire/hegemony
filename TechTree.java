/*
 * Holds all of a user's game data.
 */
package hegemony;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.util.HashMap;
/**
 *
 * @author melan
 */
public class TechTree {
    
    String timeStamp;
    int metals;
    int gas;
    TechNode root;
    
    HashMap<String, TechNode> techMap;
    
    TechTree(){
        root = new TechNode("root");        
        //initialize default graph
        initDefault();
        
    }
    
    final void initDefault(){
        metals = 0;gas = 0;
        techMap = new HashMap<>();
        TechNode node;
        node = addNode("Mines",root,0);node.incLvl();addMap(node);
        node = addNode("Extractors",root,0);node.incLvl();addMap(node);
        node = addNode("Construction",root,0);addMap(node);
        TechNode node2;
        node2 = addNode("Science Academy",node,1);addMap(node2);
        TechNode node3;
        node3 = addNode("Cold Fusion",node2,5);addMap(node3);
        node3 = addNode("Fusion Reactor",node3,1);addMap(node3);
        node2 = addNode("Advanced Aviation",node2,1);addMap(node2);
        node2 = addNode("Shipyard",node2,2);addMap(node2);
        node2 = addNode("Robotics Facility",node,2);addMap(node2);
        node = addNode("Solar Power",root,0);node.incLvl();addMap(node);
        node2 = addNode("Orbital Station",node,5);addMap(node2);
    }
    
    TechNode addNode(String name, TechNode parent, Integer parentLevelReq){
        TechNode newNode = new TechNode(name);        
        parent.addChild(newNode, parentLevelReq);
        return newNode;
    }
    
    void addMap(TechNode node) {
        techMap.put(node.getName(),node);
    }
    
    void printTree(){
        printNode(root);
    }
    void printNode(TechNode node){
        System.out.println(node.name);
        for (int i=0;i<node.children.size();i++){
            printNode(node.children.get(i));
        }
    }
    void setTimeStamp(String ts) {
        timeStamp = ts;
    }
    String getTimeStamp() {
        return timeStamp;
    }
    
    int[] getResources(){
        int[] res = new int[2];
        res[0]=metals;
        res[1]=gas;
        return res;
    }
    
    List<TechNode> getAvailableTech(){
        List<TechNode> returnNodes = new ArrayList<>();
        Stack<TechNode> stack = new Stack<>();
        stack.push(root);
        while(stack.empty() == false){
            TechNode node = stack.peek();
            returnNodes.add(node);
            stack.pop();
            List<TechNode> childNodes = node.getChildren();
            List<Integer> lvlReqs = node.getlvlReq();
            for(int i=0;i<lvlReqs.size();i++){
                if (lvlReqs.get(i)<=node.getLvl())
                    stack.push(childNodes.get(i));
            }      
        }
        return returnNodes;
    }
    
    void updateResource(long T) {
        metals += techMap.get("Mines").getLvl()*T;
        gas += techMap.get("Extractors").getLvl()*T;
    }
    //update timestamp and save
/*    void save() {
        Instant thisinst = Instant.now();
        timeStamp = thisinst.toString();
        gsonReadWrite.write(this);
    }
    
    void load() {
        
    }
    */
}
