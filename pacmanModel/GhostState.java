package pacmanModel;


public abstract class GhostState {

	public static enum State2 {VULNERABLE, DANGEROUS};

	protected GhostObservable ghost;

	protected GhostState(GhostObservable ghost) { this.ghost = ghost; }
		
	abstract State2 getState();

	public abstract void move();
}
