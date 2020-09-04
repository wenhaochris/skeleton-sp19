package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;


import static huglife.HugLifeUtils.randomEntry;
public class Clorus extends Creature {

    /*red color  */
    private int r;

    /* green color*/
    private int g;

    /* blue color*/
    private int b;


    /* create clorus with energy equal to e */
    public Clorus (double e){
       super("clorus");
       r = 0;
       g = 0;
       b = 0;
       energy = e;
      }

      public Clorus(){ this(1);}





    @Override
    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);
    }
    @Override
    public void move() {
            if(energy < 0.03){
                energy = 0;
            }else{
                energy -= 0.03;
            }
    }
    @Override
    public void stay() {
            if(energy <= 0.01){
                energy = 0;
            }else{
                energy -= 0.01;
            }
    }

    @Override
    public void attack(Creature c) {
            energy += c.energy();
    }

    @Override
    public Clorus replicate() {
        Clorus baby = new Clorus(this.energy * 0.5);
        energy *= 0.5;
        return baby;
    }



    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();
        for(Direction d : neighbors.keySet()){
            if(neighbors.get(d).name().equals("empty")){
                emptyNeighbors.addFirst(d);
            }else if(neighbors.get(d).name().equals("plip")){
                plipNeighbors.addFirst(d);
            }
        }
        if(emptyNeighbors.size() == 0){
            return new Action(Action.ActionType.STAY);
        }

        if(plipNeighbors.size() != 0){
            return new Action(Action.ActionType.ATTACK, randomEntry(plipNeighbors));
        }

        if(energy >= 1){
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyNeighbors));
        }

        return new Action(Action.ActionType.MOVE, randomEntry(emptyNeighbors));


    }


}
