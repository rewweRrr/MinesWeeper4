package ru.netCracker.web.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Created by rewweRrr on 02.03.2016
 */
public class PlayBtnEvent extends GwtEvent<PlayBtnEvent.Handler> {
    public static Type<Handler> TYPE = new Type<Handler>();

    public Type<Handler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(Handler handler) {
        handler.onPlayBtn(this);
    }

    public interface Handler extends EventHandler {
        void onPlayBtn(PlayBtnEvent event);
    }
}
