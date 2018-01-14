package services.configuration;

import org.json.JSONObject;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static org.apache.myfaces.shared.util.RequestType.EVENT;

/**
 * Created by Matthieu on 14/01/2018.
 */

@Path("/config")
public class WebConfigurationService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response process(String input) {
        JSONObject obj = new JSONObject(input);
        try {
            int simulationLength = obj.getInt("simulationLength");
            int simulationStart = obj.getInt("simulationStart");
            boolean HOVLanes = obj.getBoolean("HOVLanes");
            boolean migrationPendulaire = obj.getBoolean("migrationPendulaire");
            SimulationWebConfiguration config = new SimulationWebConfiguration();
            config.setHOVLanes(HOVLanes);
            config.setMigrationPendulaire(migrationPendulaire);
            config.setSimulationLenght(simulationLength);
            config.setSimulationStart(simulationStart);
            return Response.ok().entity(config.toString()).build();

        }catch(Exception e) {
            JSONObject error = new JSONObject().put("error", e.toString());
            return Response.status(400).entity(error.toString()).build();
        }
    }

}
