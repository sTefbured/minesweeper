package com.sTefbured.minesweeper.ui;

import com.sTefbured.minesweeper.core.GameContext;
import com.sTefbured.minesweeper.ui.menubar.MenuBar;
import com.sTefbured.minesweeper.ui.playfield.PlayField;
import org.apache.commons.lang3.tuple.Triple;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static com.sTefbured.minesweeper.Constants.APPLICATION_TITLE;

public class MainFrame extends JFrame {
    private static final Logger LOGGER = LogManager.getLogger(MainFrame.class);

    private PlayField playField;

    public MainFrame() {
        super(APPLICATION_TITLE);
        setJMenuBar(new MenuBar());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        createPlayField(GameContext.getInstance().getPlayFieldInfo());
        LOGGER.debug("Before adding play field");
        add(playField);
        LOGGER.debug("After adding play field");
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    playField.setInDebugMode(!playField.isInDebugMode());
                    LOGGER.debug("Toggle debug mode: {}", playField.isInDebugMode());
                    repaint();
                }
            }
        });
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void createPlayField(Triple<Integer, Integer, Integer> fieldInfo) {
        createPlayField(fieldInfo.getLeft(), fieldInfo.getMiddle(), fieldInfo.getRight());
    }

    public void createPlayField(int rows, int columns, int mines) {
        if (playField != null) {
            remove(playField);
        }
        playField = new PlayField(rows, columns, mines);
        add(playField);
        pack();
    }
}
