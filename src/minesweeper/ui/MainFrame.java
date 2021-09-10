package minesweeper.ui;

import minesweeper.ui.menubar.MenuBar;

import javax.swing.*;

import static minesweeper.Constants.APPLICATION_TITLE;

public class MainFrame extends JFrame {
    public MainFrame() {
        super(APPLICATION_TITLE);
        setJMenuBar(new MenuBar());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
