package fr.jabbytechs.hackathon.galaxygop.strategy;

import fr.jabbytechs.hackathon.galaxygop.model.Command;
import fr.jabbytechs.hackathon.galaxygop.model.Galaxy;

public class StrategyFinder {
	
	private static final DumbStrategy dumbStrategy = new DumbStrategy();
	private static final SurvivorStrategy survivorStrategy = new SurvivorStrategy();
	
	
	public static Command find(String strategy, Galaxy galaxy) {
		if("survivor".equals(strategy)) {
			return survivorStrategy.apply(galaxy);
		}
		return dumbStrategy.apply(galaxy);
	}
	

}
