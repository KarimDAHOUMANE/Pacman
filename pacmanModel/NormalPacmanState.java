package pacmanModel;


public class NormalPacmanState extends PacmanState {
	//Constructeur
	protected NormalPacmanState(PacmanObservable pacman) { super(pacman); } 
	
	//Méthode(s)
	@Override
	State getState() { return State.NORMAL; }

}

