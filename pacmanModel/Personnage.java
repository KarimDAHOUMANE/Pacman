package pacmanModel;

import java.util.List;


public abstract class Personnage extends Objects{	
	protected List<Coordinate> position;
	protected Direction direction;
	
	abstract void move();
	
	public List<Coordinate> getPosition() { return this.position; }
	
	public Direction getDirection() { return direction; }
}


