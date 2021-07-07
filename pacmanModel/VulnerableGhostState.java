package pacmanModel;


public class VulnerableGhostState extends GhostState{
	//Champ(s)
	private boolean motion = true;
	
	//Constructeur
	protected VulnerableGhostState(GhostObservable ghost) { super(ghost); }

	//Méthode(s)
	public void setMotion() {
		if (motion) motion= false;
		else motion = true;
	}
	//@Override
	public void move() {
		setMotion();
		if (motion) {
		Coordinate current = ghost.getCoordinate();
		Coordinate  next = new Coordinate(current.getX() + ghost.getDirection().getX(),current.getY() + ghost.getDirection().getY());
		if(!ghost.isWall()) {
			ghost.setCoordinate(next);
			switch (ghost.getGame().getMaze().getContentMaze(next.getX(), next.getY())){
			case 1:
				next = new Coordinate(9,0);
				ghost.setCoordinate(next);
				ghost.setDirection(Direction.Down);
				break;
			case 2:
				next = new Coordinate(22,13);
				ghost.setCoordinate(next);
				ghost.setDirection(Direction.Left);
				break;
			}
		}
		else {
			ghost.diriger();
		}
		ghost.notifyMove(next);
		}
	}
	
	@Override
	State2 getState() { return State2.VULNERABLE; }

}
