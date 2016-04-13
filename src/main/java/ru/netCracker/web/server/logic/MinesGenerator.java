package ru.netCracker.web.server.logic;

import java.util.ArrayList;

/**
 * Created by rewweRrr on 23.03.2016
 */
public class MinesGenerator {
    public void populateMines(int columns, int rows, int currentCol, int currentRow, int minesNum, ArrayList<ArrayList<Integer>> fields) {
        for (int i = 0; i < minesNum; i++) {

            int col, row;
            do {
                col = (int) Math.round(Math.random() * (double) (columns - 1));
                row = (int) Math.round(Math.random() * (double) (rows - 1));
            } while (fields.get(col).get(row) == -1 || col == currentCol && row == currentRow);
            fields.get(col).set(row, -1);
        }
    }
}
