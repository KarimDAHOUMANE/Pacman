package pacmanGui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import pacmanModel.Coordinate;
import pacmanModel.Game;
import pacmanModel.MyListener;


public class Gui {
	//Champ(s)
	private JFrame frame;
	private MyListener Listener;
	private Game game;
	static final int SPEED = 150;
	
	//Constructeur
	public Gui(){
		game = new Game(0, 0, new Coordinate(12,13), new Coordinate(8,7));
		game.getPacman().register(game.getMaze());
		for (int i = 0; i < game.getGhosts().length; i++) game.getGhost(i).register(game.getMaze());
		Listener = new MyListener(game);
		frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(612,500);
		frame.setTitle("Pacman");
		frame.setLocationRelativeTo(null);
		frame.setContentPane(game.getMaze());
		frame.setResizable(false);
		frame.addKeyListener(Listener);
		
		while(game.getPacman().isAlive() && (game.getScore() < 18000)) {
			game.step();
			try {
		        Thread.sleep(SPEED);
			} catch (InterruptedException e) {
		        e.printStackTrace();
			}
		}
		
		if(game.getPacman().isAlive() && (game.getScore() == 18000)) {
			JOptionPane.showMessageDialog(frame, "Vous avez gagné");
		} else {
			JOptionPane.showMessageDialog(frame, "Vous avez perdu");
		}
	}
}
