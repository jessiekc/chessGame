package com.chess.gui;

//citation: https://www.youtube.com/watch?v=w9HR4VJ8DAw&t=489s
//citation: https://github.com/Xuefeng-Zhu/Chess-Game/blob/master/src/gui/ChessBoardPanel.java
//citation: https://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel/16345968

import main.Board;
import main.Pieces;

import javax.swing.*;
import java.awt.*;

public class Table {


    private final JFrame gameFrame;
    private final ChessBoardPanel boardPanel;

    private static Dimension OUT_FRAME_DIMENSION = new Dimension(600, 600);
    public Table(){
        this.gameFrame = new JFrame("Jchess");
        this.gameFrame.setSize(OUT_FRAME_DIMENSION);
        this.gameFrame.setVisible(true);

        this.boardPanel = new ChessBoardPanel();
        this.gameFrame.add(this.boardPanel, BorderLayout.EAST);
        this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gameFrame.pack();
    }

    /**
     * class that will draw a board and set icon on every square
     */
    private class ChessBoardPanel extends JPanel{
        public JButton chessPanel[][];
        ChessBoardPanel(){
            super(new GridLayout(8, 8));
            setPreferredSize(OUT_FRAME_DIMENSION);
            chessPanel = new JButton[8][8];
            Board board = new Board();
            for(int i = 0; i < 8; i++){
                for(int j = 0 ; j < 8; j++){
                    chessPanel[j][i] = new JButton();
                    chessPanel[j][i].setOpaque(true);
                    chessPanel[j][i].setBorderPainted(false);
                    Pieces p = board.getChessByPos(j, i);
                    if (((j + i) % 2 == 0)) {
                        chessPanel[j][i].setBackground(new Color(51,51,51));
                    }
                    else{
                        chessPanel[j][i].setBackground(new Color(204,204,204));
                    }
                    if (p != null) {
                        String iconName = "./icon/" + p.type + p.player+".png";
                        Image img = new ImageIcon(iconName).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
                        chessPanel[j][i].setIcon(new ImageIcon(img));
                    }
                    this.add(chessPanel[j][i]);
                }
            }
        }
    }

    public static void main (String[] args){
        Table table= new Table();
    }


}
