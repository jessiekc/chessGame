package controller;
import main.Board;
import GUI.*;
import javax.swing.*;
public class Controller extends JFrame {
    public Board board;
    public GUIBoard guiBoard;
    public Main mainWindow;
    public int score1;
    public int score2;
    public boolean customMode;
    public String blackName;
    public String whiteName;
    public Controller(Main main, boolean customMode) {
        this.customMode= customMode;
        mainWindow = main;
        score1 = 0;
        score2 = 0;
    }

    /**
     * restart the gmae
     */
    public void restartGame() {
        if(customMode==true){
            this.board = new Board(customMode);
        }
        else {
            this.board = new Board();
        }
        this.guiBoard = new GUIBoard (mainWindow);
        mainWindow.split.setBottomComponent (guiBoard.table);
        mainWindow.window.pack ();
        mainWindow.window.setVisible (true);
    }

    /**
     *forfeit function
     */
    public void forfeit() {
        int answer = JOptionPane.showConfirmDialog (this, "Forfeit?", "Forfeit", JOptionPane.YES_NO_OPTION);
        if(answer == JOptionPane.YES_OPTION) {
            if (board.getTurns ()) {
                score1++;
            } else {
                score2++;
            }
            restartGame ();
            mainWindow.guiMenu.setScore (whiteName, blackName, score1, score2);
        }
    }

    /**
     * let user type in their name
     */
    public void uniqueName() {
        this.blackName = JOptionPane.showInputDialog ("Enter user name for the black player : ");
        this.whiteName = JOptionPane.showInputDialog ("Enter user name for the black player : ");
    }

    /**
     * undo in both gui and board structure
     */
    public void undo() {
        guiBoard.table.undo();
        board.undo ();
    }

}
