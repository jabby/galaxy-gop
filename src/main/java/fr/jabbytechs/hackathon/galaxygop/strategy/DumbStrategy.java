package fr.jabbytechs.hackathon.galaxygop.strategy;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.jabbytechs.hackathon.galaxygop.model.AssaultFleet;
import fr.jabbytechs.hackathon.galaxygop.model.Command;
import fr.jabbytechs.hackathon.galaxygop.model.Galaxy;
import fr.jabbytechs.hackathon.galaxygop.model.Planet;
import fr.jabbytechs.hackathon.galaxygop.model.Terraforming;
import io.vavr.Tuple;
import io.vavr.Tuple2;

public class DumbStrategy {
	
	
	public Command apply(Galaxy galaxy) {
		
		
		List<Planet> empirePlanets = galaxy.getPlanets().stream().filter(p -> p.getOwner() == 1).collect(Collectors.toList());
		List<Planet> rebelPlanets = galaxy.getPlanets().stream().filter(p -> p.getOwner() != 1).collect(Collectors.toList());
		
		List<AssaultFleet> assaultFleets = new ArrayList<>();	
		List<Terraforming> terraformings = new ArrayList<>();
		
		List<Tuple2<Planet, Planet>> planetTuples = findNearPlanets(empirePlanets, rebelPlanets);
		
		for (Tuple2<Planet, Planet> tuple : planetTuples) {
			
			Planet empire = tuple._1;
			Planet rebel = tuple._2;
			
			if (empire.getUnits() > 3) {
				assaultFleets.add(new AssaultFleet(Math.max(3, Math.min(empire.getUnits() - 1, rebel.getUnits() + 1)), empire.getId(), rebel.getId()));
			}
		}
				
		return new Command(assaultFleets, terraformings);
	}

	private List<Tuple2<Planet, Planet>> findNearPlanets(List<Planet> empirePlanets, List<Planet> rebelPlanets) {
		
		List<Tuple2<Planet, Planet>> tuples = new ArrayList<>();
		
		for (Planet empirePlanet : empirePlanets) {
			Planet nearest = null;
			BigDecimal nearestDistance = null;
			for (Planet rebelPlanet : rebelPlanets) {
				if (nearest != null) {
					BigDecimal distance = calculateDistance(empirePlanet, rebelPlanet);
					if (distance.min(nearestDistance).equals(distance)) {
						nearest = rebelPlanet;
						nearestDistance = calculateDistance(empirePlanet, rebelPlanet);	
					}
				} else {
					nearest = rebelPlanet;
					nearestDistance = calculateDistance(empirePlanet, rebelPlanet);
				}
			}
			tuples.add(Tuple.of(empirePlanet, nearest));	
		}
		return tuples;
	}

	
	private BigDecimal calculateDistance(Planet empire, Planet rebel) {
		return empire.getX().subtract(rebel.getX()).pow(2)
				.add(empire.getY().subtract(rebel.getY()).pow(2));
	}	
}
