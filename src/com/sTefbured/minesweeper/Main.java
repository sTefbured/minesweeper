package com.sTefbured.minesweeper;

import com.sTefbured.minesweeper.core.GameContext;
import com.sTefbured.minesweeper.ui.MainFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                LOGGER.info("Start of the application");
                GameContext.getInstance().setMainFrame(new MainFrame());
            } catch (Exception exception) {
                LOGGER.fatal("Uncaught exception", exception);
                System.exit(-1);
            }
        });
    }
}
