package ru.netCracker.web.server.logic;

import ru.netCracker.web.shared.Level;

import java.util.ArrayList;

/**
 * Created by rewweRrr on 23.03.2016
 */
public class FieldsState {
    private static FieldsState instance;

    public static final int UNUSED_FIELD = -2;
    public static final int MINE = -1;
    public static final int EXPLOSION = -3;
    public static final int FLAG = 9;
    private int columns;
    private int rows;
    private int minesNum;
    private boolean endGame;
    private boolean firstClick;
    private ArrayList<ArrayList<Integer>> currentState;
    private ArrayList<Integer> flagCoordinate;

    public static FieldsState getInstance() {
        if (instance == null)
            instance = new FieldsState();
        return instance;
    }

    public boolean isEndGame() {
        return endGame;
    }

    public void generateField(int level) {
        endGame = false;
        firstClick = true;
        chooseLevel(level);
        flagCoordinate = new ArrayList<Integer>();
    }

    public ArrayList<ArrayList<Integer>> getCurrentState() {
        if (endGame)
            return currentState;
        return createCurrentUserField();
    }

    public void addClick(int currentColumn, int currentRow, boolean leftClick) {
        if (firstClick) {
            firstClick = false;
            new MinesGenerator().populateMines(columns, rows, currentColumn, currentRow, minesNum, currentState);
        }
        if (leftClick) {
            currentState = new LiftClickHandling().aroundMineNum(columns, rows, currentColumn, currentRow, currentState);
            if (currentState.get(currentColumn).get(currentRow) == MINE) {
                openField(currentColumn, currentRow);
                endGame = true;
            } else {
                if (isWin()) {
                    openField();
                    endGame = true;
                }
            }
        } else {
            flagCoordinate = new CtrlClickHandling().addOrRemoveFlag(currentColumn, currentRow, flagCoordinate);
        }
    }

    private void chooseLevel(int level) {
        Level.getInstance();
        switch (level) {
            case Level.EASY:
                columns = 8;
                rows = 8;
                minesNum = 10;
                addUnusedFields(columns, rows);
                break;

            case Level.MEDIUM:
                columns = 16;
                rows = 16;
                minesNum = 40;
                addUnusedFields(columns, rows);
                break;

            case Level.HARD:
                columns = 16;
                rows = 31;
                minesNum = 99;
                addUnusedFields(columns, rows);
                break;

            default:
                throw new IllegalArgumentException();
        }
    }

    private void addUnusedFields(int columns, int rows) {
        if (currentState != null)
            currentState.clear();
        currentState = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < columns; i++) {
            currentState.add(new ArrayList<Integer>());
            for (int j = 0; j < rows; j++) {
                currentState.get(i).add(UNUSED_FIELD);
            }
        }
    }

    private boolean isWin() {
        int numOfFlags = flagCoordinate.size() / 2;
        boolean win = true;
        for (ArrayList<Integer> aCurrentState : currentState) {
            for (Integer anACurrentState : aCurrentState) {
                if (anACurrentState == UNUSED_FIELD) {
                    win = false;
                }
            }
        }
        if (numOfFlags != minesNum)
            win = false;

        if (win)
            endGame = true;
        return win;
    }

    private void openField() {
        flagCoordinate.clear();
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                currentState = new LiftClickHandling().aroundMineNum(columns, rows, i, j, currentState);
            }
        }
    }

    private void openField(int column, int row) {
        flagCoordinate.clear();
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                currentState = new LiftClickHandling().aroundMineNum(columns, rows, i, j, currentState);
            }
        }
        currentState.get(column).set(row, EXPLOSION);
    }

    private ArrayList<ArrayList<Integer>> addFlags(ArrayList<Integer> flags, ArrayList<ArrayList<Integer>> currentState) {

        ArrayList<ArrayList<Integer>> currentStateWithFlags = new ArrayList<ArrayList<Integer>>();
        currentStateWithFlags.addAll(currentState);

        for (int i = 0; i < flags.size(); i = i + 2) {
            currentStateWithFlags.get(flags.get(i)).set(flags.get(i + 1), FLAG);
        }
        return currentStateWithFlags;
    }

    private ArrayList<ArrayList<Integer>> createCurrentUserField() {
        ArrayList<ArrayList<Integer>> currentUserField = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < currentState.size(); i++) {
            currentUserField.add(new ArrayList<Integer>());
            for (int j = 0; j < currentState.get(i).size(); j++) {
                int state = currentState.get(i).get(j);
                if (state == MINE) {
                    currentUserField.get(i).add(UNUSED_FIELD);
                } else {
                    currentUserField.get(i).add(state);
                }
            }
        }
        return addFlags(flagCoordinate, currentUserField);
    }

}
