package pacmanModel;

import java.awt.Color;
import java.awt.Graphics;


public class Pacgomme extends Objects{
	//Champ(s)
	private PacgommeType type;
	private Game game;
	private Coordinate position;
	
	//Constructeur
	public Pacgomme(Game game, Coordinate position, PacgommeType type){
		this.game = game;
		this.position = position;
		this.type = type;
	}
	
	//Méthode(s)
	public Coordinate getPosition() { return position; }
	
	Game getGame() { return game; }
	
	public Color getColor() { return type.getColor(); }
	
	public int getPoints() { return type.getPoints(); }

	@Override
	protected void draw(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(position.getX() * Scale, position.getY() * Scale, Scale, Scale);
		g.setColor(type.getColor());
		g.fillOval(position.getX() * Scale +8, position.getY() * Scale + 8, 8, 8);
	}
}
