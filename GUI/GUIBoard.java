package GUI;
import main.Board;
import main.Pieces;
import controller.Controller;
import java.util.Stack;
//citation: https://www.youtube.com/watch?v=w9HR4VJ8DAw&t=489s
//citation: https://github.com/Xuefeng-Zhu/Chess-Game/blob/master/src/gui/ChessBoardPanel.java
//citation: https://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel/16345968
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class GUIBoard {
    public int offsetInterval;
    public JButton chessToBeMoved;
    public Controller controller;
    public Stack<JButton> blackPast;
    public Stack<JButton> whitePast;
    public JButton blackStart;
    public JButton blackEnd;
    public JButton whiteStart;
    public JButton whiteEnd;
    public Table table;

    public GUIBoard(Main main) {
        table = new Table(main);
        this.chessToBeMoved = null;
        this.controller = main.controller;
        this.offsetInterval = 0;
        blackPast = new Stack<JButton> ();
        whitePast = new Stack<JButton> ();
    }
    public class Table extends JPanel {


        public JButton chessPanel[][];
        public Dimension OUT_FRAME_DIMENSION = new Dimension(600, 600);
        public Table(Main main){
                super(new GridLayout(8, 8));
                setPreferredSize(OUT_FRAME_DIMENSION);
                chessPanel = new JButton[8][8];
                Board board = main.controller.board;
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
                        chessPanel[j][i].addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed (ActionEvent e) {
                                JButton leftTopButton = chessPanel[0][0];
                                offsetInterval = (chessPanel[7][0].getX () - leftTopButton.getX () )/7;
                                JButton currButton = (JButton) e.getSource ();
                                int destX = (currButton.getX() - leftTopButton.getX())/offsetInterval;
                                int destY = (currButton.getY() - leftTopButton.getY())/offsetInterval;
                                //on its second click so want to move the chess
                                if(chessToBeMoved != null){
                                    boolean isValid;
                                    int startX = (chessToBeMoved.getX () - leftTopButton.getX ())/offsetInterval;
                                    int startY = (chessToBeMoved.getY () - leftTopButton.getX ())/offsetInterval;
                                    Pieces currPiece = controller.board.board[startX][startY];
                                    if (controller.board.getTurns() && currPiece.player == 1){
                                        isValid= controller.board.board[startX][startY].isValidMove(destX, destY)&&controller.board.board[startX][startY].validMove(destX, destY,controller.board);
                                        if(isValid){
                                            controller.board.moveChess(startX, startY, destX, destY);
                                            blackEnd = currButton;
                                            blackStart = chessToBeMoved;
                                            blackPast.push (chessToBeMoved);
                                            blackPast.push (currButton);
                                            moveIcon(2, currButton);
                                            if(controller.board.isCheckmate(2)){
                                                JOptionPane.showMessageDialog(controller, "White wins!");
                                                controller.score2++;
                                                controller.mainWindow.guiMenu.setScore(controller.whiteName, controller.blackName, controller.score1,controller.score2);
                                                controller.restartGame();
                                            }
                                            else if(controller.board.isStalemate(2)){
                                                JOptionPane.showMessageDialog(controller, "Draw!");
                                                controller.score1++;
                                                controller.score2++;
                                                controller.mainWindow.guiMenu.setScore(controller.whiteName, controller.blackName, controller.score1,controller.score2);
                                                controller.restartGame();
                                            }
                                        }
                                        else{
                                            JOptionPane.showMessageDialog (controller,"Invalid Move!");
                                            changeButtonColorBack (chessToBeMoved);
                                            chessToBeMoved = null;
                                        }
                                    }
                                    else if (!controller.board.getTurns() && currPiece.player == 2){
                                        isValid= controller.board.board[startX][startY].isValidMove(destX, destY)&&controller.board.board[startX][startY].validMove(destX, destY,controller.board);
                                        if(isValid){
                                            controller.board.moveChess(startX, startY, destX, destY);
                                            whiteEnd = currButton;
                                            whiteStart = chessToBeMoved;
                                            whitePast.push (chessToBeMoved);
                                            whitePast.push (currButton);
                                            moveIcon(1, currButton);
                                            if(controller.board.isCheckmate(1)){

                                                JOptionPane.showMessageDialog(controller, "Black wins!");
                                                controller.score1++;
                                                controller.mainWindow.guiMenu.setScore(controller.whiteName, controller.blackName, controller.score1,controller.score2);
                                                controller.restartGame();
                                            }
                                            else if(controller.board.isStalemate(1)){
                                                JOptionPane.showMessageDialog(controller, "Draw!");
                                                controller.score1++;
                                                controller.score2++;
                                                controller.mainWindow.guiMenu.setScore(controller.whiteName, controller.blackName, controller.score1,controller.score2);
                                                controller.restartGame();
                                            }
                                        }
                                        else{
                                            changeButtonColorBack (chessToBeMoved);
                                            JOptionPane.showMessageDialog (controller,"Invalid Move!");
                                            chessToBeMoved = null;
                                        }
                                    }
                                    else{
                                        changeButtonColorBack (chessToBeMoved);
                                        JOptionPane.showMessageDialog (controller,"Not your turn!");
                                        chessToBeMoved = null;
                                        return;
                                    }
                                }
                                //want to select the first piece
                                else{
                                    if (controller.board.board[destX][destY] == null){
                                        JOptionPane.showMessageDialog (controller, "Select a chess piece, please!");
                                        return;
                                    }
                                    chessToBeMoved = currButton;
                                    if(controller.board.board[destX][destY].player == 1){
                                        chessToBeMoved.setBackground (new Color(153,0,0));
                                    }
                                    else if(controller.board.board[destX][destY].player == 2){
                                        chessToBeMoved.setBackground (new Color(204,102,0));
                                    }

                                }
                            }
                        });
                        this.add(chessPanel[j][i]);
                    }
                }
            }

            /**
             *change the icon if it was a valid move
             * @param player player to check checkmate on
             * @param endButton the grid need to update icon
             */
            public void moveIcon (int player, JButton endButton) {
                if(controller.board.isCheckmate(player)) {
                    return;
                }
                endButton.setIcon(chessToBeMoved.getIcon ());
                chessToBeMoved.setIcon (null);
                changeButtonColorBack (chessToBeMoved);
                chessToBeMoved = null;
                controller.board.switchTurns();

            }

            /**
             * change the selected button back to original color
             * @param button the button that need to set the color back
             */
            public void changeButtonColorBack(JButton button) {
                JButton leftTopButton = chessPanel[0][0];
                int destX = (button.getX() - leftTopButton.getX())/offsetInterval;
                int destY = (button.getY() - leftTopButton.getY())/offsetInterval;
                if((destX+destY)%2==0) {
                    button.setBackground (new Color(51,51,51));
                }
                else{
                    button.setBackground (new Color(204,204,204));
                }
            }

            /**
             * change icon when undo is requested
             */
            public void undo() {
                if (!controller.board.undoValid()){
                    JOptionPane.showMessageDialog (controller,"Invalid Undo!");
                    return;
                }
                blackEnd = blackPast.pop ();
                blackStart = blackPast.pop ();
                whiteEnd = whitePast.pop ();
                whiteStart = whitePast.pop ();
                if(whiteEnd.getIcon ()==null || blackEnd.getIcon ()==null){
                    JOptionPane.showMessageDialog (controller,"Invalid Undo!");
                    return;
                }
                whiteStart.setIcon (whiteEnd.getIcon ());
                blackStart.setIcon (blackEnd.getIcon ());
                whiteEnd.setIcon (null);
                blackEnd.setIcon (null);
            }
    }
}
