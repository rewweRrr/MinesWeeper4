package ru.netCracker.web.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import java.util.ArrayList;

/**
 * Created by rewweRrr on 14.03.2016
 */
public class UpDateGridEvent extends GwtEvent<UpDateGridEvent.Handler> {

    private ArrayList<ArrayList<Integer>> field;

    public ArrayList<ArrayList<Integer>> getField() {
        return field;
    }

    public void setField(ArrayList<ArrayList<Integer>> field) {
        this.field = field;
    }

    public static Type<Handler> TYPE = new Type<Handler>();

    public Type<Handler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(Handler handler) {
        handler.onUpDateGrid(this);
    }

    public interface Handler extends EventHandler {
        void onUpDateGrid(UpDateGridEvent event);
    }

}
