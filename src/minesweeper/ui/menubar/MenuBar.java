package minesweeper.ui.menubar;

import minesweeper.ui.menubar.difficulty.DifficultyMenu;
import minesweeper.ui.menubar.game.GameMenu;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    public MenuBar() {
        add(new GameMenu());
        add(new DifficultyMenu());
        add(new JMenu("Info"));
    }
}
