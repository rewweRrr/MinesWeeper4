package ru.netCracker.web.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Created by rewweRrr on 15.03.2016
 */
public class UpDateMinesNumEvent extends GwtEvent<UpDateMinesNumEvent.Handler> {
    private int deltaMines;

    public int getDeltaMines() {
        return deltaMines;
    }

    public void setDeltaMines(int deltaMines) {
        this.deltaMines = deltaMines;
    }

    public static Type<Handler> TYPE = new Type<Handler>();

    public Type<Handler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(Handler handler) {
        handler.onUpDateMinesNum(this);
    }

    public interface Handler extends EventHandler {
        void onUpDateMinesNum(UpDateMinesNumEvent event);
    }
}
