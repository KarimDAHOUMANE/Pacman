package pacmanModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class MyListener extends KeyAdapter{
	//Champs(s)
	Game game;
	
	//Constructeur
	public MyListener(Game game) { this.game=game; }
	
	//Méthode(s)
	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		switch(e.getKeyCode()) {
			case KeyEvent.VK_UP :
				if (game.getMaze().getContentMaze(game.getPacman().getCoordinate().getX(),game.getPacman().getCoordinate().getY()-1) != -1)
				game.getPacman().setDirection(Direction.Up);
				break;
			case KeyEvent.VK_DOWN :
				if (game.getMaze().getContentMaze(game.getPacman().getCoordinate().getX(),game.getPacman().getCoordinate().getY()+1) != -1)
				game.getPacman().setDirection(Direction.Down);
				break;
			case KeyEvent.VK_RIGHT :
				if (game.getMaze().getContentMaze(game.getPacman().getCoordinate().getX()+1,game.getPacman().getCoordinate().getY()) != -1)
				game.getPacman().setDirection(Direction.Right);
				break;
			case KeyEvent.VK_LEFT :
				if (game.getMaze().getContentMaze(game.getPacman().getCoordinate().getX()-1,game.getPacman().getCoordinate().getY()) != -1)
				game.getPacman().setDirection(Direction.Left);
				break;
		}
	}

}
