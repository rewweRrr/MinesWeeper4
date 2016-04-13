package ru.netCracker.web.client.toolBar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import ru.netCracker.web.client.events.*;
import ru.netCracker.web.client.logic.GameTimer;
import ru.netCracker.web.shared.Level;

/**
 * Created by rewweRrr on 23.03.2016
 */
public class UBToolBar extends Composite {
    interface UBToolBarUiBinder extends UiBinder<HorizontalPanel, UBToolBar> {
    }

    private static UBToolBarUiBinder ourUiBinder = GWT.create(UBToolBarUiBinder.class);

    public UBToolBar() {
        initWidget(ourUiBinder.createAndBindUi(this));
        level = Level.getInstance();
        timeLabel.setText(" 00:00");
        minesNumberLabel.setText(" 0");
        upDateMinesNumberLabel();
        upDateTimer();
    }

    private Level level;

    @UiField
    HorizontalPanel headPanel;
    @UiField
    Button playBtn;
    @UiField
    RadioButton easyBtn;
    @UiField
    RadioButton mediumBtn;
    @UiField
    RadioButton hardBtn;
    @UiField
    Label timeLabel;
    @UiField
    Label minesNumberLabel;

    @SuppressWarnings("UnusedParameters")
    @UiHandler("easyBtn")
    void onClickEasy(ClickEvent event) {
        level.setLevel(Level.EASY);
        ChangeLevelEvent changeLevelEvent = GWT.create(ChangeLevelEvent.class);
        MinesWeeperEventBus.get().fireEvent(changeLevelEvent);
    }

    @SuppressWarnings("UnusedParameters")
    @UiHandler("mediumBtn")
    void onClickMedium(ClickEvent event) {
        level.setLevel(Level.MEDIUM);
        ChangeLevelEvent changeLevelEvent = new ChangeLevelEvent();
        MinesWeeperEventBus.get().fireEvent(changeLevelEvent);
    }

    @SuppressWarnings("UnusedParameters")
    @UiHandler("hardBtn")
    void onClickHard(ClickEvent event) {
        level.setLevel(Level.HARD);
        MinesWeeperEventBus.get().fireEvent(new ChangeLevelEvent());
    }

    @SuppressWarnings("UnusedParameters")
    @UiHandler("playBtn")
    void onClickPlay(ClickEvent event) {
        GameTimer.getInstance().init();
        timeLabel.setText(" 00:00");
        MinesWeeperEventBus.get().fireEvent(new PlayBtnEvent());
    }

    private void upDateMinesNumberLabel() {
        MinesWeeperEventBus.get().addHandler(UpDateMinesNumEvent.TYPE, new UpDateMinesNumEvent.Handler() {
            @Override
            public void onUpDateMinesNum(UpDateMinesNumEvent event) {
                minesNumberLabel.setText(" " + event.getDeltaMines());
            }
        });

    }

    private void upDateTimer() {
        MinesWeeperEventBus.get().addHandler(ChangeTimerEvent.TYPE, new ChangeTimerEvent.Handler() {
            @Override
            public void onChangeTimer(ChangeTimerEvent event) {
                timeLabel.setText(" " + GameTimer.getInstance().getText());
            }
        });
    }
}