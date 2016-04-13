package ru.netCracker.web.server.logic;

import java.util.ArrayList;

/**
 * Created by rewweRrr on 23.03.2016
 */
public class CtrlClickHandling {

    public ArrayList<Integer> addOrRemoveFlag(int currentCol, int currentRow, ArrayList<Integer> flagCoordinate) {
        boolean flag = true;
        for (int i = 0; i < flagCoordinate.size(); i = i + 2) {
            if (flagCoordinate.get(i) == currentCol && flagCoordinate.get(i + 1) == currentRow) {
                flagCoordinate.remove(i);
                flagCoordinate.remove(i);
                i = flagCoordinate.size();
                flag = false;
            }
        }
        if (flag) {
            flagCoordinate.add(currentCol);
            flagCoordinate.add(currentRow);
        }
        return flagCoordinate;
    }

}
