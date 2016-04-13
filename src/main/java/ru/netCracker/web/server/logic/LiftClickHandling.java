package ru.netCracker.web.server.logic;

import java.util.ArrayList;

/**
 * Created by rewweRrr on 23.03.2016
 */
public class LiftClickHandling {

    public ArrayList<ArrayList<Integer>> aroundMineNum(int columns, int rows, int currentCol, int currentRow, ArrayList<ArrayList<Integer>> currentState) {
        int state = currentState.get(currentCol).get(currentRow);
        int aroundMines = 0;

        if (state == FieldsState.UNUSED_FIELD) {
            int rowTmp = currentRow;
            int colTmp = currentCol;
            ArrayList<Integer> coordinates = new ArrayList<Integer>();

            rowTmp++;
            if (rowTmp < rows) {
                aroundMines += countAroundMines(colTmp, rowTmp, coordinates, currentState);
            }

            colTmp++;
            if (rowTmp < rows && colTmp < columns) {
                aroundMines += countAroundMines(colTmp, rowTmp, coordinates, currentState);
            }

            rowTmp--;
            if (colTmp < columns) {
                aroundMines += countAroundMines(colTmp, rowTmp, coordinates, currentState);
            }

            rowTmp--;
            if (rowTmp >= 0 && colTmp < columns) {
                aroundMines += countAroundMines(colTmp, rowTmp, coordinates, currentState);
            }

            colTmp--;
            if (rowTmp >= 0) {
                aroundMines += countAroundMines(colTmp, rowTmp, coordinates, currentState);
            }

            colTmp--;
            if (rowTmp >= 0 && colTmp >= 0) {
                aroundMines += countAroundMines(colTmp, rowTmp, coordinates, currentState);
            }

            rowTmp++;
            if (colTmp >= 0) {
                aroundMines += countAroundMines(colTmp, rowTmp, coordinates, currentState);
            }

            rowTmp++;
            if (rowTmp < rows && colTmp >= 0) {
                aroundMines += countAroundMines(colTmp, rowTmp, coordinates, currentState);
            }

            currentState.get(currentCol).set(currentRow, aroundMines);

            if (aroundMines == 0) {
                for (int i = 0; i < coordinates.size(); i = i + 2) {
                    aroundMineNum(columns, rows, coordinates.get(i), coordinates.get(i + 1), currentState);
                }
            }
            coordinates.clear();
        }
        return currentState;
    }

    private int countAroundMines(int col, int row, ArrayList<Integer> coordinates, ArrayList<ArrayList<Integer>> currentState) {
        int aroundMines = 0;

        if (isMine(col, row, currentState))
            aroundMines++;
        coordinates.add(col);
        coordinates.add(row);

        return aroundMines;
    }

    private boolean isMine(int cols, int rows, ArrayList<ArrayList<Integer>> currentState) {
        return currentState.get(cols).get(rows) == FieldsState.MINE;
    }

}
