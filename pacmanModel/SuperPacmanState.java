package pacmanModel;


public class SuperPacmanState extends PacmanState{
	//Constructeur
	protected SuperPacmanState(PacmanObservable pacman) { super(pacman); }
	
	//Méthode(s)
	@Override
	State getState() { return State.SUPERPACMAN; }

}