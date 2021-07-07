package pacmanModel;


public class InvisiblePacmanState extends PacmanState{
	//Constructeur
	protected InvisiblePacmanState(PacmanObservable pacman) { super(pacman); }
	
	//Méthode(s)
	@Override
	State getState() { return State.INVISIBLE; }

}