package com.sTefbured.minesweeper.ui.menubar.game;

import javax.swing.*;

public class GameMenu extends JMenu {
    public GameMenu() {
        super("Game");
        JMenuItem newGameItem = new JMenuItem("New game");
        JMenuItem restartItem = new JMenuItem("Restart");
        JMenuItem exitItem = new JMenuItem("Exit");
        newGameItem.addActionListener((e) -> {

        });
        restartItem.addActionListener((e) -> {

        });
        exitItem.addActionListener((e) -> System.exit(0));
        add(newGameItem);
        add(restartItem);
        add(exitItem);
    }
}
