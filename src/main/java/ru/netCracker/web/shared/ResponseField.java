package ru.netCracker.web.shared;

import java.util.List;

/**
 * Created by rewweRrr on 22.03.2016
 */
public class ResponseField {
    private List<List<Integer>> field;
    private boolean endGame;

    public boolean isEndGame() {
        return endGame;
    }

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

    public List<List<Integer>> getField() {
        return field;
    }

    public void setField(List<List<Integer>> field) {
        this.field = field;
    }
}
