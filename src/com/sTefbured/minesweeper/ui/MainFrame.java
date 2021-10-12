package com.sTefbured.minesweeper.ui;

import com.sTefbured.minesweeper.ui.menubar.MenuBar;
import com.sTefbured.minesweeper.ui.playfield.PlayField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static com.sTefbured.minesweeper.Constants.APPLICATION_TITLE;

public class MainFrame extends JFrame {
    private static final Logger LOGGER = LogManager.getLogger(MainFrame.class);

    public MainFrame() {
        super(APPLICATION_TITLE);
        setJMenuBar(new MenuBar());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        PlayField playField = new PlayField(16, 30, 99);
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
}
