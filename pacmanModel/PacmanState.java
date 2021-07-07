package pacmanModel;


public abstract class PacmanState {
	public static enum State {NORMAL, SUPERPACMAN, INVISIBLE};

	protected PacmanObservable pacman;

	protected PacmanState(PacmanObservable pacman) { this.pacman = pacman; }
		
	abstract State getState();

	public void move() {
		Coordinate current = pacman.getCoordinate();
		Coordinate  next = new Coordinate(current.getX() + pacman.getDirection().getX(),current.getY() + pacman.getDirection().getY());
		switch(pacman.getGame().getMaze().getContentMaze(next.getX(), next.getY())){
		case 0 :
		case 3 :
		case 4 :
		case 5 :
		case 6 :
			pacman.setCoordinate(next);
			break;
		case 1 :
			next = new Coordinate(9,0);
			pacman.setCoordinate(next);
			pacman.setDirection(Direction.Down);
			break;
		case 2 :
			next = new Coordinate(22,13);
			pacman.setCoordinate(next);
			pacman.setDirection(Direction.Left);
			break;
		}
		pacman.notifyMove(next);
	}
}
