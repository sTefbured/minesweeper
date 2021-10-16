package com.sTefbured.minesweeper.ui.menubar.game;

import com.sTefbured.minesweeper.core.GameContext;

import javax.swing.*;

public class GameMenu extends JMenu {
    public GameMenu() {
        super("Game");
        JMenuItem newGameItem = new JMenuItem("New game");
        JMenuItem restartItem = new JMenuItem("Restart");
        JMenuItem exitItem = new JMenuItem("Exit");
        newGameItem.addActionListener((e) -> GameContext.getInstance().newGame());
        restartItem.addActionListener((e) -> {

        });
        exitItem.addActionListener((e) -> System.exit(0));
        add(newGameItem);
        add(restartItem);
        add(exitItem);
    }
}
