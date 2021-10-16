package com.sTefbured.minesweeper.core;

import com.sTefbured.minesweeper.core.enums.Difficulty;
import com.sTefbured.minesweeper.core.enums.GameState;
import com.sTefbured.minesweeper.ui.MainFrame;
import org.apache.commons.lang3.tuple.Triple;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class GameContext implements Serializable {
    private static final long serialVersionUID = 8601644419936902758L;

    private static final Map<Difficulty, Triple<Integer, Integer, Integer>> DIFFICULTY_TO_FIELD_INFO_MAP
            = new HashMap<Difficulty, Triple<Integer, Integer, Integer>>() {
        {
            put(Difficulty.EASY, Triple.of(9, 9, 10));
            put(Difficulty.MEDIUM, Triple.of(16, 16, 40));
            put(Difficulty.HARD, Triple.of(16, 30, 99));
        }
    };

    private static GameContext instance;

    private MainFrame mainFrame;
    private Difficulty difficulty = Difficulty.EASY;
    private GameState state = GameState.BEFORE_START;

    private GameContext() {
    }

    public void newGame() {
        state = GameState.BEFORE_START;
        mainFrame.createPlayField(getPlayFieldInfo());
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public Triple<Integer, Integer, Integer> getPlayFieldInfo() {
        return DIFFICULTY_TO_FIELD_INFO_MAP.get(difficulty);
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        newGame();
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public static GameContext getInstance() {
        if (instance == null) {
            instance = new GameContext();
        }
        return instance;
    }
}
