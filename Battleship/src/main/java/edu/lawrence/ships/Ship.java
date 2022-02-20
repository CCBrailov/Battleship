/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.lawrence.ships;

import edu.lawrence.board.Board;
import edu.lawrence.board.Square;
import java.util.ArrayList;


public class Ship {
    
    private Board board;
    
    private int length;
    
    private boolean hasSunk = false;
    
    private String orient;
    private String type;
    private String code;
    
    private ArrayList<Square> squares;
    
    public Ship(Board b, String t) {
        board = b;
        type = t;
        squares = new ArrayList<Square>()   ;
        setType(t);
    }
    
    public boolean hasSunk() {
        int hits = 0;
        
        for (Square sq : squares){
            hits += sq.getStatus();
        }
        
        if (hits >= length) {
            hasSunk = true;
        }
        
        return hasSunk;
    }
    
    public void addSquare (Square s) {
        squares.add(s);
    }
    
    private void setType (String t) {
        switch (t) {
            case "Carrier"    : length = 5;
                                code = "C";
                                break;
            case "Battleship" : length = 4;
                                code = "B";
                                break;
            case "Destroyer"  : length = 3;
                                code = "D";
                                break;
            case "Submarine"  : length = 3;
                                code = "S";
                                break;
            case "Patrol Boat": length = 2;
                                code = "P";
                                break;
        }
    }
    
    public int getLength () {
        return length;
    }
    
    public String getType() {
        return type;
    }
    
    public String getCode() {
        return code;
    }
    

}
