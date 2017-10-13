package fr.jabbytechs.hackathon.galaxygop.strategy;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import ch.obermuhlner.math.big.BigDecimalMath;
import fr.jabbytechs.hackathon.galaxygop.model.Planet;
import io.vavr.Tuple;
import io.vavr.Tuple2;

public class StrategyHelper {

	private static final MathContext mathContext = new MathContext(100);

	public static BigDecimal calculateDistance(Planet empire, Planet rebel) {
		return BigDecimalMath.sqrt(
				empire.getX().subtract(rebel.getX()).pow(2).add(empire.getY().subtract(rebel.getY()).pow(2)),
				mathContext);
	}

	public static int calculateTurnLeft(Planet empire, Planet rebel) {
		return calculateDistance(empire, rebel).divide(BigDecimal.valueOf(2L)).intValue();
	}

	public static List<Tuple2<Planet, Planet>> findNearestPlanet(Collection<Planet> empirePlanets,
			Collection<Planet> rebelPlanets) {

		List<Tuple2<Planet, Planet>> tuples = new ArrayList<>();

		for (Planet empirePlanet : empirePlanets) {
			Planet nearest = null;
			BigDecimal nearestDistance = null;
			for (Planet rebelPlanet : rebelPlanets) {
				BigDecimal distance = StrategyHelper.calculateDistance(empirePlanet, rebelPlanet);
				if (nearest != null) {
					if (distance.min(nearestDistance).equals(distance)) {
						nearest = rebelPlanet;
						nearestDistance = distance;
					}
				} else {
					nearest = rebelPlanet;
					nearestDistance = distance;
				}
			}
			tuples.add(Tuple.of(empirePlanet, nearest));
		}
		return tuples;
	}

	public static List<Tuple2<Planet, List<Planet>>> findNearestPlanets(Collection<Planet> empirePlanets,
			List<Planet> enemyPlanets, int numberOfPlanets) {

		List<Tuple2<Planet, List<Planet>>> tuples = new ArrayList<>();

		int planetsNb = Math.min(enemyPlanets.size(), numberOfPlanets);

		for (Planet empirePlanet : empirePlanets) {
			List<Tuple2<BigDecimal, Planet>> distancePlanets = new ArrayList<>();
			List<Planet> nearestPlanets = new ArrayList<>();
			
			for (Planet enemyPlanet : enemyPlanets) {
				 distancePlanets.add(Tuple.of(StrategyHelper.calculateDistance(empirePlanet, enemyPlanet), enemyPlanet));
			}
			
			distancePlanets.sort(new Comparator<Tuple2<BigDecimal,Planet>>() {
				@Override
				public int compare(Tuple2<BigDecimal, Planet> o1, Tuple2<BigDecimal, Planet> o2) {
					return -1 * o2._1.compareTo(o1._1);
				}
			});
			
			for (int i = 0; i < planetsNb; i++) {
				nearestPlanets.add(distancePlanets.get(i)._2);
			}
			tuples.add(Tuple.of(empirePlanet, nearestPlanets));
		}

		return tuples;
	}
}
