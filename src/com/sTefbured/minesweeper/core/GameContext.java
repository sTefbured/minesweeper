package com.sTefbured.minesweeper.core;

import org.apache.commons.lang3.tuple.Pair;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class GameContext implements Serializable {
    private static final long serialVersionUID = 8601644419936902758L;

    private static final Map<Difficulty, Pair<Integer, Integer>> DIFFICULTY_TO_FIELD_SIZE_MAP
            = new HashMap<Difficulty, Pair<Integer, Integer>>() {
        {
            put(Difficulty.EASY, Pair.of(9, 9));
            put(Difficulty.MEDIUM, Pair.of(16, 16));
            put(Difficulty.HARD, Pair.of(30, 16));
        }
    };

    private static GameContext instance;

    private Difficulty difficulty = Difficulty.EASY;
    private GameState state;

    private GameContext() {
    }

    public Pair<Integer, Integer> getPlayFieldSize() {
        return DIFFICULTY_TO_FIELD_SIZE_MAP.get(difficulty);
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public static GameContext getInstance() {
        if (instance == null) {
            instance = new GameContext();
        }
        return instance;
    }
}
