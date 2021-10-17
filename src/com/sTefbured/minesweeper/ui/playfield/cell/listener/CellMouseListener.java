package com.sTefbured.minesweeper.ui.playfield.cell.listener;

import com.sTefbured.minesweeper.ui.playfield.cell.Cell;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CellMouseListener implements MouseListener {
    private static final Logger LOGGER = LogManager.getLogger(CellMouseListener.class);

    @Override
    public void mouseReleased(MouseEvent e) {
        Cell lastEntered = Cell.getLastEntered();
        if (e.getButton() == MouseEvent.BUTTON1 && !lastEntered.isFlagged() && !lastEntered.isOpened()) {
            lastEntered.open();
            Cell.setPressingLeftMouse(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Cell sourceCell = (Cell) e.getSource();
        if ((e.getButton() == MouseEvent.BUTTON3) && !sourceCell.isOpened()) {
            sourceCell.setFlagged(!sourceCell.isFlagged());
            LOGGER.info("Cell[{}][{}]: the flag was {}",
                    sourceCell.getRow(), sourceCell.getColumn(), sourceCell.isFlagged() ? "set" : "removed");
            sourceCell.repaint();
        } else if ((e.getButton() == MouseEvent.BUTTON1) && !sourceCell.isOpened() && !sourceCell.isFlagged()) {
            sourceCell.setCurrentLook(Cell.CHECKED_ICON);
            Cell.setPressingLeftMouse(true);
            sourceCell.repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Cell sourceCell = (Cell) e.getSource();
        Cell.setLastEntered(sourceCell);
        if (sourceCell.isOpened()) {
            return;
        }
        if (Cell.isPressingLeftMouse()) {
            sourceCell.setCurrentLook(Cell.CHECKED_ICON);
        } else {
            sourceCell.setCurrentLook(Cell.SELECTED_ICON);
        }
        sourceCell.repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Cell sourceCell = (Cell) e.getSource();
        if (sourceCell.isOpened()) {
            return;
        }
        sourceCell.setCurrentLook(Cell.UNCHECKED_ICON);
        sourceCell.repaint();
    }
}
