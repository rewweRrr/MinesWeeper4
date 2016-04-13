package ru.netCracker.web.shared;

/**
 * Created by rewweRrr on 22.03.2016
 */
public class RequestCoordinate {
    private int column;
    private int row;
    private boolean leftClick;

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public boolean isLeftClick() {
        return leftClick;
    }

    public void setLeftClick(boolean leftClick) {
        this.leftClick = leftClick;
    }
}
