package pacmanModel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import pacmanModel.PacmanState.State;


public class Game{
	//Champ(s)
	private Maze maze;
	private PacmanObservable pacman;
	private List<Pacgomme> pacgommes;
	private GhostObservable[] ghosts = new GhostObservable[4]; 
	
	private static final int SUPER_PACMAN_PERIOD = 4000;
	private static final int INVISIBLE_PACMAN_PERIOD = 4000;
	
	private int superPacmanTimer;
	private int invisiblePacmanTimer;
	private int lives;
	private int score;
	private int compteurPoints;
	
	public int width;
	public int height;

	
	//Constructeur
	public Game (int width, int height, Coordinate startP, Coordinate startG) {
		this.width = width;
		this.height = height;
		maze = new Maze(this);
		pacgommes = new ArrayList<>();
		pacman = new PacmanObservable(this, startP);
		for (int i = 0; i < ghosts.length; i++) ghosts[i] = new GhostObservable(this, startG);
		for (int i = 0; i < maze.getNumbLines(); i++) {
			for (int j = 0; j < maze.getNumbColumns(); j++) {
				PacgommeType type=this.typeOfPacgomme(maze.getMaze()[i][j]);
				if(type !=null) pacgommes.add(new Pacgomme(this,new Coordinate(i,j), type));
			}
		}
		score = 0;
		lives = 3;
		superPacmanTimer = SUPER_PACMAN_PERIOD;
		invisiblePacmanTimer = INVISIBLE_PACMAN_PERIOD;
	}
	
	
	//Méthode(s)
	public PacgommeType typeOfPacgomme(int i) {
		PacgommeType type=null;
		switch(i) {
			case 0 :
				type = new PacgommeNormal();
				break;
			case 3 :
				type = new PacgommeInvisible();
				break;
			case 4 :
				type = new PacgommeSuperPacman();
				break;
			case 5 : 
				type = new PacgommeNewMaze();
				break;
			default :
				break;
		}
		return type;
	}

	public int getWidth() {	return width; }
	
	public int getHeight() { return height; }
	
	public PacmanObservable getPacman() { return pacman; }
	
	public Maze getMaze() { return maze; }
	
	public List<Pacgomme> getPacgommes() { return pacgommes; }
	
	public GhostObservable getGhost(int i) { return ghosts[i]; }
	
	public GhostObservable[] getGhosts() { return ghosts; }

	public void setGhosts (GhostObservable[] ghosts) { this.ghosts = ghosts; }
	
	public int getLives() { return lives; }
	
	public int newLife() { return lives++; }
	
	public int getScore() { return score; }

	public void addToScore(int points) {
		this.score += points;
		for(int i=1;i<=points;i++) {
			this.compteurPoints++;
			if(compteurPoints==5000) {
				newLife();
				this.compteurPoints=0;
			}
		}
	}
	
	public void collision() {
		for (int i = 0; i < ghosts.length; i++) { 
			if ((pacman.getCoordinate().equals(ghosts[i].getCoordinate()))
			 || ((pacman.getCoordinate().getX() == ghosts[i].getCoordinate().getX() + ghosts[i].getDirection().getX()) && (pacman.getCoordinate().getY() == ghosts[i].getCoordinate().getY() + ghosts[i].getDirection().getY()))
			 || ((pacman.getCoordinate().getX() + pacman.getDirection().getX()== ghosts[i].getCoordinate().getX()) && (pacman.getCoordinate().getY() + pacman.getDirection().getY()== ghosts[i].getCoordinate().getY()))) {
				if (pacman.getState() == State.SUPERPACMAN) {
					ghosts[i].setCoordinate(new Coordinate(8,7));
				} else if (pacman.getState() == State.NORMAL) {
					lives--;
					try {
				        Thread.sleep(1000);
					} catch (InterruptedException e) {
				        e.printStackTrace();
					}
					pacman.setCoordinate(new Coordinate(13,13));
				}
			}
		}
	}
	
	public void step() {
		pacman.move();
		for (GhostObservable ghost : ghosts) ghost.move();
		collision();
		for (int i=1;i<getPacgommes().size();i++) {
			Color color = getPacgommes().get(i).getColor();
			setColor(color, i);
			superPacmanEffect();
			invisiblePacmanEffect();
		}
	}

	private void setColor(Color color, int i) {
		if (pacman.getCoordinate().equals(getPacgommes().get(i).getPosition())) {
			if (color == Color.magenta) pacgommeInvisiblePacmanEffect();
			if (color == Color.green) getMaze().newMaze();
			if (color == Color.orange) pacgommeSuperPacmanEffect();
			addToScore(getPacgommes().get(i).getPoints());
			getPacgommes().remove(i);
		}
	}

	private void pacgommeInvisiblePacmanEffect() {
		pacman.setState(new InvisiblePacmanState(pacman));
		invisiblePacmanTimer = INVISIBLE_PACMAN_PERIOD;
	}

	private void pacgommeSuperPacmanEffect() {
		pacman.setState(new SuperPacmanState(pacman));
		for (int j = 0; j < ghosts.length; j++) ghosts[j].setState(new VulnerableGhostState(ghosts[j]));
		superPacmanTimer = SUPER_PACMAN_PERIOD;
	}

	private void superPacmanEffect() {
		if (pacman.getState() == State.SUPERPACMAN) {
			if (superPacmanTimer > 0) superPacmanTimer--;
			else {
				for (int j = 0; j < ghosts.length; j++)	ghosts[j].setState(new DangerousGhostState(ghosts[j]));
				pacman.setState(new NormalPacmanState(pacman));
			}
		}
	}

	private void invisiblePacmanEffect() {
		if (pacman.getState() == State.INVISIBLE) {
			if (invisiblePacmanTimer > 0) invisiblePacmanTimer--; 
			else pacman.setState(new NormalPacmanState(pacman));
		}
	}
	
}
