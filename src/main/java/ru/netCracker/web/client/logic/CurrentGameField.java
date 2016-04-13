package ru.netCracker.web.client.logic;

import com.google.gwt.user.client.Window;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.netCracker.web.client.events.MinesWeeperEventBus;
import ru.netCracker.web.client.events.UpDateGridEvent;
import ru.netCracker.web.client.services.CoordinateService;
import ru.netCracker.web.shared.*;

import java.util.List;

/**
 * Created by rewweRrr on 23.03.2016
 */
public class CurrentGameField {
    private static CurrentGameField instance;

    public static CurrentGameField getInstance() {
        if (instance == null)
            instance = new CurrentGameField();
        return instance;
    }

    public void getField() {
        CoordinateService.Instance.getInstance().getStartField(new RequestLevel(Level.getInstance().getLevel()), new MethodCallback<ResponseField>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                Window.alert("Failed getStartField");
            }

            @Override
            public void onSuccess(Method method, ResponseField responseField) {
                upDateGameField(responseField.getField());
            }
        });
    }

    public void updateField(int column, int row, boolean leftClick) {
        RequestCoordinate coordinate = new RequestCoordinate();
        coordinate.setColumn(column);
        coordinate.setRow(row);
        coordinate.setLeftClick(leftClick);

        CoordinateService.Instance.getInstance().getUpdatedField(coordinate, new MethodCallback<ResponseField>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                Window.alert("Failed getUpdatedField");
            }

            @Override
            public void onSuccess(Method method, ResponseField responseField) {
                if (responseField.isEndGame()) {
                    GameTimer.getInstance().init();
                }

                upDateGameField(responseField.getField());
            }
        });
    }

    private void upDateGameField(List<List<Integer>> field) {
        Converter converter = new Converter();
        UpDateGridEvent event = new UpDateGridEvent();

        event.setField(converter.listToArrayList(field));
        MinesWeeperEventBus.get().fireEvent(event);
    }
}
