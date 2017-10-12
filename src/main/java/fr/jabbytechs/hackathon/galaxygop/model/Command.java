package fr.jabbytechs.hackathon.galaxygop.model;

import java.util.List;

public class Command {

	private List<AssaultFleet> fleets;
	private List<Terraforming> terraformings;
	
	public Command(List<AssaultFleet> fleets, List<Terraforming> terraformings) {
		this.fleets = fleets;
		this.terraformings = terraformings;
	}

	public List<AssaultFleet> getFleets() {
		return fleets;
	}

	public void setFleets(List<AssaultFleet> fleets) {
		this.fleets = fleets;
	}

	public List<Terraforming> getTerraformings() {
		return terraformings;
	}

	public void setTerraformings(List<Terraforming> terraformings) {
		this.terraformings = terraformings;
	}

}
