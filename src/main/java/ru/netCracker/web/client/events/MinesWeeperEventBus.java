package ru.netCracker.web.client.events;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;

/**
 * Created by rewweRrr on 24.02.2016
 */
public class MinesWeeperEventBus {
    private static final EventBus INSTANCE = new SimpleEventBus();

    public static EventBus get(){
        return INSTANCE;
    }
}
