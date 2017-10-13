package fr.jabbytechs.hackathon.galaxygop.model;

public class AssaultFleet {

	public final int units;
	public final int source;
	public final int target;

	public AssaultFleet(int units, int source, int target) {
		this.units = units;
		this.source = source;
		this.target = target;
	}

	public AssaultFleet(Planet empire, Planet rebelPlanet) {
		int units = Math.max(3, Math.min(empire.getUnits() - 1, rebelPlanet.getUnits() + 1));

		empire.setUnits(empire.getUnits() - units);

		this.units = units;
		this.source = empire.getId();
		this.target = rebelPlanet.getId();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + target;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AssaultFleet other = (AssaultFleet) obj;
		if (target != other.target)
			return false;
		return true;
	}

}
