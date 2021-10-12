package com.sTefbured.minesweeper.ui.menubar;

import com.sTefbured.minesweeper.ui.menubar.difficulty.DifficultyMenu;
import com.sTefbured.minesweeper.ui.menubar.game.GameMenu;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    public MenuBar() {
        add(new GameMenu());
        add(new DifficultyMenu());
        add(new JMenu("Info"));
    }
}
