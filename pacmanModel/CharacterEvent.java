package pacmanModel;


public class CharacterEvent {
	//Champ(s)
	private Coordinate coordinate;
	public ChangeType changeType;
	public static enum ChangeType { ENTER, LEAVE }

	//Constructeur
	public CharacterEvent(Coordinate coordinate, ChangeType changeType) {
		this.coordinate = coordinate;
		this.changeType = changeType;
	}
	
	//Méthode(s)
	public Coordinate getCoordinate() { return coordinate; }
	
	public ChangeType getChangeType() { return changeType; }
}
