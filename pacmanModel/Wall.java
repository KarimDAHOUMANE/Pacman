package pacmanModel;
import java.awt.Color;
import java.awt.Graphics;

public class Wall extends Objects{
	//Champ(s)
	Maze maze;
	
	//Constructeur
	public Wall(Coordinate c) {
		this.current = c;
		this.type = Type.wall;
	}
	
	@Override
	protected void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(current.getX() * Scale, current.getY() * Scale, Scale, Scale);
	}
}
