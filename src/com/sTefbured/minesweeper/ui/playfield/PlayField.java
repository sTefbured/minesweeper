package com.sTefbured.minesweeper.ui.playfield;

import com.sTefbured.minesweeper.ui.playfield.cell.Cell;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Random;

public class PlayField extends JPanel {
    private static final Logger LOGGER = LogManager.getLogger(PlayField.class);

    private int minesCount;
    private Cell[][] cells;
    private final int rowsCount;
    private final int columnsCount;
    private int focusedRow;
    private int focusedColumn;
    private boolean isInDebugMode;

    public PlayField(int rows, int columns, int minesCount) {
        requestFocus();
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        rowsCount = rows;
        columnsCount = columns;
        this.minesCount = minesCount;
        setLayout(new GridLayout(rows, columns));
        cells = new Cell[rows][columns];
        LOGGER.info("Started creating and adding cellButtons");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j] = new Cell(this);
                cells[i][j].setFocusable(false);
            }
        }
        setMines(minesCount);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j].setRow(i);
                cells[i][j].setColumn(j);
                add(cells[i][j]);
            }
        }
        setNumbers(cells);
        LOGGER.info("Finished creating and adding cellButtons");
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                repaint();
            }
        });
    }

    private void setMines(int minesCount) {
        for (Cell[] row : cells) {
            for (int j = 0; (j < row.length) && (minesCount > 0); j++, minesCount--) {
                row[j].setValue(-1);
            }
        }
        shuffle(cells);
    }

    private void setNumbers(Cell[][] cellButtons) {
        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < columnsCount; j++) {
                if (cellButtons[i][j].getShownValue() == -1) {
                    continue;
                }
                setNumber(cellButtons, i, j);
            }
        }
    }

    private void setNumber(Cell[][] cells, int row, int col) {
        for (int i = getPreviousRow(row); i <= getNextRow(row) && i < rowsCount; i++) {
            for (int j = getPreviousColumn(col); j <= getNextColumn(col) && j < columnsCount; j++) {
                if ((i == row) && (j == col)) {
                    continue;
                }
                if (cells[i][j].getShownValue() == -1) {
                    cells[row][col].setValue(cells[row][col].getShownValue() + 1);
                }
            }
        }
    }

    private void shuffle(Cell[][] matrix) {
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < minesCount * 100; i++) {
            swap(matrix, random.nextInt(matrix.length), random.nextInt(matrix[0].length),
                    random.nextInt(matrix.length), random.nextInt(matrix[0].length));
        }
    }

    private void swap(Cell[][] matrix, int i1, int j1, int i2, int j2) {
        Cell buffer = matrix[i1][j1];
        matrix[i1][j1] = matrix[i2][j2];
        matrix[i2][j2] = buffer;
    }

    public void cellPressed(int row, int column) {
        openNeighbours(row, column);
    }

    private void openNeighbours(int row, int column) {
        for (int i = getPreviousRow(row); i <= getNextRow(row); i++) {
            for (int j = getPreviousColumn(column); j <= getNextColumn(column); j++) {
                if (!cells[i][j].isOpened() && !cells[i][j].isMined() && !cells[row][column].hasMinedNeighbours()) {
                    cells[i][j].open();
                }
            }
        }
        repaint();
    }

    private int getPreviousRow(int currentRow) {
        if (currentRow > 0) {
            return currentRow - 1;
        }
        return currentRow;
    }

    private int getPreviousColumn(int currentColumn) {
        return getPreviousRow(currentColumn);
    }

    private int getNextRow(int currentRow) {
        if (currentRow < rowsCount - 1) {
            return currentRow + 1;
        }
        return currentRow;
    }

    private int getNextColumn(int currentColumn) {
        if (currentColumn < columnsCount - 1) {
            return currentColumn + 1;
        }
        return currentColumn;
    }

    public boolean isInDebugMode() {
        return isInDebugMode;
    }

    public void setInDebugMode(boolean inDebugMode) {
        isInDebugMode = inDebugMode;
    }
}
