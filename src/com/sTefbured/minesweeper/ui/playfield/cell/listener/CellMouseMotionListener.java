package com.sTefbured.minesweeper.ui.playfield.cell.listener;

import com.sTefbured.minesweeper.ui.playfield.cell.Cell;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class CellMouseMotionListener implements MouseMotionListener {
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Cell sourceCell = (Cell) e.getSource();
        if (sourceCell.isOpened()) {
            return;
        }
        sourceCell.setCurrentLook(Cell.SELECTED_ICON);
        sourceCell.repaint();
    }
}
