package pacmanModel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Maze extends JPanel implements CharacterObserver{
	//Champ(s)
	private Game game;
	private static Objects mazeData[][];
	private static int line = 23;
	private static int column = 15;
	public int [][] maze  = 
	 	{{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
	 	 {-1, 0, 0, 0, 0, 0,-1,-1,-1, 0, 0, 0, 0, 0,-1},
	 	 {-1, 0,-1, 0,-1, 0, 0, 0, 0, 0,-1, 0,-1, 0,-1},
	 	 {-1, 3,-1, 0,-1, 0,-1,-1,-1, 0,-1, 0,-1, 6,-1},
	 	 {-1, 0,-1, 0,-1, 0, 0,-1,-1, 5,-1, 0,-1, 0,-1},
	 	 {-1, 0, 0, 0,-1,-1, 0,-1, 0,-1,-1, 0, 0, 0,-1},
	 	 {-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1,-1},
	 	 { 6, 6,-1, 0,-1, 0,-1, 0,-1, 0,-1,-1, 0,-1, 6},
	 	 {-1,-1,-1, 0, 0, 0,-1, 0,-1, 0, 0, 0, 0,-1,-1},
	 	 { 2, 0, 0, 0,-1, 0,-1,-1,-1, 0,-1, 0, 0, 0,-1},
	 	 {-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1,-1,-1},
	 	 { 6,-1, 0,-1,-1,-1,-1, 0,-1, 0,-1, 0,-1, 6, 6},
	 	 {-1,-1, 0, 0, 0, 0,-1, 0,-1, 0,-1, 0,-1,-1,-1},
	 	 {-1, 0, 0, 0,-1, 0,-1, 0,-1,-1,-1, 0, 0, 6,-1},
	 	 {-1, 0,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6,-1},
	 	 {-1, 0,-1, 0, 0,-1, 0,-1,-1,-1, 0,-1, 0, 6,-1},
	 	 {-1, 0,-1,-1, 0, 0, 0, 0, 0, 0, 0,-1, 0, 6,-1},
	 	 {-1, 0, 0, 0, 0,-1,-1,-1,-1,-1, 0, 0, 0, 0,-1},
	 	 {-1, 6,-1,-1, 0, 0, 0,-1, 0, 0, 0,-1,-1, 0,-1},
	 	 {-1, 0, 0, 0, 0,-1, 0,-1, 0,-1, 4, 0,-1, 0,-1},
	 	 {-1, 0,-1,-1,-1,-1, 0,-1, 0,-1,-1, 0,-1, 0,-1},
	 	 {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1},
	 	 {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 1,-1}};
	
	
	//Constructeur
	public Maze(Game game) {
		this.game = game;
		mazeData = new Objects[line][column];
		this.buildMazeOnPanel();
	}
	
	
	//Méthode(s)
	public int getNumbColumns() { return column; }
	
	public int getNumbLines() { return line; }
	
	public  int getContentMaze(int i, int j) { return this.maze[i][j]; }


	public void setSchemaContent(int i, int j, int val) { maze[i][j] = val; }


	public int[][] getMaze() { return maze; }
	
	public void setMaze(int i,int j,int b) {
		int a = maze[i][j];
		if ((a == 0) || (a == 3) || (a == 4) || (a == 5) || (a == 6)) maze[i][j] = b;
	}
	
	public void buildMazeOnPanel() {
		for (int i = 0; i < line; i++) {
			for (int j = 0; j < column; j++) {
				switch(this.maze[i][j]) {
				case -1:
					mazeData[i][j] = new Wall(new Coordinate(i,j));
					break;
				case 0 :
					mazeData[i][j] = new Pacgomme(game, new Coordinate(i,j), new PacgommeNormal());
					break;
				case 1 :
				case 2 :
					mazeData[i][j] = new Door(new Coordinate(i,j));
					break;
				case 3 :
					mazeData[i][j] = new Pacgomme(game, new Coordinate(i,j), new PacgommeInvisible());
					break;
				case 4 :
					mazeData[i][j] = new Pacgomme(game, new Coordinate(i,j), new PacgommeSuperPacman());
					break;
				case 5 :
					mazeData[i][j] = new Pacgomme(game, new Coordinate(i,j), new PacgommeNewMaze());
					break;
				case 6 :
					mazeData[i][j] = new Blank(new Coordinate(i,j));
					break;
				}	
			}
		}
	}
		
	public void newMaze() {
		setSchemaContent(3,9,-1);
		setSchemaContent(3,13,-1);
		setSchemaContent(7,0,-1);
		setSchemaContent(7,14,-1);
		setSchemaContent(11,14,-1);
		setSchemaContent(14,13,-1);
		setSchemaContent(15,13,-1);
		setSchemaContent(16,13,-1);
		setSchemaContent(18,1,-1);
		setSchemaContent(0,13,2);
		setSchemaContent(5,9,6);
		setSchemaContent(6,1,6);
		setSchemaContent(6,2,6);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.LIGHT_GRAY);
		for (int i = 0; i < line; i++) for (int j = 0; j < column; j++) mazeData[i][j].draw(g);
		game.getPacman().draw(g);
		for (int i = 0; i < game.getGhosts().length; i++) game.getGhost(i).draw(g);
		g.setColor(Color.black);
		g.drawString("Score : " +game.getScore(),1, 420);
		g.drawString("Vie : " +game.getLives(), 300, 420);
	}
	
	@Override
	public void notify(List<CharacterEvent> events) {
		for(int i = 0; i < events.size(); i++) {
			setMaze(game.getPacman().getCoordinate().getX(),game.getPacman().getCoordinate().getY(), 6);
			buildMazeOnPanel();
			repaint();
		}
	}
	
}
