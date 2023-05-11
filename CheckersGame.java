package am.aua.checkers;


import am.aua.checkers.core.*;

import java.io.IOException;
import java.util.Scanner;

public class CheckersGame{
    private Checkers checkers;

    public CheckersGame(Checkers c){
        checkers = c;
    }

    public void print(){
        System.out.println(checkers.toString());
    }

    public void play(){
        System.out.println("Welcome to Checkers game!");
        System.out.println("Input row and colomn number of piece location.");
        System.out.println("Then input row and column number of destination.");
        System.out.println("Example: 61 52");
        System.out.println("Input 0 to stop the game.");
        System.out.println();
        System.out.println("Good luck!");
        System.out.println();
        System.out.println(checkers.getTurn() + " is playing");
        print();
        Scanner sc = new Scanner(System.in);
        try{
            while(sc.hasNextInt()){
            if(checkers.getGameIsOver()){
                System.out.println(checkers.winnerIs() + " won!");
                System.exit(0);
            }
            int inputFrom = sc.nextInt();
            if(inputFrom == 0){
                System.out.println(checkers.winnerIs() + " won!");
                System.exit(0);
            }
            int fromRow = inputFrom / 10;
            int fromCol = inputFrom % 10;

            int inputTo = sc.nextInt();
            int toRow = inputTo / 10;
            int toCol = inputTo % 10;
            if(checkers.isValidMove(fromRow,fromCol, toRow, toCol)){
                checkers.move(fromRow,fromCol, toRow, toCol);
                print();
                System.out.println(checkers.getTurn() + " is playing");  
            }
            else 
                System.out.println("Invalid input. Try again!");
            
            }
        } catch(IOException ioe){
            ioe.getMessage();
        } catch(Exception e){
            e.getMessage();
        }
    }

    public static void main(String[] args){
        Checkers checkers = new Checkers();
        CheckersGame game = new CheckersGame(checkers);
        game.play();
    }
}