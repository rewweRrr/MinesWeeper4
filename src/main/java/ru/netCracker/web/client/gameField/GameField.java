package ru.netCracker.web.client.gameField;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import ru.netCracker.web.client.events.*;
import ru.netCracker.web.client.gameField.field.UBField;
import ru.netCracker.web.client.logic.CurrentGameField;
import ru.netCracker.web.shared.Level;

import java.util.ArrayList;

/**
 * Created by rewweRrr on 02.03.2016
 */
public class GameField extends Composite {

    public static final int UNUSED_FIELD = -2;
    public static final int MINE = -1;
    public static final int EXPLOSION = -3;
    public static final int FLAG = 9;

    private int columns = 8; // 8,16,31
    private int rows = 8;   // 8,16,16
    private int minesNum = 10; // 10,40,99
    private int flagNum = 0;
    Grid mainFieldPanel = new Grid();

    public GameField() {
        initWidget(mainFieldPanel);
        initMainField();
        upDateGrid();
    }

    private void initMainField() {
        chooseLevel();
        MinesWeeperEventBus.get().addHandler(PlayBtnEvent.TYPE, new PlayBtnEvent.Handler() {
            @Override
            public void onPlayBtn(PlayBtnEvent event) {

                createFields();
                createStartField();
            }
        });
    }

    private void chooseLevel() {
        MinesWeeperEventBus.get().addHandler(ChangeLevelEvent.TYPE, new ChangeLevelEvent.Handler() {
            @Override
            public void onChangeLevel(ChangeLevelEvent event) {

                int level = Level.getInstance().getLevel();

                switch (level) {
                    case Level.EASY:
                        columns = 8;
                        rows = 8;
                        minesNum = 10;
                        break;

                    case Level.MEDIUM:
                        columns = 16;
                        rows = 16;
                        minesNum = 40;
                        break;

                    case Level.HARD:
                        columns = 16;
                        rows = 31;
                        minesNum = 99;
                        break;

                    default:
                        throw new IllegalArgumentException();
                }
            }
        });
    }

    private void createFields() {
        mainFieldPanel.resize(columns, rows);
        flagNum = 0;
        upDateMinesNum();

        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {

                UBField field = new UBField(i,j); //, UNUSED_FIELD, "/MinesWeeper4_war_exploded/img/20x20_-2.png");
                mainFieldPanel.setWidget(i,j, field);
            }
        }
    }

    private void createStartField() {
        CurrentGameField.getInstance().getField();
        //ReceiverServer.getInstance().setClientInfo();
    }

    private void upDateGrid() {
        MinesWeeperEventBus.get().addHandler(UpDateGridEvent.TYPE, new UpDateGridEvent.Handler() {
            @Override
            public void onUpDateGrid(UpDateGridEvent event) {
                ArrayList<ArrayList<Integer>> fields = event.getField();
                int cols = fields.size();
                int rows = fields.get(0).size();

                flagNum = 0;
                mainFieldPanel.resize(cols, rows);

                for (int i = 0; i < cols; i++) {
                    for (int j = 0; j < rows; j++) {
                        int state = fields.get(i).get(j);
                        if(state == FLAG)
                            flagNum++;
//                        UBField field = new UBField(i,j); //, state, "/MinesWeeper4_war_exploded/img/20x20_" + state + ".png");
//                        mainFieldPanel.setWidget(i,j, field);
                        ((UBField)mainFieldPanel.getWidget(i, j)).setState(state);
                    }
                }
                upDateMinesNum();
            }
        });
    }

    private void upDateMinesNum() {
        UpDateMinesNumEvent upDateMinesNumEvent = new UpDateMinesNumEvent();
        upDateMinesNumEvent.setDeltaMines(minesNum - flagNum);
        MinesWeeperEventBus.get().fireEvent(upDateMinesNumEvent);
    }

}
