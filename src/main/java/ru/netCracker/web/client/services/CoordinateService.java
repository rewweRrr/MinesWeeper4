package ru.netCracker.web.client.services;

import com.google.gwt.core.client.GWT;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import ru.netCracker.web.shared.RequestCoordinate;
import ru.netCracker.web.shared.RequestLevel;
import ru.netCracker.web.shared.ResponseField;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by rewweRrr on 22.03.2016
 */
@Path("/CoordinateService")
public interface CoordinateService extends RestService {

    class Instance {
        private static CoordinateService instance;

        public static CoordinateService getInstance() {
            if (instance == null)
                instance = GWT.create(CoordinateService.class);
            return instance;
        }
    }

    @POST
    @Path("/getStartField")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    void getStartField(RequestLevel level, MethodCallback<ResponseField> callback);

    @POST
    @Path("/getUpdatedField")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    void getUpdatedField(RequestCoordinate coordinate, MethodCallback<ResponseField> callback);
}
