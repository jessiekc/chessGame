package GUI;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
public class GUIMenu extends JPanel{
    public JLabel white;
    public JLabel black;
    public GUIMenu(Main main) {
        //score
        white = new JLabel ("USERNAME(White) : 0", JLabel.CENTER);
        black = new JLabel ("USERNAME(Black) : 0", JLabel.CENTER);
        setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));
        setBorder (new EmptyBorder (new Insets (200,50,50,50)));
        add (black);
        add ( white);

        //menu bar
        JMenuBar menubar = new JMenuBar();
        JMenu game = new JMenu ("Menu");

        JMenuItem customGame = new JMenuItem ("Custom Game");
        customGame.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed (ActionEvent e) {
                String whiteName = main.controller.whiteName;
                String blackName = main.controller.blackName;
                int score1 = main.controller.score1;
                int score2 = main.controller.score2;
                main.controller = new Controller(main, true);
                main.controller.whiteName=whiteName;
                main.controller.blackName=blackName;
                main.controller.score1= score1  + 1;
                main.controller.score2= score2  + 1;
                setScore(whiteName, blackName, score1, score2);
                main.controller.restartGame ();
            }
        });

        JMenuItem standardGame = new JMenuItem ("Standard Game");
        standardGame.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed (ActionEvent e) {
                String whiteName = main.controller.whiteName;
                String blackName = main.controller.blackName;
                int score1 = main.controller.score1;
                int score2 = main.controller.score2;
                main.controller = new Controller (main, false);
                main.controller.whiteName=whiteName;
                main.controller.blackName=blackName;
                main.controller.score1= score1 + 1;
                main.controller.score2= score2 + 1;
                setScore(whiteName, blackName, score1, score2);
                main.controller.restartGame ();
            }
        });

        JMenuItem forfeit = new JMenuItem ("Forfeit");
        forfeit.addActionListener (new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                main.controller.forfeit ();
            }
        });

        JMenuItem undo = new JMenuItem ("Undo");
        undo.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed (ActionEvent e) {
                main.controller.undo ();
            }
        });
        game.add (customGame);
        game.add (standardGame);
        game.add (forfeit);
        game.add (undo);
        menubar.add (game);
        main.window.setJMenuBar(menubar);
    }
    public void setScore (String whiteName, String blackName, int blackScore, int whiteScore) {
        white.setText (whiteName + "(white) : " + whiteScore);
        black.setText (blackName + "(black) : " + blackScore);

    }
}
