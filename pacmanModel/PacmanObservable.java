package pacmanModel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pacmanModel.CharacterEvent.ChangeType;
import pacmanModel.PacmanState;
import pacmanModel.PacmanState.State;


public class PacmanObservable extends Personnage{
	//Champ(s)
	private Game game;
	private Direction direction;
	private Coordinate current;
	private boolean alive;
	public CharacterEvent pEvent;
	private List<CharacterObserver> observers;
	private PacmanState state;
	@SuppressWarnings("unused")
	private static Map<State, Color> colorMap = new HashMap<PacmanState.State, Color>();
	static {
		colorMap.put(State.NORMAL, Color.YELLOW);
		colorMap.put(State.INVISIBLE, Color.decode("#EEE8AA"));
		colorMap.put(State.SUPERPACMAN, Color.ORANGE);
	}
	
	//Constructeur
	public PacmanObservable(Game game, Coordinate start) {
		observers = new ArrayList<>();
		alive = true;
		this.game = game;
		this.direction = Direction.Right;
		current = start;
		state = new NormalPacmanState(this);
	}
	
	//Méthode(s)
	public void register(CharacterObserver o) { observers.add(o); }
	
	public void unregister(CharacterObserver o) { observers.remove(o); }
	
	private void notifyObserver(List<CharacterEvent> events) {
		for(CharacterObserver pacmanObserver : observers) pacmanObserver.notify(events);
	}
	
	public Direction getDirection() { return direction; }
	
	public void setDirection(Direction direction) { this.direction = direction; }
	
	public Coordinate getCoordinate() { return current; }
	
	public void setCoordinate(Coordinate newCurrent) { this.current = newCurrent; }
	
	Game getGame() { return game; }
	
	public void die() { alive = false; }

	void move() { state.move(); }
	
	public State getState() { return state.getState(); }
	
	void setState(PacmanState state) { this.state = state; }
	
	public void notifyMove(Coordinate c) {
		List<CharacterEvent> events = new ArrayList<>();
		events.add(new CharacterEvent(c, ChangeType.ENTER));
		notifyObserver(events);
	}
		
    public boolean isAlive() {
		if(game.getLives()!=0) return alive;
		else return !alive;
	}
	
	public void draw(Graphics g) {
		g.setColor(colorMap.get(getState()));
		g.fillOval(current.getX() * Scale, current.getY() * Scale, Scale, Scale);
	}	
}
