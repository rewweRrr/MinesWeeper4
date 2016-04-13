package ru.netCracker.web.client.gameField.field;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import ru.netCracker.web.client.gameField.GameField;
import ru.netCracker.web.client.logic.CurrentGameField;
import ru.netCracker.web.client.logic.GameTimer;

/**
 * Created by rewweRrr on 23.03.2016
 */
public class UBField extends Composite {
    interface UBFieldUiBinder extends UiBinder<FlowPanel, UBField> {
    }

    private static UBFieldUiBinder ourUiBinder = GWT.create(UBFieldUiBinder.class);

    private int currentRow;
    private int currentCol;
    //private int state;

    @UiField
    Label state;

    @UiField
    FlowPanel field;

    public UBField() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    public UBField(int currentCol, int currentRow) {
        this.currentRow = currentRow;
        this.currentCol = currentCol;
        //this.state = state;
        initWidget(ourUiBinder.createAndBindUi(this));
        //this.img.setUrl(url);
    }

    @SuppressWarnings("UnusedParameters")
    @UiHandler("state")
    void onClickImg(ClickEvent event) {
        GameTimer.getInstance().start();
        CurrentGameField.getInstance().updateField(currentCol, currentRow, !event.isControlKeyDown());
    }

    public void setState(int state) {
        //this.state = state;
        if (state <= 8 && state > 0) {
            field.setStyleName("openPanel");
            this.state.setStyleName("text text" + state);
            this.state.setText("" + state);
        } else {
            if (state == GameField.UNUSED_FIELD) {
                field.setStyleName("unusedPanel");
                this.state.setText("");
            } else if (state == GameField.FLAG) {
                field.setStyleName("flag");
                this.state.setText("");
            } else if (state == GameField.MINE) {
                field.setStyleName("mine");
                this.state.setText("");
            } else if (state == GameField.EXPLOSION) {
                field.setStyleName("explosion");
                this.state.setText("");
            } else {
                field.setStyleName("openPanel");
                this.state.setText("");
            }
        }

    }
}