package fr.jabbytechs.hackathon.galaxygop.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import fr.jabbytechs.hackathon.galaxygop.model.Galaxy;
import fr.jabbytechs.hackathon.galaxygop.strategy.StrategyFinder;

@Path("/galaxy")
public class GalaxyResource {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateGalaxy(String param) {
		
		// just to know what you received in console
		System.out.println(param);
		
		Gson gson = new Gson();
		return gson.toJson(StrategyFinder.find(gson.fromJson(param, Galaxy.class)));
	}	
}
