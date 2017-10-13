package fr.jabbytechs.hackathon.galaxygop.model;

public class Fleet {
	private int owner;
	private int units;
	private int from;
	private int to;
	private int turn;
	private int left;
	
	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	@Override
	public String toString() {
		return "Fleet [owner=" + owner + ", units=" + units + ", from=" + from + ", to=" + to + ", turn=" + turn
				+ ", left=" + left + "]";
	}
	
	
}
