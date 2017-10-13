package fr.jabbytechs.hackathon.galaxygop.strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import fr.jabbytechs.hackathon.galaxygop.model.AssaultFleet;
import fr.jabbytechs.hackathon.galaxygop.model.Command;
import fr.jabbytechs.hackathon.galaxygop.model.Fleet;
import fr.jabbytechs.hackathon.galaxygop.model.Galaxy;
import fr.jabbytechs.hackathon.galaxygop.model.Planet;
import fr.jabbytechs.hackathon.galaxygop.model.Terraforming;
import io.vavr.Tuple2;

public class SurvivorStrategy {

	private Map<Integer, Planet> neutralPlanets;
	private Map<Integer, Planet> empirePlanets;
	private Map<Integer, Planet> rebelPlanets;
	private Map<Integer, Fleet> empireFleets;
	private Map<Integer, Fleet> rebelFleets;

	public Command apply(Galaxy galaxy) {

		neutralPlanets = galaxy.getPlanets().stream().filter(p -> p.getOwner() == 0)
				.collect(Collectors.toMap(Planet::getId, Function.identity()));

		empirePlanets = galaxy.getPlanets().stream().filter(p -> p.getOwner() == 1)
				.collect(Collectors.toMap(Planet::getId, Function.identity()));

		rebelPlanets = galaxy.getPlanets().stream().filter(p -> p.getOwner() != 1 && p.getOwner() != 0)
				.collect(Collectors.toMap(Planet::getId, Function.identity()));

		empireFleets = galaxy.getFleets().stream().filter(p -> p.getOwner() == 1)
				.collect(Collectors.toMap(Fleet::getTo, Function.identity()));

		rebelFleets = galaxy.getFleets().stream().filter(p -> p.getOwner() != 1)
				.collect(Collectors.toMap(Fleet::getTo, Function.identity()));


		List<Planet> enemyPlanets = new ArrayList<>();
		enemyPlanets.addAll(neutralPlanets.values());
		enemyPlanets.addAll(rebelPlanets.values());

		List<Tuple2<Planet, List<Planet>>> planetTuples = StrategyHelper.findNearestPlanets(empirePlanets.values(),
				enemyPlanets, 3);

		List<AssaultFleet> assaultFleets = new ArrayList<>();
		List<Terraforming> terraformings = new ArrayList<>();

		for (Tuple2<Planet, List<Planet>> tuple : planetTuples) {

			Planet empire = tuple._1;
			List<Planet> rebels = tuple._2;
			boolean sendFleet = false;
			
			if (empire.getTr() == null || empire.getTr().equals("-1")) {
				// send fleet
				for (Planet rebelPlanet : rebels) {
					if (empire.getUnits() > 3) {
						AssaultFleet assaultFleet = new AssaultFleet(empire, rebelPlanet);
						if (!assaultFleets.contains(assaultFleet)) {
							assaultFleets.add(assaultFleet);
							sendFleet = true;
						}
					}
				}
				// no fleet and can be terraforming
				if (!sendFleet && Arrays.asList("H", "K", "L").contains(empire.getClasse())) {
					terraformings.add(new Terraforming(empire.getId()));
				} 
			}
		}

		return new Command(assaultFleets, terraformings);
	}

}
