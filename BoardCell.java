package am.aua.checkers;

import javax.swing.*;
import java.awt.*;

public class BoardCell extends JButton{
    private int x;
    private int y;
    private Color color;
    
    public BoardCell(int x, int y, char color){
        super();
        // if(color == 'w'){
        //     this.color = Color.WHITE;
        //     this.setOpaque(true);
        //     this.setBorderPainted(false);

        // }
        // else if(color == 'b')  {
        //     this.color = Color.BLACK;
        //     this.setOpaque(true);
        //     this.setBorderPainted(false);
        // }
        this.x = x;
        this.y = y;
    }

    public int getXcoord(){
        return x;
    }

    public int getYcoord(){
        return y;
    }

    public void setCell(){
        this.setIcon(null);
    }

    public void setCell(String icon){
        ImageIcon whites = new ImageIcon("am/aua/checkers/white_stone.png");
        Image whitesImg = whites.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        whites = new ImageIcon(whitesImg);
        ImageIcon blacks = new ImageIcon("am/aua/checkers/black_stone.png");
        Image blacksImg = blacks.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        blacks = new ImageIcon(blacksImg);
        ImageIcon whiteQ = new ImageIcon("am/aua/checkers/white_queen.png");
        Image whiteQImg = whiteQ.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        whiteQ = new ImageIcon(whiteQImg);
        ImageIcon blackQ = new ImageIcon("am/aua/checkers/black_queen.png");
        Image blackQImg = blackQ.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        blackQ = new ImageIcon(blackQImg);
        if(icon == "white"){
            this.setIcon(whites);
        }
        else if(icon == "black"){
            this.setIcon(blacks);
        }
        else if(icon == "Q"){
            this.setIcon(whiteQ);
        }
        else if(icon == "q"){
            this.setIcon(blackQ);
        }
    }
}