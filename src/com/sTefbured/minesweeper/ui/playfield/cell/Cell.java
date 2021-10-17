package com.sTefbured.minesweeper.ui.playfield.cell;

import com.sTefbured.minesweeper.ui.playfield.PlayField;
import com.sTefbured.minesweeper.ui.playfield.cell.listener.CellMouseListener;
import com.sTefbured.minesweeper.ui.playfield.cell.listener.CellMouseMotionListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Cell extends JComponent {
    private static final Logger LOGGER = LogManager.getLogger(Cell.class);

    public static final ImageIcon UNCHECKED_ICON = loadIcon("img/cell/unchecked.jpg");
    public static final ImageIcon SELECTED_ICON = loadIcon("img/cell/selected.jpg");
    public static final ImageIcon CHECKED_ICON = loadIcon("img/cell/checked.jpg");
    public static final ImageIcon FLAG_ICON = loadIcon("img/cell/flag.png");
    public static final ImageIcon MINE_ICON = loadIcon("img/cell/mine.png");
    public static final ImageIcon OPENED_MINE_ICON = loadIcon("img/cell/mine_opened.png");
    public static final Map<Integer, ImageIcon> VALUE_TO_ICON = Collections.unmodifiableMap(new HashMap<Integer, ImageIcon>() {
        {
            put(1, loadIcon("img/cell/opened1.png"));
            put(2, loadIcon("img/cell/opened2.png"));
            put(3, loadIcon("img/cell/opened3.png"));
            put(4, loadIcon("img/cell/opened4.png"));
            put(5, loadIcon("img/cell/opened5.png"));
            put(6, loadIcon("img/cell/opened6.png"));
            put(7, loadIcon("img/cell/opened7.png"));
            put(8, loadIcon("img/cell/opened8.png"));
        }
    });

    private static Cell lastEntered;
    private static boolean isPressingLeftMouse;

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
        addMouseMotionListener(new CellMouseMotionListener());
        addMouseListener(new CellMouseListener());
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
        } else if ((isOpened() || parentPlayField.isInDebugMode()) && actualValue != 0) {
            g.drawImage(VALUE_TO_ICON.get(shownValue).getImage(), 0, 0, getWidth(), getHeight(), null);
        } else if (isFlagged()) {
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

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean isFlagged) {
        this.isFlagged = isFlagged;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void open() {
        if (!isFlagged) {
            currentLook = CHECKED_ICON;
            isOpened = true;
            parentPlayField.cellPressed(row, column);
        }
    }

    public void setCurrentLook(ImageIcon currentLook) {
        this.currentLook = currentLook;
    }

    public boolean isMined() {
        return actualValue == -1;
    }

    public boolean hasMinedNeighbours() {
        return actualValue != 0;
    }

    public static Cell getLastEntered() {
        return lastEntered;
    }

    public static void setLastEntered(Cell lastEntered) {
        Cell.lastEntered = lastEntered;
    }

    public static boolean isPressingLeftMouse() {
        return isPressingLeftMouse;
    }

    public static void setPressingLeftMouse(boolean isPressingLeftMouse) {
        Cell.isPressingLeftMouse = isPressingLeftMouse;
    }
}
