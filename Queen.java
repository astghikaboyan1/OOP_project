package am.aua.checkers.core;

import am.aua.checkers.core.Checkers;
import am.aua.checkers.core.Checkers.PiecesColor;

public class Queen extends Pieces{
    public Queen(Checkers.PiecesColor color) {
        super(color);
    }
    public String toString() {
        if(this.getColor() == Checkers.PiecesColor.WHITE){
            return "Q";
        }
        else if(this.getColor() == PiecesColor.BLACK){
            return "q";
        }
        return null;
    }
}
