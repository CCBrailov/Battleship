package edu.lawrence.board;

import edu.lawrence.ships.Ship;
import java.lang.reflect.Array;

public class Square {
    private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    private int[] coords;
    private int status;
    
    public boolean hasShip;
    
    private String look;
    private String name;
    private Ship ship;
    
    public Square (int r, int c) {
        
        coords = new int[2];
        coords[0] = r;
        coords[1] = c;
        
        status = 0;
        
        hasShip = false;
        
        name = String.valueOf(alphabet.charAt(r)) + String.valueOf(c + 1);
    }
    
    public String drawHitStatus() {

        switch (status){
            case 0: look = "[ ]";
                    break;
            case 1: look = "[X]";
                    break;
            case 2: look = "[O]";
                    break;
        }
        
        return look;
    }
    
    public String drawName() {
        look = "[" + name + "]";
        return look;
    }
    
    public String drawShip() {
        if (hasShip) {
            look = "[" + ship.getCode() + "]";
        } else {
            look = "[ ]";
        }
        return look;
    }

    public void addShip(Ship s) {
        hasShip = true;
        ship = s;
    }
        
    public void setStatus(int s) {
        status = s;
    }
    
    public int[] getCoords() {
        return coords;
    }
    
    public int getStatus() {
        return status;
    }
    
    public Ship getShip() {
        return ship;
    }
    
}
