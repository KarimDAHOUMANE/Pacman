package pacmanModel;

import java.awt.Color;
import java.awt.Graphics;


public class Blank extends Objects{
	//Champ(s)
	Maze maze;
	
	//Constructeur
	public Blank(Coordinate current) {
		this.current = current;
		this.type = Type.blank;
	}

	//Méthode(s)
	@Override
	protected void draw(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(current.getX() * Scale, current.getY() * Scale, Scale, Scale);
	}
}
