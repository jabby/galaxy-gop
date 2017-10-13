package fr.jabbytechs.hackathon.galaxygop.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.jabbytechs.hackathon.galaxygop.model.AssaultFleet;
import fr.jabbytechs.hackathon.galaxygop.model.Command;
import fr.jabbytechs.hackathon.galaxygop.model.Galaxy;
import fr.jabbytechs.hackathon.galaxygop.model.Planet;
import fr.jabbytechs.hackathon.galaxygop.model.Terraforming;
import io.vavr.Tuple2;

public class DumbStrategy {
	
	
	public Command apply(Galaxy galaxy) {
		
		
		List<Planet> empirePlanets = galaxy.getPlanets().stream().filter(p -> p.getOwner() == 1).collect(Collectors.toList());
		List<Planet> rebelPlanets = galaxy.getPlanets().stream().filter(p -> p.getOwner() != 1).collect(Collectors.toList());
		
		List<AssaultFleet> assaultFleets = new ArrayList<>();	
		List<Terraforming> terraformings = new ArrayList<>();
		
		List<Tuple2<Planet, Planet>> planetTuples = StrategyHelper.findNearestPlanet(empirePlanets, rebelPlanets);
		
		for (Tuple2<Planet, Planet> tuple : planetTuples) {
			
			Planet empire = tuple._1;
			Planet rebel = tuple._2;
			
			if (empire.getUnits() > 3) {
				assaultFleets.add(new AssaultFleet(empire, rebel));
			}
		}
				
		return new Command(assaultFleets, terraformings);
	}

	
}
