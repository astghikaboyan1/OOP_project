package am.aua.checkers.core;

import am.aua.checkers.core.Checkers;
import am.aua.checkers.core.Checkers.PiecesColor;

public class Pieces implements Cloneable {
    private Checkers.PiecesColor color; 

    public Pieces(Checkers.PiecesColor color){
        this.color = color;
    }

    public PiecesColor getColor(){
        return color;
    }

    public Pieces clone(){
        try{
            Pieces copy = (Pieces) super.clone();
            return copy;
        } catch(CloneNotSupportedException cnse){
            return null;
        }
    }

    public String toString(){
        if((color) == PiecesColor.WHITE){
            return "white";
        }
        else if((color) == PiecesColor.BLACK){
            return "black";
        }
        return null;
    }
}
