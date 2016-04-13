package ru.netCracker.web.server.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.netCracker.web.server.logic.FieldsState;
import ru.netCracker.web.shared.Converter;
import ru.netCracker.web.shared.RequestCoordinate;
import ru.netCracker.web.shared.RequestLevel;
import ru.netCracker.web.shared.ResponseField;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by rewweRrr on 22.03.2016
 */
@RestController
public class SrvController {

    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/getStartField", method = RequestMethod.POST)
    public ResponseField getStartField(@RequestBody RequestLevel level) {
        ResponseField field = new ResponseField();
        FieldsState state = FieldsState.getInstance();

        state.generateField(level.getLevel());

        Converter converter = new Converter();
        field.setField(converter.arrayListToList(state.getCurrentState()));
        return field;
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/getUpdatedField", method = RequestMethod.POST)
    public ResponseField getUpdatedField(@RequestBody RequestCoordinate coordinate) {
        ResponseField field = new ResponseField();
        FieldsState state = FieldsState.getInstance();

        state.addClick(coordinate.getColumn(), coordinate.getRow(), coordinate.isLeftClick());

        Converter converter = new Converter();
        field.setField(converter.arrayListToList(state.getCurrentState()));
        field.setEndGame(state.isEndGame());
        return field;
    }
}
