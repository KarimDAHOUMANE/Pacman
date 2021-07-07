package pacmanModel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import pacmanModel.CharacterEvent.ChangeType;
import pacmanModel.GhostState.State2;


public class GhostObservable extends Personnage{
	//Champ(s)
	private Game game;
	private Coordinate current;
	private List <CharacterObserver>observers;
	private Direction direction;
	private GhostState state;
	@SuppressWarnings("unused")
	private static Map<State2, Color> colorMap = new HashMap<GhostState.State2, Color>();
	static {
		colorMap.put(State2.DANGEROUS, Color.pink);
		colorMap.put(State2.VULNERABLE, Color.blue);
	}
	
	//Constructeur
	public GhostObservable(Game game, Coordinate start) {
		this.game = game;
		current = start;
		observers = new ArrayList<>();
		this.direction = Direction.Left;
		state = new DangerousGhostState(this);
	}
	
	
	//Méthode(s)
	private void notifyObserver(List<CharacterEvent> events) {
		for(CharacterObserver ghostObserver : observers) ghostObserver.notify(events);
	}

	public void register(CharacterObserver o) { observers.add(o); }
	
	public void unregister(CharacterObserver o) { observers.remove(o); }
	
	public Direction getDirection() { return direction; }

	public void setDirection(Direction direction) { this.direction = direction; }
	
	public Coordinate getCoordinate() { return current; }
	
	public void setCoordinate(Coordinate coordinate) { this.current = coordinate; }
	
	Game getGame() { return game; }

	void move() { state.move(); }
	
	public State2 getState() { return state.getState(); }
	
	public void setState(GhostState state) { this.state = state; }
	
	public void notifyMove(Coordinate c) {
		List<CharacterEvent> events = new ArrayList<>();
		events.add(new CharacterEvent(c, ChangeType.ENTER));
		notifyObserver(events);
	}
	
	public boolean isWall() {
		Coordinate  next = new Coordinate(current.getX() + direction.getX(),current.getY() + direction.getY());
		if (game.getMaze().getContentMaze(next.getX(), next.getY()) != -1) return false;
		else return true;
	}
	
	public void diriger() {
		Random r = new Random();
		int nombreAleatoire = r.nextInt(4);
		switch (nombreAleatoire) {
		case 0:
			setDirection(Direction.Up);
			break;
		case 1:
			setDirection(Direction.Down);
			break;
		case 2:
			setDirection(Direction.Left);
			break;
		case 3:
			setDirection(Direction.Right);
			break;
		}
	}
	
	@Override
	protected void draw(Graphics g) {
		g.setColor(colorMap.get(getState()));
		g.fillOval(current.getX() * Scale, current.getY() * Scale, Scale, Scale);
	
	}
}
