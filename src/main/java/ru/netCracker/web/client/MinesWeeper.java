package ru.netCracker.web.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Created by rewweRrr on 23.03.2016
 */
public class MinesWeeper implements EntryPoint {
    public void onModuleLoad() {
        RootPanel.get().add(new UBMinesWeeper());
    }
}
