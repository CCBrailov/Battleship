/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.lawrence.board;

import edu.lawrence.ships.*;
import java.util.ArrayList;

public class Board {
    
    private final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int cols;
    private int rows;
    
    private ArrayList<Row> grid;
    private ArrayList<Ship> ships;
    
    private class Row extends ArrayList<Square> {
        
        public char letter;
        
        Row (int w, int n) {
            letter = alphabet.charAt(n);
            for (int i = 0; i < w; i++) {
                Square s = new Square(n, i);
                this.add(s);
            }
        }
    }
    
    public Board(int width, int height) {
        
        cols = width;
        rows = height;
        
        grid = new ArrayList<Row>();
        ships = new ArrayList<Ship>();
        
        for (int i = 0; i < height; i++) {
            Row r = new Row(width, i);
            grid.add(r);
        }
        
    }
    
    public void print (){
        
        //Print the column labels
        System.out.print("   ");
        for (int i = 1; i <= cols; i++) {
            System.out.print(String.valueOf(i));
            if (i < 11) {
                System.out.print("  ");
            } else {
                System.out.print(" ");
            }
        }
        System.out.println();
        
        //Print each row
        for (Row r : grid) {
            System.out.print(r.letter + " ");
            for (Square s : r) {
                System.out.print(s.drawHitStatus());
            }
            System.out.println();
        }
    }
    
    public void printShips(){
        
        //Print the column labels
        System.out.print("   ");
        for (int i = 1; i <= cols; i++) {
            System.out.print(String.valueOf(i));
            if (i < 11) {
                System.out.print("  ");
            } else {
                System.out.print(" ");
            }
        }
        System.out.println();
        
        //Print each row
        for (Row r : grid) {
            System.out.print(r.letter + " ");
            for (Square s : r) {
                System.out.print(s.drawShip());
            }
            System.out.println();
        }
    }
    
    public String placeShipAcross(Ship shp, Square sq) {
        int length = shp.getLength();
        
        int r = sq.getCoords()[0];
        int c = sq.getCoords()[1];
        
        if (c + length > cols) {
            return "Out of bounds";
        }
        
        for (int i = 0; i < length; i++) {
            if (getSquare(r, c + i).hasShip) {
                return "Space already occupied";
            }
        }
        
        for (int i = 0; i < length; i++) {
            getSquare(r, c + i).addShip(shp);
            shp.addSquare(getSquare(r, c + i));
        }
        
        ships.add(shp);
        return (shp.getType() + " placed.");
    }
    
    public String placeShipDown(Ship shp, Square sq) {
        int length = shp.getLength();
        
        int r = sq.getCoords()[0];
        int c = sq.getCoords()[1];
        
        if (r + length > rows) {
            return "Out of bounds";
        }
        
        for (int i = 0; i < length; i++) {
            if (getSquare(r + i, c).hasShip) {
                return "Space already occupied";
            }
        }
        
        for (int i = 0; i < length; i++) {
            getSquare(r + i, c).addShip(shp);
            shp.addSquare(getSquare(r + i, c));
        }
        
        ships.add(shp);
        return (shp.getType() + " placed.");
    }
    
    public Square getSquare(int r, int c){
        Square s = grid.get(r).get(c);
        return s;
    }
    
    public int[] gridRef(String s) {
        
        int[] coords = new int[2];

        coords[0] = alphabet.indexOf(s.charAt(0));
        coords[1] = Integer.valueOf(String.valueOf(s.substring(1))) - 1;
        
        return coords;
    }
    
    public String makeGuess(Square sq) {
        boolean hit = sq.hasShip;
        String result = "";
        
        if (hit) {
            sq.setStatus(1);
            result = "Hit!";
        } else {
            sq.setStatus(2);
            result = "Miss!";
        }
        
        return result;
    }
    
    public int shipsLeft() {
        int remain = ships.size();
        
        for (Ship s : ships) {
            if (s.hasSunk()) {
                remain -= 1;
            }
        }
        
        return remain;
    }
}