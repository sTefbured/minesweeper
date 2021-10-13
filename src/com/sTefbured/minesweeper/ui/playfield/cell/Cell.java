package com.sTefbured.minesweeper.ui.playfield.cell;

import com.sTefbured.minesweeper.ui.playfield.PlayField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Objects;

public class Cell extends JComponent {
    private static final ImageIcon UNCHECKED_ICON = loadIcon("img/cell/unchecked.jpg");
    private static final ImageIcon SELECTED_ICON = loadIcon("img/cell/selected.jpg");
    private static final ImageIcon CHECKED_ICON = loadIcon("img/cell/checked.jpg");
    private static final ImageIcon FLAG_ICON = loadIcon("img/cell/flag.png");
    private static final ImageIcon MINE_ICON = loadIcon("img/cell/mine.png");
    private static final ImageIcon OPENED_MINE_ICON = loadIcon("img/cell/mine_opened.png");

    private final PlayField parentPlayField;
    private int row;
    private int column;

    private ImageIcon currentLook = UNCHECKED_ICON;
    private int actualValue;
    private int shownValue;
    private boolean isOpened;
    private boolean isFlagged;

    public Cell(PlayField parentPlayField) {
        this.parentPlayField = parentPlayField;
        setPreferredSize(new Dimension(32, 32));
        addListeners();
    }

    private void addListeners() {
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (isOpened) {
                    return;
                }
                currentLook = SELECTED_ICON;
                repaint();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && !isFlagged && !isOpened()) {
                    open();
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    isFlagged = !isFlagged;
                    repaint();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (isOpened) {
                    return;
                }
                currentLook = SELECTED_ICON;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (isOpened) {
                    return;
                }
                currentLook = UNCHECKED_ICON;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(currentLook.getImage(), 0, 0, getWidth(), getHeight(), null);
        boolean isInDebugMode = parentPlayField.isInDebugMode();
        if (isMined() && isOpened()) {
            g.drawImage(OPENED_MINE_ICON.getImage(), 0, 0, getWidth(), getHeight(), null);
        } else if (isMined() && isInDebugMode) {
            g.drawImage(MINE_ICON.getImage(), 0, 0, getWidth(), getHeight(), null);
        } else if (isOpened() || parentPlayField.isInDebugMode()) {
            g.drawString(String.valueOf(shownValue), 10, 20);
        } else if (isFlagged) {
            g.drawImage(FLAG_ICON.getImage(), 5, 5, getWidth() - 10, getHeight() - 10, null);
        }
    }

    private static ImageIcon loadIcon(String path) {
        URL imageUrl = Objects.requireNonNull(Cell.class.getClassLoader().getResource(path));
        return new ImageIcon(imageUrl);
    }

    public int getShownValue() {
        return shownValue;
    }

    public void setValue(int value) {
        this.actualValue = value;
        this.shownValue = value;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void open() {
        currentLook = CHECKED_ICON;
        isOpened = true;
        parentPlayField.cellPressed(row, column);
    }

    public boolean isMined() {
        return actualValue == -1;
    }

    public boolean hasMinedNeighbours() {
        return actualValue != 0;
    }
}