package ru.netCracker.web.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 * Created by rewweRrr on 23.03.2016
 */
public class UBMinesWeeper extends Composite {
    interface UBMinesWeeperUiBinder extends UiBinder<FlowPanel, UBMinesWeeper> {
    }

    private static UBMinesWeeperUiBinder ourUiBinder = GWT.create(UBMinesWeeperUiBinder.class);

    public UBMinesWeeper() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
}