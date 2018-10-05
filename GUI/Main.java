package GUI;
import javax.swing.*;

import controller.*;
public class Main {
    public JFrame window;
    public Controller controller;
    public GUIMenu guiMenu;
    public JSplitPane split;

    public Main() {
        window = new JFrame ("Chess Game");
        controller = new Controller (this, false);
        window.setSize (600,600);
        guiMenu = new GUIMenu(this);
        split = new JSplitPane (JSplitPane.HORIZONTAL_SPLIT);
        split.setTopComponent (guiMenu);
        controller.restartGame ();
        window.add (split);
        window.pack ();
        window.setVisible (true);
        controller.uniqueName();
        guiMenu.setScore (controller.whiteName,controller.blackName, controller.score1, controller.score2);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main (String[] args) {
        new Main ();
    }
}
