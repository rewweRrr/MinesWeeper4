package ru.netCracker.web.client.logic;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Timer;
import ru.netCracker.web.client.events.ChangeTimerEvent;
import ru.netCracker.web.client.events.MinesWeeperEventBus;

/**
 * Created by rewweRrr on 15.03.2016
 */
public class GameTimer {
    private int ticks = 0;
    private boolean is_started = false;

    private static GameTimer instance;

    public static GameTimer getInstance() {
        if(instance == null) {
            instance = new GameTimer();
        }
        return instance;
    }

    private Timer timer = new Timer() {
        @Override
        public void run() {
            ticks++;
            update();
        }
    };

    public void start() {
        if (!is_started) {
            is_started = true;
            timer.scheduleRepeating(1000);
        }
    }

    private void stop() {
        is_started = false;
        timer.cancel();
    }

    public void init() {
        stop();
        ticks = 0;

    }

    public String getText() {
        int mins = (int) Math.floor((double) ticks / 60);
        int secs = ticks % 60;

        NumberFormat fmt = NumberFormat.getFormat("00");
        return fmt.format(mins) + ":" + fmt.format(secs);
    }

    private void update() {
        MinesWeeperEventBus.get().fireEvent(new ChangeTimerEvent());
    }
}
