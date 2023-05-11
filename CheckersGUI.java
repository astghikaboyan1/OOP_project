package am.aua.checkers;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import am.aua.checkers.core.*;
import am.aua.checkers.BoardCell;

public class CheckersGUI extends JFrame implements ActionListener{
    private Checkers checkers;
    private BoardCell[][] board;


    public CheckersGUI(){
        super("Checkers");
        checkers = new Checkers();
        board = new BoardCell[8][8];
    
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 900);
        setLayout(new BorderLayout());
        
        add(createControlPanel(), BorderLayout.EAST);
        add(createBoard());
        setImages();
    }

    private static void addBorder(JComponent component, String title) {
        Border etch = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        Border tb = BorderFactory.createTitledBorder(etch, title);
        component.setBorder(tb);
    }

    public JPanel createBoard() {
        JPanel boardPanel = new JPanel();
        addBorder(boardPanel, "Game");
        boardPanel.setBackground(Color.GRAY);
        boardPanel.setLayout(new GridLayout(8, 8));
    
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                char color = (row + col) % 2 == 0 ? 'w' : 'b';
                BoardCell cell = new BoardCell(row, col, color);
                if(color == 'c')
                    cell.setBackground(Color.WHITE);
                else if(color == 'b')
                    cell.setBackground(Color.BLACK);
                cell.setOpaque(true);
                cell.setBorderPainted(false);
                cell.addActionListener(this);
                board[row][col] = cell;
                boardPanel.add(cell);
            }
        }
    
        return boardPanel;
    }
    

    private JPanel createControlPanel(){
        JPanel ctrl = new JPanel(new GridLayout(7, 1, 0, 30));
        addBorder(ctrl, "Control Panel");
        ctrl.add(new JLabel());
        ctrl.add(new JLabel());
        JButton newGame = new JButton("New Game");
        newGame.addActionListener(this);
        newGame.setPreferredSize(new Dimension(100, 40));
        ctrl.add(newGame, BorderLayout.CENTER);
        JButton play = new JButton("Play");
        play.addActionListener(this);
        play.setPreferredSize(new Dimension(100, 40));
        ctrl.add(play, BorderLayout.CENTER);
        JButton finish = new JButton("Finish");
        finish.addActionListener(this);
        finish.setPreferredSize(new Dimension(100, 40));
        ctrl.add(finish, BorderLayout.CENTER);
        ctrl.add(new JLabel());
        ctrl.add(new JLabel());
        return ctrl;
    }

    // public void actionPerformed(ActionEvent e) {
    //     BoardCell source = (BoardCell) e.getSource();
        
    // }

    public void setImages(){
        String piece = null;
        for(int row = 0; row < 8; row++){
            for(int col = 0; col < 8; col++){
                Pieces a = checkers.getPiece(row, col);
                if(a != null)
                    piece = a.toString();
                else 
                    piece = null;
                if(piece != null){
                    board[row][col].setCell(piece);
                }
                else{
                    board[row][col].setCell();
                }
            }
        }
    }

    public static void main(String[] args){
        CheckersGUI gui = new CheckersGUI();
        gui.setVisible(true);
    }
}