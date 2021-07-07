package pacmanModel;

import java.awt.Color;
import java.awt.Graphics;


public class Door extends Objects{
	//Champ(s)
	Maze maze;
	
	//Constructeur
	public Door(Coordinate current) {
		this.current = current;
		this.type = Type.door;
	}

	//Méthode(s)
	@Override
	protected void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(current.getX() * Scale, current.getY() * Scale, Scale, Scale);
	}
}
