package com.sTefbured.minesweeper.ui.menubar.difficulty;

import javax.swing.*;

public class DifficultyMenu extends JMenu {
    private static final String EASY_LEVEL_NAME = "Easy";
    private static final String MEDIUM_LEVEL_NAME = "Medium";
    private static final String HARD_LEVEL_NAME = "Hard";

    public DifficultyMenu() {
        super("Difficulty");
        ButtonGroup difficultyLevelButtons = new ButtonGroup();
        JRadioButtonMenuItem easyLevelButton = new JRadioButtonMenuItem(EASY_LEVEL_NAME, true);
        JRadioButtonMenuItem mediumLevelButton = new JRadioButtonMenuItem(MEDIUM_LEVEL_NAME);
        JRadioButtonMenuItem hardLevelButton = new JRadioButtonMenuItem(HARD_LEVEL_NAME);

        difficultyLevelButtons.add(easyLevelButton);
        difficultyLevelButtons.add(mediumLevelButton);
        difficultyLevelButtons.add(hardLevelButton);
        add(easyLevelButton);
        add(mediumLevelButton);
        add(hardLevelButton);
    }
}
