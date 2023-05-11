package am.aua.checkers.core;

import am.aua.checkers.core.Pieces;
import am.aua.checkers.core.Queen;


public class Checkers {
    public enum PiecesColor {WHITE, BLACK};
    private Pieces[][] board;
    private static char turn;
    private static int whitePieces;
    private static int blackPieces;
    public final static int size = 8;
    private String winner;
    public boolean gameIsOver;


    public Checkers(){
        board = new Pieces[size][size];
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board[row].length; col++){
                if(row % 2 == 0 && col % 2 == 1 || row % 2 == 1 && col % 2 == 0){
                    if(row < 3){
                        board[row][col] = new Pieces(PiecesColor.BLACK);
                    }
                    else if(row > 4){
                        board[row][col] = new Pieces(PiecesColor.WHITE);
                    }
                }
            }   
        }
        turn = 'W';
        whitePieces = 12;
        blackPieces = 12;
        winner = " ";
        gameIsOver = false;
    }

    public char getTurn(){
        return turn;
    }

    public static int getWhitePieces(){
        return whitePieces;
    }

    public static int getBlackPieces(){
        return blackPieces;
    }

    public Pieces getPiece(int row, int col){
        return board[row][col];
    }


    public PiecesColor getPieceAt(int row, int col){
        if(board[row][col] == null)
            return null;
        return board[row][col].getColor();
    }

    public boolean getGameIsOver(){
        return gameIsOver;
    }


    public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol){
        if(fromRow < 1 || fromRow > 8 || fromCol < 1 || fromCol > 8 
            || toRow < 1 || toRow > 8 || toCol < 1 || toCol > 8){
                return false;
        }
        int range = 0;
        range = fromRow - toRow;
        int midRow = (fromRow + toRow) / 2;
        int midCol = (fromCol + toCol) / 2;

        if(board[toRow - 1][toCol - 1] == null){
            if(board[fromRow - 1][fromCol - 1] instanceof Queen){
                if(turn == 'W'){
                    if((range == 1 || range == -1) && Math.abs(fromCol - toCol) == 1){
                        if(getPieceAt(fromRow - 1, fromCol - 1) == PiecesColor.WHITE)
                            return true; 
                    }
                    else if((range == 2 || range == -2) && Math.abs(fromCol - toCol) == 2){
                        if(getPieceAt(fromRow - 1, fromCol - 1) == PiecesColor.WHITE 
                            && getPieceAt(midRow - 1, midCol - 1) == PiecesColor.BLACK)
                            return true; 
                    }
                }
                else if(turn == 'B'){
                    if(range == -1 || range == 1 && Math.abs(fromCol - toCol) == 1){
                        if(getPieceAt(fromRow - 1, fromCol - 1) == PiecesColor.BLACK) 
                            return true;
                    }
                    else if(range == -2 || range == 2 && Math.abs(fromCol - toCol) == 2){
                        if(getPieceAt(fromRow - 1, fromCol - 1) == PiecesColor.BLACK
                            && getPieceAt(midRow - 1, midCol - 1) == PiecesColor.WHITE){
                                return true;
                        }   
                    }
                }
            }

            if(turn == 'W'){
                if(range == 1 && Math.abs(fromCol - toCol) == 1){
                    if(getPieceAt(fromRow - 1, fromCol - 1) == PiecesColor.WHITE)
                        return true; 
                }
                else if(range == 2 && Math.abs(fromCol - toCol) == 2){
                    if(getPieceAt(fromRow - 1, fromCol - 1) == PiecesColor.WHITE 
                        && getPieceAt(midRow - 1, midCol - 1) == PiecesColor.BLACK)
                        return true; 
                }
            }
            else if(turn == 'B'){
                if(range == -1 && Math.abs(fromCol - toCol) == 1){
                    if(getPieceAt(fromRow - 1, fromCol - 1) == PiecesColor.BLACK) 
                        return true;
                }
                else if(range == -2 && Math.abs(fromCol - toCol) == 2){
                    if(getPieceAt(fromRow - 1, fromCol - 1) == PiecesColor.BLACK
                        && getPieceAt(midRow - 1, midCol - 1) == PiecesColor.WHITE){
                            return true; 
                    } 
                }
            }
        }
        return false;
    }

    public void move(int fromRow, int fromCol, int toRow, int toCol){
        board[toRow - 1][toCol - 1] = board[fromRow - 1][fromCol - 1];
        board[fromRow - 1][fromCol - 1] = null;
        if(Math.abs(fromRow - toRow) == 2){
            board[(fromRow - 1 + toRow - 1)/2][(fromCol - 1 + toCol - 1)/2] = null;
            if(turn == 'W')
                blackPieces--;
            else
                whitePieces--;
            if(blackPieces == 0 || whitePieces == 0){
                gameIsOver = true;
            }
        }
        
        if(toRow == 8 || toRow == 1){
            becomeQueen(toRow - 1, toCol - 1);
        }
        if(turn == 'W')
            turn = 'B';
        else
            turn = 'W';
    }


    public void becomeQueen(int toRow, int toCol){
        if(turn == 'W'){
            board[toRow][toCol] = null;
            board[toRow][toCol] = new Queen(PiecesColor.WHITE);
        }
        else {
            board[toRow][toCol] = null;
            board[toRow][toCol] = new Queen(PiecesColor.BLACK);
        }
    }

    public String winnerIs(){
        if(blackPieces > whitePieces)
            winner = "Black";
        else if(whitePieces > blackPieces)
            winner = "White";
        else 
            winner = "Draw";
        return winner;
    }


    public String toString(){
        final char WHITE = 'w';
        final char BLACK = 'b';
        final char EMPTY = '-';
        final char QUEENWHITE = 'Q';
        final char QUEENBLACK = 'q';
        StringBuilder result = new StringBuilder();
        result.append("  12345678");
        result.append("\n");
        for (int row = 0; row < size; row++) {
            result.append(row + 1);
            result.append(" ");
            for (int col = 0; col < size; col++){
                if (getPieceAt(row, col) == PiecesColor.WHITE) {
                    if (board[row][col] instanceof Queen)
                        result.append(QUEENWHITE);
                    else 
                        result.append(WHITE);
                }
                else if(getPieceAt(row, col) == PiecesColor.BLACK) {
                    if (board[row][col] instanceof Queen){
                        result.append(QUEENBLACK);
                    }
                    else
                        result.append(BLACK);
                }
                else 
                    result.append(EMPTY);
            }
            result.append("\n");
        }
        return result.toString();
    }
}