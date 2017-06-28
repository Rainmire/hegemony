/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hegemony;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author melan
 */
/*
Tech tree
*/
public class TechNode {
    String name;
    int level;
    List<TechNode> children;
    List<Integer> lvlReqs;  //the level this node needs to be to unlock child

    public TechNode(String name) {
        this.name = name;
        level=0;   
        children = new ArrayList<>();
        lvlReqs = new ArrayList<>();
    }
    int getLvl() {
        return level;
    }
    void incLvl() {
        level++;
    }
    String getName() {
        return name;
    }
    void addChild(TechNode child, Integer lvlreq) {
        children.add(child);
        lvlReqs.add(lvlreq);
    }
    List<TechNode> getChildren() {
        return children;
    }
    List<Integer> getlvlReq() {
        return lvlReqs;
    }
}
