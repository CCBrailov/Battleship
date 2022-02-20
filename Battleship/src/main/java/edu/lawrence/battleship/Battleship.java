package edu.lawrence.battleship;

import edu.lawrence.board.*;
import edu.lawrence.ships.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Battleship {
    
    private static void tryPlace(Ship shp, Board b, Scanner scn) {
        boolean validRef = false;
        boolean placed = false;
        String placedStr = "";
        Square sq;
        
        System.out.println("Orient " + shp.getType() + " [D]own or [A]cross");
        String orient = "";
        
        while (!(orient.equals("D") || orient.equals("A"))) {
            orient = scn.nextLine();
        }
        
        System.out.println("Place " + shp.getType() + ": ");
        
        while (!(placed && validRef)) {
            validRef = false;
            placed = false;
            
            String sqName = scn.nextLine();
            try {
                int r = b.gridRef(sqName)[0];
                int c = b.gridRef(sqName)[1];
                sq = b.getSquare(r, c);
                validRef = true;
                switch (orient) {
                    case "A": placedStr = b.placeShipAcross(shp, sq);
                              System.out.println(placedStr);
                              if (placedStr.equals(shp.getType() + " placed.")) {placed = true;}
                              break;
                    case "D": placedStr = b.placeShipDown(shp, sq);
                              System.out.println(placedStr);
                              if (placedStr.equals(shp.getType() + " placed.")) {placed = true;}
                              break;
                }
                
            } catch (Exception ex) {
                System.out.println("Invalid location");
            }
            

        }
        
        
    }
    
    private static void tryGuess(Board b, Scanner scn) {
        boolean validRef = false;
        Square sq;
        
        System.out.println("Make a guess:");
        
        while (!validRef) {
            validRef = false;
            
            String sqName = scn.nextLine();
            try {
                int r = b.gridRef(sqName)[0];
                int c = b.gridRef(sqName)[1];
                sq = b.getSquare(r, c);
                if (sq.getStatus() > 0) {
                    System.out.println("Already checked");
                } else {
                    validRef = true;
                    System.out.println(b.makeGuess(sq));
                    if (sq.hasShip) {
                        if (sq.getShip().hasSunk()) {
                            System.out.println("You sank my " + sq.getShip().getType() + "!");
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println("Invalid location");
            }
        }
    }
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        // Create a new board
        Board b = new Board(10, 10);
        b.print();

        // Place ships
        Ship battleship = new Ship(b, "Battleship");
        Ship destroyer = new Ship(b, "Destroyer");
        Ship submarine = new Ship(b, "Submarine");
        Ship ptboat = new Ship(b, "Patrol Boat");
        
        tryPlace(battleship, b, scanner);
        b.printShips();
        
        tryPlace(destroyer, b, scanner);
        b.printShips();
        
        tryPlace(submarine, b, scanner);
        b.printShips();
        
        tryPlace(ptboat, b, scanner);
        b.printShips();
        
        while (b.shipsLeft() > 0) {
            tryGuess(b, scanner);
            try {TimeUnit.MILLISECONDS.sleep(500);} 
            catch (InterruptedException ex) {}
            b.print();
        }
        
        System.out.println("You win!");
        
    }
    
}
