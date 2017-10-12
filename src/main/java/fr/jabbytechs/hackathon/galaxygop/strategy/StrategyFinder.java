package fr.jabbytechs.hackathon.galaxygop.strategy;

import fr.jabbytechs.hackathon.galaxygop.model.Command;
import fr.jabbytechs.hackathon.galaxygop.model.Galaxy;

public class StrategyFinder {
	
	private static final DumbStrategy dumbStrategy = new DumbStrategy();
	
	
	public static Command find(Galaxy galaxy) {
		return dumbStrategy.apply(galaxy);
	}
	

}
